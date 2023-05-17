package com.tripadvisor.webapp.driverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverFactoryClass {
	static ThreadLocal<WebDriver> threadPool=new ThreadLocal<>();
	
	public static WebDriver initWebDriver(String browser)
	{
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("--remote-allow-origins=*");
			threadPool.set(new ChromeDriver(opt));
			
		}

		else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			
			EdgeOptions opt=new EdgeOptions();
			opt.addArguments("--remote-allow-origins=*");
			threadPool.set(new EdgeDriver(opt));
		}
		
		else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions opt=new FirefoxOptions();
			opt.addArguments("--remote-allow-origins=*");
			threadPool.set(new FirefoxDriver(opt));
		}
		return threadPool.get();
	}
	
	public static WebDriver getWebDriver() {
		return threadPool.get();
	}

}
