package PageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ApplicationTest.Test.PropertyCollection;
import ApplicationTest.Test.SearchData;

public class HomePage {
	
	public  void HomePageHotelSearch()
	{
		PageFactory.initElements(PropertyCollection.driver,this);
	}	
	

	@FindBy(xpath = "//div[@id='select2-drop']//input[@type='text']")
	private static WebElement HotelsAutoSuggestTxtBox;

	@FindBy(xpath = "//input[@placeholder='Password']")
	private static WebElement passwordTextBox;

	@FindBy(xpath="//div[@class='select2-drop select2-display-none select2-with-searchbox select2-drop-active']//ul[@class='select2-results']//ul[@class='select2-result-sub']//li[1]")
	private static WebElement autoSuggestion;
	
	public void HotelsSearch() throws InterruptedException
	{
		Thread.sleep(5000);
		HotelsAutoSuggestTxtBox.sendKeys(SearchData.HotelName);
		HotelsAutoSuggestTxtBox.sendKeys(Keys.ENTER);
		WebDriverWait wait = new WebDriverWait(PropertyCollection.driver, 10);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='select2-drop select2-display-none select2-with-searchbox select2-drop-active']//ul[@class='select2-results']//ul[@class='select2-result-sub']//li[1]")));
		autoSuggestion.click();
		 
		
	}
	
	

}
