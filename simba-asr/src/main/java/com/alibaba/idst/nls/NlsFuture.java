package com.alibaba.idst.nls;

import java.io.InterruptedIOException;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.channel.Channel;

import com.alibaba.idst.nls.session.NlsSession;
import com.alibaba.idst.nls.utils.VoiceCutter;
import com.aliyun.nls.transcription.opu.OpuCodec;

public class NlsFuture {
	private static Log logger = LogFactory.getLog(NlsFuture.class);
	public static final int capacity = 512000;
	public static final byte[] amrHead = { 35, 33, 65, 77, 82, 10 };
	private NlsSession session;
	private String asrSC;
	private ByteBuffer buffer;
	private OpuCodec codec;
	private int sampleRate;
	private ByteBuffer bufferOpu;
	private byte[] tempOpuData;
	private byte[] compactPcm = new byte[640];
	short[] samples = new short[1920];
	private boolean enableOpu = false;
	private int remaindOffset = 0;
	private PcmQueue pcmQueue;
	private ExecutorService service;
	long enc;
	long dec;

	public NlsFuture(NlsSession session) {
		this.session = session;
		this.buffer = ByteBuffer.allocate(512000);
	}

	public boolean enableVoiceCompress() {
		logger.info("enableVoiceCompress in future");
		if ((this.asrSC.equalsIgnoreCase("pcm")) && (this.sampleRate == 16000)) {
			this.enableOpu = true;
			this.codec = new OpuCodec();
			this.pcmQueue = new PcmQueue();
			this.bufferOpu = ByteBuffer.allocate(10240);
			this.enc = this.codec.createEncoder();
			this.dec = this.codec.createDecoder();

			Runnable consumer = new Runnable() {
				public void run() {
					while (true)
						if (!NlsFuture.this.pcmQueue.queue.isEmpty())
							try {
								NlsFuture.this.pcmQueue.consume();
							} catch (InterruptedIOException e) {
								e.printStackTrace();
							}
						else
							try {
								Thread.sleep(100L);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
				}
			};
			this.service.submit(consumer);

			return true;
		}
		return false;
	}

	public NlsFuture sendVoice(byte[] data, int offset, int length) throws Exception {
		if (this.enableOpu) {
			this.pcmQueue.produce(data);
			this.asrSC = "opu";
		} else {
			this.buffer.put(data, offset, length);
			logger.trace("put data in regular mode,use no compress");
			doSend();
		}
		return this;
	}

	public NlsFuture sendFinishSignal() throws Exception {
		getSession().sendFinishSignal();
		if (this.enableOpu) {
			this.codec.destroyEncoder(this.enc);
			this.codec.destroyDecoder(this.dec);
		}
		return this;
	}

	public byte[] read() throws Exception {
		int count = 0;
		byte[] result = null;
		do {
			NlsSession.State state = getSession().getState();
			result = getSession().readBstream();
			if (result != null) {
				break;
			}
			if ((state == NlsSession.State.FAILED) || (state == NlsSession.State.FINISHED)) {
				break;
			}
			Thread.sleep(100L);
			count++;
		} while (count < 100);

		return result;
	}

	private void doSend() {
		if (this.asrSC.toLowerCase().equalsIgnoreCase("amr")) {
			byte[] voice = VoiceCutter.getAmr(this.buffer);
			if ((voice != null) && (voice.length > 0)) {
				System.out.print(new Date() + ": " + this.buffer.remaining() + " amr\n");

				byte[] pack = new byte[6 + voice.length];
				System.arraycopy(amrHead, 0, pack, 0, 6);
				System.arraycopy(voice, 0, pack, 6, voice.length);

				getSession().sendVoiceData(pack);
			}
		} else if (this.asrSC.toLowerCase().equalsIgnoreCase("opu")) {
			logger.info("Received opu voice length：" + Integer.valueOf(this.buffer.position()));
			byte[] voice = VoiceCutter.getOpu(this.buffer);
			if ((voice != null) && (voice.length > 0)) {
				logger.info("send opu voice to service after VoiceCutter");
				getSession().sendVoiceData(voice);
			}
		} else if ((this.asrSC.toLowerCase().equalsIgnoreCase("pcm")) || (this.asrSC.toLowerCase().equalsIgnoreCase("wav"))) {
			byte[] voice = VoiceCutter.getPcm(this.buffer);
			if ((voice != null) && (voice.length > 0))
				getSession().sendVoiceData(voice);
		} else if ((this.asrSC.toLowerCase().equalsIgnoreCase("speex")) || (this.asrSC.toLowerCase().equalsIgnoreCase("opus"))) {
			byte[] voice = VoiceCutter.getPcm(this.buffer);
			if ((voice != null) && (voice.length > 0))
				getSession().sendVoiceData(voice);
		}
	}

	private boolean isDone() {
		return (getSession().getState().equals(NlsSession.State.FINISHED)) || (getSession().getState().equals(NlsSession.State.FAILED));
	}

	public boolean await(int timeout) {
		if (timeout <= 0) {
			timeout = 5000;
		}
		if (!isDone()) {
			try {
				long start = System.currentTimeMillis();
				getSession().getLock().lock();
				while (!isDone())
					try {
						getSession().getDone().await(timeout, TimeUnit.MILLISECONDS);
						if ((isDone()) || (System.currentTimeMillis() - start > timeout))
							break;
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
			} finally {
				getSession().getLock().unlock();
			}
		}
		if (!isDone()) {
			return false;
		}

		return true;
	}

	public boolean isFailed() {
		return (getSession() == null) || (getSession().getState() == NlsSession.State.FAILED);
	}

	public Channel getChannel() {
		if (getSession() != null) {
			return getSession().getChannel();
		}

		return null;
	}

	public NlsSession getSession() {
		return this.session;
	}

	public String getAsrSC() {
		return this.asrSC;
	}

	public void setAsrSC(String asrSC) {
		this.asrSC = asrSC;
	}

	public int getSampleRate() {
		return this.sampleRate;
	}

	public void setSampleRate(int sampleRate) {
		this.sampleRate = sampleRate;
	}

	public ExecutorService getService() {
		return this.service;
	}

	public void setService(ExecutorService service) {
		this.service = service;
	}

	public class PcmQueue {
		BlockingQueue<byte[]> queue = new LinkedBlockingQueue<>(10);

		public PcmQueue() {
		}

		public void produce(byte[] pac) throws InterruptedException {
			this.queue.put(pac);
		}

		public void consume() throws InterruptedIOException {
			if (NlsFuture.this.asrSC.toLowerCase().equalsIgnoreCase("opu")) {
				byte[] bytes = new byte[512];
				int bufferOpuOffset = 0;
				byte[] data = (byte[]) this.queue.poll();

				if (NlsFuture.this.remaindOffset > 0) {
					NlsFuture.this.buffer.put(NlsFuture.this.tempOpuData, 0, NlsFuture.this.remaindOffset);
				}
				NlsFuture.this.buffer.put(data, 0, data.length);
				NlsFuture.this.buffer.flip();
				int bufferRemaining = NlsFuture.this.buffer.remaining();
				while (bufferRemaining >= 640) {
					NlsFuture.this.buffer.get(NlsFuture.this.compactPcm);
					bufferRemaining -= 640;
					for (int i = 0; i < 320; i++) {
						NlsFuture.this.samples[i] = ((short) (NlsFuture.this.compactPcm[(i * 2)] & 0xFF | (NlsFuture.this.compactPcm[(i * 2 + 1)] & 0xFF) << 8));
					}
					int nBytes = NlsFuture.this.codec.encode(NlsFuture.this.enc, NlsFuture.this.samples, 0, bytes);
					NlsFuture.this.bufferOpu.put((byte) nBytes);
					NlsFuture.this.bufferOpu.put(bytes, 0, nBytes);

					bufferOpuOffset = bufferOpuOffset + nBytes + 1;
				}
				byte[] temp = new byte[bufferOpuOffset];
				NlsFuture.this.bufferOpu.flip();
				NlsFuture.this.bufferOpu.get(temp, 0, bufferOpuOffset);

				if (NlsFuture.this.buffer.hasRemaining()) {
					NlsFuture.this.remaindOffset = NlsFuture.this.buffer.remaining();
					NlsFuture.this.tempOpuData = new byte[NlsFuture.this.remaindOffset];
					NlsFuture.this.buffer.get(NlsFuture.this.tempOpuData);
				}

				byte[] voice = VoiceCutter.getOpu(NlsFuture.this.bufferOpu);
				if ((voice != null) && (voice.length > 0)) {
					NlsFuture.logger.trace("send compressed voice package length: " + Integer.valueOf(voice.length));
					NlsFuture.this.getSession().sendVoiceData(voice);
				}
				NlsFuture.this.bufferOpu.clear();
				NlsFuture.this.buffer.clear();
			}
		}
	}
}