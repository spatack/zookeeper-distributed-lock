package com.example.demo.service;

/**
 * 锁接口类
 */
public interface Lock {

    /**
     * 锁
     */
    void getLock();

    /**
     * 释放锁
     */
    void unLock();
}
