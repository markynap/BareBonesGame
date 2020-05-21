package main;

import java.util.List;
import java.util.LinkedList;

public class ThreadPool extends ThreadGroup{
	/** The ID of this pool */
	public static IDAssigner poolID = new IDAssigner(1);
	/** Whether or not this pool is alive and running its threads */
	public boolean alive;
	/** List of tasks to run */
	private List<Runnable> taskList;
	/** ID of this ThreadPool */
	private int id;
	/** Creates a new Pool of Threads to run at the same time */
	public ThreadPool(int numThreads) {
		super("ThreadPool-" + poolID.next());
		this.id = poolID.getBaseID();
		setDaemon(true);
		taskList = new LinkedList<>();
		alive = true;
		for (int i = 0; i < numThreads; i++) {
			new PooledThread(this).start();;
		}
		
	}
	/** Adds a new Thread to the TaskList, adding it to this Thread Pool */
	public synchronized void runTask(Runnable task) {
		if (!alive) throw new IllegalStateException("ThreadPool-" + id + " is dead");
		if (task != null) {
			taskList.add(task);
			notify();
		}
	}
	/** Interrupts all threads immediately in their tracks */
	public synchronized void close() {
		if (!alive) return;
		alive = false;
		taskList.clear();
		interrupt();
	}
	/** Stops all threads one at a time at an appropriate time */
	public void join() {
		synchronized(this) {
			alive = false;
			notifyAll();
		}
		
		Thread[] threads = new Thread[activeCount()];
		int count = enumerate(threads);
		
		for (int i = 0; i < count; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				System.out.println("Went wrong in join method!");
			}
		}
		
	}
	
	/** Retrieves the next task to run */
	public synchronized Runnable getTask() throws InterruptedException {
		while (taskList.size() == 0) {
			if (!alive) return null;
			wait();
		}
		return taskList.remove(0);
	}
	
}
