/* A basic KNN Classifier
   In the meantime there is no testing involved to measure the accuracy of the model :P
   Perhaps soon ;) */

public class KNNClassifier implements MLTrainable {
    // K value: number of neighbors that vote what the new data entry will be classified as.
    int k;
    public double[][] closestNeighbors;
    double neighborOutput;
    double neighborCountLabeledAsZero = 0;
    double neighborCountLabeledAsOne = 0;
    double predictedOutput;

    // Constant array error messages
    private final String ARRAYERROR = "The array must be of size %d.";
    private final String SUBARRAYERROR = "The size of the subarrays must match.";

    // Initialization of the k variable
    // Initialization of the closestNeighbors array
    public KNNClassifier(int k) {
        this.k = k;
        closestNeighbors = new double[k][3];
    }



    @Override
    public double[][] fit(double input, double[][] xy) {
        // Check the size of the array
        if (xy.length != 2) throw new IllegalArgumentException(String.format(ARRAYERROR, 2));

        // Check the size of the subarrays if they match
        if (xy[0].length != xy[1].length) throw new IllegalArgumentException(SUBARRAYERROR);

        // Initialization of features, outputs, and distances arrays
        double[] features = xy[0];
        double[] outputs = xy[1];
        double[] distances = new double[features.length];

        // Euclidean distance metric
        EuclideanDistance e = new EuclideanDistance();

        // Iteration distance variable
        double currentDistance;

        // Initialization of fit data
        double[][] fitData = new double[xy.length + 1][features.length];

        // Inserting the features and the outputs into it
        fitData[0] = features;
        fitData[1] = outputs;

        // Fitting the data
        for (int i = 0; i < features.length; i++) {
            e.setValues(input, features[i]);
            currentDistance =  e.getDistance();
            distances[i] = currentDistance;
        }

        // Inserting the distances
        fitData[2] = distances;

        return fitData;
    }

    @Override
    public double predict(double[][] xyd) {

        // Check the size of the array
        if (xyd.length != 3) throw new IllegalArgumentException(String.format(ARRAYERROR, 3));

        // Check the size of the subarrays if they match
        if (xyd[0].length != xyd[1].length && xyd[0].length != xyd[2].length) {
            throw new IllegalArgumentException(SUBARRAYERROR);
        }


        // for k times get the minimum values
        for (int j = 0; j < k; j++) {

            // Iteration variables
            double currentFeature;
            double currentOutput;
            double currentDistance;

            // final values
            double selectedFeature = 0;
            double selectedOutput = 0;
            double minimumValue = Double.MAX_VALUE;

            // markedIndex to mark elements as -1 so that they cannot be further compared
            int markedIndex = 0;

            // Obtain the smallest k values
            // if current iteration values marked with -1 ignore them and jump to the next iteration to compare
            for (int i = 0; i < xyd[2].length; i++) {
                currentFeature = xyd[0][i];
                currentOutput = xyd[1][i];
                currentDistance = xyd[2][i];

                if (currentDistance < minimumValue && currentDistance != -1) {
                    minimumValue = currentDistance;
                    selectedFeature = currentFeature;
                    selectedOutput = currentOutput;
                    markedIndex = i;
                }

            }

            // Assign the smallest final values into the closestNeighbors array
            closestNeighbors[j][0] = selectedFeature;
            closestNeighbors[j][1] = selectedOutput;
            closestNeighbors[j][2] = minimumValue;

            // the elements obtained from the original array are now marked with -1 in the array so that they cannot be further
            // compared
            xyd[0][markedIndex] = -1;
            xyd[1][markedIndex] = -1;
            xyd[2][markedIndex] = -1;

        }

        // Iterate over the closestNeighbors array
        // Sum up the counts of closest neighbors labeled as 0 and 1
        for (double[] closestNeighbor : closestNeighbors) {
            neighborOutput = closestNeighbor[1];
            if (neighborOutput == 0) {
                neighborCountLabeledAsZero += 1;
            } else {
                neighborCountLabeledAsOne += 1;
            }
        }

        // Perform the popularity vote to obtain the predicted value
        if (neighborCountLabeledAsZero > neighborCountLabeledAsOne) {
            predictedOutput = 0;
        }
        else {
            predictedOutput = 1;
        }

        return predictedOutput;
    }
}
