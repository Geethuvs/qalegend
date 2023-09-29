package com.qalegend.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.qalegend.automationcore.Base;
import com.qalegend.reports.TestListener;

public class SignOutTest extends Base {
    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();
}
