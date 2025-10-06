# Online_Quiz_Application_API
Build the backend API for a simple quiz application.

## **Project Description**

The **Online Quiz Application API** is a backend system that allows creating, managing, and taking quizzes. Users can create quizzes, add multiple-choice or single-choice questions, fetch questions for a quiz, and submit answers to receive a score.

### **Core Features:**

- **Quiz Management:**
    - Create a quiz with a title.
    - Add questions to a quiz. Each question can have multiple options, with one marked as correct.

- **Quiz Taking:**
    - Fetch all questions for a specific quiz. The correct answer is hidden in this response to prevent cheating.
    - Submit answers for a quiz and receive a score.
        - The request body contains an array of question IDs and the selected option IDs.
        - The response returns the total score. Example:
        ```json
        {
            "score": 3,
            "total": 5
        }
        ```
## **Tech Stack**
- **Backend:** Java, Spring Boot  
- **Database:** MySQL  
- **Build Tool:** Maven  
- **Testing:** JUnit, Mockito


## **Setup & Run Instructions**

### 1. Install Prerequisites
- **Java 17** (or higher)
- **Maven** (for building the project)
- **MySQL Workbench** (for database management)
- **IDE** like IntelliJ IDEA or Eclipse (optional) or sts

### 2. Database Setup
1. Open **MySQL Workbench** and connect to your local MySQL server.
2. Create a new database named `online_quiz_db`.
   -- CREATE DATABASE online_quiz_db
   
4. Inside this database, create the following tables:
   - **quiz_information**: Stores quiz details (id, title).
   - **quesions_information**: Stores quiz questions id, question, quiz_id).
   - **options**: Stores question options (id, answer, correct_Or_Not, option, questions_id).

    ** for quiz_in formation **
  CREATE TABLE `quiz_information` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idquiz_information_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

   ** for quesions_information **
   CREATE TABLE `quesions_information` (
  `id` int NOT NULL AUTO_INCREMENT,
  `question` varchar(45) DEFAULT NULL,
  `quiz_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

 ** for options **
 CREATE TABLE `options` (
  `id` int NOT NULL AUTO_INCREMENT,
  `answer` varchar(45) DEFAULT NULL,
  `correct_Or_Not` varchar(45) DEFAULT NULL,
  `option_id` varchar(45) DEFAULT NULL,
  `questions_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

 
**Notes for Table Fields:**
- All `id` fields should be **Primary Key (PK), Not Null (NN), and Auto Increment (AI)**.
- `questions_id` in `options` should reference the `questions` table.
- `quiz_id` in `questions` should reference the `quiz_management` table.
 
### 3. Configure Application and API End Points
3.0. configuration
- Open `application.properties` (or `application.yml`) and set your MySQL credentials:
```properties
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/online_quiz_db
spring.datasource.username=YOU ARE USER NAME
spring.datasource.password=YOU ARE PASSWORD

### 3.1. API Endpoints

####  Create Quiz
- **URL:** `http://localhost:8080/OnlineQuizApp/saveQuiz`  
- **Method:** `POST`  
- **Input (JSON Body):**
```json
{
    "title": "science quiz"
}
- **Output (JSON Body):**

{
    "id": 3,
    "title": "science quiz",
    "questions": []
}
Description : Creates a new quiz with a title.and it shows an empty questions because we just created quiz not added any questions

#### 3.2 Add Questions to a Quiz
- **URL:** `http://localhost:8080/OnlineQuizApp/addQuesion/{quiz_id}`  
  - Replace `{quiz_id}` with the ID of the quiz (e.g., 1, 2, etc.).
      **for example http://localhost:8080/OnlineQuizApp/addQuesion/1**
- **Method:** `POST`  
- **Input (JSON Body):**
```json
{
  "name": "java is a---",
  "optionsEntities": [
    {"answer": "oop lang", "option": 1, "correct_Or_Not": true},
    {"answer": "library", "option": 2, "correct_Or_Not": false},
    {"answer": "framework", "option": 3, "correct_Or_Not": false},
    {"answer": "any", "option": 4, "correct_Or_Not": false}
  ]
}

- **Output (JSON Body) if quiz exists:**
{
    "id": 3,
    "name": "java is a---",
    "optionsEntities": [
        {"id": 9, "option": 1, "answer": "oop lang", "correct_Or_Not": true},
        {"id": 10, "option": 2, "answer": "library", "correct_Or_Not": false},
        {"id": 11, "option": 3, "answer": "framework", "correct_Or_Not": false},
        {"id": 12, "option": 4, "answer": "any", "correct_Or_Not": false}
    ]
}
- **Error Response if quiz does not exist:**
Quiz not found with id: {quiz_id}
Description: Adds a new question with multiple options to the specified quiz. If the quiz ID does not exist in the database, an error message is returned.

#### 3.3 Get All Questions for a Quiz
- **URL:** `http://localhost:8080/OnlineQuizApp/getAll/{quiz_id}`
 - Replace `{quiz_id}` with the ID of the quiz (e.g., 1, 2, etc.).
      **for example http://localhost:8080/OnlineQuizApp/addQuesion/1**    
- **Method:** `GET`  
- **Output (JSON) if quiz exists:**
```json
[
    {
        "id": 4,
        "name": "java is a---",
        "optionsEntities": [
            {"option_id": 1, "text": "oop lang"},
            {"option_id": 2, "text": "library"},
            {"option_id": 3, "text": "framework"},
            {"option_id": 4, "text": "any"}
        ]
    }
]
 - **Error Response if quiz does not exist:**
Quiz not found with id: {quiz_id}

Description: Fetches all questions for a specific quiz. Correct answers are hidden in this response to prevent cheating.

#### 3.4 Submit Answers for a Quiz
- **URL:** `http://localhost:8080/OnlineQuizApp/submit/{quiz_id}`  
  Replace `{quiz_id}` with the ID of the quiz, e.g., `1`, `2`, etc.
      **for example http://localhost:8080/OnlineQuizApp/addQuesion/1**
- **Method:** `GET`  
- **Input (JSON Body):**
```json
[
    {
        "question_id": 1,
        "option_id": 1
    },
    {
        "question_id": 3,
        "option_id": 4
    }
]

- **Output (JSON) if quiz exists:**
```json
{
    "score": 0,
    "total": 1
}

 - **Error Response if quiz does not exist:**
Quiz not found with id: {quiz_id}

Description: Submits answers for a specific quiz and returns the score along with the total number of questions. If the quiz ID is invalid, an error message is returned.

#### 3.5 Get All Quizzes
- **URL:** `http://localhost:8080/OnlineQuizApp/allQuiz`
- **Method:** `GET`
- **Input:** None
- **Output (JSON):**
```json
[
    {
        "id": 1,
        "title": "Science Quiz",
        "questions": [
            {
                "id": 101,
                "name": "2+2",
                "optionsEntities": [
                    {
                        "option": 1,
                        "answer": "4"
                    },
                    {
                        "option": 2,
                        "answer": "3"
                    }
                ]
            },
            {
                "id": 102,
                "name": "Capital of India?",
                "optionsEntities": [
                    {
                        "option": 1,
                        "answer": "New Delhi"
                    },
                    {
                        "option": 2,
                        "answer": "Mumbai"
                    }
                ]
            }
        ]
    },
    {
        "id": 2,
        "title": "Math Quiz",
        "questions": []
    }
]

Description: Fetches all quizzes along with their associated questions and options. Useful for displaying a list of quizzes with questions in the application.





## 4. Running Test Cases

This project includes unit tests for the core logic of the quiz application, such as answer submission and scoring functionality. The tests are implemented using **JUnit 5** and **Mockito**.

### Steps to Run Tests:

1. **Using IDE (IntelliJ/Eclipse):**
   - Open the project in your IDE.
   - Navigate to the `Online_Quiz_ServiceTest` class located in:
     ```
     src/test/java/com/Online_Quiz_Application_API/Service/Online_Quiz_ServiceTest.java
     ```
   - Right-click on the class or individual test methods and select **Run** as Junit.


### What is Tested:
- Correct scoring of quiz submissions for single-choice questions.



## 5.## **Assumptions and Design Choices**

### Assumptions:
- Each quiz can have multiple questions.
- Each question can be single-choice or multiple-choice (for now, the implementation supports single-choice scoring).
- Each question must have at least one option marked as correct.
- The correct answer is **never** exposed when fetching questions for a quiz to prevent cheating.
- Quiz IDs and Question IDs are unique and auto-generated by the database.

### Design Choices:
- **Java & Spring Boot** were used for the backend to leverage dependency injection, REST controllers, and JPA for database interaction.
- **MySQL** is used for relational storage of quizzes, questions, and options.
- **DTOs** (Data Transfer Objects) are used for API responses to hide sensitive fields like correct answers and internal IDs.
- **Exception Handling** is implemented via custom exceptions (`Quiz_Not_Found`) to return meaningful error messages.
- **Unit Testing** is implemented with JUnit 5 and Mockito to ensure core logic (like scoring) works correctly.
- **JSON API format** is standardized for all endpoints to maintain consistency.
- The API assumes clients provide valid option IDs when submitting answers; invalid option IDs are ignored in scoring.



