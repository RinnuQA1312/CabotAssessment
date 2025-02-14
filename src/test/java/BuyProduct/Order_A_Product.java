package BuyProduct;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.List;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.sourceforge.tess4j.TesseractException;

public class Order_A_Product {
	public static WebDriver driver;
	public static ChromeOptions options = new ChromeOptions();
	public static FileInputStream fis;

	public static void main(String[] args) throws IOException, InterruptedException, TesseractException {
		try {

			

			options.addArguments("--incognito"); // Add incognito argument
			driver = new ChromeDriver(options);

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
			driver.manage().deleteAllCookies();

			driver.get("https://www.amazon.in/");

			orderPlacement();

		} catch (Exception e) {

			System.out.println("Exception Caught" + " " + e);

		} finally {
			driver.navigate().refresh();
			orderPlacement();
		}

	}
	
	

	public static void orderPlacement() throws InterruptedException, IOException {

		WebElement accountClick = driver.findElement(By.xpath("//span[contains(text(),'Account & Lists')]"));
		accountClick.click();

		WebElement userLogin = driver.findElement(By.name("email"));
		userLogin.sendKeys("9551969575");

		WebElement continueBtn = driver.findElement(By.xpath("//input[@type='submit']"));
		continueBtn.click();

		WebElement passwrd = driver.findElement(By.name("password"));
		passwrd.sendKeys("Rinu@7245");

		WebElement signInBtn = driver.findElement(By.xpath("//input[@id='signInSubmit']"));
		signInBtn.click();

		WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
		driver.findElement(By.id("twotabsearchtextbox"));
		search.sendKeys("Smart Watches");
		Thread.sleep(3000);
		
		ScreenshotCapture();
		List<WebElement> sugglist= driver.findElements(By.xpath("//div[@class='left-pane-results-container']/div"));
		
		for(WebElement dplist:sugglist) {
			String text=dplist.getText();
			System.out.println(text);
			if(text.contains("smart watches for boys")) {
				dplist.click();
				ScreenshotCapture();
				break;
		}
		}
		
		WebElement product = driver.findElement(By.xpath("//button[@id='a-autoid-4-announce']"));
		product.click();
		WebElement cartClick = driver.findElement(By.xpath("//span[@id='nav-cart-count']"));
		cartClick.click();
		ScreenshotCapture();
		WebElement buyProd = driver.findElement(
				By.xpath("//form[@id='gutterCartViewForm']/div/div[3]/span/span/span/input[@type='submit']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//form[@id='gutterCartViewForm']/div/div[3]/span/span/span/input[@type='submit']")));
		buyProd.click();
		ScreenshotCapture();
	}
	
	public static void ScreenshotCapture() throws IOException {
		Date d = new Date();
		String fileName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
		File srcshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcshot, new File(System.getProperty("user.dir") + "\\target\\ScreenShot\\error_" + fileName));
		
	}

}
