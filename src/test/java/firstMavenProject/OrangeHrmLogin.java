package firstMavenProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrangeHrmLogin {
        WebDriver driver;
	
	@BeforeTest
	public void OpenBrowser() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
	}
	
	/*public void VerifyHomePage() {
		WebElement str=driver.findElement(By.id("logInPanelHeading"));
		if(str = "LOGIN Panel") {
			System.out.println("Statement is Correct");
		}
		else {
			System.out.println("Statement is False");
		}
	}*/
	
	@Test
	public void Login() {
		WebElement username=driver.findElement(By.id("txtUsername"));
		username.sendKeys("Admin");
		WebElement password=driver.findElement(By.xpath("//input[@name='txtPassword']"));
		password.sendKeys("admin123");
		driver.findElement(By.name("Submit")).click();
		//driver.navigate().back();
	}
	
	@Test
	public void VerifyAdmin() {
		driver.findElement(By.linkText("Admin")).click();
		driver.findElement(By.id("searchSystemUser_userName")).sendKeys("Prasanthganivada");
		Select userrole = new Select(driver.findElement(By.name("searchSystemUser[userType]")));
		userrole.selectByIndex(2);
		//driver.findElement(By.linkText("ESS")).click();
		driver.findElement(By.name("searchSystemUser[employeeName][empName]")).sendKeys("Prasanth");
		Select status=new Select(driver.findElement(By.name("searchSystemUser[status]")));
		status.selectByVisibleText("Enabled");
		//driver.findElement(By.linkText("Enabled")).click();
		driver.findElement(By.id("searchBtn")).click();
	}
	
	@Test
	public void VerifyForgotPass() {
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		driver.findElement(By.linkText("Forgot your password?")).click();
		WebElement respass=driver.findElement(By.name("securityAuthentication[userName]"));
		respass.sendKeys("Admin143");
		driver.findElement(By.className("searchValues")).click();
	}
	
	@AfterTest
	public void Teardown() {
		driver.quit();
	}

}
