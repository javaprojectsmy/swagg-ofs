package com.tesco.ofs.platform.trace.exception.handler;

import java.util.concurrent.Callable;

public interface IReExecutorExceptionHandler {
	
	public <T> T reexecute(Callable<T> func);

}
