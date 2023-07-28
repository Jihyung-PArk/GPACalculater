package com.jihyungpark.gpacalculater;

public class GPACalculateFormula {

    public static double calculateGPA(int hdInput, int dInput, int creditInput, int passInput, int failFInput, int failXInput) {
        final double hd = 7.0;
        final double d = 6.0;
        final double credit = 5.0;
        final double pass = 4.0;
        final double failF = 1.5;
        final double failX = 0.0;

        double totalPoint = (hd * hdInput) + (d * dInput) + (credit * creditInput) + (pass * passInput) + (failF * failFInput) + (failX * failXInput);
        double totalUnit = hdInput + dInput + creditInput + passInput + failFInput + failXInput;

        return totalPoint/totalUnit;
    }

}
