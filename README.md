Selenium-TestNg-Java-Maven
=================

Selenium-TestNg : Automation Testing Using Java

This is a test assignment for application url https://todomvc.com/examples/angular2/ with some positive and some negative test cases.

Documentation
-------------
Step 1 : `mvn clean install`

Step 2 : `Run testng.xml`

It will execute all test scripts and generate an excel report at location /reports.By Default it will run on chrome browser.
User can change browser through pom.xml.
 


Project Structure
--------------
	Project-Name
		|_reports
		|_src/main/java
		|       |_Pages
		|           |_HomePage.java
		|       |...
		|	|_Services
		|           |_BaseServices.java
		|           |_CommonServices.java	
		|	|_Utils
		|	    |_ExcelGenerate.java
		|	    |_TestRunData.java
		|	    |_UtilConstants.java	
		|_src/main/resources
		|       |_project.properties
		|_src/test/java
		|	|_FunctionalTest
		|           |_PageTest.java

Test Cases
--------------

###  TC1: navigateToPage()
This test script verifies all default page elements on page navigation
#### Steps:
1. Open Url
2. Validate Page Header as "todos"
3. Validate Input box is displayed and also enabled
4. Validate Input field label as "What needs to be done?"
5. Validate first footer text as "Double-click to edit a todo"
6. Validate second footer text as "Created by Sam Saccone and Colin Eberhardt using Angular2" 
7. Validate third footer text as "Part of TodoMVC"

###  TC2: validateSearch()
This test script verifies all page elements after search operation with single data
#### Steps:
1. Open Url
2. Perform Search with random string
3. Validate Search Result section along with one close button and radio button
4. Compare Search result with input data
5. Compare footer text with search data count as "1 item left"
6. Validate blank Search input field 

###  TC3: checkMultipleSearch()
This test script verifies all page elements after search with multiple data
#### Steps:
1. Open Url
2. Perform search with two random strings
3. Validate search section with two radio buttons and two close buttons
4. Compare both search data along with user inputs
5. Validate footer text as "2 items left"
6. Validate blank Search input field

###  TC4: verifyRadioBtnSelection()
This test script verifies all page changes on selection of radio button of Search data
#### Steps:
1. Open Url
2. Perform search with random string
3. Select radio button
4. Validate radio button selection
5. Validate search data is strikethrough
6. Validate footer text as "0 items left"
7. Validate presence of Clear completed button

###  TC5: verifyUncheckRadioBtn()
This test script verifies all page changes after radio button uncheck operation
#### Steps:
1. Open Url
2. Perform search
3. Select Radio button then de-select using double click
4. Validate search text is not strikethrough
5. Radio button is not selected 

###  TC6: editSearchData()
This test script verifies all page changes after performing edit operation on search result data
#### Steps:
1. Open Url
2. Perform Search
3. Edit Search result data on double click
4. Validate field is editable
5. Add new random string in the same field
6. Validate concatenated string presence in searxh result

###  TC7: removeSearchData()
This test script verifies all page element after removal of search result data through edit event
#### Steps:
1. Open Url
2. Perform search
3. Edit search result data field
4. Clear field data
5. Validate presence of radio button and close button
6. Validate blank search result data field

### TC8: removeSelectedData()
This test script verifies all page element after removal of one data through Clear completed button for which radio button is selected earlier
#### Steps:
1. Open Url
2. Perform Search with multiple data
3. Validate footer data as "2 items left"
4. Select radio button for one data
5. Click on Clear completed button 
6. Validate presence of 1 close button and 1 radio button and 1 search result data 
7. Validate result data set has only one data which is second random string

Reports
--------------
An excel report will be generated after execution of Test Suite at location /reports

Format of Report is shown below:

[![test-Report-Sample.jpg](https://i.postimg.cc/tTtkG4CQ/test-Report-Sample.jpg)](https://postimg.cc/ygxcFBZv)
