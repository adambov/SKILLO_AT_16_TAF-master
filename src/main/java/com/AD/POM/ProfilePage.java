package com.AD.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

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

    @FindBy (xpath = "//label[@class='btn-all btn btn-primary']")
    private WebElement allBtn;

    @FindBy (xpath = "//div[contains(@class, 'row no-gutters')]")
    private WebElement postsContainer;

    @FindBy (xpath = "//div[@class='post-modal-container']")
    private WebElement modalElement;



    public void ClickOnYesButton() {
        waitAndClickOnWebElement(areYouSureYesButton);
    }

    public void ClickOnDeleteButton() {
        wait.until(ExpectedConditions.visibilityOf(modalElement));
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

    public void clickOnAllPostFilterBtn(){
        wait.until(ExpectedConditions.visibilityOf(allBtn)).isDisplayed();
        allBtn.click();
    }

    public int getLastPostIndex() {
        List<WebElement> posts = driver.findElements(By.tagName("app-post"));
        int lastPostIndex = posts.size() - 1;
        return lastPostIndex;
    }

    public void clickPost(int postIndex) {
        List<WebElement> posts = driver.findElements(By.tagName("app-post"));
        posts.get(postIndex).click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return document.readyState").equals("complete");
    }

    public void verifyIfAllBtnisDisabled() {
        String buttonClass = allBtn.getAttribute("class");
        boolean isDisabled = buttonClass.contains("disabled");
        if (isDisabled) {
            log.info("The 'All posts' button is inactive (disabled).");
        } else {
            log.info("The 'All posts' button is active (enabled).");
        }

        Assert.assertTrue(isDisabled, "The 'All posts' button is not disabled when it should be.");
    }

    public void closePostModal() {

    }
}
