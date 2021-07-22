package renderer;


// This class has the purpose of making the variables easyer to access and to change
public class Reference {

    // World specs
	public static final int WORLD_WIDTH = 1000;
	public static final int WORLD_HEIGHT = 600;
	
    // Cubes specs
    public static final int CUBE_SIZE = 40;
    public static final int CUBE_DISTANCE = 10;
    public static final int CUBE_COUNT = 7;

    // A cell will die if it has less then LESS_NEIGHBORS_TO_DIE neighbors near it (underpopulation)
    // A cell will die if it has more then MORE_NEIGHBORS_TO_DIE neighbors near it (overpopulation)
    public static final int LESS_NEIGHBORS_TO_DIE = 2;
    public static final int MORE_NEIGHBORS_TO_DIE = 7;

    // A cell will be born if it has less then LESS_NEIGHBORS_TO_ALIVE and more then MORE_NEIGHBORS_TO_ALIVE
    public static final int LESS_NEIGHBORS_TO_ALIVE = 7;
    public static final int MORE_NEIGHBORS_TO_ALIVE = 2;

    // Do you want the view to be orthographic?
    public static final boolean ORTHOGRAPHIC = false;
}