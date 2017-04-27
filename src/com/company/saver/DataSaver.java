package com.company.saver;

import weka.core.Instances;
import weka.core.converters.ArffSaver;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by AWALICZE on 27.04.2017.
 */
public class DataSaver {

    private String FILE_NAME = "212695L3 2.arff";

    private static DataSaver instance;

    public static DataSaver getInstance(){
        if(instance == null){
            instance = new DataSaver();
        }
        return instance;
    }

    public void saveData(Instances instances){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
            writer.write(instances.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
