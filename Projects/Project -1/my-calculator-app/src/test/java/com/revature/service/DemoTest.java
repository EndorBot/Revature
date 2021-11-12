package com.revature.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// classes are also used when writing tests, because all code in Java must be contained inside classes
// (except for the package keyword and import keyword)
public class DemoTest {
	
			// 5 annotations to be aware of:
			/* 1. @BeforeAll: must be annotated on a static method
			 * 2. @BeforeEach: non-static
			 * 3. @Test: non-static
			 * 4. @AfterEach: non-static
			 * 5. @AfterAll: must be annotated on a static method
			 */
	
			// BeforeAll and BeforeEach can be used to setup initial objects for our tests
	
			// AfterAll and AfterEach might be used to cleanup resources that we have used
	
			@BeforeAll
			public static void beforeAll() {
					System.out.println("Runs before ALL of our tests");
			}
			
			@BeforeEach
			public void setUp() {
				System.out.println("Run before each and every test");
			}
			
			@AfterEach
			public void cleanUp() {
				System.out.println("Run after each and every test");
			}
			
			@AfterAll
			public static void afterAll() {
				System.out.println("Run after ALL of our tests");
			}
			
			/*
			 * Tests
			 */
			
			@Test
			public void test1() {
				System.out.println("test1 running");
			}
			
			@Test
			public void test2() {
				System.out.println("test2 running");
			}
			
			@Test
			public void test3() {
				System.out.println("test3 running");
			}

}
