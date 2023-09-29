package com.qalegend.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.qalegend.automationcore.Base;
import com.qalegend.pages.LoginPage;
import com.qalegend.pages.ResetPage;
import com.qalegend.reports.TestListener;
import com.qalegend.utilities.ExcelUtility;
import com.qalegend.utilities.RandomDataUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ResetPageTest extends Base {

    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(groups = "Regression")
    public void verifyForgetPassword(){
        extentTest.get().assignCategory("Regression");
        String email=RandomDataUtility.getEmail();
        List<List<String>> data= ExcelUtility.excelDataReader("LoginPage");
        String resetErrorMessage=data.get(5).get(1);
        LoginPage login=new LoginPage(driver);
        ResetPage reset=login.clickOnForgetYourPassword();
        reset.enterEmailAddress(email);
        reset.clickOnResetButton();
        String actualResetErrorMessage=reset.getResetErrorMessage();
        Assert.assertEquals(actualResetErrorMessage,resetErrorMessage,"Reset failed");
    }
}
