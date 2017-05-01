package com.company;

import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

import java.util.Iterator;

/**
 * Created by AWALICZE on 27.04.2017.
 */
public class ArffConverter {

    private static int LOAN_STATUS_ATTRIBUTE_INDEX = 0;
    private static int LOAN_VALUE_ATTRIBUTE_INDEX = 4;
    private static int MAX_LOAN_VALUE = 900;
    private static String REFUSAL = "odmowa";

    public static Instances deleteProperRecords(Instances instances){
        Iterator<Instance> instancesIterator = instances.iterator();
        while(instancesIterator.hasNext()){
            Instance instance = instancesIterator.next();
            if(isRecordToDelete(instance)){
                instancesIterator.remove();
            }
        }
        return  instances;
    }

    public static Instances deleteLoanStatusAttribute(Instances instances){
        Remove remove = new Remove();
        int[] attributesIndex = {LOAN_STATUS_ATTRIBUTE_INDEX};
        remove.setAttributeIndicesArray(attributesIndex);
        try {
            remove.setInputFormat(instances);
            return Filter.useFilter(instances, remove);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instances;
    }

    private static boolean isRecordToDelete(Instance instance){
        String loanStatus = getLoanStatus(instance);
        String loanValue = getLoanValue(instance);
        return isRefusal(loanStatus) || isLoanToHigh(loanValue);
    }

    private static String getLoanValue(Instance instance) {
        return instance.toString(LOAN_VALUE_ATTRIBUTE_INDEX);
    }

    private static boolean isLoanToHigh(String loanValue){
        return Double.parseDouble(loanValue) > MAX_LOAN_VALUE;
    }

    private static String getLoanStatus(Instance instance){
        return instance.stringValue(LOAN_STATUS_ATTRIBUTE_INDEX);
    }

    private static boolean isRefusal(String status){
        return status.equals(REFUSAL);
    }
}
