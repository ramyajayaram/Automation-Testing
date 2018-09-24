package PageObjects;

import static org.testng.AssertJUnit.assertEquals;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import ApplicationTest.Test.PropertyCollection;


public class BookHotelPage {

	public static final String  RENDEZVOUS_HOTEL_TITLE="Rendezvous Hotels";

	
public  void BookHotelPage()
{
	PageFactory.initElements(PropertyCollection.driver,this);
}


@FindBy(xpath = "//a[@href='https://www.phptravels.net/hotels']")
private static WebElement hotelMenu;

@FindBy(xpath = "//b[contains(text(),'Rendezvous Hotels')]")
private static WebElement RendezvousHotelshotelName;

@FindBy(xpath="//a[@href='https://www.phptravels.net/hotels/detail/Singapore/Rendezvous-Hotels']//button[@class='btn btn-danger loader loader btn-block'][contains(text(),'Details')]")
private static WebElement DetailsButton;


@FindBy(xpath="//span[contains(text(),'Rendezvous Hotels')]")
private static WebElement RendezvousHotelsTitle;







public void bookHotel() throws InterruptedException
{
	PropertyCollection.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 Actions action = new Actions(PropertyCollection.driver);
	 WebElement element =PropertyCollection. driver.findElement(By.xpath("//a[@title='Hotels']"));

    action.moveToElement(element).build().perform();
    element.click();
  
	PropertyCollection.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	RendezvousHotelshotelName.click();
	PropertyCollection.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	Assert.assertEquals(RendezvousHotelsTitle.getText(),RENDEZVOUS_HOTEL_TITLE);
	
	
	
}






}