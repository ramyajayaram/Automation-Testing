package Suites.Test;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ApplicationTest.Test.PropertyCollection;
import PageObjects.LoginPage;



public class SanitySuite {

	public static class TestBaseSetup {

		
		static final String driverPath = "C:\\Users\\Ramya\\eclipse-workspace\\ApplicationTest\\Drivers\\";

		

		private void setDriver(String browserType, String appURL) {
			switch (browserType) {
			case "chrome":
			PropertyCollection.driver = initChromeDriver(appURL);
				break;
			case "firefox":
				PropertyCollection.driver = initFirefoxDriver(appURL);
				break;
			default:
				System.out
						.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
				PropertyCollection.driver = initFirefoxDriver(appURL);
			}
		}

		private static WebDriver initChromeDriver(String appURL) {
			System.out.println("Launching google chrome with new profile..");
			System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
			PropertyCollection.driver= new ChromeDriver();
			PropertyCollection.driver.manage().window().maximize();
			PropertyCollection.driver.get(appURL);
			return PropertyCollection.driver;
		}

		private static WebDriver initFirefoxDriver(String appURL) {
			System.out.println("Launching Firefox browser..");
			WebDriver driver = new FirefoxDriver();
			PropertyCollection.driver.manage().window().maximize();
			PropertyCollection.driver.navigate().to(appURL);
			return PropertyCollection.driver;
		}

		@Test
		@Parameters({ "browserType", "appURL" })
		
		public void initializeTestBaseSetup(String browserType, String appURL)  {
			try {
				setDriver(browserType, appURL);
				LoginPage objLoginPage=new LoginPage();
				

			} catch (Exception e) {
				System.out.println("Error....." + e.getStackTrace());
			}
		}

		
		
		
	}

}
