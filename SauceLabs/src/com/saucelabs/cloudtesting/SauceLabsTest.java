package com.saucelabs.cloudtesting;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SauceLabsTest {

	public static final String USERNAME = "adityan";
	public static final String ACCESS_KEY = "c84b73f2-35e4-43ac-9dc6-e457d3baa456";
	public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";
	WebDriver driver;

	@BeforeTest
	public void Setup() throws Exception {
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("name", "Valid_Login_Page");
		dc.setCapability("platform", "Windows 7");
		dc.setCapability("browserName", "Firefox");
		dc.setCapability("version", "61");
		dc.setCapability("build", "Selenium_Saucelabs_1");
		driver = new RemoteWebDriver(new URL(URL), dc);

	}

	@Test
	public void testcase1() {
		driver.get("http://www.amazon.in/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		System.out.println(driver.getTitle());
		
		Actions acts=new Actions(driver);
		WebElement hover=driver.findElement(By.cssSelector("#nav-link-yourAccount"));
		acts.moveToElement(hover).build().perform();
		
		//searching some stuffs in capital letters
		WebElement searchfield=driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		searchfield.sendKeys(Keys.SHIFT,"pc games");
		driver.findElement(By.xpath(".//*[@id='nav-search']/form/div[2]/div/input")).click();
		
		//right click on the text field
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		
		WebElement product= driver.findElement(By.xpath(".//*[@id='nav-search']/form/div[2]/div/input"));
		acts.contextClick(product).build().perform();
	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}
}
