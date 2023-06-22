package com.adobe.aem.guides.kalyan.core.servlets;

import java.io.IOException;
import java.util.Iterator;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
@Component(service=Servlet.class)
@SlingServletResourceTypes(resourceTypes= {"kalyan/components/page","foundation/components/redirect"},
                           selectors= {"add","surge","sub"},
                           extensions= {"txt","json","xml"})
public class Kalyanservlet  extends SlingAllMethodsServlet{
	
	protected void doGet(SlingHttpServletRequest req,SlingHttpServletResponse res) throws IOException
	{
		String pagepath= req.getParameter("pagepath");
		if(pagepath==null)
		{
			pagepath="/content/kalyan/us/en/page2";
		}
		ResourceResolver rrr = req.getResourceResolver();
		PageManager pg = rrr.adaptTo(PageManager.class);
		Page page = pg.getPage(pagepath);
		Iterator<Page> childs = page.listChildren();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		while(childs.hasNext());
		{
			JsonObjectBuilder job = Json.createObjectBuilder();
			Page next = childs.next();
			job.add("title", next.getTitle());
			job.add("path",next.getPath());
			jab.add(job);
		}
		res.getWriter().write(jab.build().toString());
	}

}
