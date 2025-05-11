package com.vee.Blogapp;

import com.vee.Blogapp.repositories.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogappApplicationTests {

	@Autowired
	private UserRepo userRepo;

	@Test
	void contextLoads() {
	}

	@Test
	public void testAutoWire(){
		String className = userRepo.getClass().getName();
		String packageName = userRepo.getClass().getPackageName();

		System.out.println("className::"+className);
		System.out.println("packageName::"+packageName);


	}

}
