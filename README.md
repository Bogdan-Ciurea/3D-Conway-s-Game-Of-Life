# 3D-Conway-s-Game-Of-Life

This project is a personal one and a test for me to see what 3D application I can build in Java. The idea came from [this video on YouTube](https://www.youtube.com/watch?v=dQJ5aEsP6Fs) but also from a university project.

This project was built using my knowledge, [this](https://www.youtube.com/watch?v=DQedlivHAPc&list=PLgRPwj3No0VLXFoqYnL2aYhczXB2qwKvp) tutorial on YouTube and [this](https://github.com/HexagonNico/Conway-Game-of-Life) GitHub Repository as inspiration for building the 3D objects and, respectively, the project structure.

## Functions

The project has a simple idea in mind: **A 3D Conway's Game of Life**.

If you haven't change anything in the files, the program should have the following functions:
 - Make a window named `3D Game of life` + the FPS.
 - Make a Big Cube that contains `7 x 7 x 7` Smaller Cubes 
 - Not all of the Smaller Cubes are shown (it's random)
 - If you want to rotate the scene, you can use the `Left Click` or th `Right Click` (the Right Click is used to rotate the scene on the X Axis)
 - You can also zoom in or out with the `Scroll Wheel`
 - To make the program run to the next step you will have to press the `spacebar`

Considering that a Cell will have three times more neighbors these are ne new rules of the game:
 - If a Cell has **< 2 neighbors** will **die** due to uderpopulation
 - If a Cell has **> 7 neighbors** will **die** due to overpopulation
 - If an empty space has **>2 and <7 neighboring Cells** this will make a new Cell 

These variables are found in the `Reference.java` and can be changed.

## Files 

The project should contain 12 files:

`Main.java`, `Reference.java`, `MyPolygon.java`, `Tetrahedron.java`, `MyPoint.java`, `PointConvertor.java`, `ClickType.java`, `Mouse.java`, `Space.java`, `Entity.java`, `World.java`, `WorldManager.java`.

## Reference.java

In the `Reference.java` file you can find more variables that are used in the project to make variables easier to change rather than searching through the entire project to change a variable.

`LESS_NEIGHBORS_TO_DIE` - is the variable that will determine if underpopulation happens or nor. **Initially `2`**

`MORE_NEIGHBORS_TO_DIE` - is the variable that will determine if overpopulation happens or nor. **Initially `7`**

`LESS_NEIGHBORS_TO_ALIVE` and `MORE_NEIGHBORS_TO_ALIVE` - represent the interval in which will determine if an empty space will become a Cell. **Initially `7` and `2`**

`ORTHOGRAPHIC` - is the variable that will determine if the perspective of the whole project is orthograthic. **Initially `false`**

`CUBE_SIZE` - is the variable that will determine how big are the cubes. **Initially `40`**

`CUBE_DISTANCE` - is the variable that will determine the distance between the Cubes. **Initially `10`**

`CUBE_COUNT` - is the variable that will determine the number of the number of cubes on one edge. So the Big Cube will have `CUBE_COUNT^3`. **Initially `7`**
