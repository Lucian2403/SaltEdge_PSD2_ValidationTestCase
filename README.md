🛒 Amazon Shopping Cart Automation
==================================

This project automates adding the cheapest (in our case) Snickers and Skittles to your Amazon cart, verifies that prices are correct, and checks if you're redirected to the signup page at checkout. It is built using **Selenium WebDriver** and **Cucumber** to ensure easy-to-read test scenarios.

🚀 Features
-----------

*   **Section Navigation**: Automatically traverses complex API documentation structure
    
*   **Header Validation**: Verifies required headers exist with correct data types
    
*   **Dynamic Locators**: Adapts to documentation changes with smart XPath generation
    
*   **Visual Reporting**: Generates Allure reports with screenshots for audits
    
*   **Multi-Browser Support**: Ready for Chrome, Firefox, and Edge
    

🛠 Setup Guide
--------------

### 🔹 Prerequisites

Before you begin, make sure you have the following:

*   **Java** (JDK 11 or newer)
    
*   **Maven** (to manage dependencies)
    
*   **WebDriver** (such as ChromeDriver) for your browser
    
*   **An IDE** (IntelliJ IDEA or Eclipse is recommended but optional)
    

### 🔹 Installation Steps

#### Clone the Repository

    git clone https://github.com/Lucian2403/SaltEdge_PSD2_ValidationTestCase
    cd SaltEdge_PSD2_ValidationTestCase

#### Install Dependencies

    mvn clean install

#### Configure WebDriver

1.  Download the appropriate **WebDriver** (e.g., ChromeDriver) for your browser.
    
2.  Place it in the `src/test/resources/drivers` folder.
    
3.  Update the `config.properties` file with the correct WebDriver path.
    

#### Customize UI Validation (Optional)

Modify the `.feature` file if you want to test different UI sections.

▶️ Running the Tests
--------------------

To execute the tests, use the following command:

    mvn test

Alternatively, you can run the **TestRunner** class directly from your IDE.

📌 Test Flow
------------

1.  **Navigates to API Section**:
        
  *   PIIS → Funds → Confirmations
            
2.  **Validates Response Table**:
        
  *   Checks header presence
            
  *   Verifies data types (Boolean/String)
            
3.  **Generates Compliance Evidence**:
        
  *   Allure reports with screenshots
            
  *   Console output with color-coded results
            
    

📊 Expected Output
------------------

If everything works correctly, you will see output similar to this:

    Starting Page URL: https://priora.saltedge.com/docs/berlingroup
    
    The PIIS section is opened
    Assertion text: Expected = 'Confirmations', Actual = 'Confirmations'
    Navigated at PIIS -> Funds -> Confirmations
    Successfully scrolled to Response section
    
    The requested header is fundsAvailable
    The requested header type is boolean, required

If any issues arise, the output will indicate what went wrong.

🔧 Customization & Configuration
--------------------------------

*   **Modify UI Sections**: Edit the `.feature` file to test different items.
    
*   **Update Headers and their types**: Adjust the types in the `.feature` file.
  
*   **Use multiple header & types verification**: Insert new rows into the table from `.feature` file
    
*   **Switch Browsers**: Change the browser configuration in `config.properties`.
    

🛠 Troubleshooting
------------------

### WebDriver Issues

*   Ensure your **WebDriver version matches your browser version**.
    
*   Verify the **WebDriver path** in `config.properties`.
        

### Test Failures

*   Review **console logs** for error messages.
    
*   Confirm that the **UI sections** are valid.
    

🤝 Contributing
---------------

If you’d like to contribute:

1.  **Fork the repository**.
    
2.  **Create a new branch** for your changes.
    
3.  **Submit a pull request** with a description of your updates.
    

📜 License
----------

This project is open-source under the **MIT License**. Feel free to use and modify it!

📩 Contact
----------

For questions or support, reach out:

*   **Your Name**: lucianciubotaru2403@gmail.com
    
*   **Project Repository**: [GitHub Repo](https://github.com/Lucian2403/SaltEdge_PSD2_ValidationTestCase)
