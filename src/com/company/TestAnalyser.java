package com.company;

import weka.core.matrix.Matrix;

/**
 * Created by Artur on 2017-05-22.
 */
public class TestAnalyser {

    public static void analyseTestResult(Matrix matrix) {

        double accuracy = (matrix.get(0, 0) + matrix.get(1, 1)) /
                (matrix.get(0, 0) + matrix.get(0, 1) + matrix.get(1, 0) + matrix.get(1, 1));
        System.out.println("Accuracy: " + accuracy);

        double TNrate = matrix.get(1, 1) / (matrix.get(1, 0) + matrix.get(1, 1));
        System.out.println("TNrate: " + TNrate);

        double TPrate = matrix.get(0, 0) / (matrix.get(0, 0) + matrix.get(0, 1));
        System.out.println("TPrate: " + TPrate);

        double FPrate = matrix.get(1, 0) / (matrix.get(1, 0) + matrix.get(1, 1));
        System.out.println("FPrate: " + FPrate);

        double GMean = Math.sqrt(TPrate * TNrate);
        System.out.println("GMean: " + GMean);

        double AUC = (1 + TPrate + FPrate) / 2;
        System.out.println("AUC: " + AUC);
    }
}
