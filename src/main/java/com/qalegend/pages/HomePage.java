package com.qalegend.pages;

import com.qalegend.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends TestHelperUtility {
    WebDriver driver;
    public HomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//li[@class='dropdown user user-menu']//a[@class='dropdown-toggle']/span")
    WebElement userAccountName;

    @FindBy(xpath = "//span[text()='User Management']")
    WebElement userManagement;

    public String getUserAccountName(){
        return webElement.getElementText(userAccountName);
    }
    public UserManagementPage clickOnUserManagementPanel(){
        webElement.clickOnElement(userManagement);
        return new UserManagementPage(driver);
    }
    public String getHomePageTitle(){
        return webDriver.getTitleOfWebPage(driver);
    }

}
