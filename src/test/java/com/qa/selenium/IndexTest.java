package com.qa.selenium;

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
	public void beforeAll() {
		//system.property
		System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
		//driver
		driver = new ChromeDriver();
		
	}
	
	@AfterAll
	public  void afterAll() {
		//closes the chrome driver
		driver.quit();
	}
	
//	@Test
	public void indexcreate() {
		driver.get(URL);
		targ=driver.findElement(By.xpath(""));
		targ.click();
	}
}
