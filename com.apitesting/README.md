# API TESTING USING REST ASSURED AND HAMCREST

## Why REST Assured?
REST Assured simplify and make simple the test scripting. This allows to focus more on the test scripting and to not waste time configuring a Framework with custom HTTP calls. It also has built-in support to Hamcrest matchers, this means that we can write test that resemble English sentences. This makes the tests very readable. We could use REST Assured without Hamcrest, but this means that more java code is required to write the assertions.

## Why using pure JUnit and not Cucumber?
The API to tests are fairly simple, and also Hamcrest makes the tests already readable. In this case there is no point to use Cucumber which adds more complexity to the framework.

## Why using Maven?
Maven allows to install the required packages really easy. Also, it makes the framework portable since Maven will install all the required packages every time the Test suite is executed.

## Preparation 
Maven and Java needs to be installed and configured. Please follow [this tutorial](https://www.mkyong.com/maven/how-to-install-maven-in-windows/) to install Maven and configure it. 

##Run the tests from the command line
Once Maven and Java are configured, navigate to the project folder using the command line. Once inside the folder execute **mvn test**, this will install the missing packages and start the test execution. At the end of the execution it will return the tests results and also the location where the report -as txt file- is stored
