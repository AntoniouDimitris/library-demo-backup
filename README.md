# library-demo-backup

A spring-hibernate library example application for REST APIs
User API
Book API
Lending API

API Endpoints

Return user list
GET
http://localhost:8080/spring-crm-rest/api/customers

Return a user
GET
http://localhost:8080/spring-crm-rest/api/customers/{customerId}

Add a user
POST
http://localhost:8080/spring-crm-rest/api/customers

Update existing user
PUT
http://localhost:8080/spring-crm-rest/api/customers

Deleting user
DELETE
http://localhost:8080/spring-crm-rest/api/customers/{customerId}

Return book list
GET
http://localhost:8080/spring-crm-rest/api/books

Add a book
POST
http://localhost:8080/spring-crm-rest/api/books

Delete a book
DELETE
http://localhost:8080/spring-crm-rest/api/books/{bookId}

Lend a book
GET
http://localhost:8080/spring-crm-rest/api/lendbook/{customerId}/{bookId}
