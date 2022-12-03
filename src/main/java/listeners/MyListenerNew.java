package listeners;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import utils.DateTimeUtilities;

import java.io.File;


public class MyListenerNew extends TestListenerAdapter {

    public static ExtentSparkReporter sparkReporter;
    public static ExtentReports extentReports;
    public static ExtentTest extentTest;

    public void onStart(ITestContext iTestContext) {
        sparkReporter = new ExtentSparkReporter(new File(System.getProperty("user.dir") + "/Reports/AutomationReport" + DateTimeUtilities.currentSystemDate("_dd-MM-YYYY-HH-mm-ss") + ".html"));
        sparkReporter.config().setDocumentTitle("Test Execution Summary Report");
        sparkReporter.config().setReportName("Test Execution Summary Report");
        sparkReporter.config().setTheme(Theme.STANDARD);

        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        extentReports.setSystemInfo("HostName", "LocalHost");
        extentReports.setSystemInfo("Automation Flow", "Automation Practice");
        extentReports.setSystemInfo("Tester Name", "Robin");

        System.out.println("Execution of test cases has started");

    }
    public void onTestStart(ITestResult r) {
        System.out.println("Test started");
        extentTest = extentReports.createTest(r.getMethod().getMethodName());
        System.out.println("Test Started: " + r.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult r) {
        System.out.println("Test passed");
        extentTest.log(Status.PASS, r.getMethod().getMethodName());
        System.out.println("Test Passed: " + r.getMethod().getMethodName());
    }

    public void onTestFailure(ITestResult r) {
        System.out.println("Test failed");
        extentTest.log(Status.FAIL, r.getMethod().getMethodName());
        extentTest.log(Status.FAIL, "Test Case Failed and Exception is: " + r.getThrowable());
        System.out.println("Test Failed: " + r.getMethod().getMethodName());
    }

    public void onTestSkipped(ITestResult r) {
        System.out.println("Test skipped");
        extentTest.log(Status.SKIP, r.getMethod().getMethodName());
        System.out.println("Test Skipped: " + r.getMethod().getMethodName());
    }

    public void onFinish(ITestContext iTestContext) {
        System.out.println("Execution of test cases are finished");
        extentReports.flush();
    }
}
