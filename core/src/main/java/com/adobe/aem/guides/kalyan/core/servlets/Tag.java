package com.adobe.aem.guides.kalyan.core.servlets;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import com.day.cq.tagging.TagManager;
@Component(service=Servlet.class,immediate=true)
@SlingServletPaths(value="/apps/kalyan")
public class Tag extends SlingSafeMethodsServlet{
	protected void doGet(SlingHttpServletRequest req,SlingHttpServletResponse res) throws IOException,ServletException
	{
		ResourceResolver resolver = req.getResourceResolver();
		com.day.cq.tagging.Tag resolve = resolver.adaptTo(TagManager.class).resolve("/content/cq:tags/taggingconcept/colors");
		Iterator<com.day.cq.tagging.Tag> listChildren = resolve.listChildren();
		JSONObject pageobject =  new JSONObject();
		JSONArray pagearray  = new JSONArray();
	
		
		while(listChildren.hasNext())
		{
			com.day.cq.tagging.Tag next = listChildren.next();
			try {
				pageobject.put(next.getTitle(),next.getPath());
				pagearray.put(pageobject);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		res.getWriter().write(pageobject.toString());
	}

}
