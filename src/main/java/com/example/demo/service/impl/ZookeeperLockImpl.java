package com.example.demo.service.impl;

import com.example.demo.service.AbstractZookeeperLock;
import org.I0Itec.zkclient.IZkDataListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

/**
 * zk分布式锁实现类
 */
@Service
public class ZookeeperLockImpl extends AbstractZookeeperLock {
    private CountDownLatch countDownLatch = null;

    @Override
    protected boolean tryLock() {
        try {
            //创建临时节点
            client.createEphemeral(PATH);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void waitLock() {

        //创建监听类
        IZkDataListener zkDataListener = new IZkDataListener() {
            /**
             * 节点删除时
             *
             * @param s path
             * @param o data
             * @throws Exception
             */
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                //唤醒
                countDownLatch.countDown();
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {

            }
        };
        //开启监听
        client.subscribeDataChanges(PATH, zkDataListener);
        //检查节点是否存在
        if (client.exists(PATH)) {
            countDownLatch = new CountDownLatch(1);
            try {
                //存在，等待
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //上面的countdownLatch放行，取消订阅
        client.unsubscribeDataChanges(PATH, zkDataListener);
    }
}
