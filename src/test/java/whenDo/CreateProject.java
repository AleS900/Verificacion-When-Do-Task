package whenDo;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Date;

public class CreateProject {

    private AppiumDriver appiumDriver;

    @BeforeEach
    public void openApplication() throws MalformedURLException {
        DesiredCapabilities capabilities= new DesiredCapabilities();
        capabilities.setCapability("deviceName","Galaxy S8");
        capabilities.setCapability("platformVersion","9.0");
        capabilities.setCapability("appPackage","com.vrproductiveapps.whendo");
        capabilities.setCapability("appActivity",".ui.HomeActivity");
        capabilities.setCapability("platformName","Android");

        appiumDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);

        // implicit
        appiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }
    @AfterEach
    public void closeApplication(){
        appiumDriver.quit();
    }

    @Test
    public void verifyProjectCreation() throws InterruptedException {
        Thread.sleep(5000);
        long microSec = new Date().getTime();
        int random = (int) (microSec%10000);
        String name = "Project" + random;
        String dscp = "Description" + random;

        // Add New Project Button
        appiumDriver.findElement(By.id("com.vrproductiveapps.whendo:id/fab")).click();
        // New Project Name Text Box
        appiumDriver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextTitle")).sendKeys(name);
        // New Project Description Text Box
        appiumDriver.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextNotes")).sendKeys(dscp);
        // Save New Project Button
        appiumDriver.findElement(By.id("com.vrproductiveapps.whendo:id/saveItem")).click();
        Thread.sleep(2000);
        // Verify New Project Name
        String actualResult = appiumDriver.findElement(By.xpath("//android.widget.TextView[@text='"+name+"']")).getText();
        Thread.sleep(2000);
        Assertions.assertEquals(name, actualResult, "ERROR");
    }
}

