### spring-mailing-list
This is a simple mailing list system with Spring Security, JPA/Hibernate and thymeleaf.
This application has an API to subscribe and unsubscribe from lists and a simple UI to manage mailing lists and export subscriptions.

####API
POST `/api/v1/subscribe/{listId}`
- Example request: `{"email": "example@email.com"}`
- Responds: `201 CREATED`

POST `/api/v1/unsubscribe/{listId}`
- Example request: `{"email": "example@email.com"}`
- Responds: `200 OK`