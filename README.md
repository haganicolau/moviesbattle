### Battle Movies API

This is an API project developed with Spring 3.2.5, Maven, and Java 17. The API was created to manage the "Battle Movies" game, where players compete by answering questions about movies.

#### Project Description

The API provides endpoints to create quizzes, answer questions about movies, get player scores, and more. The goal is to provide a fun platform for players to test their knowledge of movies.

Inside the `docs` folder, you'll find the Swagger of the REST API for viewing and interacting with the endpoints. Additionally, there's also a Postman export to facilitate importing and testing the endpoints.

### Docs folder
- Swagger api documentation
- Postman export
- Entity Relationship Diagram

#### Project Commands

To build the project, execute the following commands:

```bash
mvn clean
mvn install
```

To execute the tests commands:
```bash
mvn test
```

#### cURLs and Responses

Here are some examples of how to use the API with cURL:

1. **Get a Quiz:**
    ```bash
    curl --location --request GET 'http://localhost:8081/battlemovies/quiz' \
    --header 'Authorization: Basic dXNlcjE6cGFzc3dvcmQ='
    ```
   **Response (If Exists):**
    ```json
    {
        "startDateTime": "05-16-2024 01:02:29",
        "status": "ACTIVE",
        "id": 1,
        "mistakes": 0
    }
    ```
   **Response (If Not Exists):**
    ```
    HTTP/1.1 404 Not Found
    ```

2. **Create a Quiz:**
    ```bash
    curl --location --request POST 'http://localhost:8081/battlemovies/quiz/start' \
    --header 'Authorization: Basic dXNlcjE6cGFzc3dvcmQ='
    ```
   **Response:**
    ```json
    {
        "startDateTime": "05-16-2024 01:02:29",
        "status": "ACTIVE",
        "id": 1,
        "mistakes": 0
    }
    ```

3. **Get a Question:**
    ```bash
    curl --location --request GET 'http://localhost:8081/battlemovies/1/question' \
    --header 'Authorization: Basic dXNlcjE6cGFzc3dvcmQ='
    ```
   **Response:**
    ```json
    {
        "quiz": {
            "startDateTime": "05-16-2024 01:02:29",
            "status": "ACTIVE",
            "id": 1,
            "mistakes": 0
        },
        "movies": [
            {
                "title": "Wonder Woman 1984",
                "year": "2020",
                "imdbId": "tt7126948"
            },
            {
                "title": "Kung Fu Panda 4",
                "year": "2024",
                "imdbId": "tt21692408"
            }
        ],
        "status": "UNANSWERED",
        "questionId": 1
    }
    ```

4. **Answer a Question:**
    ```bash
    curl --location --request POST 'http://localhost:8081/battlemovies/1/answer' \
    --header 'Content-Type: application/json' \
    --header 'Authorization: Basic dXNlcjE6cGFzc3dvcmQ=' \
    --data-raw '{
        "titleMovie":"Wonder Woman 1984",
        "questionId":"1",
        "imdbId":"tt7126948"
    }'
    ```
   **Response:**
    ```json
    {
        "message": "Right answer! You earned 1 point"
    }
    ```

5. **Get Scores:**
    ```bash
    curl --location --request GET 'http://localhost:8081/battlemovies/score' \
    --header 'Authorization: Basic dXNlcjE6cGFzc3dvcmQ='
    ```
   **Response:**
    ```json
    {
        "scores": {
            "user1": 1,
            "user2": 5,
            "user3": 3
        }
    }
    ```
   