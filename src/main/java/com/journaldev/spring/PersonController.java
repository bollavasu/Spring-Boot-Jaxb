package com.journaldev.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

@RestController
public class PersonController {
	
	@RequestMapping("/")
	public String convert() {
		jaxbJavaToXml();
		jaxbXmlToJava();
		return "Converted Successfully...";
	}
	
	public void jaxbJavaToXml(){
		  Customer customer = new Customer();
		  customer.setId(100);
		  customer.setName("mkyong");
		  customer.setAge(29);

		  try {

			File file = new File("E:\\InputFiles\\file.xml");
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(customer, file);
			jaxbMarshaller.marshal(customer, System.out);

		    } catch (JAXBException e) {
			  e.printStackTrace();
		    }
	}
	
	public void jaxbXmlToJava(){
		try {
			File file = new File("E:\\InputFiles\\file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Customer customer = (Customer) jaxbUnmarshaller.unmarshal(file);
			System.out.println(customer);

		  } catch (JAXBException e) {
			e.printStackTrace();
		  }
	}
}
