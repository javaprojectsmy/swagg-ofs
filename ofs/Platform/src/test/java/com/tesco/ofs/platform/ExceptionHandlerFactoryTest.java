package com.tesco.ofs.platform;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tesco.ofs.platform.trace.exception.handler.ExceptionHandlerFactory;
import com.tesco.ofs.platform.trace.exception.handler.ExceptionToJsonMapper;
import com.tesco.ofs.platform.trace.exception.handler.IExceptionMapperHandler;
import com.tesco.ofs.platform.trace.exception.handler.ILoggerExceptionHandler;
import com.tesco.ofs.platform.trace.exception.handler.IReExecutorExceptionHandler;
import com.tesco.ofs.platform.trace.exception.handler.IThrowableExceptionHandler;
import com.tesco.ofs.platform.trace.exception.handler.LoggerExceptionHandler;
import com.tesco.ofs.platform.trace.exception.handler.ReExecutor;
import com.tesco.ofs.platform.trace.exception.handler.ThrowableExceptionHandler;

public class ExceptionHandlerFactoryTest {
	
	ExceptionHandlerFactory fact = ExceptionHandlerFactory.getFactory();

	@Before
	public void setUp() throws Exception {		
		fact.registerExceptionHandler();		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testShouldCheckIfFactoryReturnsLoggerExceptionHandler() {	
		ILoggerExceptionHandler eh = fact.getLoggerExceptionHandler(LoggerExceptionHandler.class);		
		assertTrue("Factory Returned LoggerExceptionHandler", (eh instanceof LoggerExceptionHandler));
		
	}
	
	@Test
	public void testShouldCheckIfFactoryReturnsExceptionToJsonMapperExceptionHandler() {	
		IExceptionMapperHandler eh = fact.getExceptionMapperHandler(ExceptionToJsonMapper.class);		
		assertTrue("Factory Returned ExceptionToJsonMapperExceptionHandler", (eh instanceof ExceptionToJsonMapper));
		
	}
	
	@Test
	public void testShouldCheckIfFactoryReturnsThrowableExceptionHandler() {	
		IThrowableExceptionHandler eh = fact.getThrowableExceptionHandler(ThrowableExceptionHandler.class);		
		assertTrue("Factory Returned ThrowableExceptionHandler", (eh instanceof ThrowableExceptionHandler));
		
	}
	
	@Test
	public void testShouldCheckIfFactoryReturnsReExecutorExceptionHandler() {	
		IReExecutorExceptionHandler eh = (IReExecutorExceptionHandler) fact.getReExecutorHandler(ReExecutor.class);		
		assertTrue("Factory Returned ReExecutorExceptionHandler", (eh instanceof ReExecutor));
		
	}

}
