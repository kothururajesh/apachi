package com.apach;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.utility.Utilityclass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Writingexel {
	WebDriver driver;
	Utilityclass ut;
	int col;
	int row;
	String s;
	String t;

	@BeforeMethod
	public void setup() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		
		
	}

	@AfterMethod
	public void tardown() {
		driver.close();

	}

	@Test(enabled = false)
	public void test1() {
		String s = driver.getTitle();
		Assert.assertEquals(s, "Free and open-source eCommerce platform. ASP.NET based shopping cart. - nopCommerce");
	}

	@Test(enabled = false)
	public void test2() {

		throw new SkipException("skipped");

	}

	@Test(enabled = false)
	public void test3() {
		boolean b = driver.findElement(By.xpath("//a[@clas='mobile-logo']")).isDisplayed();
		Assert.assertEquals(b, true);
	}

	@Test(dataProvider = "login1")
	public void test4(String user, String pass) throws IOException {

		driver.findElement(By.linkText("Log in")).click();
		driver.findElement(By.id("Email")).sendKeys(user);
		driver.findElement(By.id("Password")).sendKeys(pass);
		driver.findElement(By.xpath("//button[@class='button-1 login-button']")).click();
		String st = driver.findElement(By.xpath("//span[@id='Email-error']")).getText();


		
	

		
		ut.setCellData("login",row++, 2, st);}
		
	
	@DataProvider(name = "login1")
	public String[][] login() {
		int row = 0;
		int col = 0;
		ut = new Utilityclass(".\\SampleExcel.xlsx");
		try {
			row = ut.getRowCount("login");
			col = ut.getCellCount("login", row);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		String logindata[][] = new String[row][col];

		for (int i = 1; i <= row; i++) {
			for (int j = 0; j < col; j++) {
				try {
					logindata[i - 1][j] = ut.getCellData("login", i, j);
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}

			}

		}
		return logindata;
	}

}
