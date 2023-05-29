package com.adobe.aem.guides.core.servlets;


import java.io.IOException;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;


@Component(service=Servlet.class,
property= {
		"sling.servlet.paths=/bin/recent/article"
})
public class Server  extends SlingAllMethodsServlet{
	
	protected void doGet(SlingHttpServletRequest req,SlingHttpServletResponse res)
			throws IOException{
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("data", "calling to mom");
		job.add("company", "surge");
		job.add("website", "surge.com");
	
		res.getWriter().write(job.build().toString());
	}

}

