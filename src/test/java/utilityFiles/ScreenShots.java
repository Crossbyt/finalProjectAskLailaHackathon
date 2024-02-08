package utilityFiles;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import pageObjects.BasePage;
import testBase.BaseClass;

public class ScreenShots extends BasePage {
	
	
	
	public ScreenShots(WebDriver driver) {
		super(driver);
	}
	
	public  String screenshot(String a) throws IOException {
		TakesScreenshot tshot = (TakesScreenshot)driver;
		String filePath=a+".png";
		try {
			File sourceFile = tshot.getScreenshotAs(OutputType.FILE);
		filePath=System.getProperty("user.dir")+"\\Screenshots\\"+a+".png";
			File targetFile = new File(filePath);
			FileUtils.copyFile(sourceFile, targetFile);
		}
		catch(Exception e) {
			e.getMessage();
		}
		return filePath;
 
	}

}
