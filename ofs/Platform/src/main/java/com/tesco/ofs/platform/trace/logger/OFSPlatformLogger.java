package com.tesco.ofs.platform.trace.logger;


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.core.joran.spi.JoranException;

//import com.tesco.ofs.platform.trace.exception.OFSPlatformRunTimeException;


public class OFSPlatformLogger {
	
	private Logger logger ;
	
	public final static Marker INFO = MarkerFactory.getMarker("INFO");
	public final static Marker ERROR = MarkerFactory.getMarker("ERROR");
	public final static Marker DEBUG = MarkerFactory.getMarker("DEBUG");
	public final static Marker TRACE = MarkerFactory.getMarker("TRACE");
	public final static Marker WARN = MarkerFactory.getMarker("WARN");
	public final static Marker METRIC = MarkerFactory.getMarker("METRIC");
		
	private OFSPlatformLogger(Class clazz)
	{
		this.logger =  LoggerFactory.getLogger(clazz);

	}
	
	public static OFSPlatformLogger getLogger(Class clazz)
	{		
		return new OFSPlatformLogger(clazz);
		
	}
		
	public void debug(String msg)
	{
		logger.debug(msg);		
	}
	
	public void debug(Marker marker, String msg)
	{		
		logger.debug(marker, msg);
	}
	public void error(String msg)
	{
		logger.error(msg);
		
	}
	
	public void error(Marker marker, String msg)
	{
		logger.error(marker, msg);
	}
	
	public void error(String msg, Throwable t)
	{
		logger.error(msg, t);
	}
	
	public void info(String msg)
	{
		logger.info(msg);
	}
	
	public void info(Marker marker, String msg)
	{
		logger.info(marker, msg);
	}
	
	public void warn(String msg)
	{
		logger.warn(msg);	
	}
	
	public void warn(Marker marker, String msg)
	{
		logger.warn(marker, msg);
	}	
	
	public void trace(String msg)
	{
		logger.trace(msg);	
	}	
	
	public void trace(Marker marker, String msg)
	{
		logger.trace(marker, msg);
	}
	
	/*
	 * Set key-value pair in MDC
	 */

	public static void setInMDC(String key, String value) {
		MDC.getCopyOfContextMap();
		MDC.put(key, value);
	}

	/*
	 * Fetch key-value pair from MDC
	 */

	public static String getFromMDC(String key) {
		MDC.getCopyOfContextMap();
		return (String) MDC.get(key);
	}
			
	public void setLogbackLogger()  {

		LoggerContext context = getCurrentLogContext();
		context.reset();
		ContextInitializer initializer = new ContextInitializer(context);

		try {	
			initializer.autoConfig();
		} catch(JoranException e) {			
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public static LoggerContext getCurrentLogContext() {

		return (LoggerContext)LoggerFactory.getILoggerFactory();
	}
	
	/*
	 * This method calculates the start of timer 
	 */
	
	//Implements threadLocal
	private static ThreadLocal<Map> timerThreadLocalMap = new ThreadLocal();

	public static void beginTimer(String methodName, Class classz) {
		
		//Retrieves the map for the current thread
		Map<Object, Object> timerMap = (Map) timerThreadLocalMap.get();

		// if the map is null, then set a new Hashmap to the current thread
		if(null == timerMap) {
			timerMap = new HashMap<>();
			timerThreadLocalMap.set(timerMap);
		}

		//Get the current system time in milliseconds and set it in a Long Object
		Long beginTime = new Long(System.currentTimeMillis());

		//if the method for which timer needs to be set is not present in the threadLocalMap, then add the method and beginTime to the Map	
		if(!timerMap.containsKey(methodName)) {
			timerMap.put(methodName, beginTime);
		} else {
			// Otherwise retrieve the object from map. 		
			Object x = timerMap.get(methodName);
			Stack<Object> stack;
			if(x instanceof Long) {
				//If the object is an Long Object then create a stack and push the old and new Long Object to the stack.
				stack = new Stack<Object>();
				stack.push(x);
				timerMap.put(methodName, stack);
			} else {
				//  If the object is a stack object then push new LongObject to the stack.
				stack =  (Stack<Object>)x;
			}

			stack.push(beginTime);
			
		}
		
		if(getCurrentLogContext().getLogger(classz).getEffectiveLevel().isGreaterOrEqual(Level.DEBUG)) {
			getCurrentLogContext().getLogger(classz).debug(methodName + " - Begin");
		}

	}
	
	/*
	 * This method calculates the end of timer 
	 */
	
	public static void endTimer(String methodName, Class classz) {

		//Retrieves the map for the current thread
		Map<Object, Object> timerMap = (Map) timerThreadLocalMap.get();
		// if the map is null, then the time diff will be set as 0
		if(null == timerMap) {
			getCurrentLogContext().getLogger(classz).debug("No Timer Map is present for the current logger");
			timerMap = new HashMap<>();
			timerThreadLocalMap.set(timerMap);
		}

		long timeDiff = 0L;
		//Get the current system time in milliseconds
		long endTime = System.currentTimeMillis();

		//if the method for which timer needs to be set is not present in the threadLocalMap, then time diff will be 0
		if(!timerMap.containsKey(methodName)) {
			getCurrentLogContext().getLogger(classz).debug("No Begin Timers for the method : " + methodName);
		} else {
			// Otherwise retrieve the object from map. 		
			Object x = timerMap.get(methodName);
			long beginTime = 0L;
			if(x instanceof Long) {
				//If the object is an Long Object then set the beginTime as the LongObject			
				beginTime = ((Long)x).longValue();
			} else if(((Stack) x).isEmpty()){
				// if stack is empty then the timediff will be set as 0
				beginTime = endTime; 
			} else {
				//  If the object is a stack object then pop the LongObject fom the stack.
				beginTime = ((Long) ((Stack) x).pop()).longValue();
			}

			timeDiff = endTime - beginTime;
		}

		if(getCurrentLogContext().getLogger(classz).getEffectiveLevel().isGreaterOrEqual(Level.DEBUG)) {
			getCurrentLogContext().getLogger(classz).debug(methodName + " - End -  [" + timeDiff + "]");
		}

	}



}
