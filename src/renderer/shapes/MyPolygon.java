package renderer.shapes;

import renderer.point.MyPoint;
import renderer.point.PointConvertor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

import java.util.List;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;

// The class that will take care of the polygons
public class MyPolygon {

    private Color color;
    private MyPoint[] points;

    // The builder method
    public MyPolygon(Color color, MyPoint...points){
        this.color = color;
        
        this.points = new MyPoint[points.length];
        for(int i = 0; i < points.length; i++){
            MyPoint p = points[i];
            this.points[i] = new MyPoint(p.x, p.y, p.z);
        }
    }

    public MyPolygon(MyPoint...points){
        this.color = Color.RED;
        
        this.points = new MyPoint[points.length];
        for(int i = 0; i < points.length; i++){
            MyPoint p = points[i];
            this.points[i] = new MyPoint(p.x, p.y, p.z);
        }
    }

    // The function that will fill the polygon with points
    public void render(Graphics g){
        Polygon poly = new Polygon();
        for(int i = 0; i < this.points.length; i++){
            Point p = PointConvertor.convertPoint(this.points[i]);
            poly.addPoint(p.x, p.y);
        }
        g.setColor(this.color);
        g.fillPolygon(poly);
    }

    // The function that will get the average position of the X axis
    // to see what polygon whould be drawn first
    public double getAverageX(){
        double sum = 0;
        for(MyPoint p : this.points){
            sum += p.x;
        }
        
        return sum / this.points.length;
    }

    // The function that will call the rotate functions for all the points
    public void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees){
        for(MyPoint p : points){
            PointConvertor.rotateAxisX(p, CW, xDegrees);
            PointConvertor.rotateAxisY(p, CW, yDegrees);
            PointConvertor.rotateAxisZ(p, CW, zDegrees);
        }
    }

    // The function that will sort an array of polygons by they'r X axis
    public static MyPolygon[] sortPolygons(MyPolygon[] polygons){
        List<MyPolygon> polygonsList = new ArrayList<MyPolygon>();

        for(MyPolygon poly : polygons){
            polygonsList.add(poly);
        }

        Collections.sort(polygonsList, new Comparator<MyPolygon>(){
            @Override
            public int compare(MyPolygon p1, MyPolygon p2){
                double p1AverageX = p1.getAverageX();
                double p2AverageX = p2.getAverageX();
                double diff = p2AverageX - p1AverageX;

                if(diff == 0){
                    return 0;
                }

                return diff < 0 ? 1 : -1;
            }
        });

        for(int i = 0; i < polygons.length; i++){
            polygons[i] = polygonsList.get(i);
        }

        return polygons;
    }

    // The function that will set a specific color to the polygon
    public void setColor(Color color){
        this.color = color;
    }
}   
