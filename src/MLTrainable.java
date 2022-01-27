public interface MLTrainable {
    double[][] fit(double input, double[][]xy);
    double predict(double[][]xyd);
}
