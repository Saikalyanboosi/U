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
property= {"sling.servlet.resourceTypes=kalyan/components/page",
"sling.servlet.method=POST",
"sling.servlet.method=GET",
"sling.servlet.method=PUT",
"sling.servlet.selectors=recent",
"sling.servlet.selectors=current",
"sling.servlet.extensions=txt",
"sling.servlet.extensions=json",
})
public class SlingservletExamp extends SlingAllMethodsServlet{


	protected void doGet(SlingHttpServletRequest req,SlingHttpServletResponse res) throws IOException
	{
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("kalyan", "Is a software Enginerr");
		job.add("sadik","Is a productbased company");
		job.add("kosushik","Is a system Engineer");
		
		res.getWriter().write(job.build().toString());
	}
}

