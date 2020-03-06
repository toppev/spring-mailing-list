### spring-mailing-list
This is a simple mailing list system with Spring Security, JPA/Hibernate and thymeleaf.
This application has an API to subscribe and unsubscribe from lists and a simple UI to manage mailing lists and export subscriptions.

### Build
1. Clone this repository with `git clone https://github.com/toppev/spring-mailing-list.git`
 or `git clone git@github.com:toppev/spring-mailing-list.git`
2. Configure `src/main/resources/application.properties`
3. Build with `./gradlew build`
4. The jar file is located at `./build/libs` directory. Run it with `java -jar <jar file>`

#### API
POST `/api/v1/subscribe/{listId}`
- Example request: `{"email": "example@email.com"}`
- Responds: `201 CREATED`

POST `/api/v1/unsubscribe/{listId}`
- Example request: `{"email": "example@email.com"}`
- Responds: `200 OK`