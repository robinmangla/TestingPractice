package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.List;

public class SauceLabPracticeTest extends BaseTest {
    public WebDriver driver;

    public void doLogin() throws InterruptedException {
        driver = getDriver();
        driver.get("https://www.saucedemo.com/");
        Thread.sleep(10000);
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
        Thread.sleep(2000);
    }


    @Test
    public void TC01_itemsCheck(Method method) throws InterruptedException {
        System.out.println("thread id is: " + method.getName() + Thread.currentThread().getId());
        doLogin();
//        getDriver().manage().window().maximize();
//        getDriver().get("https://www.saucedemo.com/");
//        Thread.sleep(10000);
//        getDriver().findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
//        Thread.sleep(2000);
//        getDriver().findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
//        Thread.sleep(2000);
//        getDriver().findElement(By.xpath("//input[@id='login-button']")).click();
//        Thread.sleep(2000);

        List<WebElement> items = driver.findElements(By.xpath("//*[@class='inventory_item']"));
        Thread.sleep(2000);
        Assert.assertTrue(items.size()==6);
    }


    @Test
    public void TC02_addToCartButtonCheck(Method method) throws InterruptedException {
        System.out.println("thread id is: " + method.getName() + Thread.currentThread().getId());
        doLogin();
//        getDriver().manage().window().maximize();
//        getDriver().get("https://www.saucedemo.com/");
//        Thread.sleep(10000);
//        getDriver().findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
//        Thread.sleep(2000);
//        getDriver().findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
//        Thread.sleep(2000);
//        getDriver().findElement(By.xpath("//input[@id='login-button']")).click();
//        Thread.sleep(2000);

        List<WebElement> addToCartButtons = driver.findElements(By.xpath("//*[text()='Add to cart']"));
        Thread.sleep(2000);
        Assert.assertTrue(addToCartButtons.size()==6);
    }
}
