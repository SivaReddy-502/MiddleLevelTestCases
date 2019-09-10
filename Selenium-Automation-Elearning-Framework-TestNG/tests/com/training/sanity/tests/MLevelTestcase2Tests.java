package com.training.sanity.tests;

import static org.testng.Assert.fail;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.MLevelTestcase2POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class MLevelTestcase2Tests {

	private WebDriver driver;
	private String baseUrl;
	private MLevelTestcase2POM MLevelTestcase2pom;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		MLevelTestcase2pom = new MLevelTestcase2POM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser 
		driver.get(baseUrl);
		
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		//Close the all browsers
		driver.quit();
	}
	@Test
	public void TestCase3() throws InterruptedException {
		
		//enter valid credentials in Username textbox
		MLevelTestcase2pom.sendUserName("admin");
		//enter valid credentials in Password textbox
		MLevelTestcase2pom.sendPassword("admin@123");
		//click on Login button
		MLevelTestcase2pom.clickLoginBtn(); 
		
		//Verifying Dashboard Title for login successful webpage
		String ExpectedTitle="Dashboard";
		String ActualTitle=driver.getTitle();
		if(ActualTitle.contentEquals(ExpectedTitle)) {
			Assert.assertEquals(ActualTitle, ExpectedTitle);
		}
		else {
			Assert.fail();
		}
		
		screenShot.captureScreenShot("First");
		
		System.out.println("Testcase 3 : Step 1 : Click on Catolog icon and display the list of links");
		
		//Move the element to Catlog icon
		Actions action=new Actions(driver);
		WebElement obj=driver.findElement(By.xpath("//*[@id='catalog']/a/i"));
		action.moveToElement(obj).build().perform();
		obj.click();
		
		//Actual list of Catlog links
		String[] Actuallistdata={"Categories", "Products", "Recurring Profiles", "Filters", "Attributes", "Options", "Manufacturers", "Downloads", "Reviews", "Information"};
		String Expectedlistdata;	
		List<WebElement> Catloglinks = driver.findElements(By.xpath("//*[@id='catalog']/ul/li/a"));
		System.out.println(Catloglinks.size());
		//Gettting the list of Catlog links
		for(int i=0;i<Catloglinks.size();i++) {
			Expectedlistdata=Catloglinks.get(i).getText();
			System.out.println(Expectedlistdata);
			System.out.println(Actuallistdata[i]);
			if(Expectedlistdata.equals(Actuallistdata[i])) {
				Assert.assertEquals(Actuallistdata[i], Expectedlistdata);
				System.out.println("The Actual list Data "+ Actuallistdata[i] + " and The Expected data list " + Expectedlistdata + "is same hence the testcase pass");
			}
			else
			{
				Assert.fail();
				System.out.println("test case failed");
			}
		}	
		
		System.out.println("testcase 3: Step 2 : click on products link in Catlog and display the list of Categories");
		
		// click on products link
		MLevelTestcase2pom.clickProductsLink();
		
		String ProductListData;
		List<WebElement> ProductList = driver.findElements(By.xpath("//*[@id='form-product']/div/table/tbody/tr/td"));
		//Getting the list of Categories
		for(int i=0;i<ProductList.size();i++) {
			ProductListData = ProductList.get(i).getText();
			System.out.println(ProductListData);
		}	
		
		//Productname 
		MLevelTestcase2pom.sendProductName("ShirtFullSleeves");
		String Actualproductname=driver.findElement(By.id("input-name")).getAttribute("value");
		System.out.println(Actualproductname);
		String Expectedproductname="ShirtFullSleeves";
		
		if(Actualproductname.contentEquals(Expectedproductname)) {
			Assert.assertEquals(Actualproductname, Expectedproductname);
			System.out.println("product name are equal");
		}
		else {
			Assert.fail();
			System.out.println("product names are not equal");
		}
		
		MLevelTestcase2pom.clickFilterBtn();
		
		
		List<WebElement> productnameList=driver.findElements(By.xpath("//*[@id='form-product']/div/table/tbody/tr/td[3]"));
		for(int i=0;i<productnameList.size();i++) {
			
			String ActualList=productnameList.get(i).getText();
			Assert.assertEquals(ActualList, Expectedproductname);
			System.out.println("Filter prouct names are Equal");
		}
		
		driver.findElement(By.id("input-name")).clear();
		
		//price
		MLevelTestcase2pom.sendPrice("0.0000");
		String Actualprice=driver.findElement(By.id("input-price")).getAttribute("value");
		System.out.println(Actualprice);
		String Expectedprice="0.0000";
		
		if(Actualprice.contentEquals(Expectedprice)) {
			Assert.assertEquals(Actualprice, Expectedprice);
			System.out.println("price are equal");
		}
		else {
			Assert.fail();
			System.out.println("price names are not equal");
		}
		
		MLevelTestcase2pom.clickFilterBtn();
		
		List<WebElement> priceList=driver.findElements(By.xpath("//*[@id='form-product']/div/table/tbody/tr/td[5]"));
		for(int i=0;i<priceList.size();i++) {			
			String ActualpriceList=priceList.get(i).getText();
			Assert.assertEquals(ActualpriceList, Expectedprice);
			System.out.println("Filter prices are Equal");
		}
		
		driver.findElement(By.id("input-price")).clear();
		
		//Status
		MLevelTestcase2pom.clickStatus();
		WebElement status=driver.findElement(By.id("input-status"));
		Select sel=new Select(status);
		
		//select the value from the dropdown
		sel.selectByIndex(1);
		
		String Actualstatus = sel.getFirstSelectedOption().getText();
		System.out.println(Actualstatus);
		
		String Expectedstatus="Enabled";
		
		if(Actualstatus.contentEquals(Expectedstatus)) {
			Assert.assertEquals(Actualstatus, Expectedstatus);
			System.out.println("status are equal");
		}
		else {
			Assert.fail();
			System.out.println("status are not equal");
		}
		
		MLevelTestcase2pom.clickFilterBtn();
		
		List<WebElement> statusList=driver.findElements(By.xpath("//*[@id='form-product']/div/table/tbody/tr/td[7]"));
		for(int i=0;i<statusList.size();i++) {			
			String ActualstatusList=statusList.get(i).getText();
			Assert.assertEquals(ActualstatusList, Expectedstatus);
			System.out.println("Filter status are Equal");
		}

		//Model		
		MLevelTestcase2pom.SendModel("TBSM");
		String ActualpModel=driver.findElement(By.id("input-model")).getAttribute("value");
		System.out.println(ActualpModel);
		String ExpectedModel="TBSM";
		
		if(ActualpModel.contentEquals(ExpectedModel)) {
			Assert.assertEquals(ActualpModel, ExpectedModel);
			System.out.println("Model are equal");
		}
		else {
			Assert.fail();
			System.out.println("Model are not equal");
		}
		
		MLevelTestcase2pom.clickFilterBtn();
		
		List<WebElement> ModelList=driver.findElements(By.xpath("//*[@id='form-product']/div/table/tbody/tr/td[4]"));
		for(int i=0;i<ModelList.size();i++) {			
			String ActualmodelList=ModelList.get(i).getText();
			Assert.assertEquals(ActualmodelList, ExpectedModel);
			System.out.println("Filter Model are Equal");
		}
		
		driver.findElement(By.id("input-model")).clear();
	
		//Quantity	
		MLevelTestcase2pom.sendQuantity("1");
		String ActualpQuantity=driver.findElement(By.id("input-quantity")).getAttribute("value");
		System.out.println(ActualpQuantity);
		String ExpectedQuantity="1";
		
		if(ActualpQuantity.contentEquals(ExpectedQuantity)) {
			Assert.assertEquals(ActualpQuantity, ExpectedQuantity);
			System.out.println("Quantity are equal");
		}
		else {
			Assert.fail();
			System.out.println("Quantity are not equal");
		}
		
		MLevelTestcase2pom.clickFilterBtn();
		
		List<WebElement> QuantityList=driver.findElements(By.xpath("//*[@id='form-product']/div/table/tbody/tr/td[6]"));
		for(int i=0;i<QuantityList.size();i++) {			
			String ActualmodelList=QuantityList.get(i).getText();
			Assert.assertEquals(ActualmodelList, ExpectedQuantity);
			System.out.println("Filter quantity are Equal");
		}
		
		driver.findElement(By.id("input-quantity")).clear();
		
		
		
	}
}	



