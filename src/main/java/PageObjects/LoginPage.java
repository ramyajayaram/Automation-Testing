package PageObjects;



import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ApplicationTest.Test.PropertyCollection;


	class ExcelTest {
		public FileInputStream fis = null;
		public XSSFWorkbook workbook = null;
		public XSSFSheet sheet = null;
		public XSSFRow row = null;
		public XSSFCell cell = null;

		public ExcelTest(String xlFilePath) throws Exception {
			fis = new FileInputStream(xlFilePath);
			workbook = new XSSFWorkbook(fis);
			fis.close();
		}

		public String getCellData(String sheetName, String colName, int rowNum) {
			int col_Num = -1;
			try {

				sheet = workbook.getSheet(sheetName);
				row = sheet.getRow(0);
				for (int i = 0; i < row.getLastCellNum(); i++) {
					if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
						col_Num = i;
				}

				row = sheet.getRow(rowNum - 1);
				cell = row.getCell(col_Num);

				if (cell.getCellTypeEnum() == CellType.STRING)
					return cell.getStringCellValue();
				else if (cell.getCellTypeEnum() == CellType.NUMERIC || cell.getCellTypeEnum() == CellType.FORMULA) {
					String cellValue = String.valueOf(cell.getNumericCellValue());
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						DateFormat df = new SimpleDateFormat("dd/MM/yy");
						Date date = cell.getDateCellValue();
						cellValue = df.format(date);
					}
					return cellValue;
				} else if (cell.getCellTypeEnum() == CellType.BLANK)
					return "";
				else
					return String.valueOf(cell.getBooleanCellValue());
			} catch (Exception e) {
				e.printStackTrace();
				return "row " + rowNum + " or column " + col_Num + " does not exist  in Excel";
			}
		}
	}

	public class LoginPage
	{
		public LoginPage() {
			PageFactory.initElements(PropertyCollection.driver,this);
		}


	
		static String Email;

		static String Password;

		@FindBy(xpath = "//input[@placeholder='Email']")
		private static WebElement emailTextBox;

		@FindBy(xpath = "//input[@placeholder='Password']")
		private static WebElement passwordTextBox;

		@FindBy(xpath = "//button[contains(text(),'Login')]")
		private WebElement LoginBtn;
		
		@FindBy(xpath = "//li[@class='open']//a[@class='dropdown-toggle go-text-right']")
		private WebElement LogOutMenu;
		
		
		@FindBy(xpath = "//li[@class='open']//ul[@class='dropdown-menu']//li//a[@class='go-text-right'][contains(text(),'Logout')]")
		private WebElement LogOutSubmenu;
		
		
		
		
		

		public void Login() {
			final String driverPath = "C:\\Users\\Ramya\\eclipse-workspace\\ApplicationTest\\Drivers\\";

			PropertyCollection.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			ExcelTest objExcelData = null;
			try {
				objExcelData = new ExcelTest("C:\\Users\\Ramya\\Downloads\\Login.xlsx");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Email = objExcelData.getCellData("Sheet1", "Email", 2);
			Password = objExcelData.getCellData("Sheet1", "Password", 2);
			emailTextBox.sendKeys(Email);
			passwordTextBox.sendKeys(Password);
			LoginBtn.click();

		}

		public void logout() {
			LogOutMenu.click();	
			LogOutSubmenu.click();
		}

		public void loginFailed() {

		}

		public void forgotPassword() {

		}
	
}


