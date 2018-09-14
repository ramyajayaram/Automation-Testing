package PageObjects;

import static org.testng.AssertJUnit.assertEquals;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import ApplicationTest.Test.PropertyCollection;


public class BookHotelPage {

	public static final String  RENDEZVOUS_HOTEL_TITLE="Rendezvous Hotels";

	
public  void BookHotelPage()
{
	PageFactory.initElements(PropertyCollection.driver,this);
}


@FindBy(xpath = "//a[@title='Hotels']")
private static WebElement hotelMenu;

@FindBy(xpath = "//b[contains(text(),'Rendezvous Hotels')]")
private static WebElement RendezvousHotelshotelName;

@FindBy(xpath="//a[@href='https://www.phptravels.net/hotels/detail/Singapore/Rendezvous-Hotels']//button[@class='btn btn-danger loader loader btn-block'][contains(text(),'Details')]")
private static WebElement DetailsButton;


@FindBy(xpath="//span[contains(text(),'Rendezvous Hotels')]")
private static WebElement RendezvousHotelsTitle;



public void bookHotel()
{
	hotelMenu.click();
	PropertyCollection.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	RendezvousHotelshotelName.click();
	PropertyCollection.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	Assert.assertEquals(RendezvousHotelsTitle.getText(),RENDEZVOUS_HOTEL_TITLE);
	
	
	
}






}