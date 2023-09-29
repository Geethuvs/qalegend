package com.qalegend.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.qalegend.automationcore.Base;
import com.qalegend.pages.HomePage;
import com.qalegend.pages.LoginPage;
import com.qalegend.pages.UserManagementPage;
import com.qalegend.reports.TestListener;
import com.qalegend.utilities.ExcelUtility;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class UserManagementTest extends Base {
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    @Test(groups = "Regression")
    public void verifyUserManagementPanelData(){
        extentTest.get().assignCategory("Sanity");
        List<List<String>> data= ExcelUtility.excelDataReader("LoginPage");
        String userName=data.get(1).get(1);
        String password=data.get(2).get(1);
        List<List<String>> panelData= ExcelUtility.excelDataReader("UserManagement");
        String panelValues=panelData.get(0).get(1);
        LoginPage login=new LoginPage(driver);
        HomePage home=login.loginToApplication(userName,password);
        UserManagementPage userManagement=home.clickOnUserManagementPanel();
        List<String> expectedPanelValues=userManagement.getSplitedPanelValues(panelValues);
        List<String> actualPanelValues=userManagement.getUserManagementPanelValues();
        Assert.assertEquals(actualPanelValues,expectedPanelValues,"Required panel values not under the list");
    }
}
