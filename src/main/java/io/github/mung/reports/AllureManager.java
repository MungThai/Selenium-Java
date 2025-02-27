package io.github.mung.reports;

import com.github.automatedowl.tools.AllureEnvironmentWriter;
import com.google.common.collect.ImmutableMap;
import io.github.mung.constants.GlobalVars;
import io.github.mung.drivers.DriverManager;
import io.github.mung.enums.Browser;
import io.github.mung.helpers.FileHelpers;
import io.github.mung.utils.BrowserInfoUtils;
import io.github.mung.utils.LogUtils;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static io.github.mung.constants.GlobalVars.EXPORT_VIDEO_PATH;
import static org.openqa.selenium.OutputType.BYTES;

public class AllureManager {
    private AllureManager() {
    }

    public static void setAllureEnvironmentInformation() {
        AllureEnvironmentWriter.allureEnvironmentWriter(
                ImmutableMap.<String, String>builder().
                    //    put("Test URL", FrameworkConstants.URL_CRM).
                        put("Target Execution", GlobalVars.TARGET).
                        put("Global Timeout", String.valueOf(GlobalVars.WAIT_DEFAULT)).
                        put("Page Load Timeout", String.valueOf(GlobalVars.WAIT_PAGE_LOADED)).
                        put("Headless Mode", GlobalVars.HEADLESS).
                        put("Local Browser", String.valueOf(Browser.CHROME)).
                        put("Remote URL", GlobalVars.REMOTE_URL).
                        put("Remote Port", GlobalVars.REMOTE_PORT).
                        build());

        System.out.println("Allure Reports is installed.");

    }

    @Attachment(value = "Failed test Screenshot", type = "image/png")
    public static byte[] takeScreenshotToAttachOnAllureReport() {
        try {
            return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(BYTES);
        } catch (Exception ex) {
            ex.getMessage();
        }
        return new byte[0];
    }

    @Attachment(value = "Step Screenshot", type = "image/png")
    public static byte[] takeScreenshotStep() {
        try {
            return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(BYTES);
        } catch (Exception ex) {
            ex.getMessage();
        }
        return new byte[0];
    }

    @Attachment(value = "Browser Information", type = "text/plain")
    public static String addBrowserInformationOnAllureReport() {
        return BrowserInfoUtils.getOSInfo();
    }


    //Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    //HTML attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    public static void addAttachmentVideoAVI() {
        if (GlobalVars.VIDEO_RECORD.toLowerCase().trim().equals(GlobalVars.YES)) {
            try {
                //Get file Last Modified in folder
                File video = FileHelpers.getFileLastModified(EXPORT_VIDEO_PATH);
                if (video != null) {
                    Allure.addAttachment("Video record AVI", "video/avi", new FileInputStream(video), "avi");
                } else {
                    LogUtils.warn("Video record not found.");
                    LogUtils.warn("Can not attachment Video in Allure report");
                }

            } catch (IOException e) {
                LogUtils.error("Can not attachment Video in Allure report");
                e.printStackTrace();
            }
        }
    }

    public static void addAttachmentVideoMP4() {
        try {
            //Get file Last Modified in folder
            File video = FileHelpers.getFileLastModified(EXPORT_VIDEO_PATH);
            //File video = new File("exportdata/vFideos/SampleVideo.mp4");
            if (video != null) {
                Allure.addAttachment("Video record MP4", "video/mp4", new FileInputStream(video), "mp4");
            } else {
                LogUtils.warn("Video record not found.");
                LogUtils.warn("Can not attachment Video in Allure report");
            }

        } catch (IOException e) {
            LogUtils.error("Can not attachment Video in Allure report");
            e.printStackTrace();
        }
    }
}
