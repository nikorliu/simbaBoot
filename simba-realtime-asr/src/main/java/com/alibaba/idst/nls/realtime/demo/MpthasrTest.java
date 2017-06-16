package com.alibaba.idst.nls.realtime.demo;

import java.io.File;
import java.io.FileInputStream;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.idst.nls.realtime.NlsClient;
import com.alibaba.idst.nls.realtime.NlsFuture;
import com.alibaba.idst.nls.realtime.event.NlsEvent;
import com.alibaba.idst.nls.realtime.event.NlsListener;
import com.alibaba.idst.nls.realtime.protocol.NlsRequest;
import com.alibaba.idst.nls.realtime.protocol.NlsResponse;

public class MpthasrTest {
	private static String appKey = "nls-service-shurufa16khz";
	static Logger logger = LoggerFactory.getLogger(MpthasrTest.class);
	@SuppressWarnings("unused")
	private static final long keepAliveTime = 10L;
	public static String filePath = "";
	private static int poolSize = 3;
	private static long totalRunning = 100L;
	@SuppressWarnings("unused")
	private static String ak_id = "";
	@SuppressWarnings("unused")
	private static String ak_secret = "";
	private static String host = "";
	@SuppressWarnings("unused")
	private static int port;
	private static String enableCompress = "";

	private final NlsClient client = new NlsClient();
	private ArrayBlockingQueue<Runnable> queue;
	private Semaphore taskSemaphore;
	private ThreadPoolExecutor pool;

	public MpthasrTest() {
		this.queue = new ArrayBlockingQueue<>(poolSize + 20);
		this.taskSemaphore = new Semaphore(poolSize);
	}

	public void Init() {
		System.out.println("init Nls client...");
		this.client.init(false, 8010);
		this.client.setHost(host);
		ak_id = "id";
		ak_secret = "secret";
		this.pool = new ThreadPoolExecutor(poolSize, poolSize + 10, 10L, TimeUnit.SECONDS, this.queue);
	}

	public void Shutdown() {
		System.out.println("close NLS client");
		this.client.close();
		System.out.println("demo done");
	}

	public void Run() {
		for (int i = 0; i < totalRunning; i++) {
			try {
				this.taskSemaphore.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("Create the task: " + i);
			try {
				Thread.sleep(1000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.pool.execute(new NlsTask(this.client, i, this.taskSemaphore));
		}
		try {
			this.taskSemaphore.acquire(poolSize);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		this.pool.shutdown();
	}

	public static void main(String[] args) {
		if ((args.length != 0) && (args.length < 5)) {
			System.out.println(args.length + "java com...MpthTest [off|pro concurrency times]");
			return;
		}

		if (args.length >= 5) {
			poolSize = Integer.parseInt(args[0]);
			totalRunning = Integer.parseInt(args[1]);
			filePath = args[2];
			host = args[3];
			port = Integer.parseInt(args[4]);
			if (args.length == 6) {
				enableCompress = args[5];
			}
		}

		MpthasrTest multithread = new MpthasrTest();
		multithread.Init();
		multithread.Run();
		multithread.Shutdown();
	}

	public static class NlsTask implements NlsListener, Runnable {
		public NlsClient client;
		public int order;
		public Semaphore taskSemaphore;
		public long time0 = System.currentTimeMillis();
		public long timeFirst = 0L;

		public NlsTask(NlsClient clt, int num, Semaphore ts) {
			this.client = clt;
			this.order = num;
			this.taskSemaphore = ts;
		}

		public void onMessageReceived(NlsEvent e) {
			NlsResponse response = e.getResponse();
			if ((response.result != null) && (!response.result.getText().equals(""))) {
				if (this.timeFirst == 0L) {
					this.timeFirst = System.currentTimeMillis();
					System.out.println("the latency is : " + (this.timeFirst - this.time0));
				}
			}
		}

		public void onOperationFailed(NlsEvent e) {
			String taskLog = ">>> " + this.order + " " + e.getErrorMessage();
			MpthasrTest.logger.error(taskLog);
		}

		public void onChannelClosed(NlsEvent e) {
			MpthasrTest.logger.warn("Channel closed,Finish the task: " + this.order);
		}

		@SuppressWarnings("deprecation")
		public void run() {
			System.out.println("open audio file...");
			FileInputStream fis = null;
			try {
				File file = new File(MpthasrTest.filePath);
				fis = new FileInputStream(file);
			} catch (Exception e) {
				e.printStackTrace();
			}

			long timeStart = System.currentTimeMillis();
			NlsRequest req = new NlsRequest();

			if (MpthasrTest.enableCompress.equals("enableCompress")) {
				req.setEnableCompress(true);
			}
			req.setAppkey(MpthasrTest.appKey);
			req.setFormat("pcm");
			req.setResponseMode("streaming");
			req.setSampleRate(16000);

			String taskLog = ">>> " + this.order + " ";
			boolean failed = false;
			this.time0 = System.currentTimeMillis();
			NlsFuture future = null;
			try {
				future = this.client.createNlsFuture(req, this);
				if (future.getChannel() != null) {
					System.out.println(">>> " + this.order + " a new channel: " + future.getChannel().getId());
					System.out.println("call NLS service");
					byte[] b = new byte[8000];
					int len = 0;
					while ((len = fis.read(b)) > 0) {
						future.sendVoice(b, 0, len);
						Thread.sleep(250L);
					}
					MpthasrTest.logger.warn("Send finish now!");
					future.sendFinishSignal();
					MpthasrTest.logger.info("main thread enter waiting for less than 10s.");
					future.await(10000);
					failed = future.isFailed();
				}
			} catch (Exception e) {
				failed = true;
				e.printStackTrace();
			}

			if (!failed) {
				long timeFinish = System.currentTimeMillis();
				taskLog = taskLog + " and the time-cost is : " + (timeFinish - timeStart);
				System.out.println(taskLog);
			} else {
				System.out.println(taskLog + " but failed.");
			}

			this.taskSemaphore.release();
		}
	}
}