package com.example.demo.service;

import org.I0Itec.zkclient.ZkClient;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

/**
 * zk分布式锁抽象类
 */
public abstract class AbstractZookeeperLock implements Lock {

    private static final String HOST = "localhost:2182";
    protected ZkClient client = new ZkClient(HOST);
    protected static final String PATH = "/order";


    /**
     * 获取锁
     */
    @Override
    public void getLock() {
        if (tryLock()) {
            System.out.println("####获取到锁######");
        } else {
            System.out.println("####未获取到锁####");
            //等待锁
            waitLock();
            //重新获取锁
            getLock();
        }
    }

    /**
     * 释放锁
     */
    @Override
    public void unLock() {
        System.out.println("#####删除节点#####");
        client.delete(PATH);
    }

    /**
     * 尝试获取锁
     *
     * @return 获取结果
     */
    protected abstract boolean tryLock();

    /**
     * 等待锁
     */
    protected abstract void waitLock();

}
