# AI Chatbot

This Spring Boot backend application provides a RESTful API for scheduling appointments between landlords and tenants.
It leverages the power of Artificial Intelligence, specifically the DeepSeek API, to intelligently suggest optimal
meeting times based on availability, preferences, and potentially other contextual information.

This backend handles user authentication, appointment creation, retrieval, and utilizes the DeepSeek API for smart
scheduling recommendations. It aims to streamline the appointment booking process, making it efficient and convenient
for both landlords and tenants.

## Architecture

The application follows a typical N-layered architecture pattern, which includes:

- **Controller Layer**: Handles incoming HTTP requests and responses. It acts as an interface between the client and the
  service layer.
- **Service Layer**: Contains the business logic of the application. It processes requests from the controller layer and
  interacts with the repository layer to perform CRUD operations.
- **Repository Layer**: Interacts with the database to perform CRUD operations. It uses Spring Data JPA to simplify
  database interactions.
- **Model Layer**: Contains the data models (entities) that represent the structure of the data in the database.
- **Configuration Layer**: Contains the configuration classes for the application, including security and database
  configurations.

## Getting Started

To get started with the AI chatbot, you need to have Java 17 and Maven installed on your machine. You can download
Java 17 from the [official website](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html) and Maven from
the [official website](https://maven.apache.org/download.cgi).
Once you have Java and Maven installed, you can clone the repository and navigate to the project directory:

```bash
git clone https://github.com/clementwanjau/ai_chatbot.git
cd ai_chatbot
```

## Environment Variables

To run the chatbot, you need to set the following environment variables:

- `DEEPSEEK_API_KEY`: Your DeepSeek API key.
- `JWT_SECRET`: A secret key used for signing JWT tokens. To generate a random secret key, you can use the following
  command in your terminal:

```bash
openssl rand -base64 32
```

- `DB_URL`: The URL of the Postgres database. For example, `jdbc:postgresql://localhost:5432/mydatabase`.
- `DB_USER`: The username for the Postgres database.
- `DB_PASSWORD`: The password for the Postgres database.

> Note that if
> you have docker installed you can use the `docker-compose.yml` file provided to run a local postgres database. To do
> this, run the following command in the root directory of the project: ```bash docker-compose up -d```. You can always
> change the username and password in the `docker-compose.yml` file.

## Building And Running Locally

To build the project locally, you need to have Docker installed. Once you have Docker installed, you can build the
project using the following command:

```bash
./mvnw spring-boot:run
```

This will start the Spring Boot application and run it on your local machine. You can then access the chatbot at
`http://localhost:8080`.

## Running Tests

To run the tests, you can use the following command:

```bash
./mvnw test
```

This will run all the tests in the project and display the results in the terminal.

# API Endpoints

The chatbot exposes the following API endpoints:

- `GET /api/v1/chat?message={message}`: This endpoint is used to send a message to the chatbot and receive a response.
  The message should be
  sent as a query parameter in the URL. The response will be in JSON format and will contain the chatbot's response.
  This is how you
  will be able to interact with the chatbot.
- `GET /api/v1/appointments`: This endpoint is used to retrieve all the appointments stored in the database. The
  response will be in JSON format and will contain a list of all the appointments.
- `POST /api/v1/auth/register`: This endpoint is used to register a new user. The request body should contain the user's
  username and password in
  JSON format. The response will be in JSON format and will contain a JWT token and refresh token.

> ###### Sample request body:
> ```json
> {
>   "fullName": "Test User",
>   "username": "testuser",
>   "password": "testpassword"
> }
> ```

- `POST /api/v1/auth/login`: This endpoint is used to log in a user. The request body should contain the user's username
  and password in JSON format. The response will be in JSON format and will contain the user's ID and a JWT token.

> ###### Sample request body:
> ```json
> {
>  "username": "testuser",
>  "password": "testpassword"
> }

- `POST /api/v1/auth/refresh`: This endpoint is used to refresh the JWT token.

## API Usage

To use the API, you can use a tool like Postman or curl to send requests to the endpoints. For example, to get
a list of all appointments, you can use the following curl command:

```bash
curl -X GET \
  "http://localhost:8080/api/v1/appointments" \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer <your_jwt_token>'
```

This will send a GET request to the `/api/v1/appointments` endpoint and return a list of all appointments in JSON
format.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.