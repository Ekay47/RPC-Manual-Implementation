//package zookeeper;
//
//import org.apache.curator.RetryPolicy;
//import org.apache.curator.RetrySleeper;
//import org.apache.curator.framework.CuratorFramework;
//import org.apache.curator.framework.CuratorFrameworkFactory;
//import org.apache.curator.framework.recipes.cache.*;
//import org.apache.curator.retry.ExponentialBackoffRetry;
//import org.apache.zookeeper.CreateMode;
//
//import java.nio.charset.StandardCharsets;
//import java.util.Arrays;
//
//public class Test {
//    private static final int BASE_SLEEP_TIME = 1000;
//    private static final int MAX_RETRIES = 3;
//
//    public static void main(String[] args) throws Exception {
//        RetryPolicy retryPolicy = new ExponentialBackoffRetry(BASE_SLEEP_TIME, MAX_RETRIES);
//        CuratorFramework zkClient = CuratorFrameworkFactory.builder()
//                .connectString("127.0.0.1:2181")
//                .retryPolicy(retryPolicy)
//                .build();
//        zkClient.start();
//
//        // watcher
//        String path = "/node1";
//        CuratorCache cache = CuratorCache.build(zkClient, path);
//        cache.listenable().addListener(new CuratorCacheListener() {
//            @Override
//            public void event(Type type, ChildData childData, ChildData childData1) {
//
//            }
//        });
//    }
//
//}
