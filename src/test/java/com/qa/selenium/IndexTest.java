package com.qa.selenium;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IndexTest {
	private static RemoteWebDriver driver;
	private final String URL = "http://localhost:8082/index.html";
	private static WebElement targ;
	private static List<WebElement> targList;
	
	@BeforeAll
	public static void beforeAll() {
		//system.property
		System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
		//driver
		driver = new ChromeDriver();
		
	}
	
	@AfterAll
	public static void afterAll() {
		//closes the chrome driver
		driver.quit();
	}
	
	@Test
	public void indexcreate() {
		//Given that i can access the index page
		driver.get(URL);
		//when i enter the title for a new todolist
		targ=driver.findElement(By.xpath("//*[@id=\"ToDoTitle\"]"));
		targ.sendKeys("House Chores");
		// and click the button
		targ=driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[1]/button"));
		targ.click();
		//then the text should appear with an ID that lets us know its created.
		targ=driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[2]/h3"));
		boolean result = targ.isDisplayed();
		
		assertEquals(true,result);
	}
}
