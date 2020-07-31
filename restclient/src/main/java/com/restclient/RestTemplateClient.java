package com.restclient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
public class RestTemplateClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getEmployees();

	}
	
	public static void getEmployees()
	{
	    final String uri = "http://localhost:8081/emps?key=actvn";
	     
	    //String empJson = 	"{\"eID\": 106,\"eName\": \"Anish\",\"dptName\": \"Aero\"}";
	    Emp empJson = new Emp();
	    empJson.seteID(106);
	    empJson.setDptName("Aero");
	    empJson.seteName("Anish");
	    RestTemplate restTemplate = new RestTemplate();
	 //   String result = restTemplate.getForObject(uri, String.class);
	    ResponseEntity<Emp> response =restTemplate.postForEntity(uri, empJson, Emp.class);
	    Emp apiOutput = response.getBody();
	    System.out.println("###::"+apiOutput.getCreatedDate());
	}

}
