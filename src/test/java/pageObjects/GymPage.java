package pageObjects;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilityFiles.ExcelUtils;

public class GymPage extends BasePage {

	public GymPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//div[@id='searchSubCategory']//button") WebElement gymSubCategory;
	@FindBy(xpath = "//div[@id='searchSubCategory']//ul[@class='dropdown-menu pad0']//li") List<WebElement> categories;
	
	public void clickSubCategory() {
		highlightElement(gymSubCategory);
		gymSubCategory.click();
	}
	
	public void subCategories() throws IOException{
		ExcelUtils excel = new ExcelUtils();
		String xlfile = System.getProperty("user.dir") + "\\testData\\testdataexcel.xlsx";
		int i=1;
		System.out.println("Gym Categories : ");
    	for(WebElement g:categories) {
    		String Category = g.getText();
    		System.out.println(Category);
    		excel.setCellData(xlfile, "GymSubCategories", i, 0, Category);
    		i++;
    	}
    	System.out.println("<===========================>");
		
	}
	
	

}
