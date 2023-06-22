package com.adobe.aem.guides.kalyan.core.services;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

@Component(service=OSGIConfigurationMethods.class)
@Designate(ocd= StudentConfigration1.class)
public class OSGIConfigurationImpl1 implements OSGIConfigurationMethods{

	private String StudentName;
	private int RollNumber;
	private boolean Regular;
	private String[] Subjects;
	private String Countries;
	
	@Activate
	protected void start(StudentConfigration1 config)
	{
	   StudentName=config.getStudentName();
	   RollNumber=config.getRollNumber();
	   Regular=config.getRegular();
	   Subjects=config.getSubjects();
	   Countries=config.getcountries();
	}

	@Override
	public String getStudentName() {
	
		return StudentName;
	}

	@Override
	public int getRollNumber() {
		
		return RollNumber;
	}


	@Override
	public boolean getRegular() {
	
		return Regular;
	}

	@Override
	public String[] getSubjects() {

		return Subjects;
	}

	@Override
	public String getCountries() {
		
		return Countries;
	}

	
}
