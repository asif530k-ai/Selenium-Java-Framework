package rahulshettyacademy.TestComponenets;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resorces.ExtentReporterNG;

import org.testng.ITestListener;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;

public class Listeners extends BaseTest implements ITestListener{
	
	ExtentTest test;
	ExtentReports extent=ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	
    @Override
    public void onTestStart(ITestResult result) {
        // Executes when a test starts
    	test=extent.createTest(result.getMethod().getMethodName());
    	extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Executes when a test passes
    	extentTest.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Executes when a test fails
    	//test.log(Status.FAIL, "Test Passed");
    	extentTest.get().fail(result.getThrowable());
    	
    	try {
    		driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	
    	
    
    	String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Executes when a test is skipped
    }

    @Override
    public void onStart(ITestContext context) {
        // Executes before the test context starts
    }

    @Override
    public void onFinish(ITestContext context) {
        // Executes after the test context finishes
    	extent.flush();
    }
}
