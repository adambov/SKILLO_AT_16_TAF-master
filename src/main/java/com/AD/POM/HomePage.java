package com.AD.POM;

import org.apache.logging.log4j.Logger;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends BasePage {
    public static final String HOME_PAGE_URL = "/posts/all";
    public static final String EXPECTED_TITLE = "ISkillo";
    public static final String VALID_USERNAME = "NaskoDambov";

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

    @FindBy (xpath = "//input[@id='search-bar']")
    private WebElement searchBar;

    @FindBy (xpath = "//div[contains(@class, 'small-user-container')]")
    private WebElement searchResultContainer;

    @FindBy (xpath = "//div[contains(@class, 'small-user-container')]//a[contains(text(), 'NaskoDambov')]")
    private WebElement searchedUsernamecontainer;

    @FindBy (xpath = "//app-all-posts[@class='ng-star-inserted']")
    private WebElement allPostsContainerHomePage;

    @FindBy (xpath = "//div[@class='modal-content']")
    private WebElement postModalHomePage;

    @FindBy (xpath = "//i[contains(@class, 'like far fa-heart')]")
    private WebElement likeButtonHomePageModal;

    @FindBy (xpath = "//div[contains(@id, 'toast-container')][last()]")
    private WebElement toastMSGAfterPostLike;

    @FindBy (xpath = "//div[@class='post-feed-img'][last()]")
    private WebElement lastPost;

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

    public void clickOnSearchBar ()  {
        waitAndClickOnWebElement(searchBar);
    }

    public void enterValidUsernameinSearchBar() {
        wait.until(ExpectedConditions.visibilityOf(allPostsContainerHomePage));
        searchBar.sendKeys(VALID_USERNAME);
    }

    public boolean verifySearchResultIsShown() {
        wait.until(ExpectedConditions.visibilityOf(searchResultContainer));
        wait.until(ExpectedConditions.elementToBeClickable(searchResultContainer));
        String actualUsername = searchedUsernamecontainer.getText();
        String expectedUsername = VALID_USERNAME;

        if (actualUsername.equals(expectedUsername)) {
            return true;
        } else {
            log.error("Actual username " + actualUsername + "does not match the expected username" + expectedUsername);
            return false;
        }
    }

    public void clickOnSearchResult() {
        wait.until(ExpectedConditions.visibilityOf(searchResultContainer));
        wait.until(ExpectedConditions.elementToBeClickable(searchedUsernamecontainer));
        searchedUsernamecontainer.click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe("http://training.skillo-bg.com:4300/posts/all")));
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

    public void clickOnLastPostOnHomePageAfterLogin() {
        List<WebElement> posts = driver.findElements(By.cssSelector("app-post-detail"));

        if (posts.size() > 0) {
            lastPost.click();
        } else {
            System.out.println("There are no available posts");
        }
    }


    public void clickLikeOnPostonHomePageAfterPostModalIsLoaded() {
        likeButtonHomePageModal.click();
    }

    public void waitForToastMessageToDisappear() {
        wait.until(ExpectedConditions.invisibilityOf(toastMSGAfterPostLike));
        log.info("Toast message is no longer visible.");
    }


    public String getToastMSGTextAfterLikeOrDislike () {
        wait.until(ExpectedConditions.visibilityOf(toastMSGAfterPostLike));
        String msg = toastMSGAfterPostLike.getText();
        return msg;
    }


    public void verifyIfPostIsNotLiked () throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(likeButtonHomePageModal));
        String buttonClass = likeButtonHomePageModal.getAttribute("class");
        boolean isLiked = buttonClass.contains("liked");
        if (isLiked) {
            log.info("The post is already liked and it should not be liked. We disliking the Post at this point");
            clickLikeOnPostonHomePageAfterPostModalIsLoaded();
            Thread.sleep(11111);
        } else {
            log.info("The post is still not liked.");
        }
    }
}