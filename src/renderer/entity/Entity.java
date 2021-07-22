package renderer.entity;

import java.awt.Graphics;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import renderer.shapes.MyPolygon;
import renderer.shapes.Tetrahedron;

// The class that will show a list of Cubes
// We will consider an Entity an element with more cubes
// The saved cubes will be saved even if we don't show them on the screen
public class Entity{

    private List<Tetrahedron> tetrahetrons;
    private MyPolygon[] polygons;

    // The function that will add to an array all the cubes that needs to be shown,
    // make an array of all the polygons in the array and sort the polygons
    public Entity(List<Tetrahedron> tetrahetrons){
        this.tetrahetrons = tetrahetrons;
        List<MyPolygon> tempList = new ArrayList<MyPolygon>();
        for(Tetrahedron tetra : this.tetrahetrons){
            if(tetra.isVisible)
                tempList.addAll(Arrays.asList(tetra.getPolygons()));
        }
        this.polygons = new MyPolygon[tempList.size()];
        this.polygons = tempList.toArray(this.polygons);
        this.sortPolygons();
    }

    // Same as the above function
    public void update(List<Tetrahedron> tetrahetrons){
        this.tetrahetrons = tetrahetrons;
        List<MyPolygon> tempList = new ArrayList<MyPolygon>();
        for(Tetrahedron tetra : this.tetrahetrons){
            if(tetra.isVisible)
                tempList.addAll(Arrays.asList(tetra.getPolygons()));
        }
        this.polygons = new MyPolygon[tempList.size()];
        this.polygons = tempList.toArray(this.polygons);
        this.sortPolygons();
    }

    // The function that will render all the polygons
    public void render(Graphics g) {
        for(MyPolygon poly : this.polygons){
            poly.render(g);
        }
    }

    // The function that will rotate all the polygons and then sort them
    public void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees) {
        for(Tetrahedron tetra: tetrahetrons){
            tetra.rotate(CW, xDegrees, yDegrees, zDegrees);
        }
        this.sortPolygons();
    }
    
    // The function that will sort the polygons
    private void sortPolygons(){
        MyPolygon.sortPolygons(this.polygons);
    }

    
}
