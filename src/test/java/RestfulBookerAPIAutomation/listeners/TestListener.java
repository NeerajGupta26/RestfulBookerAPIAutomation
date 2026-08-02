package listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import reports.ExtentManager;

public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);

    private static final ExtentReports extent = ExtentManager.getInstance();
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        logger.info("========== Test Suite Started : {} ==========",
                context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("========== Test Suite Finished : {} ==========",
                context.getName());

        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {

        logger.info("STARTED : {}", result.getMethod().getMethodName());

        test.set(extent.createTest(result.getMethod().getMethodName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        logger.info("PASSED : {}", result.getMethod().getMethodName());

        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        logger.error("FAILED : {}", result.getMethod().getMethodName());
        logger.error("Reason : ", result.getThrowable());

        test.get().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        logger.warn("SKIPPED : {}", result.getMethod().getMethodName());

        test.get().skip("Test Skipped");
    }
}