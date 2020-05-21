package main;

public class PooledThread extends Thread{
	/** ID Assigner for thread */
	private static IDAssigner threadID = new IDAssigner(1);
	/** Thread handler this thread belongs to */
	private ThreadPool pool;
	/** A new thread that will be added to the pool of threads this Game has */
	public PooledThread(ThreadPool pool) {
		super(pool, "PooledThread-" + threadID.next());
		this.pool = pool;
	}
	/** Runs task for this thread */
	public void run() {
		if (interrupted())
			notify();
		while (!isInterrupted()) {
			Runnable task = null;
			try {
				task = pool.getTask();
			} catch (InterruptedException e) {
				System.err.println("Thread was interrupted!");
			}
			if (task == null) return;
			
			try {
				task.run();
			} catch (Throwable t) {
				pool.uncaughtException(this, t);
			}
			
		}
	}
	/** Interrupts the task of this thread */
	public void stopTask() {
		if (isInterrupted()) return;
		this.interrupt();
		
	}
}
