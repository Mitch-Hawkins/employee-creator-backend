# Employee Creator - Spring Backend

<!--
{add test badges here, all projects you build from here on out will have tests, therefore you should have github workflow badges at the top of your repositories: [Github Workflow Badges](https://docs.github.com/en/actions/monitoring-and-troubleshooting-workflows/adding-a-workflow-status-badge)} -->

<!-- ## Demo & Snippets

-   Include hosted link
-   Include images of app if CLI or Client App

--- -->

## Requirements / Purpose

- I needed a web application to create, list, modify and delete employees. The application should consist of a spring RESTful API and a React Typescript frontend. The schema for the employee is left to the criteria of the candidate.
- This project was something I picked to work on, as it allows me to gain more experience not only with full stack development, but also with frameworks that are heavily used in the industry.
- As mentioned, this project is to be built with Spring as the Backend in Java. This is a framework I've had prior experience with in doing my To Do List full stack app, which used a similar stack. However, I plan to make this employee creator app a bit more well rounded, with more testing and error handling that is fully fledged out.

---

## Build Steps

- This project is currently a WIP. A docker container can be run, but it needs a MySQL database to talk to. Will update with further build details as the project is further completed

---

## Design Goals / Approach

- My main goal with this backend was to cover as many aspects of a simple CRUD application as possible, and explore more areas of back end development than I have before. Some areas I look forward to working on and uncovering more information and experience include
  - Error Handling (Including creating custom exceptions)
  - Backend testing with JUnit and Jupiter (Mocks with Mockito)
  - Experimenting with different logging strategies, and finding what works for this app
  - Exploring different security configurations
- Alongside working with different areas from within spring, I also found this to be a great opportunity to explore other aspects of development outside the framework, particularly with how the project is built and hosted, as there are many different patterns and solutions. Currently looking at a combination of running the backend as a docker container from Microsoft Azure services, to get an experience with both Docker and using Azure.

---

## Features

- Current Features:
  - Simple CRUD functionality, ability to create, read, update and delete Employees and their respective information from a MySQL database
  - Basic Error handling that uses Springs built in Validation Exceptions, and a custom NotFoundException that sends concise information to be used and displayed for user feedback in the front end.
  - Simple testing of the EmployeeService class, testing the calls are made to the database for all services
  - The Employee entity which contains validation for the following fields
    - ID
    - First Name
    - Middle Name
    - Last Name
    - Email
    - Phone Number
    - Start Date
    - Employment Type
    - Hours Per Week

---

## Known issues

- Docker Container will run, but struggles to connect to any database networks cross container
- Certain error messages can have a little bit more data to send back to the front end

---

## Future Goals

- Features to be Implemented:
  - More exceptions with constructed feedback to be sent to the front end (Server Errors, Authorisation errors, etc)
  - Some more configuration around Web Security (CORS) to get experience with good industry practices.
  - More test suites for other classes involved in the back end
  - Experimenting with implementing a proper well-thought logging strategy to help with feedback on service processes as well as dissecting any errors
  - Creation of proper documentation for all endpoints of the app (Potentially with Swagger UI)
  - Ability to run and host the application in a Docker Container that has the ability to talk to different SQL Databases

---

## Change logs

### Thursday 14/03/2024

11:22am

- Begun the creation of the back end application - Installed Dependencies:
  - Spring Web
  - Validation
  - Spring Data JPA
  - MySQL Driver
  - Spring Devtools
  - Lombok
- Created mitch_employee_creator database on local machine
- Confirmed application runs
- Created basic Entity and CreateDTO boilerplate code, as well as the EmployeeRepository magic.

11:50am

- Implemented first Post and GetAll requests.
- Eliminated Pattern matching for phone number and email to test database functionality first, will adjust validation and error handling next

1:07pm

- Created Global Error Handling for any Validation Errors upon creation of a new Employee
- Used the @AllArgsConstructor annotation with the build command through lombok to create and save the Employee in the service to the repo
- Create a GetEmployeeById controller and findById service that throws a custom EmployeeNotFoundException if the ID is not matched to a field in the database
- Confirmed both the Validation exception and EmployeeNotFound function as expected.

5:53pm

- Finished simple CRUD functionality with patch/update requests and delete requests.
- Included basic back end error handling including simple user feedback messages as well as correct error codes.
  Installed model mapper and used that to map the UpdateEmployeeDTO to the Employee entity for the Patch service

### Friday 15/03/2024

12:07pm

- Started work on back end unit testing, wrote first simple test for EmployeeService using Mockito and Junit Jupiter
- Tests getAllEmployees and test is currently passing as expected.
  12:50pm
- Wrote similarly simple test for EmployeeService for getEmployeeById. Not sure at this point how extensive to test these individual services
- Added swagger to pom.xml and confirmed swagger-ui has created basic documentation

### Monday 18/03/2024

9:34am

- Created more unit tests, specifically for createEmployee, as well as updateEmployee
- Updated both CreateEmployeeDTO and UpdateEmployeeDTO to use hard-coded constructors, rather than default lombok annotations, as the tests can have a hard time viewing the lombok ones, even with a public access level + modifier.

### Wednesday 20/03/2024

11:22am

- Improved the Entity and DTO's for the Employee entity, added more fields including middleName, employmentType, startDate and hoursPerWeek
- employmentType uses a custom enum of EmploymentType. Consists of FullTime, PartTime, Casual and Contract.
- Updated createEmployee service in EmployeeService to use modelMapper for mapping data to the Employee.class, as opposed to writing a large constructor
  - This allows me to be able to scale the employee entity and dto's to be as complex or simple as I would like
- Have not yet updated the EmployeeServiceTests to use the new constructor of the improved entity.

12:35pm

- Begun first attempt at containerizing the application using docker.
- Created Dockerfile. Changed pom.xml to use a specific jar name
- Corrected tests to use the correct constructors and variable types. Tests currently all passing as expected.
- Moved EmploymentType enum to its own file. A bit of a quick and dirty solution at the moment, but will do for the time being.
- Prepared project to be containerized.
- Added simple base url endpoint that returns a string, for debugging purposes.

---

## Current Struggles

- As mentioned, this app is my first experience with containerising an application. Whilst the process of using docker and creating a container from the image seems to work smoothly enough, I find it very difficult to get the application to connect to the MySQL database I would like it to use, whether that be running on my Host Machine, or in another docker container. I've used different URLs, Network Configurations and the like. I'm sure I will find a solution as I keep exploring more about Docker Containers and playing with some other properties and network options

---

## Further details, related projects, reimplementations

- NOTE: The front end for this app has not yet been developed. However, the link to the front end repository is here: https://github.com/Mitch-Hawkins/employee-creator-frontend
