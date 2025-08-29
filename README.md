# IdentifyCourses - Coursera Test Automation Framework

A comprehensive Selenium-based test automation framework for testing Coursera website functionality using Java, TestNG, Cucumber BDD, and Allure reporting.

## 📋 Project Overview

This project automates testing of Coursera's web platform with three main test scenarios:

1. **Search Web Development Courses** - Extract beginner-level web development courses in English with filters
2. **Language Learning Data Extraction** - Extract all available languages and learning levels with counts
3. **Enterprise Form Validation** - Comprehensive validation of form fields in the "For Enterprise" section

## 🔧 Technology Stack

- **Java**: JDK 21
- **Build Tool**: Maven 3.6+
- **Test Framework**: TestNG 7.11.0
- **BDD Framework**: Cucumber 7.27.2
- **Web Automation**: Selenium WebDriver 4.35.0
- **Reporting**: Allure 2.29.0
- **Data Management**: Apache POI 5.4.1 (Excel), Jackson 2.19.2 (JSON)
- **WebDriver Management**: WebDriverManager 6.2.0
- **Logging**: Log4j 2.25.1

## 📋 Prerequisites

Before running the tests, ensure you have the following installed:

### Required Software

1. **Java Development Kit (JDK) 21**
   - Download from: https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html
   - Set JAVA_HOME environment variable

2. **Maven 3.6+**
   - Download from: https://maven.apache.org/download.cgi
   - Add Maven bin directory to PATH

3. **Web Browsers**
   - Chrome (latest version)
   - Firefox (latest version)
   - Edge (latest version)

### Required JAR Files & Tools

4. **Selenium Server (Standalone) - Version 4.35.0**
   - Download from: [Selenium Downloads](https://www.selenium.dev/downloads/)
   - Direct link: https://github.com/SeleniumHQ/selenium/releases/download/selenium-4.35.0/selenium-server-4.35.0.jar
   - Place the jar file in the project root directory

5. **Allure Command Line - Version 2.29.0**
   - Download from: [Allure Command Line](https://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline/2.29.0/)
   - Extract to your desired location and add to PATH environment variable

## 🚀 Project Setup

### 1. Clone the Repository
```bash
git clone <repository-url>
cd IdentifyCourses
```

### 2. Install Dependencies
```bash
mvn clean install
```

### 3. Configuration Setup

#### Test Configuration
The project uses `src/main/resources/config.properties` for configuration:

```properties
# Application Configuration
app.url=https://coursera.org/

# Execution Environment (local/remote)
execution_env=remote

# Timeouts (in seconds)
implicit.wait=30
explicit.wait=40
page.load.timeout=60

# Test Data Files
test.data.file=src/test/resources/testdata/test_data.xlsx
test.data.file.json=src/test/resources/testdata/test_data.json
```

#### Test Data
- **Excel Data**: Located at `src/test/resources/testdata/test_data.xlsx`
- **JSON Data**: Located at `src/test/resources/testdata/test_data.json`

## 🏃‍♂️ Execution Instructions

### Method 1: Using Batch Files (Recommended for Remote Execution)

#### Step 1: Start Selenium Grid
```bash
selenium_grid.bat
```
This will start Selenium Grid in standalone mode on the local machine.

#### Step 2: Run Tests
```bash
run.bat
```
This will:
- Execute all tests using Maven
- Generate Allure results
- Create and open Allure HTML report

### Method 2: Maven Commands (Direct Execution)

#### Local Execution
```bash
# Run all tests
mvn clean test

# Run specific test tags
mvn clean test -Dcucumber.filter.tags="@Smoke"

# Generate Allure Report
allure generate target/allure-results -o test-output/allure-report --clean
allure open test-output/allure-report
```

### Method 3: IDE Execution
Run `src/test/java/com/identifycourses/tests/TestRunner.java` directly from your IDE.

## ⚙️ Configuration for Different Environments

### For Remote Execution (Default)
1. Ensure `execution_env=remote` in `config.properties`
2. Start Selenium Grid using `selenium_grid.bat`
3. Run tests using `run.bat`

### For Local Execution
1. Change `execution_env=local` in `config.properties`
2. Run tests directly using Maven or IDE

### Modifying Paths in Batch Files

If you need to run the project from a different location, update the paths in the batch files:

#### selenium_grid.bat
```batch
@echo off
cd /d "YOUR_PROJECT_PATH_HERE"

echo Starting selenium grid...
call java -jar selenium-server-4.35.0.jar standalone

pause
```

#### run.bat
```batch
@echo off
cd /d "YOUR_PROJECT_PATH_HERE"

echo Running Maven Tests...
call mvn clean test

echo Generating Allure Report...
call allure generate target/allure-results -o test-output/allure-report --clean

echo Opening Allure Report...
call allure open test-output/allure-report

pause
```

**Replace `YOUR_PROJECT_PATH_HERE` with your actual project directory path.**

## 📊 Test Scenarios

### 1. Search Web Development Courses (`@Smoke`)
- **Feature**: `a_search_web_dev_courses.feature`
- **Purpose**: Extract first two beginner-level web development courses in English
- **Filters Applied**: Beginner level, English language
- **Output**: Course names, learning hours, and ratings

### 2. Language Learning Data Extraction (`@Smoke`)
- **Feature**: `b_language_learning_filters.feature`
- **Purpose**: Extract all available languages and learning levels with counts
- **Output**: Complete list of languages and levels with their respective counts

### 3. Enterprise Form Validation (`@Smoke`, `@Fieldlevel`)
- **Feature**: `c_enterprise_form_validation.feature`
- **Purpose**: Comprehensive validation of enterprise form fields
- **Validations**: Email, First Name, Last Name, Phone Number, Institution Type, Job Role, Department, State, etc.

## 📈 Reporting

### Allure Reports
- **Location**: `test-output/allure-report/`
- **Features**: 
  - Detailed test execution results
  - Screenshots on failure
  - Step-by-step execution logs
  - Categorization by features and tags
  - Timeline and trend analysis

### Logs
- **Location**: Console output and log files
- **Framework**: Log4j2 with custom configuration

## 🔧 Troubleshooting

### Common Issues

1. **Java Version Error**
   - Ensure JDK 21 is installed and JAVA_HOME is set correctly

2. **Maven Dependencies**
   - Run `mvn clean install` to download dependencies

3. **Selenium Grid Connection**
   - Ensure Selenium Grid is running before executing tests
   - Check if port 4444 is available

4. **Browser Driver Issues**
   - WebDriverManager handles driver management automatically
   - Ensure browsers are updated to latest versions

5. **Allure Report Generation**
   - Ensure Allure CLI is installed and added to PATH
   - Check if `target/allure-results` directory exists

### Port Configuration
- **Selenium Grid**: Default port 4444
- **Allure Report Server**: Default port assigned by Allure

## 📁 Project Structure

```
IdentifyCourses/
├── src/
│   ├── main/
│   │   ├── java/com/identifycourses/
│   │   │   ├── base/           # Driver setup and base configurations
│   │   │   ├── models/         # Data models for test data
│   │   │   ├── pages/          # Page Object Model classes
│   │   │   └── utils/          # Utility classes (Excel, JSON, Config, Screenshot)
│   │   └── resources/
│   │       ├── config.properties
│   │       └── log4j2.xml
│   └── test/
│       ├── java/com/identifycourses/
│       │   ├── rerun/          # Retry mechanisms
│       │   ├── stepdefinitions/ # Cucumber step definitions
│       │   └── tests/          # TestNG test runner
│       └── resources/
│           ├── features/       # Cucumber feature files
│           ├── testdata/       # Test data (Excel and JSON)
│           ├── allure.properties
│           └── testng.xml
├── target/                     # Build output and test results
├── test-output/               # Allure reports and screenshots
├── selenium-server-4.35.0.jar # Selenium Grid server
├── selenium_grid.bat          # Grid startup script
├── run.bat                    # Test execution script
└── pom.xml                    # Maven configuration
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add/update tests as needed
5. Ensure all tests pass
6. Submit a pull request

## 📞 Support

For issues or questions:
1. Check the troubleshooting section
2. Review logs in console output
3. Check Allure reports for detailed test execution information

---

**Happy Testing! 🚀** 