package com.jihyungpark.gpacalculater;

import android.util.Log;
import android.widget.TextView;

public class SubjectCalculateFormula {

    public static double calculateSubject(float mark, float fullMark, float percentage) {

        double subject = (mark/fullMark) * percentage;

        return subject;
    }

    public static String subjectGrade(double totalResultValue){
        if(85 <= totalResultValue ){
            return "High Distinction";
        } else if (75 <= totalResultValue && totalResultValue < 85) {
            return "Distinction";
        } else if (65 <= totalResultValue && totalResultValue < 75) {
            return "Credit";
        } else if (50 <= totalResultValue && totalResultValue < 65) {
            return "Pass";
        } else {
            return "Fail";
        }
    }

    public static String predictGrade(int totalPercentageValue, double totalResultValue){

        int leftoverGrade = 100 - totalPercentageValue;
        double minimumGradeRequire = 85 - totalResultValue;
        double possibleMaximumGrade = leftoverGrade + totalResultValue;
        String announce = "You got " + String.format("%.2f",totalResultValue) + " out of 100. You need at least" + String.format("%.2f", minimumGradeRequire) + "to get ";

        if(85 <= possibleMaximumGrade){
            return announce + "HD.";
        } else if (75 <= possibleMaximumGrade && possibleMaximumGrade < 85) {
            return announce + "D.";
        } else if (65 <= possibleMaximumGrade && possibleMaximumGrade < 75) {
            return announce + "Credit.";
        } else if (50 <= possibleMaximumGrade && possibleMaximumGrade < 65) {
            return announce + "Pass.";
        }else {
            return "You got " + String.format("%.2f",totalResultValue) + " out of 100.";
        }
    }

    public static String subjectCalculate (String itemNameValue, String markValue, String fullMarkValue, String percentageValue){
        if(itemNameValue.isEmpty() || markValue.isEmpty() || fullMarkValue.isEmpty() || percentageValue.isEmpty()){
            return ("Please fill all elements.");
        } else if (Integer.parseInt(percentageValue) > 100) {
            return ("Percentage cannot over 100.");
        } else if ((Integer.parseInt(markValue)) > (Integer.parseInt(fullMarkValue))) {
            return ("Mark must be smaller than FullMark.");
        } else {
            double subject = SubjectCalculateFormula.calculateSubject(Float.parseFloat(markValue), Float.parseFloat(fullMarkValue), Float.parseFloat(percentageValue));
            return (String.format("%.2f", subject));
        }
    }

    public static String subjectCalculateBeforeConvert (String itemNameValue, String markValue, String fullMarkValue, String percentageValue){
        if(itemNameValue.isEmpty() || markValue.isEmpty() || fullMarkValue.isEmpty() || percentageValue.isEmpty()){
            return ("");
        } else if (Integer.parseInt(percentageValue) > 100) {
            return ("");
        } else if ((Integer.parseInt(markValue)) > (Integer.parseInt(fullMarkValue))) {
            return ("");
        } else {
            double subject = SubjectCalculateFormula.calculateSubject(Float.parseFloat(markValue), Float.parseFloat(fullMarkValue), Float.parseFloat(percentageValue));
            return (String.format("%.2f", subject));
        }
    }
}
