package com.AD.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class ProfilePage extends BasePage {

    @FindBy (xpath = "//div[contains(@class,'edit-profile-pic ')]")
    private WebElement uploadImage;

    @FindBy (id = "upload-img" )
    private WebElement hiddenUploadImage;

    @FindBy (xpath = "//i[contains(@class,'ml-4 far fa-thumbs-down fa-2x')]")
    private WebElement dislikeButton;

    @FindBy (xpath = "//label[contains(@class,'delete-ask')]")
    private WebElement deletePostButton;

    @FindBy (xpath = "//button[contains(@class,'btn btn-primary btn-sm')]")
    private WebElement areYouSureYesButton;

    @FindBy (xpath = "//div[contains(@aria-label,'Post Deleted!')]")
    private WebElement confirmDeletionMessage;

    @FindBy (xpath = "//div[contains(@aria-label,'Post disliked')]")
    private WebElement postDislikeMessage;

    public void ClickOnYesButton() {
        waitAndClickOnWebElement(areYouSureYesButton);
    }

    public void ClickOnDeleteButton() {
        waitAndClickOnWebElement(deletePostButton);
    }



    public void ClickOnDisikeButton() {
        waitAndClickOnWebElement(dislikeButton);
    }

    Actions action = new Actions(driver);
    public void HoverOverProfilePicture () {
        action.moveToElement(uploadImage).perform();
    }

    public ProfilePage (WebDriver driver, Logger log) {
        super(driver,log);
        PageFactory.initElements(driver,this);
    }

    public String getUsername() {
        WebElement username = driver.findElement(By.tagName("h2"));
        return username.getText();
    }

    public int getPostCount() {
        List<WebElement> posts = driver.findElements(By.tagName("app-post"));
        return posts.size();
    }


    public boolean isDeletedMessageVisible() {
        boolean isDeletedMessageVisible = false;
        try {
            isDeletedMessageVisible = wait.until(ExpectedConditions.visibilityOf(confirmDeletionMessage)).isDisplayed();
            log.info("CONFIRMATION # The Post Deleted! message is displayed.");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            log.error("ERROR : The Post Deleted! message is not displayed!");
            isDeletedMessageVisible = false;
        }
        return isDeletedMessageVisible;
    }

    public boolean isDislikeMessageVisible() {
        boolean isDislikeMessageVisible = false;
        try {
            isDislikeMessageVisible = wait.until(ExpectedConditions.visibilityOf(postDislikeMessage)).isDisplayed();
            log.info("CONFIRMATION # The Post disliked message is displayed.");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            log.error("ERROR : The Post disliked message is not displayed!");
            isDislikeMessageVisible = false;
        }
        return isDislikeMessageVisible;
    }


    public void closePostModal() {


    }
}
