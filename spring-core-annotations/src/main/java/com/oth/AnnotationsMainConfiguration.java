package com.oth;

import com.oth.business.IBusiness;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class AnnotationsMainConfiguration {
	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext("com.oth");

		//Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

		context.getBean(IBusiness.class).fetchData();
	}
}
