// Euclidean Distance Metric
// This implementation is still limited. It performs only a one dimensional evaluation of distance between two points.
// At least for our purpose it fits the bill :p

import java.lang.Math;

public class EuclideanDistance {
    private double x2;
    private double x1;

    // Set the data points
    public void setValues(double x2, double x1) {
        this.x2 = x2;
        this.x1 = x1;
    }

    // Calculate the distance
    public double getDistance() {
        return Math.sqrt(Math.pow((x2 - x1), 2));
    }
}
