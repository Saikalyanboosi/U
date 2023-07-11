package com.adobe.aem.guides.kalyan.core.servlets;
import java.io.IOException;
import java.util.Iterator;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageFilter;
import com.day.cq.wcm.api.PageManager;

@Component(service=Servlet.class)  //registering servlet
@SlingServletResourceTypes(resourceTypes={"kalyan/components/page","foundation/components/redirect"},
							selectors={"add","sub","surge"},
							extensions={"txt","json","xml"})

public class DSOSGISExamples extends SlingSafeMethodsServlet{
	private static final Logger LOG=LoggerFactory.getLogger(DSOSGISExamples.class);
	
	protected void doGet(SlingHttpServletRequest req,SlingHttpServletResponse res) throws IOException {
		
		
		String pagePath=req.getParameter("pagePath");
		if(pagePath==null) {
			pagePath="/content/kalyan/us/en/page1";
		} 
		ResourceResolver rr=req.getResourceResolver();           //to all configuration and connectivity
		PageManager pageManager=rr.adaptTo(PageManager.class);   //adaptTo() : is responsible to convert resource to an object
		Page page= pageManager.getPage(pagePath);                //From Page manager we're getting pages and also perform CRUD operations
		Iterator<Page> childPages=page.listChildren(new PageFilter(),true);        //listChildern(): which will iterate the child pages of particular page
		
		JsonArrayBuilder jab=Json.createArrayBuilder();
		
		while(childPages.hasNext()) 
		{
			
			Page next =childPages.next();	
			JsonObjectBuilder job=Json.createObjectBuilder(); //JsonObjectBuilder is Responsible to store Json Object
			LOG.info("childPages:"+next.getTitle());
			job.add("Title", next.getTitle());  //getting title
			job.add("Path",next.getPath());     //getting path
			job.add("Resource",next.getContentResource().toString());
			jab.add(job);                      //Adding to array object
			
		}
		res.getWriter().write(jab.build().toString());
	}

}

