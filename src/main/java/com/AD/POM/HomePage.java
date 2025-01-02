package com.AD.POM;

import org.apache.logging.log4j.Logger;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;


public class HomePage extends BasePage {
    public static final String HOME_PAGE_URL = "/posts/all";
    public static final String EXPECTED_TITLE = "ISkillo";

    @FindBy(id = "nav-link-home")
    private WebElement navBarHome;

    @FindBy(id = "nav-link-new-post")
    private WebElement navBarNewPost;

    @FindBy(id = "nav-link-profile")
    private WebElement navBarProfile;

    @FindBy(id = "nav-link-login")
    private WebElement navBarLogin;

    @FindBy(css = "i.fas.fa-sign-out-alt.fa-lg")
    private WebElement navBarLogout;

    @FindBy (xpath = "//i[contains(@class,'like far fa-heart fa-2x')]")
    private WebElement likeButton;

    @FindBy (xpath = "//div[contains(@aria-label,'Post liked')]")
    private WebElement postLikeMessage;

    public HomePage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver, this);
    }

    public void openHomePage() {
        navigateTo(HOME_PAGE_URL);
    }

    public boolean isNavHomeShown() {
      return  isPresented(navBarHome);
    }

    public boolean isNavLoginShown() {
     return isPresented(navBarLogin);
    }

    public boolean isNavLogoutShown() {
        return isPresented(navBarLogout);
    }

    public boolean isNavProfileShown() {
        return isPresented(navBarProfile);
    }

    public void clickOnNavBarLogin () {
       waitAndClickOnWebElement(navBarLogin);
    }

    public void clickOnNavBarProfile(){
        waitAndClickOnWebElement(navBarProfile);
    }

    public void clickOnNavBarNewPost () {
       waitAndClickOnWebElement(navBarNewPost);
    }

    public void clickPost(int postIndex) {
        List<WebElement> posts = driver.findElements(By.tagName("app-post"));
        posts.get(postIndex).click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return document.readyState").equals("complete");
    }

    public void ClickOnLikeButton() {
        waitAndClickOnWebElement(likeButton);
    }

    public boolean isLikeMessageVisible() {
        boolean isLikeMessageVisible = false;
        try {
            isLikeMessageVisible = wait.until(ExpectedConditions.visibilityOf(postLikeMessage)).isDisplayed();
            log.info("CONFIRMATION # The Post liked message is displayed.");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            log.error("ERROR : The Post liked message is not displayed!");
            isLikeMessageVisible = false;
        }
        return isLikeMessageVisible;
    }
}