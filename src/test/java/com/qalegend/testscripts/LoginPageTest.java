package com.qalegend.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qalegend.automationcore.Base;
import com.qalegend.dataprovider.DataProviderManager;
import com.qalegend.pages.HomePage;
import com.qalegend.pages.LoginPage;
import com.qalegend.reports.TestListener;
import com.qalegend.retryanalyzer.RetryAnalyzer;
import com.qalegend.utilities.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class LoginPageTest extends Base {
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(retryAnalyzer = RetryAnalyzer.class,groups ={"Smoke"} )
    public void verifyLoginPageTitle(){
        extentTest.get().assignCategory("Smoke");
        LoginPage login=new LoginPage(driver);
        String actualTitle=login.getLoginPageTitle();
        extentTest.get().log(Status.PASS,"Login page title received as "+actualTitle);
        List<List<String>> data= ExcelUtility.excelDataReader("LoginPage");
        String expectedTitle=data.get(0).get(1);
        Assert.assertEquals(actualTitle,expectedTitle,"Title not matching");
        extentTest.get().log(Status.PASS,"Expected login page title"+expectedTitle+" Matched with actual title "+actualTitle);
    }
    @Test(groups = {"Regression"})
    public void verifyUserLogin(){
        extentTest.get().assignCategory("Regression");
        List<List<String>> data= ExcelUtility.excelDataReader("LoginPage");
        String userName=data.get(1).get(1);
        String password=data.get(2).get(1);
        String userAccountName=data.get(3).get(1);
        LoginPage login=new LoginPage(driver);
        HomePage home=login.loginToApplication(userName,password);
        String actualUserAccountName=home.getUserAccountName();
        Assert.assertEquals(actualUserAccountName,userAccountName,"User login failed");
    }
    @Test(dataProvider = "InvalidUserCredentials",dataProviderClass = DataProviderManager.class,groups = "Smoke")
    public void verifyInvalidUserCredentials(String userName,String password){
        extentTest.get().assignCategory("Smoke");
        List<List<String>> data= ExcelUtility.excelDataReader("LoginPage");
        String LoginErrorMessage=data.get(6).get(1);
        LoginPage login=new LoginPage(driver);
        login.enterUserName(userName);
        login.enterPassword(password);
        login.clickOnLoginButton();
        String actualLoginErrorMessage= login.getLoginErrorMessage();
        Assert.assertEquals(actualLoginErrorMessage,LoginErrorMessage,"Login failed");
    }
}
