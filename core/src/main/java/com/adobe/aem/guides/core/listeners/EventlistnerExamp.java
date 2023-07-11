package com.adobe.aem.guides.core.listeners;
import java.util.EventListener;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.observation.Event;
import javax.jcr.observation.EventIterator;

import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component(service=EventListener.class,immediate=true)
public class EventlistnerExamp implements EventListener{
	private static final Logger log = LoggerFactory.getLogger(EventlistnerExamp.class);
	private Session session;
	
	@Reference
	SlingRepository slingRepository;
	
	@Activate
	public void activate() throws Exception
	{
		try {
		session=slingRepository.loginService("ProjectMsubservice", null);
		session.getWorkspace().getObservationManager().addEventListener((javax.jcr.observation.EventListener) this, Event.NODE_ADDED/Event.PROPERTY_ADDED, "/content/projectM/us/en", true, null, null,true);
		}
		catch(RepositoryException e)
		{
			log.info("\n Error while adding event Listener :{}",e.getMessage());
		}
	}
	public void onEvent(EventIterator eventIterator)
	{
		try {
		while(eventIterator.hasNext())
		{
			log.info("\n Path :{}",eventIterator.nextEvent().getPath());
		}
		}
		catch(Exception e)
		{
			
		}
		
	}
}


