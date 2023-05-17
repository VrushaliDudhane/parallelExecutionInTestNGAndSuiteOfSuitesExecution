# parallelExecutionInTestNGAndSuiteOfSuitesExecution
Demonstration of Parallel execution of Methos, classes and Tests in TestNG along with execution of suite of suite Integration of spark Extent report as well

To clone the project
git clone httpsgithub.comVrushaliDudhaneparallelExecutionInTestNGAndSuiteOfSuitesExecution.git

To Run the classes in parallel
mvn test -DxmlFileToRun=ParallelExecutionOfClassesTestng.xml

To run the methods in parallel
mvn test -DxmlFileToRun=ParallelExecutionOfMethodsTestng.xml

To run the suit of suites
mvn test -DxmlFileToRun=suiteOfSuitesTestng.xml


This repository demonstrates how to do parallel testing using TestNG as the testing framework. 

In this project, I am following the single responsibility model. So each test method has single and independent responsibility. 

In this framework, I have created the instance of ThreadLocal which contains all the instances of webdriver.
This is essentially needed when we run the methods in parallel 

Inside BaseTest class, I have defined methods annotated with @BeforeMethod (Which initialize webdriver for browser parameter passed) and @AfterMethod (Which quit the browser)

These methods are essentially synchronized because when we run methods in parallel all the threads which are running methods try to acquire the instance of webdriver which can create unsynchronized behavior and loss of resources.
