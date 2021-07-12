Selenium-TestNg-Java-Maven
=================

Selenium-TestNg : Automation Testing Using Java

This is a test assignment for application url https://todomvc.com/examples/angular2/ with some positive and some negative test cases.

Documentation
-------------
Step 1 : `mvn clean install`

Step 2 : `Run testng.xml`

It will execute all test scripts and generate an excel report at location /reports
 


Framework Architecture
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
		

Running test
--------------

Go to your project directory from terminal and hit following commands
* `mvn test (defualt will run on chrome browser)`
User can change browser from pom.xml file.

