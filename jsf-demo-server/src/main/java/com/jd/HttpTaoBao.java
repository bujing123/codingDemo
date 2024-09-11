package com.jd;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class HttpTaoBao {
    public static void main(String[] args) throws MalformedURLException {


        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444"), new FirefoxOptions());
        driver.get("http://baidu.com");
    }
}
