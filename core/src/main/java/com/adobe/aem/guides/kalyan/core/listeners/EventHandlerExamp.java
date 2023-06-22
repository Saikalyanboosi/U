package com.adobe.aem.guides.kalyan.core.listeners;

import org.osgi.service.component.annotations.Component; 
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component(service=EventHandler.class,immediate=true, 
           property = {
        		   EventConstants.EVENT_TOPIC+"=org/apache/sling/api/resource/Resource/ADDED",
        		   EventConstants.EVENT_TOPIC+"=org/apache/sling/api/resource/Resource/REMOVED",
        		   EventConstants.EVENT_TOPIC+"=org/apache/sling/api/resource/Resource/CHANGED",
        		   EventConstants.EVENT_TOPIC+"=com/day/cq/replication",
        		   EventConstants.EVENT_FILTER+"=(|(|(path=/content/kalyan/us/en/*)(path=/content/projectM/us/en/*))(type=activate))"
        		   
	
})
 
public class EventHandlerExamp implements EventHandler {
	public static final Logger LOG = LoggerFactory.getLogger(EventHandlerExamp.class);

	@Override
	public void handleEvent(Event event) {
		LOG.info("Event Handler is Excuted...");
		LOG.info("Topic Name{}",event.getTopic());
		String[] propertyNames = event.getPropertyNames();
		for(String names:propertyNames)
		{
			LOG.info("Property Name{},Property Value{}",names,event.getProperty(names).toString());	
		}
		
	}

}
