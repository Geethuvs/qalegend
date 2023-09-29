package com.qalegend.pages;

import com.qalegend.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestHelperUtility {
    WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(id="username")
    WebElement userName;

    @FindBy(id="password")
    WebElement password;

    @FindBy(xpath = "//button[@class='btn btn-primary']")
    WebElement loginButton;

    @FindBy(xpath = "//button[@class='btn btn-default btn-sm']")
    WebElement endTour;

    @FindBy(xpath = "//a[@class='btn btn-link']")
    WebElement forgetPassword;

    @FindBy(xpath = "//span[@class='help-block']//strong")
    WebElement invalidCredentialErrorMessage;

    public String getLoginPageTitle(){
        return webDriver.getTitleOfWebPage(driver);
    }
    public void enterUserName(String uName){
        webElement.enterText(userName,uName);
    }
    public void enterPassword(String pWord){
        webElement.enterText(password,pWord);
    }
    public HomePage clickOnLoginButton(){
        webElement.clickOnElement(loginButton);
        return new HomePage(driver);
    }
    public void clickOnEndTour(){
       webElement.clickOnElement(endTour);
    }

    public ResetPage clickOnForgetYourPassword(){
        webElement.clickOnElement(forgetPassword);
        return new ResetPage(driver);
    }
    public HomePage loginToApplication(String uName,String pWord){
        enterUserName(uName);
        enterPassword(pWord);
        clickOnLoginButton();
        clickOnEndTour();
        return new HomePage(driver);
    }
    public String getLoginErrorMessage(){
        return webElement.getElementText(invalidCredentialErrorMessage);
    }
}
