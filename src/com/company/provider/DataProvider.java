package com.company.provider;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils;
import java.util.ArrayList;


/**
 * Created by AWALICZE on 27.04.2017.
 */
public class DataProvider {

    private String FILE_NAME = "212695L4 1.arff";

    private static DataProvider instance;

    public static DataProvider getInstance(){
        if(instance == null){
            instance = new DataProvider();
        }
        return instance;
    }

    public Instances getData(){
        Instances data  = null;
        try {
            ConverterUtils.DataSource source = new ConverterUtils.DataSource(FILE_NAME);
            data = source.getDataSet();
        } catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }
}
