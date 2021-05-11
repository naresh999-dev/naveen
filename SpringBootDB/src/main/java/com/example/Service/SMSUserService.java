package com.example.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


@Service
public class SMSUserService{
	@Value("${ACCOUNTSID}")
	private  String ACCOUNT_SID ;
	@Value("${AUTHTOKEN}")
	private  String AUTH_TOKEN ;
	@Value("${NO}")
	private String FROM_NO;
	  
	    public Message sendSMS(String toNumber, String data) {
	        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	        Message   message=  Message.creator(new PhoneNumber(toNumber), new PhoneNumber(FROM_NO),
	                data).create();
	        return message;
	    }

}
