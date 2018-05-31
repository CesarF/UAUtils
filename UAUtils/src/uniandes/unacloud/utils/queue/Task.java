package uniandes.unacloud.utils.queue;

public abstract class Task implements Runnable {
	
	private TaskStatus status;
	
	public Task() {
		status = TaskStatus.READY;
	}
	
	private enum TaskStatus{
		RUNNING, FINISHED, READY
	}

	public boolean isRunning() {
		return status == TaskStatus.RUNNING;
	}
	
	public boolean isFinished() {
		return status == TaskStatus.FINISHED;
	}
	
	@Override
	public void run() {
		if(status != TaskStatus.READY) 
			return;
		status = TaskStatus.RUNNING;
		try {
			execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		status = TaskStatus.FINISHED;
	}

	protected abstract void execute();
}
