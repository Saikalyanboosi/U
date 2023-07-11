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
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.core.service.OSGIEmployeeServices;
import com.day.cq.wcm.api.Page;

@Component(service=Servlet.class)
@SlingServletPaths(value="/bin/demo/recent")
public class OSGIPathConfiguration  extends SlingAllMethodsServlet{
	
	private static final Logger LOG  = LoggerFactory.getLogger(OSGIPathConfiguration.class);
	
	@Reference
	 OSGIEmployeeServices empdetails;

	protected void doGet(SlingHttpServletRequest req,SlingHttpServletResponse res) throws IOException
	{
		String path = empdetails.getPath();
		LOG.info("path",path);
		String query ="SELECT * FROM [cq:Page] AS page WHERE ISDESCENDANTNODE(page,'/content/citi-core-libs/us/en') AND page.[jcr:created] >= CAST('2023-06-21T10:16:27.676+05:30' AS DATE)";
		LOG.info("query",query);
		ResourceResolver resolver= req.getResourceResolver();
		LOG.info("query",query);
		Iterator<Resource> results = resolver.findResources(query, Query.JCR_SQL2);
		LOG.info("query",query);
		JsonArrayBuilder jab = Json.createArrayBuilder();
		LOG.info("query",query);
		while(results.hasNext())
		{
			JsonObjectBuilder job = Json.createObjectBuilder();
			Resource resource = results.next();
			Page currentpage = resource.adaptTo(Page.class);
			LOG.info("query",query);
			if(currentpage!=null)
			{
				job.add("path",currentpage.getPath());
				LOG.info("query",query);
				jab.add(job);
				LOG.info("path",path);
			}
			
			
		}
		
		res.getWriter().write(jab.build().toString());
		LOG.info("query",query);
		
		
	}

}
