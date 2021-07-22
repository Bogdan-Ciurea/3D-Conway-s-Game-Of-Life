package renderer.entity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import renderer.point.MyPoint;
import renderer.shapes.MyPolygon;
import renderer.shapes.Tetrahedron;
import renderer.Reference;

// The class that will act as the constrictor and manager of the whole 3D map
// Here we will take care of actial algorithm that says when a cell is dead or alive
public class World {

    private boolean [][][] grid;
    private static final Random RANDOM = new Random();
    private List<Tetrahedron> tetras;
    
    // The function is the constructior 
    // Here we will first make the 3D grid and give it boolean values
    // then we will make a list of cubes that contain the color 
    // (the color will take values depentind the position on the world)
    // and the coordinates of each cube and save it in the 'tetras' variable
    public World(int elements, double size, double centerX, double centerY, double centerZ){
        this.grid = new boolean[elements][elements][elements];

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid.length; j++){
                for(int k = 0; k < grid.length; k++){
                    grid[i][j][k] = RANDOM.nextBoolean();
                }
            }
        }

        tetras = new ArrayList<Tetrahedron>();

        int colorR = 255 / elements;
        int colorG = 255 / elements;
        int colorB = 255 / elements;

        int start = -(elements / 2);
        int end = elements / 2 + 1;

        double cubeSpacing = Reference.CUBE_DISTANCE;

        for(int i = start; i < end; i++){
            double cubeCenterX = i * (size + cubeSpacing) + centerX;
            for(int j = start; j < end; j++){
                double cubeCenterY = j * (size + cubeSpacing) + centerY;
                for(int k = start; k < end; k ++){
                    double cubeCenterZ = k * (size + cubeSpacing) + centerZ;
                    MyPoint p1 = new MyPoint(cubeCenterX - size / 2, cubeCenterY - size / 2, cubeCenterZ - size / 2);
                    MyPoint p2 = new MyPoint(cubeCenterX - size / 2, cubeCenterY - size / 2, cubeCenterZ + size / 2);
                    MyPoint p3 = new MyPoint(cubeCenterX - size / 2, cubeCenterY + size / 2, cubeCenterZ - size / 2);
                    MyPoint p4 = new MyPoint(cubeCenterX - size / 2, cubeCenterY + size / 2, cubeCenterZ + size / 2);
                    MyPoint p5 = new MyPoint(cubeCenterX + size / 2, cubeCenterY - size / 2, cubeCenterZ - size / 2);
                    MyPoint p6 = new MyPoint(cubeCenterX + size / 2, cubeCenterY - size / 2, cubeCenterZ + size / 2);
                    MyPoint p7 = new MyPoint(cubeCenterX + size / 2, cubeCenterY + size / 2, cubeCenterZ - size / 2);
                    MyPoint p8 = new MyPoint(cubeCenterX + size / 2, cubeCenterY + size / 2, cubeCenterZ + size / 2);
                    
                    Tetrahedron tetra = new Tetrahedron( 
                        new Color( (int) (colorR * (i - start)), (int) (colorG * (j - start)), (int) (colorB * (k - start))), false,
                        new MyPolygon(p5, p6, p8, p7),
                        new MyPolygon(p2, p4, p8, p6),
                        new MyPolygon(p3, p4, p8, p7),
                        new MyPolygon(p1, p2, p6, p5),
                        new MyPolygon(p1, p2, p4, p3),
                        new MyPolygon(p1, p3, p7, p5));
                    
                    if(!grid[i - start][j - start][k - start])tetra.isVisible = false;
                    
                    tetras.add(tetra);
                }
            }
        }

    }

    // The function that will return the inital state of the map
    public Entity initialState(){
        return new Entity(this.tetras);
    }

    // The function that will return a list of Cubes that will be used in the Entity class
    public List<Tetrahedron> update(){
        this.updateGrid();

        for(int i = 0; i < Reference.CUBE_COUNT; i++){
            for(int j = 0; j < Reference.CUBE_COUNT; j++){
                for(int k = 0; k < Reference.CUBE_COUNT; k++){
                    this.tetras.get(i * Reference.CUBE_COUNT * Reference.CUBE_COUNT 
                        + j * Reference.CUBE_COUNT + k).isVisible = this.grid[i][j][k];
                }
            }
        }

        return this.tetras;
    }

    // The function that will update the 3D gridd
    private void updateGrid(){
        int aliveCount;
        boolean newGrid[][][] = new boolean[Reference.CUBE_COUNT][Reference.CUBE_COUNT][Reference.CUBE_COUNT]; 
        
        for(int i = 0; i < Reference.CUBE_COUNT; i++){
            for(int j = 0; j < Reference.CUBE_COUNT; j++){
                for(int k = 0; k < Reference.CUBE_COUNT; k++){
                    aliveCount = countAliveNeighbors(i, j, k);

                    if(this.grid[i][j][k]){
                        if(aliveCount < Reference.LESS_NEIGHBORS_TO_DIE){
                            newGrid[i][j][k] = false;
                        }
                        else if(aliveCount > Reference.MORE_NEIGHBORS_TO_DIE){
                            newGrid[i][j][k] = false;
                        }
                        else{
                            newGrid[i][j][k] = true;
                        }
                    }
                    else {
                        if(aliveCount > Reference.MORE_NEIGHBORS_TO_ALIVE && aliveCount < Reference.LESS_NEIGHBORS_TO_ALIVE){
                            newGrid[i][j][k] = true;
                        }
                        else{
                            newGrid[i][j][k] = false;
                        }
                    }
                }
            }
        }

        this.grid = newGrid;
    }

    // The function that will count all the alive neighbors of a cell
    private int countAliveNeighbors(int currentI, int currentJ, int currentK){
        int s = 0;
        for(int i = -1 + currentI; i < 2 + currentI; i++){
            for(int j = -1 + currentJ; j < 2 + currentJ; j++){
                for(int k = -1 + currentK; k < 2 + currentK; k++){
                    try{
                        if(this.grid[i][j][k] == true){
                            s++;
                        } 
                    } catch(ArrayIndexOutOfBoundsException e) {
                        continue;
                    }
                }
            }
        }
        return s;
    }

}
