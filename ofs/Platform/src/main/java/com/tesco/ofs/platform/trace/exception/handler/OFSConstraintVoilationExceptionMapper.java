package com.tesco.ofs.platform.trace.exception.handler;

import io.dropwizard.jersey.validation.ValidationErrorMessage;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tesco.ofs.platform.trace.exception.OFSPlatformException;
import com.tesco.ofs.platform.trace.exception.OFSPlatformExceptionEnum;
import com.tesco.ofs.platform.trace.exception.OFSPlatformRunTimeException;
import com.tesco.ofs.platform.trace.logger.OFSPlatformLogger;


public class OFSConstraintVoilationExceptionMapper implements ExceptionMapper<ConstraintViolationException>{
	
	private static final OFSPlatformLogger logger = OFSPlatformLogger.getLogger(OFSConstraintVoilationExceptionMapper.class);
	private static final int UNPROCESSABLE_ENTITY = 422;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Response toResponse(ConstraintViolationException e) {
						
		Set<ConstraintViolation<?>> constraintVoilations = e.getConstraintViolations();
		/*final ValidationErrorMessage message = new ValidationErrorMessage(constraintVoilations);
		List list = message.getErrors();*/
		
		OFSPlatformException ope = new OFSPlatformException(e.getMessage(), e);
		
		Map params = new HashMap();		
		for(ConstraintViolation constraintVoilation : constraintVoilations )
		{						
			params.put(constraintVoilation.getPropertyPath().toString()/*prop.value()*/, constraintVoilation.getMessage());
		}
						
		ope.setErrorCode(OFSPlatformExceptionEnum.DATA_VALIDATION_ERROR);
		ope.setErrorType(OFSPlatformExceptionEnum.DATA_VALIDATION_ERROR);				
		ope.setErrorDescription("Input Data Validation Failed");
		ope.setParams(params);
		IExceptionMapperHandler exceptionMapperHandler = new ExceptionToJsonMapper();
		String errorJson = exceptionMapperHandler.mapException(ope);
		
		logger.debug("Json error::::" +errorJson);
		
		return Response.status(UNPROCESSABLE_ENTITY)
                .type(MediaType.APPLICATION_JSON)               
                .entity(errorJson)
                .build();

	}

}
