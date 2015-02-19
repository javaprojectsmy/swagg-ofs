package com.tesco.ofs.platform.trace.exception.handler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import com.tesco.ofs.platform.trace.logger.OFSPlatformLogger;

public class ExceptionHandlerFactory {
	
	private static ExceptionHandlerFactory factory = null;

	private final static OFSPlatformLogger logger = OFSPlatformLogger.getLogger(ExceptionHandlerFactory.class); 
	
	@SuppressWarnings("rawtypes")
	private Map exceptionHandlerMap = new ConcurrentHashMap();

	
	public Map getExceptionHandlerMap() {
		return exceptionHandlerMap;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void registerExceptionHandler() 
	{				
		Properties prop = new Properties();	
		
		InputStream input;

		try 
		{
			input = ExceptionHandlerFactory.class.getClass().getResourceAsStream("exceptionhandler.properties")/*new FileInputStream("exceptionhandler.properties")*/;
			System.out.println("input:::" + input);

			//load properties file
			
			if(input != null)
				prop.load(input);
			else
				logger.error("exceptionhandler.properties file not in classpath");
				
		} catch (IOException  e) {			
			logger.error("Error in loading exceptionhandler.properties file", e);
		} 	
		Enumeration keys = prop.propertyNames();
						
		//add the extended implementation of exception handler to the map
		while(keys.hasMoreElements()){
			String cls = (String)prop.get(keys.nextElement());
			try {
				Class clazz = Class.forName(cls);								
				exceptionHandlerMap.put(clazz, clazz.newInstance());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException  e) {
				// TODO Auto-generated catch block
				logger.error("Error registering custom exception handlers", e);
			} 				
		}
		
		//add the out of box exception handler implementations.
		exceptionHandlerMap.put(LoggerExceptionHandler.class, new LoggerExceptionHandler());
		exceptionHandlerMap.put(ExceptionToJsonMapper.class, new ExceptionToJsonMapper());
		exceptionHandlerMap.put(ReExecutor.class, new ReExecutor());
		exceptionHandlerMap.put(ThrowableExceptionHandler.class, new ThrowableExceptionHandler());
		
	}
	
	public static ExceptionHandlerFactory getFactory()
	{
		
		if(factory == null)
		{
			synchronized(ExceptionHandlerFactory.class)
			{
				if(factory == null)
					factory = new ExceptionHandlerFactory();
			}
		}
								
		return factory;
	}

	@SuppressWarnings("rawtypes")
	public ILoggerExceptionHandler getLoggerExceptionHandler(Class exceptionHandlerCls)
	{						
		return (ILoggerExceptionHandler) exceptionHandlerMap.get(exceptionHandlerCls);
	}
	
	
	@SuppressWarnings("rawtypes")
	public IThrowableExceptionHandler getThrowableExceptionHandler(Class exceptionHandlerCls)
	{
		return (IThrowableExceptionHandler) exceptionHandlerMap.get(exceptionHandlerCls);
	}
	
	
	@SuppressWarnings("rawtypes")
	public IExceptionMapperHandler getExceptionMapperHandler(Class exceptionHandlerCls)
	{
		
		return (IExceptionMapperHandler) exceptionHandlerMap.get(exceptionHandlerCls);
	}
	
	@SuppressWarnings("rawtypes")
	public IReExecutorExceptionHandler getReExecutorHandler(Class reExecutor)
	{
		
		return (IReExecutorExceptionHandler) exceptionHandlerMap.get(reExecutor);
	}
		
}
