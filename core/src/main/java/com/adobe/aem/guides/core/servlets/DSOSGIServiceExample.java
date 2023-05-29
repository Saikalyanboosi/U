package com.adobe.aem.guides.core.servlets;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import java.util.Map;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.adobe.fd.fp.service.Query;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
@Component(service=Servlet.class)
@SlingServletResourceTypes(resourceTypes= {"projectM/components/page","foundation/components/redirect"},
                           selectors= {"add","sub","surge"},
                            extensions= {"txt","json","xml"})
@SlingServletPaths(value="/bin/demo/recent")
public class DSOSGIServiceExample extends SlingSafeMethodsServlet{
	@Reference

	QueryBuilder queryBuilder;
	
	protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse res) throws IOException
	{
		Map<String,String> predicate = new HashMap<String,String>();
		predicate.put("type", "cq:Page");
		predicate.put("path","/content/we-retail/us/en");
		predicate.put("orderby","@jcr:content/cq:lastModified");
		predicate.put("orderby.sort","desc");
		predicate.put("p.limit","-1");
		
		com.day.cq.search.Query query = queryBuilder.createQuery(PredicateGroup.create(predicate),req.getResourceResolver().adaptTo(Session.class));
		SearchResult result = query.getResult();
		List<Hit> resourceList = result.getHits();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		for(Hit hit:resourceList)
		{
			try {
			JsonObjectBuilder job = Json.createObjectBuilder();
				Resource resource = hit.getResource();
				Resource content = resource.getResourceResolver().getResource(resource.getPath()+"/jcr:content");
				job.add("title", content.getValueMap().get("jcr:title",String.class));
				job.add("path", resource.getPath());
				jab.add(job);
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
			res.getWriter().write(jab.build().toString());
		}
	}
	
	
}
