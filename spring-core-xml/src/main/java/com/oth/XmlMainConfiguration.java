package com.oth;

import com.oth.business.IBusiness;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class XmlMainConfiguration {
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

//		Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

//		IBusiness business = (IBusiness) context.getBean("IBusiness");
//		business.fetchData();

		context.getBean(IBusiness.class).fetchData();
	}
}
