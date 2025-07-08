package com.example.FileStorage.util;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.FileStorage.beans.BaseResponse;

import com.example.FileStorage.beans.Status;



@Component
public class SecurityUtil {

	private static final String $COUNT = "$count";

	private static final String $FILTER = "$filter";

	private Log log = LogFactory.getLog(SecurityUtil.class);

	@Autowired
	Status status;



	@Autowired
	BaseResponse baseResponse;
	




	
	public BaseResponse setMessageReponse(int code , String Message) {
		
		if(code==1)
		{
			
			status.setStatusCode(1);
			//status.setStatusMessage("Un Authorized Access");
			status.setStatusMessage(Message);
			baseResponse.setStatus(status);
		}else if(code==-1)
		{
			
			status.setStatusCode(-1);
			//status.setStatusMessage("Un Authorized Access");
			status.setStatusMessage(Message);
			baseResponse.setStatus(status);
		}
		else if(code==-2)
		{
			
			status.setStatusCode(-2);
			//status.setStatusMessage("Un Authorized Access");
			status.setStatusMessage(Message);
			baseResponse.setStatus(status);
		}
		else if(code==2)
		{
			
			status.setStatusCode(2);
			//status.setStatusMessage("Un Authorized Access");
			status.setStatusMessage(Message);
			baseResponse.setStatus(status);
		}
		else if(code==3)
		{
			
			status.setStatusCode(3);
			//status.setStatusMessage("Un Authorized Access");
			status.setStatusMessage(Message);
			baseResponse.setStatus(status);
		}
		return baseResponse;
	}
	

	public BaseResponse setMessageReponse(int code) {
		if(code==-1)
		{
			
			status.setStatusCode(-1);
			//status.setStatusMessage("Un Authorized Access");
			status.setStatusMessage("Un-Authorized Access");
			baseResponse.setStatus(status);
			//return baseResponse;
		}else if(code==-2)
		{
			
			status.setStatusCode(-2);
			//status.setStatusMessage("Un Authorized Access");
			status.setStatusMessage("Internal server error");
			baseResponse.setStatus(status);
			//return baseResponse;
		}else if(code==-3)
		{
			
			status.setStatusCode(-3);
			//status.setStatusMessage("Un Authorized Access");
			status.setStatusMessage("Bad Request Error");
			baseResponse.setStatus(status);
			//return baseResponse;
		}
		if(code==1)
		{
			
			status.setStatusCode(1);
			//status.setStatusMessage("Un Authorized Access");
			status.setStatusMessage("Request Processed Successfully");
			baseResponse.setStatus(status);
		}
		if(code==3)
		{
			
			status.setStatusCode(3);
			status.setStatusMessage("No Data Found");
			baseResponse.setStatus(status);
		}
		return baseResponse;
	}
	
	
	
	
}
	

	


