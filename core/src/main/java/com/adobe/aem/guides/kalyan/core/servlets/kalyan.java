package com.adobe.aem.guides.kalyan.core.servlets;

import java.io.IOException;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
@Component(service=Servlet.class,
property= {"sling.servlet.paths=/bin/current/article"})

public class kalyan extends SlingAllMethodsServlet {
	
	protected void doGet(SlingHttpServletRequest req,SlingHttpServletResponse res) throws IOException
	{
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("AEM", "Adobe Expirence Manager");
		job.add("Java","Object Oriented Programming Language");
		job.add("Clanguage","IS not Object Oriented Programming language");
		
		
		res.getWriter().write(job.build().toString());
	}

}
