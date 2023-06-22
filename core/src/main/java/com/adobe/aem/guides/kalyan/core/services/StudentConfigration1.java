package com.adobe.aem.guides.kalyan.core.services;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

@ObjectClassDefinition(name="StudentDetails",description="this is studentDetails")
public @interface StudentConfigration1 {
	
	@AttributeDefinition(name="StudentName",
			             type=AttributeType.STRING,
			             description="enter studentname here")
	public String getStudentName() default "kalyan";
	

	@AttributeDefinition(name="RollNumber",
			             type=AttributeType.INTEGER,
			             description="enter Rollnumber here")
	public int getRollNumber() default 3;
	
	@AttributeDefinition(name="Regular",
			             type=AttributeType.BOOLEAN,
			             description="is student is Regular")
	public boolean getRegular() default true;
	
	@AttributeDefinition(name="Subjects",
			             type=AttributeType.STRING,
			             description="enter your subjects")
	public String[] getSubjects() default{"maths","telugu","hindi"};
	
	@AttributeDefinition(name="Countries",
			             type=AttributeType.STRING,
			             description="select your country",
	                     options= {
	                    		 @Option(label="India" ,value="india"),
	                    		 @Option(label="Pakishan" ,value="pakishan"),
	                    		 @Option(label="America" ,value="america"),
	                    		 @Option(label="London" ,value="london")
	                    		 
	                    		 
	                     })
	
	
	public String getcountries() default "India";
	


}
