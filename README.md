# Battleship Game
This is a Battleship game implementation using Java and Hibernate for persistence.

## Requirements
Java 17
Maven
MySQL
## Setup
Clone the repository:

git clone https://github.com/pabloloz23/PruebaBattleship.git
cd PruebaBattleship
## Configure the MySQL database:

Create a database named battleship.
Update the hibernate.cfg.xml file with your MySQL database credentials.
## Build the project using Maven:

mvn clean install
## Run the application:

    mvn exec:java

## Project Structure
  - src/main/java/application/**builder**/: Contains builder classes for creating game components.
  - src/main/java/application/**config**/: Contains configuration classes.
  - src/main/java/application/**game**/: Contains the main game logic.
  - src/main/java/application/**model**/: Contains the data models.
  - src/main/java/application/**repository**/: Contains repository classes for data persistence.
## Usage
Start a new game by running the Main class in 
```
     src/main/java/application/game/Main.java
```
Follow the prompts to create players and assign ships.
Use the provided methods to attack opponent ships and check the game status.
## License
This project is licensed under the MIT License.
