<img align="center" src="skilloLogo.png" alt="Skillo Academy Logo" />


<div align="center">

# Test automation framework
</div>

# Automating iSkillo website

## Table of Contents
- [Overview](#overview)
- [Application / software under test]()
- [Installation](#installation)
- [Usage](#usage)
- [Bug report](#bug-report)
- [Contact](#contact)

## Overview
Iskillo social networks is an online platforms where users can create profiles,
connect with other Skillo students or graduees.

All Iskillo users can share content, and engage with
a broader community.

ISkillo have revolutionized communication,
enabling instant sharing of information and fostering
global connections not only in Bulgaria.

Many QA engineers are connected via the fast variety of test automation activities performed on ISkillo.

## Test activities performed with Selenium 4.25 and TestNG 10 Java unit framework:

List of the test cases.

- End to end scenario
  - Registration - Login - Profile - Post

- Login feature
  - Verify already registered user can successfully log in in the system.
  - Verify already registered user can NOT successfully log in in the system  with WRONG PASSWORD.
  - Verify already registered user can NOT successfully log in in the system  with WRONG USERNAME
  - Verify already registered user can NOT successfully log in in the system  with NO CREDENTIALS
  - Verify already registered user can NOT successfully log in in the system  with NO USERNAME entered.
  - Verify already registered user can NOT successfully log in in the system  with NO PASSWORD entered.

- Post feature
  - Verify  user can create a new post.
  - Verify user can delete a post.

- Registration feature
    - Verify user can register in the system with valid data.
    - Verify user cannot register in the system with invalid data.
  
- Users feature
    - Verify user can follow another user and access their private and all posts.
  
## Installation

- Clone the repository (please use this link): https://github.com/adambov/SKILLO_AT_16_TAF-master/
  
- Make sure you have JAVA version 23 and up and running

- Make sure you have Maven version 3.0.9 and up running

- Make sure you have Selenium version 4.26.0 and up running 


## Usage

INSTALLATION:

Please visit the Test Automation Framework with linK: https://github.com/adambov/SKILLO_AT_16_TAF-master/

Make sure that you can clone the repository. Follow the 3 different ways to do so:

Tip number 1:
- Visit the GitHub repository and click the "Download" button.
- Extract the archive to your preferred location.

Tip number 2:
- Copy the HTTPS link from the repository and use Git Bash:
  - git clone "<repository_link>"
  - cd <repository_folder>

Tip number 3:
- Use IntelliJ IDEA Community Edition (v21+):
    - Select New Project from VCS.
    - Paste the Git repository link.
    - Click Clone.


CHECK FOLDER PATHS:

This is a steps that needs to be done for Windows OS users:
Go to SRC TEST RESOURCES folder and verify if the following folders are presented:
- There is a folder with name "reports"
- There is a folder with name "screenshots"
- There is a folder with name "upload"

IF NOT
When you build the project in src/test/java/gui you can find the folders created by the automation script.

RUNNING AUTOMATION

STEP 1:
Go with the terminal/shell/msPrompt to the folder of the project that POM.XML lives (exists).

STEP 2:
Run command:

mvn clean test

STEP 3:

Wait a bit the automation to start and after the test execution a report will be generated

## Bug report
If you find any bugs that you want to report, please do so with the bug report and liveCicle explained:

1. Summary: Brief description of the bug.

2. Steps to Reproduce: Provide a step-by-step guide.

3. Expected Behavior: Describe what should have happened.

4. Actual Behavior: Detail what actually happened.

5. Attachments: Include screenshots or logs if applicable.

## Contact

- [Skulo Student](mailto:atanas.dambov@gmail.com)
- Project Link: [TAF Selenium 4 TestNG 7 ](https://github.com/adambov/SKILLO_AT_16_TAF-master)