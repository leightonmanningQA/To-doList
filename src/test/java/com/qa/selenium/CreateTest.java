package com.qa.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CreateTest {
	private static RemoteWebDriver driver;
	private final String URL = "http://localhost:8082/create.html";
	private static WebElement targ;
	private static List<WebElement> targList;
	
	@BeforeAll
	public static void beforeAll() {
		//system.property
		System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
		//driver
		driver = new ChromeDriver();	
		driver.manage().window().maximize();
	}
	
	@AfterAll
	public static void afterAll() {
		//closes the chrome driver
		driver.quit();
	}
	@Test
	public void createToDo() {
		//Given that i can access the index page
		driver.get(URL);
		//when i enter the title for a new todolist
		targ=driver.findElement(By.id("ToDoTitle"));
		targ.sendKeys("Important List");
		// and click the button
		targ=driver.findElement(By.id("button-addon2"));
		targ.click();
		//then the text should appear with an ID that lets us know its created.
		targ=driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/h4"));
		boolean result = targ.isDisplayed();
		
		assertEquals(true,result);
	}
	@Test
	public void createTask() {
		//Given that i can access the index page
		driver.get(URL);
		//when i enter the descriptions for the tasks
		targ=driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/input[1]"));
		targ.sendKeys("Sky Diving");
		targ=driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/input[2]"));
		targ.sendKeys("Travel World");
		targ=driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/input[3]"));
		targ.sendKeys("Disney Land");
		//and enter the ID of the todo list
		targ=driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/input[4]"));
		targ.sendKeys("3");
		// and click the button
		targ=driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/button"));
		targ.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//then the text should appear to let us know the tasks are created.
		targ=driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/h4"));
		String result = targ.getText();
		
		assertEquals("Successfully added! Add more or head to read!",result);
	}
	@Test
	public void updateToDo() {
		//Given that i can access the index page
		driver.get(URL);
		//when i enter the id for a todolist
		targ=driver.findElement(By.id("inputid2"));
		targ.sendKeys("3");
		// and i enter the new title for the todo list
		targ=driver.findElement(By.id("todoinput2"));
		targ.sendKeys("Updated ToDo List");
		//and i press the update button
		targ=driver.findElement(By.id("updateToDobtn"));
		
		targ.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//then the text should appear with an ID that lets us know its created.
		targ=driver.findElement(By.xpath("/html/body/div[2]/div/div[4]/div[1]/h4"));
		boolean result = targ.isDisplayed();
		
		assertEquals(true,result);
	}
	
	@Test
	public void updateTask() {
		//Given that i can access the index page
		driver.get(URL);
		//when i enter the title for a new task
		targ=driver.findElement(By.id("inputtaskid"));
		targ.sendKeys("1");
		// and i enter the new description for the task
		targ=driver.findElement(By.id("updatetaskdesc"));
		targ.sendKeys("Updated Task");
		// and i enter the id for the todo list
		targ=driver.findElement(By.id("updatetasktodoid"));
		targ.sendKeys("2");
		//and i press the update button
		targ=driver.findElement(By.xpath("/html/body/div[2]/div/div[4]/button[2]"));
		targ.click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//then the text should appear with an ID that lets us know its created.
		targ=driver.findElement(By.id("updateTaskbtn"));
		boolean result = targ.isDisplayed();
		
		assertEquals(true,result);
	}
}
