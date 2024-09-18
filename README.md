Automation Exercise


Overview


This repository contains automated tests for (https://www.automationexercise.com/) e-commerce website using Selenium WebDriver and Java for UI testing, and API testing using REST Assured using TestNg for Execution of tests. 
Along with sample Extent Report generated after Excution for referance.

The following UI test scenarios are covered:
1. User Sign-up: Verify that a new user can successfully sign up on the website.
2. Single Product Order: Place an order for a single product: Frozen Tops for Kids.
3. Multiple Product Order: Place an order for multiple products:
    - Beautiful Peacock Blue Cotton Linen Saree
    - Sleeveless Dress
    - Pure Cotton V-Neck T-Shirt
4. Filtering: Filter products by Men’s jeans and verify that 3 results are shown.
5. Product Review: Write a review for a product costing Rs. 478.


The following API test scenarios are covered:
1. Search Product API: Verify that the search product API returns a successful response.
2. Delete Product API: Verify that deleting a product without login returns an error.
3. Update User Account API: Verify that updating user account information returns a successful response.


Technologies Used


- Java 8+
- Selenium WebDriver 4+
- REST Assured
- Maven 3+
- Log4j 2+
- TestNG


Setup

Prerequisites
1. Java 8+ installed on your system.
2. Eclipse or IntelliJ IDEA IDE installed.
3. Maven 3+ installed.
4. Git installed.


Step 1: Clone the Repository
Step 2: Set up Eclipse or IntelliJ IDEA

  2.1: Eclipse:

    1. Open Eclipse.
    2. Import the project: File > Import > Existing Maven Projects.
    3. Select the cloned repository directory.
    4. Click Finish.
    5. Run Maven Commands:
        - Open Command Prompt or Terminal.
        - Navigate to the project directory.
        - Run the command: mvn clean
        - Press Enter.
        - Run the command: mvn install
        - Press Enter.
    6. Force Update Maven Project:
        - Right-click on the project.
        - Select Maven > Update Project.
        - Check "Force Update of Snapshots/Releases".
        - Click OK.
    7. Install TestNG Plugin:
        - Open Eclipse Marketplace: Help > Eclipse Marketplace.
        - Search for "TestNG for Eclipse".
        - Click Install.
        - Restart Eclipse.


  2.2: IntelliJ IDEA:

    1. Open IntelliJ IDEA.
    2. Import the project: File > New > Project from Existing Sources.
    3. Select the cloned repository directory.
    4. Click Clone.
    5. Run Maven Commands:
        - Open Command Prompt or Terminal.
        - Navigate to the project directory.
        - Run the command: mvn clean
        - Press Enter.
        - Run the command: mvn install
        - Press Enter.
    6. Force Update Maven Project:
        - Open Maven tool window: View > Tool Windows > Maven.
        - Click on the "Reimport All Maven Projects" button.
        - Check "Force rebuild of projects".
    7. Install TestNG Plugin:
        - Open Settings: File > Settings.
        - Navigate to "Plugins".
        - Search for "TestNG".
        - Click Install.
        - Restart IntelliJ IDEA.






