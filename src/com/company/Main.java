package com.company;

import com.company.provider.DataProvider;
import com.company.saver.DataSaver;
import weka.core.Instances;

public class Main {

    public static void main(String[] args) {
        Instances data = DataProvider.getInstance().getData();

        //Instances newInstances = ArffConverter.deleteProperRecords(data);
        //newInstances = ArffConverter.deleteLoanStatusAttribute(newInstances);
        GainRatioAnalyzer.analyse(data);

        DataSaver.getInstance().saveData(data);
    }
}
