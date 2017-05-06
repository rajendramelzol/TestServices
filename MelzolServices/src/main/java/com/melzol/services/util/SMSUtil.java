package com.melzol.services.util;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;

public class SMSUtil {

	public static int getnerateOTP(){
		return ThreadLocalRandom.current().nextInt(1001, 9999);
	}
	public static void postSms(String mobile,int otp) throws HttpException, IOException {
		String url="http://smsalertbox.com/api/sms.php";
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		 method.addParameter("uid", "6b726973686e6170");
		 method.addParameter("pin", "597fe533f16fc3791835fc2b59af9a54");
		 method.addParameter("sender", "MELZOL");
		 method.addParameter("route", "5");
		 method.addParameter("tempid", "66445");
		 method.addParameter("mobile", mobile);
		 method.addParameter("pushid", "1");
		 method.addParameter("message", "Your One Time Password is "+otp+" Please proceed for Registration at MelZol.");
		client.executeMethod(method);

	}
}
