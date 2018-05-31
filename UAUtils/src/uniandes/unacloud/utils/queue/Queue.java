package uniandes.unacloud.utils.queue;

import uniandes.unacloud.utils.queue.exceptions.QueueTaskException;


public class Queue {
	
	private Node head;
	
	private Task executedTask;
	
	public Queue() {
		head = new Node();
	}
	
	public void push(Task task){
		head.push(task);
	}
	
	public boolean isReady(){
		return !isBusy() && !isEmpty();
	}
	
	public boolean isBusy(){
		return executedTask != null && executedTask.isRunning();
	}
	
	public boolean isEmpty(){
		return head.isEmpty();
	}
	
	public void execute() throws QueueTaskException {
		if(isBusy())
			throw new QueueTaskException("There is a task executing");
		executedTask = head.pop();
		new Thread(executedTask);
	}
	
	private class Node {
		
		private Task current;
		
		private Node next;
		
		public void push(Task task) {
			if (current == null)
				current = task;
			else {
				if(next == null)			
					next = new Node();
				next.push(task);
			}				
		}
		
		public Task pop() {
			Task task = current;
			if (next == null)
				current = null;
			else {
				current = next.pop();
				if(next.isLast())
					next = null;
			}
			return task;
		}
		
		public boolean isLast(){
			return next == null;
		}
		
		public boolean isEmpty(){
			return current == null;
		}
	}

}
