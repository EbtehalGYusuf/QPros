
# Web App Testing using Selenium

This framework is used to execute test cases on "Automation Practice" Website

______________________________________________________________________________________________________

## Framework Design Pattern

The used design pattern is PageObject

Design Pattern:
1. Core Classes -->Contain locators and interactions for ex. any class in core.pageobjects.qpros
2. Base Classes -->Extend core classes having all methods and Extent Report steps for ex. any class in base.qpros
3. Test Set Classes:
    a.Test Cases Classes -->Contain implemented test cases for ex. any class in testCases Packet
    b. Configuration Class -->Contains all
    required methods for initializing driver and opening URL for ex. any class in configurations Packet
4. All required dependencies are added in pom.xml including maven_surefire that has testNG file name in it
5. Extensions Folder:
    AdBlock extensions are added to block ads 
______________________________________________________________________________________________________

## Reports

There is an automatically generated Extent Reports

Extent Report Configuration Class is: 
    ./ExtentReportsManagercore/pageobjects/qpros/ExtentReportsManager.java

Output Folder is: .
    ./target/extent-report

Report Name Control:
    Located in testNG.xml

Report Naming Convention:
reportName + currentTime.html
______________________________________________________________________________________________________

## Parallel Test

To run test classes in parallel the following configs is added to testNG file:
    1. parallel="tests" thread-count="5"
    2. Two Tests are added for the same class in testNG file but with differnt browser values one for chrome and one for fireFox
    3. UNFORTUNATELY THERE IS AN ISSUE FACINBG ME IN PARALLEL TESTING IT JUST NEEDS MORE TIME TO BE SOLVED
Check the following copy of the testNG for declaration:
    <suite name="RegressionSuite" parallel="tests" thread-count="5">
	<test name="Chrome Test">
		<parameter name="browser" value="firefox" />
		<parameter name="reportName" value="purchaseReport" />
		<classes>
			<class
				name="testCases.purchasingProcess">
			</class>
		</classes>
	</test>

	<test name="FireFox Test">
		<parameter name="browser" value="firefox" />
		<classes>
			<class
				name="testCases.purchasingProcess">
			</class>
		</classes>
	</test>
</suite> 
______________________________________________________________________________________________________
## Executing Automation Tests

So as to run automation test do the following:
    1. Right Click on pom.xml file and Click on Run As-->Maven Test
