# Restful Booker API Automation

## Project Description

Restful Booker API Automation is a REST API testing framework developed
using Java, Rest Assured, TestNG, and Maven. The framework automates the
complete CRUD flow of the Restful Booker application, including
authentication, booking creation, retrieval, update, partial update, and
deletion.

The framework is designed with reusable components such as a common
BaseTest, centralized configuration, reusable request specifications,
JSON payload files, logging, reporting, and TestNG listeners to keep the
test suite clean and maintainable.

## Technologies Used

-   Java 
-   Rest Assured
-   TestNG
-   Maven
-   Log4j2
-   Extent Reports
-   Jackson Databind

## Framework Features

-   Authentication Token Generation
-   Health Check API Validation
-   Create Booking API Testing
-   Get Booking API Testing
-   Update Booking API Testing
-   Partial Update (PATCH) API Testing
-   Delete Booking API Testing
-   Positive and Negative Test Scenarios
-   JSON Payload Management
-   Centralized Configuration using ConfigReader
-   RequestSpecification Reusability
-   TestNG Listener Integration
-   HTML Extent Reports
-   Log4j2 Logging

## Project Structure

``` text
src
├── resources
│   └── config
├── test
│   ├── java
│   │   ├── base
│   │   ├── listeners
│   │   ├── reports
│   │   ├── test
│   │   └── utils
│   └── payload
```

## Test Execution

Run all test cases:

``` bash
mvn clean test
```

## Test Results

-   Total Test Cases: 50
-   Passed: 50
-   Failed: 0

## Reports

-   Extent Report
-   Log4j2 Execution Log
