package com.qalegend.pages;

import com.qalegend.utilities.TableUtility;
import com.qalegend.utilities.TestHelperUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ManageUsersPage extends TestHelperUtility {
    WebDriver driver;

    public ManageUsersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='btn btn-block btn-primary']")
    WebElement addButton;
    @FindBy(xpath = "//input[@class='form-control input-sm']")
    WebElement searchField;
    @FindBy(xpath = "//tbody//tr[@role='row']")
    List<WebElement> rowElements;
    @FindBy(xpath = "//tbody//tr[@role='row']//td")
    List<WebElement> columnElements;
    @FindBy(xpath = "//a[@class='dropdown-toggle']")
    WebElement signOut;
    @FindBy(xpath = "//td[@class='dataTables_empty']")
    WebElement invalidEmailErrorMessage;
    @FindBy(xpath = "//button[@class='btn btn-xs btn-danger delete_user_button']")
    WebElement deleteButton;

    public AddUsersPage clickOnAddButton() {
        webElement.clickOnElement(addButton);
        return new AddUsersPage(driver);
    }

    public void enterValueInSearchField(String value) {
        webElement.enterText(searchField, value);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<List<String>> getUserDetails(String emailId) {
        wait.fluentWaitForVisibilityOfWebElementLocatedByXpath(driver, "//tbody//tr[@role='row']");
        List<List<String>> userDetails = TableUtility.get_Dynamic_TwoDimension_TablElemnts(rowElements, columnElements);
        return userDetails;
    }
    public List<List<String>> getUserDetails(String prefix,String firstName,String lastName,String userName,String email,String role){
        return page.getUserDataIntoList(prefix,firstName,lastName,userName,email,role);
    }
    public SignOutPage clickOnUserAccountId(){
        //wait.waitForVisibilityOfElementLocatedByXpath(driver,"//a[@class='dropdown-toggle']");
        webElement.clickOnElement(signOut);
        return new SignOutPage(driver);
    }
    public String getErrorMessage(){
        return webElement.getElementText(invalidEmailErrorMessage);
    }
    public void clickOnDeleteButton(){
        webElement.clickOnElement(deleteButton);
    }
}

