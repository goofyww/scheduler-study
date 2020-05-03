package com.zcoox;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobTypeConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.script.ScriptJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.zcoox.job.MyDataFlowJob;
import com.zcoox.job.MySimpleJob;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");
//        new JobScheduler(zkCenter(), simpleJobConfiguration()).init();    // simple
        new JobScheduler(zkCenter(), dataflowJobConfiguration()).init();  // dataflow
//        new JobScheduler(zkCenter(), scriptJobConfiguration()).init();      // script
    }

    public static CoordinatorRegistryCenter zkCenter() {
        String zkHost = "localhost:2181";
        String namespace = "elastic-job-simple";
        CoordinatorRegistryCenter registryCenter = new ZookeeperRegistryCenter(new ZookeeperConfiguration(zkHost, namespace));
        registryCenter.init();
        return registryCenter;
    }

    public static LiteJobConfiguration simpleJobConfiguration() {
        JobCoreConfiguration coreConfiguration = JobCoreConfiguration
                .newBuilder("mySimpleJob", "0/10 * * * * ?", 2)
                .build();
        JobTypeConfiguration typeConfiguration = new SimpleJobConfiguration(coreConfiguration, MySimpleJob.class.getCanonicalName());
        return LiteJobConfiguration
                .newBuilder(typeConfiguration)
                .overwrite(true)
                .build();
    }

    public static LiteJobConfiguration dataflowJobConfiguration() {
        JobCoreConfiguration coreConfiguration = JobCoreConfiguration
                .newBuilder("myDataFlowJob", "0/10 * * * * ?", 2)
                .build();
        JobTypeConfiguration typeConfiguration = new DataflowJobConfiguration(coreConfiguration, MyDataFlowJob.class.getCanonicalName(), true);
        return LiteJobConfiguration
                .newBuilder(typeConfiguration)
                .overwrite(true)
                .build();
    }

    public static LiteJobConfiguration scriptJobConfiguration() {
        JobCoreConfiguration coreConfiguration = JobCoreConfiguration
                .newBuilder("myScriptJob", "0/5 * * * * ?", 2)
                .misfire(false)
                .build();

        String scriptPath = App.class.getClassLoader().getResource("script.cmd").getPath();
        JobTypeConfiguration typeConfiguration = new ScriptJobConfiguration(coreConfiguration, scriptPath);
        return LiteJobConfiguration
                .newBuilder(typeConfiguration)
                .overwrite(true)
                .build();
    }
}
