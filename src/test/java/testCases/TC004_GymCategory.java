package testCases;

import java.io.IOException;

import org.testng.annotations.Test;


import pageObjects.GymPage;
import pageObjects.GymandSearchPage;
import utilityFiles.ScreenShots;

public class TC004_GymCategory extends TC003_FreeListing{
	
	@Test(priority=7,groups= {"regression","master"})
	public void clickGym() {
		GymandSearchPage gsp = new GymandSearchPage(driver);
		gsp.selectGym();
	}
	
	@Test(priority =8,groups= {"regression","master"})
	public void displayCategories() throws InterruptedException, IOException {
		GymPage gp = new GymPage(driver);
		ScreenShots sc = new ScreenShots(driver);
		gp.clickSubCategory();
		Thread.sleep(3000);
		logger.info("--//Gym Sub Categories taken//--");
		sc.screenshot("GymSubCategories");
		gp.subCategories();
		Thread.sleep(2000);
	}

}
