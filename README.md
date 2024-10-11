# eBay Search Functionality Test Suite

This project contains automated tests for the search functionality on the eBay website using **Cucumber**, **Selenium WebDriver**, and **Java**. The tests are written following the Behavior-Driven Development (BDD) approach using Gherkin syntax.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Project Structure](#project-structure)
- [Running the Tests](#running-the-tests)
- [Generating Reports](#generating-reports)
- [Viewing the Reports](#viewing-the-reports)
- [Contributing](#contributing)


---

## Prerequisites

Before you begin, ensure you have the following installed on your system:

- **Java Development Kit (JDK) 8 or higher**
- **Apache Maven 3.6 or higher**
- **Google Chrome Browser**
- **Git** (optional, for cloning the repository)

---

## Installation

### **1. Clone the Repository**

Clone the project repository from GitHub:

```bash
git clone https://github.com/greyle12/ebay-search-test-suite.git
```
Alternatively, you can download the repository as a ZIP file and extract it.

### **2. Navigate to the Project Directory**

```bash
cd ebay-search-test-suite
```

### **3. Install Dependencies**

Use Maven to download all necessary dependencies:
```bash
mvn clean install
```

---

## Project Structure
The project follows a standard Maven project structure:

- **src/main/java**: Contains the main Java classes (e.g., page objects).
- **src/test/java**: Contains test classes, including step definitions and the test runner.
- **src/test/resources**: Contains feature files and configuration files.
- **pom.xml**: The Maven Project Object Model file containing project dependencies and build configurations.

---

## Running the Tests
You can run the tests using Maven or an IDE like Eclipse or IntelliJ IDEA.

### **Using Maven**
To run all tests:
```bash
mvn test
```

### **Using an IDE**
1. **Import the Project**

    Open your IDE(Eclipse).
    Import the project as a Maven project.

2. **Run the Test Runner**

    Navigate to `src/test/java`.

    Locate the `TestRunner` class.

    Right-click on `TestRunner` and select **Run**.

---

## Generating Reports
The project is configured to generate a detailed HTML test report using Cucumber's reporting capabilities.

### **Configuration**
In the `TestRunner` class, the following plugin configuration enables report generation:
```java
@CucumberOptions(
    // ... other configurations
    plugin = {"pretty", "html:target/cucumber-reports.html"},
    monochrome = true
)
```
### **Generating the Report**
The report is generated automatically when you run the tests using Maven:
```bash
mvn test
```

---

## Viewing the Reports
After the tests have executed:

 **1. Locate the Report**

- The report is saved at `target/cucumber-reports/report.html` and `test-output/SparkReport/MySparkReport.html`

**2. Open the Report**

- Use a web browser to open the `report.html` and `MySparkReport.html` files.

- The report provides a detailed summary of test execution, including passed and failed scenarios, step details, and any error messages.

---

## Contributing
Contributions are welcome! To contribute:

1. **Fork the Repository**

     - Click on the Fork button at the top right of the GitHub repository page.

2. **Clone Your Fork**
```bash
git clone https://github.com/greyle12/ebay-search-test-suite.git
```
3. **Create a Feature Branch**
```bash
   git checkout -b feature/your-feature-name
```
4. **Make Your Changes**

       - Implement your feature or fix.
   
5. **Commit and Push**
```bash
git add .
git commit -m "Add feature XYZ"
git push origin feature/your-feature-name
```
6. **Submit a Pull Request**

    - Go to your forked repository on GitHub.

    - Click on **New Pull Request**.

    - Submit your pull request for review.

---

## Contact
For any questions or issues, please open an issue on GitHub.

---

## Additional Information
### **Dependencies**

The project uses the following main dependencies, managed via Maven:

- **Cucumber Java**

- **Cucumber JUnit**

- **Selenium Java**

- **WebDriverManager**

- **JUnit**

These dependencies are specified in the `pom.xml` file and will be downloaded automatically when you run `mvn clean install`.

### WebDriverManager Usage
The project uses WebDriverManager to automatically manage the browser driver binaries.

In the `setUp` method of your step definitions or hooks:
```java
@Before
public void setUp() {
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    // ... other setup code
}
```

   
