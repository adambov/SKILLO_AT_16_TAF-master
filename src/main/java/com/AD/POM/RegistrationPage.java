package com.AD.POM;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage  extends BasePage {
    //1. CONST
    public static final String REGISTARATION_PAGE_URL = "/users/register";
    //2.UI MAP
    @FindBy(css = "h4.text-center.mb-4")
    private WebElement regFormTitle;

    @FindBy(css = "input.is-invalid")
    private WebElement usernameInputField;

    @FindBy(xpath = "//input[@placeholder='email']")
    private WebElement emailInputField;

    @FindBy(xpath = "//input[@placeholder='Birth date']")
    private WebElement birthDateInput;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement passwordInputField;

    @FindBy(xpath = "//input[@placeholder='Confirm Password']")
    private WebElement confirmPasswordInputField;

    @FindBy(xpath = "//textarea[@placeholder='Public info']")
    private WebElement publicInfoInput;

    @FindBy(xpath = "//button[@id='sign-in-button']")
    private WebElement signInButton;


    //3 CONSTRUCTOR
    public RegistrationPage(WebDriver driver, Logger log) {
        super(driver, log);
        PageFactory.initElements(driver, this);
    }

    //3.1. NAVIGATION
    public void navigateToRegPage(){
        navigateTo(REGISTARATION_PAGE_URL);
    }

    //4 USER ACTION

    //5. SUPPORT METHODS

    //6.Verifications


}
