package com.qalegend.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.qalegend.automationcore.Base;
import com.qalegend.pages.*;
import com.qalegend.reports.TestListener;
import com.qalegend.utilities.CommandUtility;
import com.qalegend.utilities.ExcelUtility;
import com.qalegend.utilities.RandomDataUtility;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.bouncycastle.crypto.tls.ContentType.alert;

public class ManageUsersTest extends Base {
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
    LoginPage login;
    HomePage home;
    UserManagementPage userManagement;
    ManageUsersPage manageUser;
    AddUsersPage addUsers;
    String prefix=RandomDataUtility.getPrefix();
    String firstName=RandomDataUtility.getFirstName();
    String lastName=RandomDataUtility.getLastName();
    String email=RandomDataUtility.getEmail();
    String uName=firstName.toLowerCase()+lastName.toLowerCase();
    String passWord=RandomDataUtility.getPassword();
    String salesCommissionPercentage=RandomDataUtility.getRandomNumber();
    @Test(groups = "Regression")
    public void verifyInvalidUserSearch(){
        extentTest.get().assignCategory("Regression");
        String email=RandomDataUtility.getEmail();
        List<List<String>> data= ExcelUtility.excelDataReader("LoginPage");
        String userName=data.get(1).get(1);
        String password=data.get(2).get(1);
        List<List<String>> invalidEmailErrorMessage= ExcelUtility.excelDataReader("ManageUsers");
        String errorMessage=invalidEmailErrorMessage.get(0).get(1);
        login=new LoginPage(driver);
        home=login.loginToApplication(userName,password);
        userManagement=home.clickOnUserManagementPanel();
        manageUser=userManagement.clickOnUsers();
        manageUser.enterValueInSearchField(email);
        String actualErrorMessage=manageUser.getErrorMessage();
        Assert.assertEquals(actualErrorMessage,errorMessage,"User not exist");
    }
    @Test(groups = "Regression")
    public void verifyUserDelete(){
        extentTest.get().assignCategory("Regression");
        List<List<String>> data= ExcelUtility.excelDataReader("LoginPage");
        String userName=data.get(1).get(1);
        String password=data.get(2).get(1);
        List<List<String>> invalidEmailErrorMessage= ExcelUtility.excelDataReader("ManageUsers");
        String errorMessage=invalidEmailErrorMessage.get(0).get(1);
        login=new LoginPage(driver);
        home=login.loginToApplication(userName,password);
        userManagement=home.clickOnUserManagementPanel();
        manageUser=userManagement.clickOnUsers();
        addUsers=manageUser.clickOnAddButton();
        manageUser=addUsers.addUserDetails(prefix,firstName,lastName,email,uName,passWord,passWord,salesCommissionPercentage);
        manageUser.enterValueInSearchField(email);
        manageUser.clickOnDeleteButton();
        manageUser.acceptUserDeletionPopup();
        manageUser.enterValueInSearchField(email);
        String actualErrorMessage=manageUser.getErrorMessage();
        Assert.assertEquals(actualErrorMessage,errorMessage,"Unable to delete the user");
    }
}
