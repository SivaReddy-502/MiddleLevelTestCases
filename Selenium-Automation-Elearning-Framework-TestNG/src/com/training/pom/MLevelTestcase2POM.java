package com.training.pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class MLevelTestcase2POM {
	private WebDriver driver; 
	
	public MLevelTestcase2POM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="input-username")
	private WebElement userName; 
	
	@FindBy(id="input-password")
	private WebElement password;
	
	@FindBy(xpath="//*[@class='btn btn-primary' and @type='submit']")
	private WebElement loginBtn; 
		
	//Catlog options
	@FindBy(xpath="//*[@id='catalog']/ul/li[1]/a")
	private WebElement Catloglink;
	
	//Products link
	@FindBy(xpath="//*[@id='catalog']/ul/li[2]/a")
	private WebElement Productlink;
	
	//product name
	@FindBy(id="input-name")
	private  WebElement productname;
	
	//price
	@FindBy(id="input-price")
	private WebElement price;
	
	//status - Enabled/Disabled
	@FindBy(name="filter_status")
	private WebElement status;
	
	//Model
	@FindBy(id="input-model")
	private WebElement Model;
	
	//Quantity
	@FindBy(id="input-quantity")
	private WebElement Quantity;
	
	//Filter
	@FindBy(id="button-filter")
	private WebElement Filter;
	
	
	public void sendUserName(String userName) {
		this.userName.clear();
		this.userName.sendKeys(userName);
	}
	
	public void sendPassword(String password) {
		this.password.clear(); 
		this.password.sendKeys(password); 
	}
	
	public void clickLoginBtn() {
		this.loginBtn.click(); 
	}
		
	public void clickCatlogOptions() {
		this.Catloglink.click();
	}
	
	public void clickProductsLink() {
		this.Productlink.click();
	}
	
	public void sendProductName(String productname) {
		this.productname.clear();
		this.productname.sendKeys(productname);
	}
	
	public void sendPrice(String price) {
		this.price.clear();
		this.price.sendKeys(price);
	}
	
	public void clickStatus() {
		this.status.click();
	}
	
	public void SendModel(String model) {
		this.Model.clear();
		this.Model.sendKeys(model);
	}
	
	public void sendQuantity(String quantity) {
		this.Quantity.clear();
		this.Quantity.sendKeys(quantity);
	}
	
	public void clickFilterBtn() {
		this.Filter.click();
	}
	
}




