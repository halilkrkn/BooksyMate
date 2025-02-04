# BooksyMate

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
    - [Backend](#backend-book-social-network)
    - [Frontend](#frontend-book-social-network-ui)
 
- [Getting Started](#getting-started)
- [License](#license)
- [Acknowledgments](#acknowledgments)
- [Acknowledgments for Training](#acknowledgments-for-training)
  
## Overview

**BooksyMate** is a full-stack application that enables users to manage their book collections and engage with a community of book enthusiasts. It offers features such as user registration, secure email validation, book management (including creation, updating, sharing, and archiving), book borrowing with checks for availability, book return functionality, and approval of book returns. The application ensures security using JWT tokens and adheres to best practices in REST API design. The backend is built with Spring Boot 3 and Spring Security 6, while the front end is developed using Angular with Bootstrap for styling.

## Features

- User Registration: Users can register for a new account.
- Email Validation: Accounts are activated using secure email validation codes.
- User Authentication: Existing users can log in to their accounts securely.
- Book Management: Users can create, update, share, and archive their books.
- Book Borrowing: Implements necessary checks to determine if a book is borrowable.
- Book Returning: Users can return borrowed books.
- Book Return Approval: Functionality to approve book returns.

#### Class diagram
![Class diagram](screenshots/class-diagram.png)

#### Spring security diagram
![Security diagram](screenshots/security.png)

#### Backend pipeline
![Security diagram](screenshots/be-pipeline.png)

#### Backend pipeline
![Security diagram](screenshots/fe-pipeline.png)

## Technologies Used

### Backend (book-network)

- Spring Boot 3
- Spring Security 6
- JWT Token Authentication
- Spring Data JPA
- JSR-303 and Spring Validation
- OpenAPI and Swagger UI Documentation
- Docker
- GitHub Actions
- Keycloak

### Frontend (book-network-ui)

- Angular
- Component-Based Architecture
- Lazy Loading
- Authentication Guard
- OpenAPI Generator for Angular
- Bootstrap

## Getting Started

Follow the branches step by step in this project. 
To get started with the BooksyMate project, follow the setup instructions in the respective directories:

- [Backend Setup Instructions](/backend/BooksyMate/README.md)
- [Frontend Setup Instructions](/frontend/booksy-mate/README.md)


## License

This project is licensed under the MIT License 2.0. See the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Special thanks to the developers and maintainers of the technologies used in this project. Their hard work and dedication make projects like this possible.

## Acknowledgments for Training
- We would like to thank Mr Bouali Ali for providing training within the scope of this project.
- Youtube: https://www.youtube.com/@BoualiAli
- Github: https://github.com/ali-bouali/book-social-network/tree/main
