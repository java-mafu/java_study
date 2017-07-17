/*
 * Copyright (C) 2012, 2013, 2016 RICOH Co., Ltd. All rights reserved.
 */
package jpl.ch14.ex10;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 縺吶＞縺ｾ縺帙ｓ�ｼ後ｏ縺九ｊ縺ｾ縺帙ｓ�ｼ�*/

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

	int queueCapacity;
	Queue<Runnable> taskQueue;
	Thread threadList[];
	boolean isThreadStart;

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
		if (queueSize < 1 || numberOfThreads < 1)
			throw new IllegalArgumentException();
		queueCapacity = queueSize;
		taskQueue = new LinkedList<Runnable>();
		threadList = new Thread[numberOfThreads];

		Runnable task = new Runnable() {
			public void run() {
				while (isThreadStart) {
					Runnable runner;
					try {
						runner = remove();
						if (runner != null)
							runner.run();
					} catch (InterruptedException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
				}
			}
		};

		for (int i = 0; i < threadList.length; i++)
			threadList[i] = new Thread(task);
		isThreadStart = false;

	}

	private Runnable remove() throws InterruptedException {
		synchronized (taskQueue) {
			while (taskQueue.size() == 0) {
				taskQueue.wait();
				if (!isThreadStart)
					return null;
			}
			taskQueue.notifyAll();
			return taskQueue.remove();
		}
	}

	private void add(Runnable runnable) {
		synchronized (taskQueue) {
			if (taskQueue.size() >= queueCapacity) {
				try {
					taskQueue.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			taskQueue.add(runnable);
			taskQueue.notifyAll();
		}
	}

	/**
	 * Starts threads.
	 *
	 * @throws IllegalStateException
	 *             if threads has been already started.
	 */
	public void start() {
		if (isThreadStart)
			throw new IllegalStateException();
		else
			isThreadStart = true;
		for (int i = 0; i < threadList.length; i++)
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
		boolean isActiveThread;
		do {
			isActiveThread = false;

			synchronized (taskQueue) {
				taskQueue.notifyAll();
			}

			for (Thread th : threadList) {
				try {
					th.join(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (th.isAlive())
					isActiveThread = true;
			}
		} while (isActiveThread);
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
