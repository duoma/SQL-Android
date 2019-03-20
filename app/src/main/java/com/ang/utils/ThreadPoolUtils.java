package com.ang.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 类 描 述: 线程池
 *
 * @author duoma
 * @date 2019/02/22
 * <p>
 * Executors.newCachedThreadPool(); // 缓存线程池，缓存生命周期短的线程
 * Executors.newFixedThreadPool(2); // 固定数目线程的线程池
 * Executors.newScheduledThreadPool(2); // 支持定时及周期性的任务执行的线程池
 * Executors.newScheduledThreadPool(); // 单线程化的线程池
 */
public class ThreadPoolUtils {

    private static ExecutorService cachedThreadPool;

    static {
        // 缓存线程池
        cachedThreadPool = Executors.newCachedThreadPool();
    }

    /**
     * 添加线程到线程池中
     *
     * @param runnable 实现Runnable接口的类
     */
    public static void addThread(Runnable runnable) {
        cachedThreadPool.execute(runnable);
    }

    public static ExecutorService getCachedThreadPool() {
        return cachedThreadPool;
    }
}
