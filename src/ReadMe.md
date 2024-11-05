# Requirements
* Java 11
* Maven 3
* Allure 2


## Java Installation
Java version 11 is required.
Command:
```bash
$ java --version
```
Ensure your JAVA_HOME environment to the location of the installed JDK.
## Maven Installation
1. Download Maven
2. Unzip the distribution archive to the directory you wish to install Maven.
3. Add Maven to the PATH
4. Verify Maven was correctly installed
```bash
$ mvn --version
```
## Allure Installation
1. Download Allure
2. Unzip the distribution archive to the directory you wish to install Allure.
3. Add Allure to the PATH
4. Verify Allure was correctly installed
```bash
$ allure --version
```

## How to build project
```bash
$ mvn package
```
### Setup

* Clone the repo
* Install dependencies `mvn compile`
## How to run tests

* To run Suite, run `mvn clean test`

## How to see test report
```bash
$ allure serve
```
# Built with
- Maven : Build automation & dependency management
- TestNG : Testing framework, control flow of tests
- Allure : Report tool

### Implements
* Rest Assured : Java library for automating and simplifying the testing of RESTful APIs
* Business Object Model

# About Tests
- One Happy Path Test for each API endpoints of Books, Authors
- One Negative Cases Test for each API endpoints of Books, Authors
- Tests set to run in parallel through testng.xml
