package uniandes.unacloud.utils.queue;

import java.util.Set;
import java.util.TreeMap;

import uniandes.unacloud.utils.queue.exceptions.QueueTaskException;

public class QueueManager {
	
	private static final String PRIORITY_TOPIC = "ptopic";
	
	private TreeMap<String, Queue> queues;
	
	public QueueManager() {
		queues = new TreeMap<String, Queue>();
		new Thread() {
	    	public void run() {
	    		try {
		    		Thread.sleep(2000);   
		    		executeTasks();	
				} 
	    		catch (QueueTaskException e) {
	    			System.out.println("Queue Error");
					e.printStackTrace();
				} 
	    		catch (Exception e) {
					e.printStackTrace();
				}   	    		
	    	};
	    }.start();
	}
	
	public void publishProrityTask(Task task){
		publishTask(PRIORITY_TOPIC, task);
	}
	
	public synchronized void publishTask(String queueKey, Task task){
		if(queues.get(queueKey) != null)
			queues.get(queueKey).push(task);
		else {
			Queue queue =  new Queue();
			queue.push(task);
			queues.put(queueKey,queue);
		}
	}
	
	private void executeTasks() throws QueueTaskException {
		cancelUnusedQueues();
		//In case a priority task is running
		if(queues.get(PRIORITY_TOPIC) != null && queues.get(PRIORITY_TOPIC).isBusy()) return;
		//In case there is a priority task but is not running
		if(queues.get(PRIORITY_TOPIC) != null && queues.get(PRIORITY_TOPIC).isReady())
		{
			for(String key: queues.keySet())
				if(queues.get(key).isBusy()) return;
			queues.get(PRIORITY_TOPIC).execute();
		}
		//In case there is not a running or queued priority task 
		else {
			for(String key: queues.keySet())
				if(!key.equals(PRIORITY_TOPIC) && queues.get(key).isReady())
					queues.get(key).execute();
		}	
		
	}
	
	private void cancelUnusedQueues(){
		for(String key: queues.keySet()) {
			if(queues.get(key).isEmpty() && !queues.get(key).isBusy())
				queues.remove(key);
		}	
	}
	
	public Set<String> getKeys(){
		return queues.keySet();
	}
	
	public int 
}
