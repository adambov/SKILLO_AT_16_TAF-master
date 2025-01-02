package com.AD.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;


public class PostModal extends BasePage{
    private final WebElement modalElement;
    @FindBy(xpath = "//i[contains(@class, 'fa-lock') or contains(@class, 'fa-unlock')]")
    private WebElement padlockIcon;

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
        wait.until(ExpectedConditions.visibilityOf(padlockIcon));
        String actualPadlock = padlockIcon.getAttribute("class");
        Assert.assertTrue(actualPadlock.contains("fa-lock"), "Padlock icon is not locked");
        return true;
    }
}
