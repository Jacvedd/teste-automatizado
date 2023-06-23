package com.example.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;

@SpringBootApplication
public class SeleniumApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(SeleniumApplication.class, args);

		System.setProperty(
				"webdriver.chrome.driver",
				"C:\\Users\\jvenanci\\Downloads\\chromedriver_win32\\chromedriver.exe");

		var options = new ChromeOptions();
		options.addArguments("no-sandbox");
		options.addArguments("--remote-allow-origins=*");

		// remove cookies
		var prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.cookies", 2);

		options.setExperimentalOption("prefs", prefs);



		WebDriver driver = new ChromeDriver(options);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

		driver.get("https://www.google.com.br");

		// seleciona o elemento na pagina buscando pelo nome da classe
		WebElement textArea = driver.findElement(By.className("gLFyf"));
		textArea.click();

		CharSequence charSequence = "nttdata";

		textArea.sendKeys(charSequence);
		textArea.sendKeys(Keys.ENTER);

		WebElement link = driver.findElement(By.className("LC20lb"));
		link.click();

		WebElement carrers = driver.findElements(By.className("navbar-list-item"))
				.parallelStream()
				.filter(i -> i.getText().contains("Careers"))
				.findFirst()
				.orElse(null);

		carrers.click();

		WebElement jobs = driver.findElements(By.className("navbar-link"))
				.parallelStream()
				.filter(i -> i.getText().contains("Job opportunities"))
				.findFirst()
				.orElse(null);

		jobs.click();

		driver.close();

	}

}
