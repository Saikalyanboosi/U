package com.adobe.aem.guides.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.adobe.aem.guides.core.service.EmployeeDetailsMethods;


@Model(adaptables=SlingHttpServletRequest.class,defaultInjectionStrategy=DefaultInjectionStrategy.OPTIONAL)
public class EmployeeSlingModel {

	@OSGiService
	EmployeeDetailsMethods osgiemp;
	
	public String getPath()
	{
		return osgiemp.getPath();
	}
	 
	 
}
