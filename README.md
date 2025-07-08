# zusChess

## To run the project

1. Clone the repository:
   ```bash
   git clone git@github.com:brentmzey/zusChess.git
    ```

2. Ensure you have Java (version 17-21), Kotlin (ideally version 2+), (and optionally Gradle) installed. You can check your versions with: (or with SDKman)
   ```bash
   java -version
   kotlinc -version
   gradle -version
   sdk ls java
   sdk ls kotlin
   sdk ls gradle
   ```
3. In the root of the project, compile by running:
   ```bash
   ./gradlew build
   # or if you have Gradle installed on your system
   gradle build
   ```
   
4. To run the project, execute:
   ```bash
   ./gradlew run
   # or if you have Gradle installed on your system
   gradle run
   # or just execute the created JAR file
   java -jar build/libs/zusChess-1.0-SNAPSHOT.jar
   ```

5. To run the tests, execute:
   ```bash
   ./gradlew test
   # or if you have Gradle installed on your system
   gradle test
   ```