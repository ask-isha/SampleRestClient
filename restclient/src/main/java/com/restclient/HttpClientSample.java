package com.restclient;

import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.http.client.HttpClient;

import com.fasterxml.jackson.databind.ObjectMapper;
public class HttpClientSample {
	Logger logger=LoggerFactory.getLogger(HttpClient.class);

	public  void demoGetRESTAPI() throws Exception 
	{
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
    	HttpClient httpClient =  httpClientBuilder.build();

	    try
	    {
	    	//httpClientBuilder.setSslcontext(null);  /* to do security */
	        logger.info("HttpClient:entered Get()..........");
	    	HttpGet getRequest = new HttpGet("http://localhost:8081/emps");
	    	getRequest.addHeader("accept", "application/json");
	    	HttpResponse response = httpClient.execute(getRequest);

	    	int statusCode = response.getStatusLine().getStatusCode();
	    	if (statusCode != 200) 
	    	{
	    		throw new RuntimeException("Failed with HTTP error code : " + statusCode);
	    	}

	    	HttpEntity httpEntity = response.getEntity();
	    	String apiOutput = EntityUtils.toString(httpEntity);

	    	logger.info(apiOutput); 

	    	ObjectMapper om = new ObjectMapper();
	    	List<Emp> emp = Arrays.asList(om.readValue(apiOutput, Emp[].class));

	    	//Verify the populated object
	    	emp.forEach(item -> logger.info("ID::"+item.geteID()+" DptName::"+item.getDptName()+" CreateDate::"+item.getCreatedDate()));
	    }
	    finally
	    {
	        httpClient.getConnectionManager().shutdown();
	    }
	}

	public void demoPostRESTAPI() throws Exception 
	{
		logger.info("before demoPostRESTAPI()..............");
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
    	HttpClient httpClient =  httpClientBuilder.build();

	    //String empJson = 	"{\"eID\": 106,\"eName\": \"Anish\",\"dptName\": \"Aero\"}";
	     
	    try
	    {
	    	logger.info("step1 demoPostRESTAPI()..............");
	        HttpPost postRequest = new HttpPost("http://localhost:8081/emps?key=actvn");
	        postRequest.setHeader("content-type", "application/json");
	        postRequest.setHeader("Accept", "application/json");

	        //Change 1
	        ObjectMapper om = new ObjectMapper();
	        Emp empJson = new Emp();
		    empJson.seteID(106);
		    empJson.setDptName("Aero");
		    empJson.seteName("Anish");
		    
		    StringEntity userEntity = new StringEntity(om.writeValueAsString(empJson)); // Change1
	        postRequest.setEntity(userEntity);
	        
	        HttpResponse response = httpClient.execute(postRequest);
	        HttpEntity httpEntity = response.getEntity();
	    	String apiOutput = EntityUtils.toString(httpEntity);

	    	logger.info(apiOutput); 

	    	
	    	Emp emp = om.readValue(apiOutput, Emp.class);
	    	logger.info("ID::"+emp.geteID()+" DptName::"+emp.getDptName()+" CreateDt::"+emp.getCreatedDate());

	        int statusCode = response.getStatusLine().getStatusCode();
	        System.out.println("statuscode:::"+statusCode);
	        if (statusCode != 201) 
	        {
	            throw new RuntimeException("Failed with HTTP error code : " + statusCode);
	        }
	        logger.info("end demoPostRESTAPI()..............");
	    }
	    finally
	    {
	        //Important: Close the connect
	        httpClient.getConnectionManager().shutdown();
	    }
	}

	public void demoPostKieSrvrRESTAPI() throws Exception 
	{
		logger.info("before demoPostKieSrvrRESTAPI()..............");
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		 CredentialsProvider provider = new BasicCredentialsProvider();
		 UsernamePasswordCredentials credentials = new UsernamePasswordCredentials( "krisv", "krisv" );
		 provider.setCredentials( AuthScope.ANY, credentials );
		httpClientBuilder.setDefaultCredentialsProvider(provider);
    	HttpClient httpClient =  httpClientBuilder.build();
	     
	    try
	    {
	    	logger.info("step1 demoPostKieSrvrRESTAPI()..............");
	    	HttpPost postRequest = new HttpPost("http://localhost:8080/kie-server/services/rest/server/containers/ELTEvents_1.0.1-SNAPSHOT/processes/ELTEvents.SignalD/instances"); 
	        postRequest.setHeader("content-type", "application/json");
	        postRequest.setHeader("Accept", "application/json");
	        postRequest.setEntity(null);
	          
	        HttpResponse response = httpClient.execute(postRequest);
	        HttpEntity httpEntity = response.getEntity();
	    	String apiOutput = EntityUtils.toString(httpEntity);

	    	logger.info(apiOutput); 


	        int statusCode = response.getStatusLine().getStatusCode();
	        System.out.println("statuscode:::"+statusCode);
	        if (statusCode != 201) 
	        {
	            throw new RuntimeException("Failed with HTTP error code : " + statusCode);
	        }
	        logger.info("end demoPostKieSrvrRESTAPI()..............");
	    }
	    finally
	    {
	        //Important: Close the connect
	        httpClient.getConnectionManager().shutdown();
	    }
	}
	
	public static void main (String args[]) {
	//	HttpClient httpClient = new HttpClient();
		try {
			new HttpClientSample().demoGetRESTAPI();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
