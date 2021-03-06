package com.revature.app;

//import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		// To use Selenium WebDriver, it needs to point to a location of the webdriver
		// file
		System.setProperty("webdriver.chrome.driver", "C:\\webdrivers\\chromedriver.exe");

		// Now instantiate a WebDriver object
		WebDriver driver = new ChromeDriver();

		driver.get("http://localhost:8080/index.html"); // navigates to the website

		// locate the 2 input elements and button for adding numbers
		WebElement addInput1 = driver.findElement(By.id("addNum1"));
		WebElement addInput2 = driver.findElement(By.id("addNum2"));
		WebElement addButton = driver.findElement(By.id("addBtn"));

		addInput1.sendKeys("135.20"); // puts that number in the first element
		addInput2.sendKeys("1.80"); // puts that number in the second element
		addButton.click(); // clicks the add button

		driver.switchTo().frame("addResult"); // Switch into the inside of the add iframe
		WebElement addOutput = driver.findElement(By.tagName("pre")); // find the 'pre' tag that is embedded inside of
																		// iframes
		System.out.println("The result of adding 135.20 + 1.80 is: " + addOutput.getText()); // print out the text of
																								// the output element

		driver.switchTo().parentFrame(); // switch back outside of the iframe

		// Subtraction
		// locate the 2 input elements and button for subtracting numbers
		WebElement subInput1 = driver.findElement(By.id("subNum1"));
		WebElement subInput2 = driver.findElement(By.id("subNum2"));
		WebElement subButton = driver.findElement(By.id("subBtn"));

		subInput1.sendKeys("125"); // puts that number in the first element
		subInput2.sendKeys("10"); // puts that number in the second element
		subButton.click(); // clicks the subtract button

		driver.switchTo().frame("subResult"); // Switch into the inside of the add iframe
		WebElement subOutput = driver.findElement(By.tagName("pre")); // find the 'pre' tag that is embedded inside of
																		// iframes
		System.out.println("The difference of 125.20 - 10.80 is: " + subOutput.getText()); // print out the text of the
																							// output element

		driver.switchTo().parentFrame(); // switch back outside of the iframe

		// Multiply
		// locate the 2 input elements and button for multiplying numbers
		WebElement multiInput1 = driver.findElement(By.id("multiNum1"));
		WebElement multiInput2 = driver.findElement(By.id("multiNum2"));
		WebElement multiButton = driver.findElement(By.id("multiBtn"));

		multiInput1.sendKeys("7"); // puts that number in the first element
		multiInput2.sendKeys("8"); // puts that number in the second element
		multiButton.click(); // clicks the multiply button

		driver.switchTo().frame("multiResult"); // Switch into the inside of the add iframe
		WebElement multiOutput = driver.findElement(By.tagName("pre")); // find the 'pre' tag that is embedded inside of
																		// iframes
		System.out.println("The product of 7 * 8 is: " + multiOutput.getText()); // print out the text of the
																					// output element

		driver.switchTo().parentFrame(); // switch back outside of the iframe

		// Divide
		// locate the 2 input elements and button for dividing numbers
		WebElement divInput1 = driver.findElement(By.id("divNum1"));
		WebElement divInput2 = driver.findElement(By.id("divNum2"));
		WebElement divButton = driver.findElement(By.id("divBtn"));

		divInput1.sendKeys("64"); // puts that number in the first element
		divInput2.sendKeys("8"); // puts that number in the second element
		divButton.click(); // clicks the divide button

		driver.switchTo().frame("divResult"); // Switch into the inside of the add iframe
		WebElement divOutput = driver.findElement(By.tagName("pre")); // find the 'pre' tag that is embedded inside of
																		// iframes
		System.out.println("The quotient of 64 / 8 is: " + divOutput.getText()); // print out the text of the
																					// output element

		driver.switchTo().parentFrame(); // switch back outside of the iframe

		Thread.sleep(15000); // Pausing for 15 seconds
		// quit method
		// When done with the scripted tasks provided by the Selenium WebDriver, we
		// should quit the driver
		// it will actually close the chromedriver.exe and free up allocated memory, so
		// it's really important
		driver.quit();
	}

}
