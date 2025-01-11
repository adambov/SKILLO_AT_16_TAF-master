package com.AD.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;


public class PostModal extends BasePage{
    private final WebElement modalElement;

    public PostModal (WebDriver driver, Logger log) {
        super(driver,log);
        this.modalElement = driver.findElement(By.className("post-modal"));
    }
    public boolean isImageVisible() {
        try {
            WebElement image = modalElement.findElement(By.cssSelector(".post-modal-img img"));
            return wait.until(ExpectedConditions.visibilityOf(image)).isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }
    public String getPostUser() {
        WebElement postUser = modalElement.findElement(By.className("post-user"));
        wait.until(ExpectedConditions.visibilityOf(postUser));
        return postUser.getText();
    }

    public boolean isPostPrivate() {
        log.info("Checking if the post is private...");
        WebElement modalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='post-modal-container']")));
        WebElement padlockIcon = modalElement.findElement(By.xpath(".//i[contains(@class, 'fa-lock') or contains(@class, 'fa-unlock')]"));

        wait.until(ExpectedConditions.visibilityOf(padlockIcon));
        String actualPadlock = padlockIcon.getAttribute("class");
        Assert.assertTrue(actualPadlock.contains("fa-lock"), "Padlock icon is not locked");
        return actualPadlock.contains("fa-lock");
    }


}
