package com.rest.api;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Path("/rest")
public class ApiHandler {
	
	@GET
	@Path("/getdetails")
	public Response getDetails(@Context ServletContext context) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		JSONObject jb = new JSONObject();
		
		try {
			String path = context.getRealPath("/WEB-INF/json.txt");
			Object unitsObj = parser.parse(new FileReader(path));
			return Response.status(200).entity(unitsObj.toString()).build();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				jb.put("response", "Server Error");
				jb.put("status", "failure");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return Response.status(400).entity(jb).build();
		}
	}
}
