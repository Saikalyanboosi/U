package com.adobe.aem.guides.core.service;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name="EmployeeDetails",description="Enter the EmployeeDetails")
public @interface EmployeeDetails {
	
	@AttributeDefinition(name="Path",
			             type=AttributeType.STRING,
			             description="Enter the Pagepath here")
	public String getPath();

}
