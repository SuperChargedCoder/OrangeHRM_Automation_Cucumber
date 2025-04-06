package com.OrangeHRM.TestNG_Cucumber_Runner;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class AccessGenerator {
	
	@Test
	public void GenerateIdPassword () {
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.xpath("//span[normalize-space()='PIM']")).click();
		driver.findElement(By.xpath("//button[normalize-space()='Add']")).click();
		driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys("Shubham");
		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("Chaurasia");
		driver.findElement(By.xpath("//span[@class='oxd-switch-input oxd-switch-input--active --label-right']")).click();
		driver.findElement(By.xpath("//label[text()='Username']/following::input[1]")).sendKeys("shubham");
		driver.findElement(By.xpath("//label[text()='Password']/following::input[1]")).sendKeys("Testing@123");
		driver.findElement(By.xpath("//label[text()='Confirm Password']/following::input[1]")).sendKeys("Testing@123");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		driver.close();
		
	}
}
