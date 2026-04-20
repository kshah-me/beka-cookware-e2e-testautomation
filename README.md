# Beka-Cookware Ecommerce Test Automation TestNG

### This is a complete project where an [E-commerce site](https://www.beka-cookware.com/) site is automated by writing test suites using selenium-webdriver and TestNG as testing framework

The following key modules/pages are automated:

- **HomePage**
- **SearchPage**
- **ProductDetailsPage**
- **CartPage**
- **Checkout**</br>

Key test cases(total **33**) are written for test suites created including the positive and negative test cases.</br>A state-transition flow of test-cases are designed and run like a user searching and adding product into cart from an e-commerce site.</br>
For failed test cases it will take a screenshot as well at the point of failure.


---

**Project Structure:**

- **pom.xml:** Maven configuration file containing project dependencies for Selenium Webdriver, Java, RestAssued, TestNG and Chaintest .

-**configuration:**
  -	**config.property:** This file helps to provide the URL, set the preferred browser and provide the preferred Language settings in which the URL would be loaded. 
  
- **src/main/java:**
  - **base:** Contains DriverFactory.java, a class providing setup and teardown methods, initializing WebDriver instances.
  - **config:** ConfigReader.java, offering methods to read data from config file.
  - **pageObjects:** Stores CartPage.java, CheckoutPage.java, HomePage.java, ProductDetailsPage.java, ProductFilterPage.java,
	  ProductGridPage.java, SearchPage.java defining XPath expressions for elements on mentioned pages.
  - **utility:** Contains various utility classes:
    - **AnnotationTransformer.java and RetryAnalyzer.java:** Controls the retry behavior of failed tests in TestNG.
    - **CaptureScreenshotEvidence.java:** Provides methods capturing screenshot while failing testcases.
    - **MouseKeyboardAction.java:** To perfomr mouseorkeybaord operation on any Webelement.
    - **PageScrollUtility.java:** It helps to scroll the page to defined element.
	- **UrlCheck.java:** This helps to verify all urls and images are broken or not
	- **WaitUtils:** This helps to wait for an Webelement for given condition 

- **src/test/java:**
  - **com.bekacookware.test:** Contains all testcases related to feature 

- **pom.xml:** All maven project dependencies are mentioned here

---


### Technology: </br>

- Tool: Selenium Webdriver
- IDE: Intellij IDEA
- Build tool: Maven
- Language: Java
- Testing Framework : TestNG

### Prerequisite: </br>

- Need to install jdk 21, maven and chaintest
- Configure Environment variable for jdk 21, maven and chaintest
- Clone this project and unzip it
- Open the project into IntellIJ IDEA

### Build the project

```java
mvn clean install -DskipTests
```

### Run the Automation Script by the following command

```java
mvn clean test
```
- Let the project build successfully
- Selenium will open the browser and start automating.
- After automation to view allure report , give the following commands:

```Chaintest report overview


```