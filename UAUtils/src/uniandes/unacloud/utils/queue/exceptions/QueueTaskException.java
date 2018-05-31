package uniandes.unacloud.utils.queue.exceptions;

public class QueueTaskException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8676892151959356868L;
	
	public QueueTaskException() {
		super("There was an error in task execution process");
	}
	
	public QueueTaskException(String message){
		super(message);
	}

}
