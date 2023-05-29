package com.adobe.aem.guides.core.schedulers;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name="Schedular Config",description="schedular Description")
public @interface SchedulerConfiguration {
	@AttributeDefinition(name="Service Name",
			type=AttributeType.STRING,
			description="Enter service name Here")
	
	public String getServiceName() default "surge service";
	
	@AttributeDefinition(name="Cron Expression",
			type=AttributeType.STRING,
			description="Enter Cron Expression Here")
	
	public String getCornExpression() default "0/1 0 0 ? * * *";

}

