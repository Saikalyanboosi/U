package com.adobe.aem.guides.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.Workflow;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.model.WorkflowModel;
@Component(service=Servlet.class)
@SlingServletResourceTypes(resourceTypes= {"wknd/components/page","foundation/components/redirect"},
                          selectors= {"add","sub","surge"},
                          extensions= {"txt","json","xml"})
@SlingServletPaths(value="/bin/demo/recent")

public class DSsaiExample extends SlingSafeMethodsServlet{
	private static final Logger LOG  =  LoggerFactory.getLogger(DSsaiExample.class);
	public String workflow="not executing";
	
	protected void doGet(SlingHttpServletRequest req,SlingHttpServletResponse res) throws IOException
	{
		ResourceResolver resourceResolver = req.getResourceResolver();
		String payload = req.getParameter("page").toString();
		
		try {
		if(StringUtils.isNotBlank(payload))
		{
			WorkflowSession workflowSession = resourceResolver.adaptTo(WorkflowSession.class);
			WorkflowModel workflowModel = workflowSession.getModel("/var/workflow/models/create-version-model");
			WorkflowData newWorkflowData = workflowSession.newWorkflowData("JCR_PATH",payload);
		    workflow = workflowSession.startWorkflow(workflowModel, newWorkflowData).getState();
		}
		}
		catch(Exception e) 
		{
			LOG.info("Showing workflow exception",e.getMessage());
		}
		res.getWriter().write(workflow);
	}


}
