package com.lswq.app.util;

import com.lswq.app.util.spring.LqConfigClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangsw on 2017/4/2.
 */
public class ZookeeperClientUtil extends Thread implements Watcher {

    private static final Logger logger = LoggerFactory.getLogger(ZookeeperClientUtil.class);

    private ZooKeeper zk;
    //zookeeper地址  
    private String servers;
    private Properties props;
    private LqConfigClient client;

    // 所要监控的结点的子结点列表
    private static List<String> nodeList;

    // 链接超时时间  
    private int sessionTimeout = 40000;
    private CountDownLatch latch = new CountDownLatch(1);

    private String mainPath;

    public ZooKeeper getAliveZk() {
        ZooKeeper aliveZk = zk;
        if (aliveZk != null && aliveZk.getState().isAlive()) {
            return aliveZk;
        } else {
            zkReconnect();
            return zk;
        }
    }

    public synchronized void zkReconnect() {
        close();
        try {
            connect();
            latch.await();
            this.start();
        } catch (IOException | InterruptedException e) {
            logger.info("zk服务器中心连接失", e);
        }
    }

    public synchronized void close() {
        if (zk != null) {
            try {
                zk.close();
            } catch (InterruptedException e) {
            }
            zk = null;
        }
    }

    private synchronized void connect() throws IOException {
        if (zk == null && !StringUtils.isBlank(servers))
            zk = new ZooKeeper(servers, sessionTimeout, this);
    }

    public String getData(String path) {
        String result = null;
        try {
            byte[] data = getAliveZk().getData(path, Boolean.TRUE, null);
            if (null != data) {
                result = new String(data, "UTF-8");
            }
        } catch (KeeperException | InterruptedException | UnsupportedEncodingException e) {
            logger.error("获取数据失败，{}", e);
        }
        return result;
    }

    public List<String> getChildren() {
        try {
            nodeList = getAliveZk().getChildren(mainPath, Boolean.TRUE);
        } catch (KeeperException | InterruptedException e) {
            logger.error("获取子节点失败，{}", e);
        }
        return nodeList;
    }

    @Override
    public void process(WatchedEvent event) {
        // 连接状态
        Event.KeeperState keeperState = event.getState();
        // 事件类型
        Event.EventType eventType = event.getType();
        if (Event.KeeperState.SyncConnected == keeperState) {
            // 成功连接上ZK服务器
            if (Event.EventType.None == eventType) {
                logger.info("zookeeper is ok !");
                latch.countDown();
            }
        }
    }

    @Override
    public void run() {
        synchronized (this) {
            Watcher wc = event -> {
                // 结点数据改变之前的结点列表
                List<String> nodeListBefore = nodeList;
                String eventPath = event.getPath();
                // 主结点的数据发生改变时
                if (event.getType() == Watcher.Event.EventType.NodeDataChanged) {
                    logger.info("Node data changed:{}", eventPath);
                }
                if (event.getType() == Watcher.Event.EventType.NodeDeleted) {
                    logger.info("Node deleted:", eventPath);
                }
                if (event.getType() == Watcher.Event.EventType.NodeCreated) {
                    logger.info("Node created:", eventPath);
                }
                //  获取新的节点
                try {
                    nodeList = zk.getChildren(event.getPath(), false);
                } catch (KeeperException | InterruptedException e) {
                    logger.error("", e);
                }

                List<String> nodeListNow = nodeList;
                // 增加结点
                if (nodeListBefore.size() < nodeListNow.size()) {
                    for (String key : nodeListNow) {
                        if (!nodeListBefore.contains(key)) {
                            props.put(key, getData(event.getPath() + "/" + key));
                            client.scanAnnotation();
                        }
                    }
                }
            };
            /**
             * 持续监控mainPath下的结点
             */
            for (; ; ) {
                try {
                    // 所要监控的主结点
                    zk.exists(mainPath, wc);
                } catch (KeeperException | InterruptedException e) {
                    logger.error("", e);
                }
                try {
                    nodeList = zk.getChildren(mainPath, wc);
                    for (String child : nodeList) {
                        logger.info("child: " + child);
                    }
                } catch (KeeperException | InterruptedException e) {
                    logger.error("", e);
                }
                // 对PATH下的每个结点都设置一个watcher
                for (String nodeName : nodeList) {
                    try {
                        zk.exists(mainPath + "/" + nodeName, wc);
                    } catch (KeeperException | InterruptedException e) {
                        logger.error("获取子节点失败，{}", e);
                    }
                }
                try {
                    // sleep一会，减少CUP占用率
                    TimeUnit.MINUTES.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getMainPath() {
        return mainPath;
    }

    public void setClient(LqConfigClient client) {
        this.client = client;
    }

    public void setMainPath(String mainPath) {
        this.mainPath = mainPath;
    }

    public void setServers(String servers) {
        this.servers = servers;
    }

    public void setProps(Properties props) {
        this.props = props;
    }
}
