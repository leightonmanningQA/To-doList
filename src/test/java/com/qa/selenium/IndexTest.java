package com.qa.selenium;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

 class IndexTest {
	private static RemoteWebDriver driver;
	private final String URL = "http://localhost:8082/index.html";
	private static WebElement targ;
	private static List<WebElement> targList;
	private static ExtentReports report;
	private static ExtentTest test;
	
	@BeforeAll
	public static void beforeAll() {
		//setup
		
		//Extent report
		report = new ExtentReports("target/reports/Tdlindexreport.html", true);
		
		//system.property
		System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
		//driver
		driver = new ChromeDriver();	
	}
	
	@AfterEach
	public void endTest() {
		report.endTest(test);
	}
	
	@AfterAll
	public static void afterAll() {
		//closes the chrome driver
		driver.quit();
		
		//cleanup extent Reports
		report.flush();
		report.close();
	}
	
	@Test
	 void indexcreateToDo() {
		
		test=report.startTest("Create A To-do List");
		
		//Given that i can access the index page
		driver.get(URL);
		//when i enter the title for a new todolist
		targ=driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/input"));
		targ.sendKeys("House Chores");
		// and click the button
		targ=driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[1]/button"));
		targ.click();
		//then the text should appear with an ID that lets us know its created.
		targ=driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div"));
		boolean result = targ.isDisplayed();
		
		if(result==true) {
			test.log(LogStatus.PASS, "Created a To-Do list");
		}else {
			test.log(LogStatus.FAIL,"Failed create to-do");
		}
		assertEquals(true,result);
		
	}
	
	@Test
	 void indexcreateTask() {
		test=report.startTest("Create Tasks");
		//Given that i can access the index page
		driver.get(URL);
		//when i enter the descriptions for the tasks
		targ=driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/input[1]"));
		targ.sendKeys("Sky Diving");
		targ=driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/input[2]"));
		targ.sendKeys("Travel World");
		targ=driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/input[3]"));
		targ.sendKeys("Disney Land");
		//and enter the ID of the todo list
		targ=driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/div[2]/input"));
		targ.sendKeys("3");
		// and click the button
		targ=driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/button"));
		targ.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//then the text should appear to let us know the tasks are created.
		targ=driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/h4"));
		String result = targ.getText();
		
		if(result.equals("Successfully added! Now head to create or read.")) {
			test.log(LogStatus.PASS, "Created some tasks");
		}else {
			test.log(LogStatus.FAIL,"Failed create tasks");
		}
		assertEquals("Successfully added! Now head to create or read.",result);
	}
	
}
