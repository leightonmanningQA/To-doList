
Coverage: 93.9
# To-Do List Web Application 

This is an application that a user can interact with via a working website.. It is a To-Do List web application that can add different To-Do lists and tasks to be then displayed and completed. Both the tasks and the To-Do lists are stored inside a database. 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them


You will have to have java installed first. Download open JDK here
https://adoptopenjdk.net/index.html/

You will have to have maven installed as well. Download here
https://maven.apache.org/index.html

You will need spring tools to be able to develop the program.
https://spring.io/tools

Finally I'm using git for my version control and branches. I installed it from here
https://gitforwindows.org/


### Installing
A step by step series of examples that tell you how to get a development env running

1. Fork this repository so that you have your own copy of the files

```
Go to link https://github.com/leightonmanningQA/To-doList
and click the button in the top right that reads "Fork".

```
2. Head over to your forked repository now (It should be https://github.com/yourname/To-doList) you want to press the green button and copy the HTTPS link.

3. Open up git bash in the folder you want the project by right clicking and clicking "Git bash here"(If you dont see it git isn't installed correctly)

4.Once open you want to enter commands as follows

```
git clone your_link

```
5. Once the repository has been cloned you will have all the files needed to run and develop this application. Now we need to open this project in Spring Tools. Open Spring tools and go to
```
File, Import, Existing Maven Project
Browse root directory and select the folder the project is in.
Check the box and press finish.

```

6. Now you can run or develop this program. I'm running the program on the port 8082. You can change it if needed 
```
This can be found in the application.properties

```
7. Now you can run the application by right clicking the ToDoListApplication.java file and clicking 
```
Run as, Java Application

```
8. Once the program is running you should see a line in the console saying "Started ToDoListApplication" 

9. Navigate to the website http://localhost:8082/index.html (change the port if needed) for the landing page.

10. You can also access the database with the link http://localhost:8082/h2/

11. There is also swagger UI set up. Head to http://localhost:8082/swagger-ui/index.html to see the functionality and commands.



## Running the tests

Explain how to run the automated tests for this system. Break down into which tests and what they do

### Unit Tests 

Unit testing is where individual units / components of a software are tested. The purpose is to validate that each unit of the software performs as designed.

```
Right click on the testing portion of the project src/test/java and click run as Junit Test. 
To check coverage right click on the entire project and click coverage as Junit Test.
```
### Integration Tests 

To prove that each integration of the application is functioning as expected

```
These tests will be ran as part of the JUNIT test, follow steps above or.
Right click on either of the Integration tests and click run as JUnit test.
```

### Coding Style Tests 

I Used Sonarqube for static analysis of the code. Makes sure that bugs and security issues are caught. Also makes sure that we are using best coding practice.

```
For help on how to install and setup sonarqube https://docs.sonarqube.org/latest/setup/overview/

If sonarqube is all set up and running you would right click the pom.xml and click run as Maven build...
```

### Selenium Acceptance Tests 

I Used Selenium for the testing of my front end code. Again to run these follow the steps for unit testing.

```
The chromedriver.exe is in the src/main/resources file and it may need changing depending on the browser. The tests will also need to be changed depending on browser.
```

## Deployment

Add additional notes about how to deploy this on a live system
1. Open command line in the project folder
2. Type **mvn clean** , we do this so that we can remove old builds of our code before rebuilding it again.
3. Once the target folder has been removed you want to type **mvn package**, This will package all the source code in our Java project into the .jar file
4. After this the target file will return. Navigate to that folder with the command **cd target**
5. Run the program with **java -jar ToDoList-0.0.1.jar**

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Leighton Manning**  - [leightonmanning](https://github.com/leightonmanningQA)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments


