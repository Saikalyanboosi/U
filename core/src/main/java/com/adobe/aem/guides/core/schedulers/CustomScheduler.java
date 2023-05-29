package com.adobe.aem.guides.core.schedulers;
import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;
import org.apache.sling.commons.scheduler.Job;
import org.apache.sling.commons.scheduler.JobContext;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate=true,service=Job.class)
@Designate(ocd=SchedulerConfiguration.class)
public class CustomScheduler implements Job {
	private static final Logger LOG =  LoggerFactory.getLogger(CustomScheduler.class);
	
	@Reference
	Scheduler scheduler;
	
	public int Schedulerid;
	
	@Activate
	public void activate(SchedulerConfiguration config)
	{
		Schedulerid =config.getCornExpression().hashCode();
		jobscheduler(config);
	}
	public void jobscheduler(SchedulerConfiguration config)
	{
		ScheduleOptions sc = scheduler.EXPR("0 0/1 * 1/1 * ? *");
		Map<String,Serializable> obj =new HashMap<>();
		obj.put("name","surge");
		obj.put("Location","Bangoler");
		obj.put("url","www.surge.com");
		sc.config(obj);
		scheduler.schedule(this,sc);
		
		ScheduleOptions sv = scheduler.EXPR("0 0/1 * 1/1 * ? *");
		Map<String,Serializable> obj1 =new HashMap<>();
		obj1.put("name","Infosys");
		obj1.put("Location","hydreabad");
		obj1.put("url","www.infosys.com");
		sc.config(obj1);
		scheduler.schedule(this,sv);
		
		ScheduleOptions sr= scheduler.EXPR("0 0/1 * 1/1 * ? *");
		Map<String,Serializable> obj2 =new HashMap<>();
		obj2.put("name","TCS");
		obj2.put("Location","chennai");
		obj2.put("url","www.tatacommunicationservice.com");
		sc.config(obj2);
		scheduler.schedule(this,sr);
	}

	@Override
	public void execute(JobContext jobContext) {
		LOG.info("Name{} Location{} url{}",jobContext.getConfiguration().get("name"),jobContext.getConfiguration().get("Location"),jobContext.getConfiguration().get("url"));
	
		
	}

}

