package selenium.sless_01;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import selenium.sless_01.App;

public class Task {

	protected WebDriver webDriver;

	@BeforeMethod
	public void setup() throws Exception {
		String browserName = System.getProperty("browserName");
		webDriver = App.getInstance(browserName);
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void tearDown() throws Exception {
		if (webDriver != null) {
			App.killDriverInstance();
		}
	}

	@Test
	public void simpleWebDriverTest() throws IOException, InterruptedException {
		Reporter.log("opening gmail.com", true);
		webDriver.get("https://accounts.google.com/ServiceLogin?service=mail&continue=https://mail.google.com/mail/&hl=en");
		Actions seleniumActions = new Actions(webDriver);
		seleniumActions.contextClick(webDriver.findElement(By.xpath("//input[@name='btnI']"))).
				sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).
				build().perform();
		
		Assert.assertTrue(webDriver.getTitle().contains("Lviv"));
	}
}
