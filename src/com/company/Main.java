package com.company;

import com.company.provider.DataProvider;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.SMO;
import weka.classifiers.rules.JRip;
import weka.classifiers.rules.ZeroR;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.matrix.Matrix;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Instances data = DataProvider.getInstance().getData();
        data.setClassIndex(0);
        try {
            performTests(data, getClassifiers());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void performTests(Instances instances, List<Classifier> classifiers) throws Exception {
        for (Classifier classifier : classifiers) {
            System.out.println();
            System.out.println(classifier.getClass().getSimpleName());
            Matrix result = ClassifierTester.crossValidation(classifier, instances, TestParams.FOLDS_NUMBER, TestParams.REPEAT);
            TestAnalyser.analyseTestResult(result);
        }
    }

    public static List<Classifier> getClassifiers() {
        return Arrays.asList(
                new JRip(),
                new ZeroR(),
                new J48(),
                new SMO(),
                new MultilayerPerceptron(),
                new NaiveBayes()
        );
    }
}
