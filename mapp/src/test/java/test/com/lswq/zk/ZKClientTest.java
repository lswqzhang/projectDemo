package test.com.lswq.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created by zhangsw on 2017/4/3.
 */
public class ZKClientTest {

    private static final Logger logger = LoggerFactory.getLogger(ZKClientTest.class);

    private ZooKeeper zk;

    @Before
    public void init() throws IOException {
        //创建一个Zookeeper实例，第一个参数为目标服务器地址和端口，第二个参数为Session超时时间，第三个为节点变化时的回调方法
        // 监控所有被触发的事件
        zk = new ZooKeeper("192.168.15.190:2181", 500000, event -> {
            logger.info("回调路径为：{}", event.getPath());
            logger.info("状态类型为：{}", event.getType());
        });
    }

    /**
     * 创建一个永久的节点
     * <p>
     * 创建一个节点root，数据是mydata,不进行ACL权限控制，节点为永久性的(即客户端shutdown了也不会消失)
     *
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void createNodeAndSetData() throws KeeperException, InterruptedException {
        zk.create("/configuration/mmap", "mmap application".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    /**
     * 在root下面创建一个childone znode,数据为childone,不进行ACL权限控制，节点为永久性的
     *
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void createMulitNodeAndSetData() throws KeeperException, InterruptedException {
        zk.create("/root/children", "one".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    /**
     * 取得相应节点下的子节点名称,返回List<String>
     *
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void getChildrenNodes() throws KeeperException, InterruptedException {
        List<String> children = zk.getChildren("/root", true);
        for (String child : children) {
            logger.info("data is {}", child);
        }
    }

    /**
     * 取得某节点下的数据,返回byte[]
     *
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void getNodeData() throws KeeperException, InterruptedException {
        String zNode = "/configuration/mmap/imagePath";
        String data = new String(zk.getData(zNode, true, null));
        logger.info("节点{}的值为：{}", zNode, data);
    }

    /**
     * 修改某节点的数据
     * <p>
     * 修改节点/root/childone下的数据，第三个参数为版本，如果是-1，那会无视被修改的数据版本，直接改掉
     *
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void updateNodeData() throws KeeperException, InterruptedException {
        String zNode = "/configuration/mmap";
        Stat exists = zk.exists(zNode, true);
        logger.info("获取总版本数{}",exists.getVersion());
        logger.info("查询当前版本数{}",exists.getCversion());
        zk.setData(zNode, "web maven application".getBytes(), -1);
    }


    /**
     * 删除某这个节点，第二个参数为版本，－1的话直接删除，无视版本
     *
     * @throws KeeperException
     * @throws InterruptedException
     */
    @Test
    public void deleteZNode() throws KeeperException, InterruptedException {
        String zNode = "/root/children";
        zk.delete(zNode, -1);
    }


    /**
     * 测试后关闭
     *
     * @throws InterruptedException
     */
    @After
    public void destroy() throws InterruptedException {
        zk.close();
    }
}
