package excel;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DDTExcel
{
  ChromeDriver driver;
  
   
 @Test(dataProvider="testdata")
 public void DemoProject(String username, String password) throws InterruptedException
 {
	 System.setProperty("webdriver.chrome.driver", "D:\\Java Practice programs\\Data Driven using Excel\\chromedriver.exe");
	 driver = new ChromeDriver();
	 driver.get("https://www.facebook.com/");
	 
	 driver.findElement(By.id("email")).sendKeys(username);
	 driver.findElement(By.id("pass")).sendKeys(password);
	 driver.findElement(By.id("u_0_2")).click();
	 
	 /*String title = driver.getTitle();
	 System.out.println(title);*/
	 
	 Thread.sleep(2000);
	 Assert.assertTrue("Invalid Credentials",driver.getTitle().matches(""));
	 System.out.println("Login Successfully");
	 
   }
 
   @AfterMethod
   public void cloaseBrowser()
   {
	   driver.quit();
   }
  
   
   
   @DataProvider(name="testdata")
   public Object[][] testDataFeed()
   {
	  
	   ReadExcelFile config = new ReadExcelFile("D:\\Java Practice programs\\Data Driven using Excel\ExcelFile\\LoginCredential.xlsx");
	   
	   int row = config.getRowCount(0);
	   Object[][] credentials = new Object[row][2];
	   
	   for(int i=0;i<row;i++)
	   {
		   credentials[i][0] =config.getData(0,i,0);
		   credentials[i][1] = config.getData(1,i,1);
	   }
	   return credentials;
   }
   
}
