package cn.wl.test.framework;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaiduSearch {
    public void searchBaidu1() {
        System.setProperty("webdriver.chrome.driver", "E:\\moving\\GIT\\java-test-e2e\\junit\\src\\main\\resources\\driver\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("http://www.baidu.com");

        driver.findElement(By.id("aa"));
        driver.findElement(By.name("aa"));
        driver.findElement(By.className("aa"));
        driver.findElement(By.tagName("aa"));
        driver.findElement(By.linkText("aa"));
        driver.findElement(By.partialLinkText("aa"));
        driver.findElement(By.cssSelector("aa"));
        driver.findElement(By.xpath("aa"));

        //a[starts-with(@name, 'user_')]
        //a[contains(@name, 'name')]
        //a[text()='hello']
        driver.findElement(By.xpath("//div/a[1]"));
        driver.findElements(By.xpath("//div/a"));
        driver.findElement(By.xpath("//a[starts-with(@name, 'user_')]"));
        driver.findElement(By.xpath("//a[ends-with(@id, '_name')]"));
        driver.findElements(By.xpath("//a[contains(@name, 'name')]"));
        driver.findElements(By.xpath("//a[text()='hello"));


    }
}
