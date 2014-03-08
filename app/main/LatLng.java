package main;

/**
 * Created by alex on 08/03/14.
 */
public class LatLng {

    private double x, y;
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public LatLng(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double calculateDistance(LatLng other) {
        //TODO: Calculate this value correctly and stop being lazy
        return Math.sqrt( (this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y) );
    }
}
