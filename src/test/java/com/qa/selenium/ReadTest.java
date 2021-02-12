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
import java.util.concurrent.TimeUnit;

public class ReadTest {
	private static RemoteWebDriver driver;
	private final String URL = "http://localhost:8082/read.html";
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
	public void readAllTodo() {
		//Given that i can access the index page
		driver.get(URL);
		//and click the read all button
		targ=driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/button"));
		targ.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//then the text should appear with all the current to-do lists.
		targ=driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/h3[1]"));
		boolean result = targ.isDisplayed();
		
		assertEquals(true,result);
	}
	@Test
	public void readOneTodo() {
		//Given that i can access the read page
		driver.get(URL);
		//and enter the ID of the To-Do list i want to read.
		targ=driver.findElement(By.id("ToDolistid"));
		targ.sendKeys("2");
		//and click the read button
		targ=driver.findElement(By.id("button-addon2"));
		targ.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//then the text should appear with the to-do list you searched in this case 2.
		targ=driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/h3[1]"));
		boolean result = targ.isDisplayed();
		
		assertEquals(true,result);
	}
	@Test
	public void deleteTodo() {
		//Given that i can access the read page
		driver.get(URL);
		//and enter the ID of the To-Do list i want to delete.
		targ=driver.findElement(By.id("deletetodoid"));
		targ.sendKeys("1");
		//and click the delete button
		targ=driver.findElement(By.id("button-addon3"));
		targ.click();
		
		//then the text should appear saying successfully deleted.
		targ=driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div[1]"));
		String result = targ.getText();
		
		assertEquals("Successfully Deleted",result);
	}
	@Test
	public void deleteTask() {
		//Given that i can access the read page
		driver.get(URL);
		//and enter the ID of the Task i want to delete.
		targ=driver.findElement(By.id("deletetaskid"));
		targ.sendKeys("3");
		//and click the delete button
		targ=driver.findElement(By.id("button-addon4"));
		targ.click();
		
		//then the text should appear saying successfully deleted.
		targ=driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div[2]/h3"));
		String result = targ.getText();
		
		assertEquals("Successfully Deleted",result);
	}
}
