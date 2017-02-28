/*
 * Copyright (C) 2012, 2013, 2016 RICOH Co., Ltd. All rights reserved.
 */
package jpl.ch14.ex10;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Simple Thread Pool class.
 *
 * This class can be used to dispatch an Runnable object to be exectued by a
 * thread.
 *
 * [Instruction] Implement one constructor and three methods. Don't forget to
 * write a Test program to test this class. Pay attention to @throws tags in the
 * javadoc. If needed, you can put "synchronized" keyword to methods. All
 * classes for implementation must be private inside this class. Don't use
 * java.util.concurrent package.
 */
public class ThreadPool {

	int threadnumber;
	Queue<Runnable> taskQueue;
	Thread threadList[];
	boolean isThreadStart;

	Runnable task = new Runnable() {
		public void run() {
			while (isThreadStart) {
				try {
					remove().run();
				} catch (InterruptedException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
		}
	};

	private synchronized Runnable remove() throws InterruptedException{
		while(taskQueue.size() == 0)
			wait();
		return taskQueue.remove();
	}

	private synchronized void add(Runnable runnable){
		taskQueue.add(runnable);
		notifyAll();
	}

	/**
	 * Constructs ThreadPool.
	 *
	 * @param queueSize
	 *            the max size of queue
	 * @param numberOfThreads
	 *            the number of threads in this pool.
	 *
	 * @throws IllegalArgumentException
	 *             if either queueSize or numberOfThreads is less than 1
	 */
	public ThreadPool(int queueSize, int numberOfThreads) throws IllegalArgumentException {
		taskQueue = new LinkedBlockingQueue<Runnable>(queueSize);
		threadList = new Thread[numberOfThreads];
		for(int i = 0;i < threadList.length; i++)
			threadList[i] = new Thread(task);
		isThreadStart = false;
	}

	/**
	 * Starts threads.
	 *
	 * @throws IllegalStateException
	 *             if threads has been already started.
	 */
	public void start() {
		if (isThreadStart)
			throw new IllegalArgumentException();
		else
			isThreadStart = true;
		for(int i = 0;i < threadList.length; i++)
			threadList[i].start();
	}

	/**
	 * Stop all threads and wait for their terminations.
	 *
	 * @throws IllegalStateException
	 *             if threads has not been started.
	 */
	public void stop() {
		if (!isThreadStart)
			throw new IllegalStateException();
		else
			isThreadStart = false;
		for(Thread th : threadList){
			try {
				th.join();
			} catch (InterruptedException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

	/**
	 * Executes the specified Runnable object, using a thread in the pool. run()
	 * method will be invoked in the thread. If the queue is full, then this
	 * method invocation will be blocked until the queue is not full.
	 *
	 * @param runnable
	 *            Runnable object whose run() method will be invoked.
	 *
	 * @throws NullPointerException
	 *             if runnable is null.
	 * @throws IllegalStateException
	 *             if this pool has not been started yet.
	 */
	public synchronized void dispatch(Runnable runnable) {
		if (runnable == null)
			throw new NullPointerException();
		if (!isThreadStart)
			throw new IllegalStateException();
		add(runnable);
	}
}