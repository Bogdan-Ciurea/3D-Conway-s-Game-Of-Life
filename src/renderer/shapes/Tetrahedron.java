package renderer.shapes;

import java.awt.Color;
import java.awt.Graphics;

// This class is the class responsible with the cubes/tetrahedrons

public class Tetrahedron {
    
    private MyPolygon[] polygons;
    private Color color;
    public boolean isVisible = true;

    // The builder functions
    public Tetrahedron(Color color, boolean decay, MyPolygon... polygons){
        this.color = color;
        this.polygons = polygons;
        if(decay){
            setDecayingPolygonColor();
        }else{
            setPolygonColor();
        }
        this.sortPolygons();
    }
    
    public Tetrahedron(MyPolygon... polygons){
        this.color = Color.RED;
        this.polygons = polygons;
        this.sortPolygons();
    }

    // The function that will render all the polygons in the cube 
    public void render(Graphics g){
        for(MyPolygon poly: this.polygons){
            poly.render(g);
        }
    }

    // The function that will make the cube rotate
    public void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees){
        for(MyPolygon p : polygons){
            p.rotate(CW, xDegrees, yDegrees, zDegrees);
        }
        this.sortPolygons();
    }

    // The function that will sort all the faces of the cube 
    // in order to have ordered faces
    public void sortPolygons(){
        MyPolygon.sortPolygons(this.polygons);
    }

    // The function that will get all the polygons in the cube
    // in order to be used if we have more cubes
    public MyPolygon[] getPolygons(){
        return this.polygons;
    }

    // The function that will set a specific color to the cube
    public void setPolygonColor(){
        for(MyPolygon poly: this.polygons){
            poly.setColor(this.color);
        }
    }

    // The function that will set a specific decaying color to the cube
    public void setDecayingPolygonColor(){
        double decayFactor = 0.95;
        for(MyPolygon poly: this.polygons){
            poly.setColor(this.color);
            int r = (int) (this.color.getRed() * decayFactor);
            int g = (int) (this.color.getGreen() * decayFactor);
            int b = (int) (this.color.getBlue() * decayFactor);
            this.color = new Color(r, g, b);
        }
    }

}
