package renderer.entity;

import java.awt.Graphics;

import renderer.input.Mouse;
import renderer.input.Space;
import renderer.input.ClickType;
import renderer.point.PointConvertor;
import renderer.Reference;

public class WorldManager {

    private Entity entity;
    private World world;
    private int initialX, initialY;
    private double mouseSenitivity = 2.5;

    public WorldManager(){
        this.world = new World(Reference.CUBE_COUNT, Reference.CUBE_SIZE, 0, 0, 0);
    }

    public void init(){
        this.entity = world.initialState();
    }

    public void update(Mouse mouse, Space space){
        int x = mouse.getX();
        int y = mouse.getY();

        if(mouse.getButton() == ClickType.LeftClick){               
            int xDif = x - initialX;
            int yDif = y - initialY;
            this.rotate(true, 0, -yDif / mouseSenitivity, -xDif / mouseSenitivity);
        }
        else if (mouse.getButton() == ClickType.RightClick){
            int xDif = x - initialX;
            this.rotate(true, -xDif / mouseSenitivity, 0, 0);
        }

        if(mouse.isScollingUp()){
            PointConvertor.zoomIn();
        } else if(mouse.isScollingDown()){
            PointConvertor.zoomOut();
        }

        mouse.resetScroll();

        if(space.spaceIsPressed && !space.hasExecutedOnce){
            this.entity.update(this.world.update());;
            space.hasExecutedOnce = true;
        }

        initialX = x;
        initialY = y;
    }

    public void render(Graphics g){
        entity.render(g);
    }

    private void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees){
        entity.rotate(CW, xDegrees, yDegrees, zDegrees);   
    }

}
