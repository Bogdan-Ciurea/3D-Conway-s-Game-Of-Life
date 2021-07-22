package renderer.point;

import java.awt.Point;
import renderer.Reference;

// The class that will take care of all the mathemathical calculations regarding the points
public class PointConvertor {

    private static double scale = 1;
    private static final double zoomFactor = 1.2;

    // The function that will take care of zooming in 
    public static void zoomIn(){
        scale *= zoomFactor;
    }

    // The function that will take care of zooming out
    public static void zoomOut(){
        scale /= zoomFactor;
    }

    // The function that will convert a points position from 3D to 2D
    public static Point convertPoint(MyPoint point3D){
        double x3d = point3D.y * scale;
        double y3d = point3D.z * scale;
        double depth = point3D.x * scale;
        double[] newVal = scale(x3d, y3d, depth);

        int x2d = (int) (Reference.WORLD_WIDTH / 2 + newVal[0]);
        int y2d = (int) (Reference.WORLD_HEIGHT / 2 + newVal[1]);

        Point point2d = new Point(x2d, y2d);
        return point2d;
    }

    // The function that will make transform the view 
    // from an orthographic one to a normal one
    public static double[] scale(double x3d, double y3d, double depth){
        double dist = Math.sqrt(x3d * x3d + y3d * y3d);
        double theta = Math.atan2(y3d, x3d); 
        double depth2 = 15 - depth;
        double localScale = Math.abs(1400/(depth2 + 1400));
        
        if(!Reference.ORTHOGRAPHIC){
            dist *= localScale;
        }

        double[] newVal = new double[2];
        newVal[0] = dist * Math.cos(theta);
        newVal[1] = dist * Math.sin(theta);
        
        return newVal;
    }

    // The function that will take care of the mathematic calculations
    // for rotating a point on the X axis
    public static void rotateAxisX(MyPoint p, boolean CW, double degrees){
        double radius = Math.sqrt(p.y*p.y + p.z*p.z);
        double theta = Math.atan2(p.z, p.y);
        theta += 2 * Math.PI / 360 * degrees * (CW ? -1 : 1);
        p.y = radius * Math.cos(theta);
        p.z = radius * Math.sin(theta);
    }

    // The function that will take care of the mathematic calculations
    // for rotating a point on the Y axis
    public static void rotateAxisY(MyPoint p, boolean CW, double degrees){
        double radius = Math.sqrt(p.x*p.x + p.z*p.z);
        double theta = Math.atan2(p.x, p.z);
        theta += 2 * Math.PI / 360 * degrees * (CW ? 1 : -1);
        p.x = radius * Math.sin(theta);
        p.z = radius * Math.cos(theta);
    }

    // The function that will take care of the mathematic calculations
    // for rotating a point on the Z axis
    public static void rotateAxisZ(MyPoint p, boolean CW, double degrees){
        double radius = Math.sqrt(p.y*p.y + p.x*p.x);
        double theta = Math.atan2(p.y, p.x);
        theta += 2 * Math.PI / 360 * degrees * (CW ? -1 : 1);
        p.y = radius * Math.sin(theta);
        p.x = radius * Math.cos(theta);
    }
}
