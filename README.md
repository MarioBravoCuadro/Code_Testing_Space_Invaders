
# Space Invaders Game: Debugging and Fixing a Broken Game

## Overview
This project involved receiving a **non-functional and broken** version of the classic **Space Invaders** game. The task was to **test**, **debug**, and **correct** the code to restore the game’s functionality and make it playable again.

This was a **class project** developed collaboratively with my classmates:  
- **Johannes Robayna Pérez**  
- **Piyush Mulchandani Makhija**  
- **Jesús Monforte Chinarro**  
- **Pablo Celador Larrén**

## Objective
The main goal of the project was to identify and fix the bugs preventing the game from functioning properly. This included:

- Debugging code errors causing crashes or unexpected behavior.
- Testing the game mechanics to ensure everything worked as expected.
- Correcting logical errors affecting gameplay, such as movement, shooting, and enemy behavior.

## Steps Taken

1. **Initial Testing:** 
   - Played through the game to identify non-functional elements and errors.
   - Created a list of issues, including broken controls, unresponsive enemies, and game crashes.
   
2. **Debugging:**
   - Investigated the existing code and debugged various components (e.g., game loop, collision detection, input handling).
   - Used IntelliJ's debugging tools and logs to trace errors and pinpoint issues.

3. **Fixing and Refactoring:**
   - Corrected code logic to restore movement, shooting, and enemy interactions.
   - Refactored parts of the code to optimize performance and ensure smooth gameplay.
   
4. **Testing:**
   - **Black-box Testing:** Focused on testing the game’s functionalities without knowledge of the internal code, simulating real-world user experiences, such as gameplay flow and UI interactions.
   - **White-box Testing:** Involved testing the internal workings of the game, including code structure, methods, and the correctness of functions (using unit tests).
   - **Unit Testing**: Used **JUnit** to write tests for individual components to verify that each function worked as expected.
   - **Integration Testing**: Ensured that different parts of the game integrated seamlessly and that new fixes did not break existing features.
   - **Mockito** was used to mock dependencies for isolated unit tests.

5. **Final Testing:**
   - Performed extensive testing after each change to ensure that all game mechanics worked as expected.
   - Verified that the game was now **playable** and free from critical bugs.

## Result
After applying the necessary fixes and debugging, the **Space Invaders** game is now fully functional. All game mechanics, including shooting, movement, and enemy behavior, were restored and refined for optimal play.

## Technologies Used
- **Programming Language:** Java
- **Frameworks/Libraries:**
  - **JFrame** for the graphical user interface (GUI)
  - **JUnit** for unit testing
  - **Mockito** for mocking dependencies in tests
- **IDE:** IntelliJ IDEA
- **Testing:** Unit and Integration Testing, **Black-box** and **White-box** Testing

## How to Play
1. Clone or download the repository.
2. Compile and run the program using IntelliJ.
3. Enjoy the restored Space Invaders experience!

## Contributing
Feel free to fork the repository and contribute by fixing bugs, adding new features, or improving the code. Contributions are always welcome!

## License
[Include licensing information here, e.g., MIT License]
