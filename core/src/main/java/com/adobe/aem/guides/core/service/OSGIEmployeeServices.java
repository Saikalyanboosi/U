package com.adobe.aem.guides.core.service;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;
@Component(service=EmployeeDetailsMethods.class)
@Designate(ocd=EmployeeDetails.class)
public class OSGIEmployeeServices implements EmployeeDetailsMethods {
	
	private String path;
	@Activate()
	protected void start(EmployeeDetails emp) {
	   path=emp.getPath();
	}

	@Override
	public String getPath() {
		return path;
	}

}
