# Welcome to CRUD API of Employee

### Steps to follow to run the API

- Unzip the file or clone it
- Open with your favorite IDE
- Open src\main\java\com\example\employee_api\EmployeeApiApplication.java
- Run EmployeeApiApplication.java as java file

**A local server will be started at http://localhost:8080**

### Following are the endpoints which you can access

---

- For Department Routes

  - GET - [To get all departments](http://localhost:8080/departments)
    http://localhost:8080/departments
  - GET - [To get a department by id](http://localhost:8080/departments/1)
    http://localhost:8080/departments/1
  - POST - [To create a department](http://localhost:8080/departments)
    http://localhost:8080/departments
  - PUT - [To update a department](http://localhost:8080/departments/2)
    http://localhost:8080/departments/2
  - DELETE - [To delete a department](http://localhost:8080/departments/1)
    http://localhost:8080/departments/1

- For Employee Routes
  - GET - [To get all employees with departmentId](http://localhost:8080/departments/1/employees)
    http://localhost:8080/departments/1/employees
  - GET - [To get an employee by id](http://localhost:8080/employees/1)
    http://localhost:8080/employees/1
  - POST - [To create an employee](http://localhost:8080/departments/1/employees)
    http://localhost:8080/departments/1/employees
  - PUT - [To update an employee](http://localhost:8080/departments/1/employees/1)
    http://localhost:8080/departments/1/employees/1
  - DELETE - [To delete an employee](http://localhost:8080/employees/1)
    http://localhost:8080/employees/1
  - POST - [To create list of employees from CSV file](http://localhost:8080/employees)
    http://localhost:8080/employees

---

**This API has all the required log messages and exception handling**
