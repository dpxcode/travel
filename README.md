# Travel API Project

This is a Java Spring Boot project for a travel API. 

## Getting Started

To get started with this project, follow the steps below:

1. Clone the repository to your local machine by running the following command in your terminal:
    ```
    git clone https://github.com/dpxcode/travel-java-api.git
    cd travel-java-api
    ```

2. Open the project in your preferred Java IDE and VSCode.

3. To create a `.env` file and set the values for `MONGODB_URI` and `PORT`, follow these steps:

    - Create a new file in the root directory of your project called `.env`.

    - Open the `.env` file and add the following lines:

        ```
        MONGODB_URI=your_mongodb_uri
        PORT=your_port_number
        ```

    Replace `your_mongodb_uri` with the actual URI of your MongoDB database, and `your_port_number` with the desired port number for your application.

    - Save the `.env` file.

    - Make sure to add the `.env` file to your `.gitignore` file to prevent sensitive information from being committed to your repository.

    - Now you can access the values of `MONGODB_URI` and `PORT` in your code using environment variables.

    - Continue with the remaining steps in the README.md file.


4. Build and run the project using the following command:
    ```
    ./mvnw spring-boot:run
    ```

5. The project should now be running locally on your machine. You can access the API endpoints by navigating to `http://localhost:8080` in your web browser.


## Features

This travel API project includes the following features:

- User authentication and authorization
- CRUD operations for managing travel destinations
- Search functionality for finding destinations based on various criteria
- Integration with external APIs for retrieving additional travel information

## Technologies Used

The following technologies are used in this project:

- Java
- Spring Boot
- Hibernate
- MongoDB
- RESTful API principles

## Documentation

For detailed documentation on how to use the API endpoints, refer to the [API documentation](/docs/api.md).

## Contributing

If you would like to contribute to this project, please follow the guidelines outlined in the [CONTRIBUTING.md](/CONTRIBUTING.md) file.

## License

This project is licensed under the [MIT License](/LICENSE).
