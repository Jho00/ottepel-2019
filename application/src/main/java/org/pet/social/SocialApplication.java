package org.pet.social;

import org.pet.social.DAL.contracts.ProblemInterface;
import org.pet.social.DAL.contracts.UserInterface;
import org.pet.social.common.entity.Problem;
import org.pet.social.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.Timestamp;
import java.util.Arrays;

@SpringBootApplication
public class SocialApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SocialApplication.class, args);

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
	}

}

