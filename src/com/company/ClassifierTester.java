package com.company;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.matrix.Matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Artur on 2017-05-22.
 */
public class ClassifierTester {

    public static Matrix crossValidation(Classifier classifier, Instances instances, int foldsNumber, int repeatsNumber) throws Exception {
        int seed;
        List<Matrix> matrixList = new ArrayList<>();

        for (int i = 0; i < repeatsNumber; i++) {
            Matrix matrix = new Matrix(2, 2);
            seed = i + 1;
            Instances data = new Instances(instances);
            Random random = new Random(seed);
            data.randomize(random);

            if (data.classAttribute().isNominal()) {
                data.stratify(foldsNumber);
            }

            Evaluation evaluation = new Evaluation(data);
            for (int j = 0; j < foldsNumber; j++) {
                Instances train = data.trainCV(foldsNumber, i);
                Instances test = data.testCV(foldsNumber, i);

                Classifier c = classifier;
                c.buildClassifier(train);
                evaluation.evaluateModel(c, test);
                matrix = matrix.plus(new Matrix(evaluation.confusionMatrix()));
            }
            //add new matrix
            matrixList.add(matrix);
        }
        return calculateAvgMatrix(matrixList, repeatsNumber);
    }

    public static Matrix calculateAvgMatrix(List<Matrix> matrixList, double repeatsNumber) {
        if(matrixList.size() == 1) {
            return matrixList.get(0); //no need to calculate avg for 1 matrix
        }

        Matrix avgMatrix = new Matrix(2, 2);
        for (int i = 0; i < avgMatrix.getRowDimension(); i++) {
            for (int j = 0; j < avgMatrix.getColumnDimension(); j++) {
                double avg = 0;
                for (Matrix matrix : matrixList) {
                    avg += matrix.get(i, j);
                }
                avgMatrix.set(i, j, avg / repeatsNumber);
            }
        }
        return avgMatrix;
    }

}
