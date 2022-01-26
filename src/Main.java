/* Author: Juxhin Bazelli
   CreatedAt: 25.01.22
   FinishedAt: 26.01.22
   Version: 1.0
   ------ KNN Classification Program ------
   This is a program that uses the KNN Algorithm to classify new data based on previous labeled data.
   It performs the Euclidean distance under the hood to find the distance of the data points.
   This is the Main Application where our model is executed and evaluated against a fake dataset.
   The algorithms were developed from scratch. No libraries involved, nothing, nada :)

   Note: The program was developed for learning purposes. It is not intended for production.
   There are far better libraries out there that are better optimized like: scikit-learn, apache mllib, xgboost etc.
*/


public class Main {
    public static void main(String[] args) {

        // The KNNClassifier is initialized with a k value
        // k: number of neighbors that vote based on majority what the input data will be classified as
        KNNClassifier classifier = new KNNClassifier(3);

        // A dataset that consists of features: dataset[0] and outputs (or rather classes): dataset[1]
        double[][] dataset = {
                {42, 65, 52, 76, 96, 50, 91, 58, 25, 23, 75, 46, 87, 96, 45, 32, 63, 21, 26, 93, 68, 96},
                {1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0}
        };

        // The classifier accepts two arguments: the input feature and the dataset array.
        // At the end it returns the output (or rather class) that the input feature belongs to.
        double[][] fitdata = classifier.fit(90, dataset);
        double result = classifier.predict(fitdata);
        System.out.println(result);

    }
}
