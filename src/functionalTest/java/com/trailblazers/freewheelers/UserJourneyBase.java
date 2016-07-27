package com.trailblazers.freewheelers;

import com.trailblazers.freewheelers.apis.AdminApi;
import com.trailblazers.freewheelers.apis.ScreenApi;
import com.trailblazers.freewheelers.apis.UserApi;
import com.trailblazers.freewheelers.pages.NetPromoterScoreReportPage;
import com.trailblazers.freewheelers.pages.NetPromoterScoreSurveyForm;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;

public class UserJourneyBase {

    private static WebDriver driver;

    protected static AdminApi admin;
    protected static UserApi user;
    protected static ScreenApi screen;

    // Specific for NPS Flow
    protected static NetPromoterScoreSurveyForm npsSurveyForm;
    protected static NetPromoterScoreReportPage npsReportPage;

    @BeforeClass
    public static void before() {
        driver = new FirefoxDriver();

        admin = new AdminApi();
        user = new UserApi(driver);
        screen = new ScreenApi(driver);

        // NPS Pages
        npsSurveyForm = new NetPromoterScoreSurveyForm(driver);
        npsReportPage = new NetPromoterScoreReportPage(driver);

        // set screen size
        driver.manage().window().setSize(new Dimension(1400, 600));
    }

    @AfterClass
    public static void after() {
        driver.quit();
    }

    @Rule
    public TestRule testWatcher = new TestWatcher() {
        private String screenshotsDir = System.getProperty("user.dir") + "/build/functionalTests/snapshots/";;

        @Override
        public void failed(Throwable e, Description d) {
            takeScreenshot(d);
        }

        private void takeScreenshot(Description d) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String scrFilename = d.getClassName() + "." + d.getMethodName();
            try {
                FileUtils.forceMkdir(new File(screenshotsDir));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            File screenshotPath = new File(screenshotsDir, scrFilename + ".png");
            try {
                FileUtils.copyFile(scrFile, screenshotPath);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    };
}
