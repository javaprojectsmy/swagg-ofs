package com.tesco.ofs.platform.trace.exception.handler;

import java.util.concurrent.Callable;

public class ReExecutor implements IReExecutorExceptionHandler {
	
	 private int currentCount; 

	 private int maxCount = 5; 


	public void setMaxCount(int maxCount) {
		this.maxCount = maxCount;
	}
	public int getMaxCount() {
		return maxCount;
	}

	
	public ReExecutor(int maxCount)
	{
		this.maxCount = maxCount;
	}

	
	public ReExecutor()
	{
		
	}

	@Override
	public <T> T reexecute(Callable<T> func) {
		// TODO Auto-generated method stub
		  System.out.println("Failed to execute, retrying  " + maxCount + " times."); 
		  currentCount = 0;         
		  while (currentCount < maxCount) 
		  {             
			  try 
			  {                 
				  return func.call();          
		      } catch (Exception ex) 
			  {   
		    	  try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	  currentCount++;                 
		    	  System.out.println("Failed to Execute, retrying " + currentCount + " of " + maxCount + " error: " + ex );                
		    	  if (currentCount >= maxCount) 
		    	  {                     
		    		  System.out.println("Max retries exceeded.");                     
		    		  break;                 
		    	  }             
		      }         
		  }
		
		  
		throw new RuntimeException("Command failed on all of " + maxCount + " retries");     		  
	
	}
	
	public static void main(String args[])
	{
		ReExecutor re = new ReExecutor(10);
		re.setMaxCount(7);	
		re.reexecute(
				new Callable<Integer>()
				{

					@Override
					public Integer call() throws Exception {
						// TODO Auto-generated method stub
						return add(2, 3);
					}
					
				}
				);
	}
	
	public static int add(int a, int b)
	{
		int c = a+b;		
		System.out.println("c::::" + c);
		
		if(a==2)
		{
			throw new RuntimeException("Throwing test exception.....");
		}
		return c;
	}

}
