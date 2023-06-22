package com.adobe.aem.guides.kalyan.core.models;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables= {SlingHttpServletRequest.class,Resource.class})
public class SlingModelExamp {
	
	@ValueMapValue
	public String text;
	
	@Inject
	public String text1;
	
	public String getTextValue()
	{
		return text;
	}
	public String getTecxt1Value()
	{
		return text1;
	}

}
