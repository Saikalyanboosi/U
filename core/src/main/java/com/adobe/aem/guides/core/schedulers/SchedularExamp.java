package com.adobe.aem.guides.core.schedulers;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(immediate=true,service=Runnable.class)
@Designate(ocd=SchedulerConfiguration.class)
public class SchedularExamp implements Runnable{
	private static final Logger LOG = LoggerFactory.getLogger(SchedularExamp.class);

	private int SchedulerId;
	@Reference
	private Scheduler scheduler;
	@Activate
	protected void activate(SchedulerConfiguration config)
	{
		SchedulerId=config.getServiceName().hashCode();
		addscheduler(config);
	}
	@Deactivate
	protected void deactivate(SchedulerConfiguration config)
	{
		removescheduler(config);
	}
	protected void removescheduler(SchedulerConfiguration config)
	{
		scheduler.unschedule(String.valueOf(SchedulerId));
	}
	protected void addscheduler(SchedulerConfiguration config)
	{
		ScheduleOptions sc = scheduler.EXPR(config.getCornExpression());
		sc.name(String.valueOf(SchedulerId));
		sc.canRunConcurrently(true);
		scheduler.schedule(this,sc);
	}
	@Override
	public void run() {
		LOG.info("Scheduler is active");
		
	}

}
