package io.github.mung.listeners;

import io.github.mung.constants.GlobalVars;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


public class Retry implements IRetryAnalyzer {

    private int count = 0;
    private static int maxTry =Integer.parseInt(GlobalVars.RETRY_TEST_FAIL);  // Run the failed test 2 times

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            if(count < maxTry) {
                count++;
                iTestResult.setStatus(ITestResult.FAILURE);
                return true;
            } else {
                iTestResult.setStatus(ITestResult.FAILURE);
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }

}
