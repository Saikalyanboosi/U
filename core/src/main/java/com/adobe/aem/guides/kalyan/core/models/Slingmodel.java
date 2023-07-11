package com.adobe.aem.guides.kalyan.core.models;
import java.util.ArrayList;
import java.util.Iterator;

import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.kalyan.core.beans.SlingModelBean;

@Model(adaptables= {SlingHttpServletRequest.class,Resource.class},defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Slingmodel{
	
	@ValueMapValue
	public String name;
	
	@ValueMapValue
	public String rollnumber;
	
	@ValueMapValue
	public String text;
	
	@ValueMapValue
	public String pic;
	@ValueMapValue
	public String country;
	@ValueMapValue
	public String gender;
	@ValueMapValue
	public String select;
	@ValueMapValue
	public String fname;
	@ValueMapValue
	public String empid;
	@ValueMapValue
	public String image;
	
	private static final Logger LOG  = LoggerFactory.getLogger(Slingmodel.class);
	
	@Inject
	Resource resource;
	
	public String getImage()
	{
		return image;
	}
	public String getEmpid()
	{
		return empid;
	}
	
	public String getFname()
	{
		return fname;
	}
	public String getName()
	{
		return name;
	}
	public String getRollnumber()
	{
		return rollnumber;
	}
	public String getText()
	{
		return text;
	}
	
	public String getPic()
	{
		return pic;
	}
	public String getCountry()
	{
		return country;
	}
	public String getGender()
	{
		return gender;
	}
	public String getSelect()
	{
		return select;
	}
	
	public String getPath()
	{
		return resource.getPath();
	}
	public String getParentNodeName()
	{
		Resource parent = resource.getParent();
		String parentnodename=parent.getName();
		
		return parentnodename;
	}
	@ChildResource
	Resource kalyan;
	
	public ArrayList<SlingModelBean> getMultiChildNodes()
	{
	ArrayList<SlingModelBean> object=new ArrayList<SlingModelBean>();
		SlingModelBean slingModelBean = new SlingModelBean();
		  Iterator<Resource> itemResources = kalyan.listChildren();
		  while(itemResources.hasNext()){
			  
			  Resource itemResource = itemResources.next();
			  String zipcode = itemResource.getValueMap().get("zipcode",String.class);
			  String fullname = itemResource.getValueMap().get("fullname",String.class);
			  String presentaddress = itemResource.getValueMap().get("persentAddress",String.class);
			  String district = itemResource.getValueMap().get("District",String.class);
			  slingModelBean.setZipcode(zipcode);
			  slingModelBean.setFullname(fullname);
			  slingModelBean.setPresentaddress(presentaddress);
			  slingModelBean.setDistrict(district);
			  object.add(slingModelBean);
			
		  }
		
		 return object;
	}

	
	public ArrayList<SlingModelBean> getNestedChildNodes(){
	ArrayList<SlingModelBean> obj=new ArrayList<SlingModelBean>();
		SlingModelBean slingModelBean = new SlingModelBean();
		Resource child = resource.getChild("bookdetailswithnastedmultifield");
		  Iterator<Resource> item = child.listChildren();
		  while(item.hasNext()){ 
			  Resource item1 = item.next();
			  String bookname = item1.getValueMap().get("bookname",String.class);
			  String booksubject = item1.getValueMap().get("booksubject",String.class);
			  slingModelBean.setBookname(bookname);
			  slingModelBean.setBooksubject(booksubject);
			  
			  //SUB CHILD
			
		 	  Resource subchildResource = item1.getChild("bookeditons");
			  LOG.info("childResourceName:"+subchildResource.getName());
			  Iterator<Resource> item2 = subchildResource.listChildren();
			  
			  while (item2.hasNext()) {
				Resource subItemResource1 =item2.next();
				 LOG.info("SubItem:"+subItemResource1.getName());
				String editionDate = subItemResource1.getValueMap().get("editondate",String.class);
				LOG.info("editondate:"+editionDate);
				slingModelBean.setEditondate(editionDate);
				String bookEdition = subItemResource1.getValueMap().get("bookediton",String.class);
				LOG.info("bookEdition:"+bookEdition);
				slingModelBean.setBookediton(bookEdition);
				
				
				
				 //SUB1 CHILD
				Resource item3 = subItemResource1.getChild("collagedetails");
				  LOG.info("item3:"+item3.getName());
				  Iterator<Resource> subItemResources1 = item3.listChildren();
				  
				  while (subItemResources1.hasNext()) {
					Resource subItemResource2 =subItemResources1.next();
					 LOG.info("SubItem1:"+subItemResource2.getName());
					String CollageName = subItemResource2.getValueMap().get("collagename",String.class);
					LOG.info("collagename:"+CollageName);
					slingModelBean.setCollagename(CollageName);
					String  Department= subItemResource2.getValueMap().get("department",String.class);
					LOG.info("department:"+Department);
					slingModelBean.setDepartment(Department);
					String Gender = subItemResource2.getValueMap().get("gender",String.class);
					LOG.info("gender:"+Gender);
					slingModelBean.setGender(Gender);
					String Year = subItemResource2.getValueMap().get("year",String.class);
					LOG.info("year:"+Year);
					slingModelBean.setYear(Year);
					obj.add(slingModelBean);
			  }	
		  }
		
	}
		return obj;
	}
}

	
	
	
	/*@Inject
	Resource process;
	public ArrayList<SlingModelBean> getNestedChildNodes1(){
		ArrayList<SlingModelBean> obj1=new ArrayList<SlingModelBean>();
			SlingModelBean slingModelBean = new SlingModelBean();
			Resource child2 = process.getChild("collagedetails");
			Iterator<Resource> children = child2.listChildren();
			while(children.hasNext())
			{
				Resource childrens = children.next();
				String CollageName = childrens.getValueMap().get("collagename",String.class);
				String Department = childrens.getValueMap().get("department",String.class);
				String Gender = childrens.getValueMap().get("gender",String.class);
				String Year = childrens.getValueMap().get("year",String.class);
				slingModelBean.setCollagename(CollageName);
				slingModelBean.setDepartment(Department);
				slingModelBean.setGender(Gender);
				slingModelBean.setYear(Year);
				obj1.add(slingModelBean);
			}
			return obj1;*/

  
