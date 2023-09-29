package com.qalegend.dataprovider;

import com.qalegend.utilities.ExcelUtility;
import org.testng.annotations.DataProvider;

public class DataProviderManager {
    @DataProvider(name="InvalidUserCredentials")
    public Object[][] readInvalidUserCredentials(){
        ExcelUtility excel=new ExcelUtility();
        Object[][] data=excel.dataProviderRead("InvalidUserCredentials");
        return data;
    }
}
