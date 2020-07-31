package com.restclient;

import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class JbossRestesyClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 final String uri = "http://localhost:8081/emps";
	     
			ResteasyClient client = new ResteasyClientBuilder().build();
			
			//GET example
			ResteasyWebTarget getDummy = client.target(uri);
			
			Response getDummyResponse = getDummy.request().get();
			
			String value = getDummyResponse.readEntity(String.class);
	        System.out.println("=====>>>>"+value);
	        getDummyResponse.close(); 
	}

}
