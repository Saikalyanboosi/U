package com.adobe.aem.guides.core.servlets;

import java.io.IOException;

import java.util.Iterator;

import javax.jcr.query.Query;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import com.day.cq.wcm.api.Page;

@Component(service=Servlet.class)
@SlingServletResourceTypes(resourceTypes= {"projectM/components/page","foundation/components/redirect"},
                           selectors= {"add","sub","surge"},
                            extensions= {"txt","json","xml"})
@SlingServletPaths(value="/bin/demo/recent")
public class DSOSGIServices extends SlingSafeMethodsServlet{
	
	protected void doGet(SlingHttpServletRequest req,SlingHttpServletResponse res) throws IOException
	{
		String queryparam = req.getParameter("qparam");
		String query ="SELECT * FROM [cq:Page] AS s WHERE ISDESCENDANTNODE([/content/projectM]) AND s.[jcr:content/jcr:title] like '"+queryparam+"%'";
		ResourceResolver resolver= req.getResourceResolver();
		Iterator<Resource> results = resolver.findResources(query, Query.JCR_SQL2);
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		while(results.hasNext())
		{
			JsonObjectBuilder job = Json.createObjectBuilder();
			Resource resource = results.next();
			Page currentpage = resource.adaptTo(Page.class);
			if(currentpage!=null)
			{
				job.add("title", currentpage.getTitle());
				job.add("path",currentpage.getPath());
				jab.add(job);
			}
			
			
		}
		
		res.getWriter().write(jab.build().toString());
		
		
	}

}
