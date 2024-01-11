package apitest;

import java.io.File;
import java.util.List;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import io.qameta.allure.Allure;

public class AutomationListener implements ISuiteListener, ITestListener, IInvokedMethodListener {

	public AutomationListener() {
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult iTestResult) {
		if (iTestResult.getStatus() == ITestResult.FAILURE || iTestResult.getStatus() == ITestResult.SKIP) {
			List<String> testLogs = Reporter.getOutput(iTestResult);
			attachLogsInAllureReport(testLogs);
			Reporter.clear();
		}
	}

	private void attachLogsInAllureReport(List testLogs) {
		for (Object testLog : testLogs) {
			Allure.addAttachment("Logs", testLog.toString());
		}
	}

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ISuite iSuite) {

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ISuite iSuite) {

		try {
			File allureResults = new File("./allure-results");

			for (File f : allureResults.listFiles()) {
				if (f.getName().contains(".txt")) {
					f.delete();
				}

				if (f.getName().contains("container.json")) {
					f.delete();
				}

				if (f.getName().contains("result.json")) {
					f.delete();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
	}

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		String testName = iTestResult.getName();
	}

	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		String testName = iTestResult.getName();
	}

	@Override
	public void onTestStart(ITestResult iTestResult) {
		String testName = iTestResult.getName();

	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		String testName = iTestResult.getName();

	}
}