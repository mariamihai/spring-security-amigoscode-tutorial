# Spring Security
My implementation for the [Spring Boot Security Course](https://amigoscode.com/courses/spring-boot-security) from Amigoscode.

  - [Description for each branch](#description-for-each-branch)
    - [Section 2 - Getting Started with Spring Security](#section-2---getting-started-with-spring-security)
      - [Newly developed API calls](#newly-developed-api-calls)
        - [Obtain one of the students](#obtain-one-of-the-students)
    - [Section 3 - Users Roles and Authorities](#section-3---users-roles-and-authorities)
    - [Section 4 - Permission Based Authentication](#section-4---permission-based-authentication)
      - [Newly developed API calls (management endpoints)](#newly-developed-api-calls-management-endpoints)
        - [Obtain all students](#obtain-all-students)
        - [Create a new student](#create-a-new-student)
        - [Update student](#update-student)
        - [Delete student](#delete-student)
    - [Section 5 - Cross Site Request Forgery](#section-5---cross-site-request-forgery)
    - [Section 6 - Form Based Authentication](#section-6---form-based-authentication)
    - [Section 7 - Database Authentication](#section-7---database-authentication)
    - [Section 8 - JSON Web Tokens](#section-8---json-web-tokens)
      - [API calls](#api-calls)
        - [Login and receive token](#login-and-receive-token)
        - [Send token with each request](#send-token-with-each-request)
  - [Status](#status)

## Description for each branch
### Section 2 - Getting Started with Spring Security
```
git checkout section-2-getting-started-with-spring-security
```
The username available for this section is `user` and it is using the generated password available in the logs.
(`Using generated security password: bf5ac150-92d2-47de-9715-2db137874388`)

The main page of the project http://localhost:8080 was whitelisted.

#### Newly developed API calls
##### Obtain one of the students

 * __URI:__ _api/v1/students/:studentId_
 
 * __Method:__ _GET_

 * __URL params:__ <br/>
    * required: <br/>
        `studentId=[Integer]`
    * optional: - <br/>
    
 * __Query params:__ <br/>
    * required: - <br/>
    * optional: - <br/>
        
 * __Successful call:__
 
    (with Basic Auth)
 
    * __Response:__
        * Code: 200 OK <br/>
        * Content:
        ```
        {
            "studentId": 1,
            "name": "Student 1"
        }
        ```
        
 * __Failed call:__
 
    (without authorization)
 
    * __Response:__
        * Code: 401 Unauthorized <br/>
        * Content:
        ```
        {
            "timestamp": "2020-09-22T12:30:01.402+0000",
            "status": 401,
            "error": "Unauthorized",
            "message": "Unauthorized",
            "path": "/api/v1/students/1"
        }
        ```

### Section 3 - Users Roles and Authorities
```
git checkout section-3-user-roles-and-authorities
```
Under this section more users were added. Available users:

| Username | Password |    Role  | Authorities |
| ---------| ---------| ---------| ------------|
| student1 | pass     | STUDENT* | STUDENT:READ, STUDENT:WRITE, COURSES:READ |
| steve    | pass012  | ADMIN    | STUDENT:READ, STUDENT:WRITE, COURSES:READ, COURSES:WRITE |

\* The authorities that should be associated with the STUDENT role were not added.

The available roles can be found in the `ApplicationUserRole` enum, while the permissions are defined in the `ApplicationUserAuthority` enum.

### Section 4 - Permission Based Authentication
```
git checkout section-4-permission-based-authentication
```
Available users:

| Username | Password |     Role     | Authorities |
| ---------| ---------| -------------| ------------|
| student1 | pass     | STUDENT*     | STUDENT:READ, STUDENT:WRITE, COURSES:READ |
| steve    | pass012  | ADMIN        | STUDENT:READ, STUDENT:WRITE, COURSES:READ, COURSES:WRITE |
| tom      | pass012  | ADMINTRAINEE | STUDENT:READ, COURSES:READ|

\* The authorities that should be associated with the STUDENT role were not added.

The available roles can be found in the `ApplicationUserRole` enum, while the permissions are defined in the `ApplicationUserAuthority` enum.

#### Newly developed API calls (management endpoints)
The defined endpoints evaluate the use of `hasAuthority` and `preAuthorize` with users with different roles and 
permissions and are not real CRUD implementations.

##### Obtain all students
For ADMIN and ADMINTRAINEE roles, for STUDENT:READ authority.

 * __URI:__ _management/api/v1/students_
 * __Method:__ _GET_

 * __URL params:__ <br/>
    * required: - <br/>
    * optional: - <br/>
    
 * __Query params:__ <br/>
    * required: - <br/>
    * optional: - <br/>
        
 * __Success response:__
    * Code: 200 OK <br/>
    * Content:
    ```
    [
        {
            "studentId": 1,
            "name": "Student 1"
        },
        {
            "studentId": 2,
            "name": "Student 2"
        },
        {
            "studentId": 3,
            "name": "Student 3"
        }
    ]
    ```

##### Create a new student
For ADMIN role, for STUDENT:WRITE authority.

 * __URI:__ _management/api/v1/students_
 * __Method:__ _POST_

 * __URL params:__ <br/>
    * required: - <br/>
    * optional: - <br/>
    
 * __Query params:__ <br/>
    * required: - <br/>
    * optional: - <br/>
 
 * __Data params:__ <br/>
    * required: <br/>
        student=[Student]
         ``` 
         {
             "name": "Student X"
         }
         ```
    * optional: - <br/>

 * __Success response:__
    * Code: 200 OK <br/>
 
 * __Fail response:__
    * Code: 403 Forbidden for the other roles.

##### Update student
For ADMIN role, for STUDENT:WRITE authority.

 * __URI:__ _management/api/v1/students/:studentId_
 * __Method:__ _PUT_

 * __URL params:__ <br/>
    * required: <br/>
        `studentId=[Integer]` <br/>
    * optional: - <br/>
    
 * __Query params:__ <br/>
    * required: - <br/>
    * optional: - <br/>
 
 * __Data params:__ <br/>
    * required: <br/>
        student=[Student]
         ``` 
         {
             "name": "Student X"
         }
         ```
    * optional: - <br/>

 * __Success response:__
    * Code: 200 OK <br/>

 * __Fail response:__
    * Code: 403 Forbidden for the other roles.

##### Delete student
For ADMIN role, for STUDENT:WRITE authority.

 * __URI:__ _management/api/v1/students/:studentId_
 * __Method:__ _DELETE_

 * __URL params:__ <br/>
    * required: <br/>
        `studentId=[Integer]` <br/>
    * optional: - <br/>
    
 * __Query params:__ <br/>
    * required: - <br/>
    * optional: - <br/>

 * __Success response:__
    * Code: 200 OK <br/>

 * __Fail response:__
    * Code: 403 Forbidden for the other roles.

### Section 5 - Cross Site Request Forgery
```
git checkout section-5-cross-site-request-forgery
```
Add the XSRF_TOKEN header in POST, PUT and DELETE requests when CSRF is not disabled in `ApplicationSecurityConfig.configure method`.

### Section 6 - Form Based Authentication
```
git checkout section-6-form-based-authentication
```
Custom login page. <br/>
Added a "Course" page with logout button. <br/>
Played with `SESSIONID` and `remember-me` cookies.

### Section 7 - Database Authentication
```
git checkout section-7-database-authentication
```
Adding custom `UserDetailsService` and custom `UserDetails` "faking" connecting to a database to obtain the users.

### Section 8 - JSON Web Tokens
```
git checkout section-8-jwt
```
#### API calls 
##### Login and receive token
 * __URI:__ _login_
 * __Method:__ _PUT_

 * __URL params:__ <br/>
    * required: - <br/>
    * optional: - <br/>
    
 * __Query params:__ <br/>
    * required: - <br/>
    * optional: - <br/>
 
 * __Data params:__ <br/>
    * required: <br/>
        usernameAndPasswordAuthenticationRequest=[UsernameAndPasswordAuthenticationRequest]
         ``` 
         {
             "username": "anna",
             "password": "pass"
         }
         ```
    * optional: - <br/>

 * __Success response:__
    * Code: 200 OK <br/>
    * Added Header:
    ```
    Authorization: Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhbm5hIiwiYXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6IlJPTEVfU1RVREVOVCJ9XSwiaWF0IjoxNjAwODc2Njc5LCJleHAiOjE2MDIwMjE2MDB9.vEYLlZgOl_TFQYxbCq3SIuKwgrs7_ilZ3VoUvqQvdXoOVPeYHd76hmfE9WUYoj2w
    ```

##### Send token with each request
For each request add `Authorization` Header with `Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhbm5hIiwiYXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6IlJPTEVfU1RVREVOVCJ9XSwiaWF0IjoxNjAwODc2Njc5LCJleHAiOjE2MDIwMjE2MDB9.vEYLlZgOl_TFQYxbCq3SIuKwgrs7_ilZ3VoUvqQvdXoOVPeYHd76hmfE9WUYoj2w`.

## Status
**[COMPLETED]** - As I finished the section of the course and the associated project, I am setting a personal status of "Completed" and will probably not update this repository in the near future as this was a learning project.
