package com.simba.cluster.registryTable;

import org.springframework.stereotype.Component;

import com.simba.framework.distributed.ClusterExecute;
import com.simba.registry.model.RegistryTableData;

/**
 * 注册表集群传递之后 要执行的方法
 * 
 * @author caozj
 *
 */
@Component
public class RegistryTableClusterExecute implements ClusterExecute {

  @Override
  public void execute(Object data) {
    if (!(data instanceof RegistryTableClusterData)) {
      throw new RuntimeException("类型错误：" + data.getClass().getCanonicalName());
    }
    RegistryTableClusterData clustData = (RegistryTableClusterData) data;
    if (clustData.getMethod().equals("add")) {
      RegistryTableData.getInstance().add(clustData.getCode(), clustData.getValue());
    } else if (clustData.getMethod().equals("remove")) {
      RegistryTableData.getInstance().remove(clustData.getCode());
    }
  }

}
