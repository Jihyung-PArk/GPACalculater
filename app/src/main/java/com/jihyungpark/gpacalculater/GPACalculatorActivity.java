package com.jihyungpark.gpacalculater;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class GPACalculatorActivity extends AppCompatActivity {

    private final String[] grades = {"hd", "d", "credit", "pass", "failF", "failX"};

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpacalculator);


        //keep gpa data when rotated screen
        if (savedInstanceState != null) {

            //call grades text view value
            for (String grade : grades) {
                String id = grade + "InputText";
                @SuppressLint("DiscouragedApi") int idInt = getResources().getIdentifier(id, "id", getPackageName());

                int input = savedInstanceState.getInt(id);
                ((TextView) findViewById(idInt)).setText(Integer.toString(input));
            }

            //call result text view value
            TextView result = findViewById(R.id.gpaDisplay);
            Double gpa = savedInstanceState.getDouble("result");
            result.setText(String.format("%.2f", gpa));
            Log.i("testResult", String.valueOf(result.getText()));
            //if value exist, visible grade
            if (!Double.isNaN(Double.parseDouble((String) result.getText())) || !String.valueOf(result.getText()).equals("0.00")) {
                result.setVisibility(View.VISIBLE);
            } else {
                result.setVisibility(View.INVISIBLE);
            }
        }
        //if value is not exist, invisible grade
        TextView result = findViewById(R.id.gpaDisplay);
        if (Double.isNaN(Double.parseDouble((String) result.getText())) || String.valueOf(result.getText()).equals("0.00")) {
            result.setVisibility(View.INVISIBLE);
        }
    }

    //menu button for return menu page
    public void backToMenuClicked(View view){
        Intent backToMenu = new Intent(this, MenuPageActivity.class);
        startActivity(backToMenu);
    }

    // decrease and increase button for each grade (start)
    @SuppressLint("SetTextI18n")
    public void decreaseButtonClicked(View view){

        ArrayList<Integer> inputs = new ArrayList<>();
        int getId = view.getId();

        for (String grade : grades) {
            String id = grade + "InputText";
            @SuppressLint("DiscouragedApi") int idInt = getResources().getIdentifier(id, "id", getPackageName());

            TextView textView = findViewById(idInt);
            int input = Integer.parseInt(textView.getText().toString());
            inputs.add(input);
        }
        if(getId == R.id.hdDecreaseButton){
            if(inputs.get(0) != 0) {
                ((TextView) findViewById(R.id.hdInputText)).setText(Integer.toString(inputs.get(0) - 1));
            }
        } else if (getId == R.id.dDecreaseButton) {
            if(inputs.get(1) != 0) {
                ((TextView) findViewById(R.id.dInputText)).setText(Integer.toString(inputs.get(1) - 1));
            }
        } else if (getId == R.id.creditDecreaseButton) {
            if(inputs.get(2) != 0) {
                ((TextView) findViewById(R.id.creditInputText)).setText(Integer.toString(inputs.get(2) - 1));
            }
        } else if (getId == R.id.passDecreaseButton) {
            if(inputs.get(3) != 0) {
                ((TextView) findViewById(R.id.passInputText)).setText(Integer.toString(inputs.get(3) - 1));
            }
        } else if (getId == R.id.failFDecreaseButton) {
            if(inputs.get(4) != 0) {
                ((TextView) findViewById(R.id.failFInputText)).setText(Integer.toString(inputs.get(4) - 1));
            }
        } else if (getId == R.id.failXDecreaseButton) {
            if(inputs.get(5) != 0) {
                ((TextView) findViewById(R.id.failXInputText)).setText(Integer.toString(inputs.get(5) - 1));
            }
        }
    }

    //increase button clicked
    @SuppressLint("SetTextI18n")
    public void increaseButtonClicked(View view){

        ArrayList<Integer> inputs = new ArrayList<>();
        int getId = view.getId();

        for (String grade : grades) {
            String id = grade + "InputText";
            @SuppressLint("DiscouragedApi") int idInt = getResources().getIdentifier(id, "id", getPackageName());

            TextView textView = findViewById(idInt);
            int input = Integer.parseInt(textView.getText().toString());
            inputs.add(input);
        }
        if(getId == R.id.hdIncreaseButton){
            ((TextView) findViewById(R.id.hdInputText)).setText(Integer.toString(inputs.get(0) + 1));
        } else if (getId == R.id.dIncreaseButton) {
            ((TextView) findViewById(R.id.dInputText)).setText(Integer.toString(inputs.get(1) + 1));
        } else if (getId == R.id.creditIncreaseButton) {
            ((TextView) findViewById(R.id.creditInputText)).setText(Integer.toString(inputs.get(2) + 1));
        } else if (getId == R.id.passIncreaseButton) {
            ((TextView) findViewById(R.id.passInputText)).setText(Integer.toString(inputs.get(3) + 1));
        } else if (getId == R.id.failFIncreaseButton) {
            ((TextView) findViewById(R.id.failFInputText)).setText(Integer.toString(inputs.get(4) + 1));
        } else if (getId == R.id.failXIncreaseButton) {
            ((TextView) findViewById(R.id.failXInputText)).setText(Integer.toString(inputs.get(5) + 1));
        }
    }

    //calculate GPA
    @SuppressLint("DefaultLocale")
    public void calculateButtonClicked(View view){

        ArrayList<Integer> inputs = new ArrayList<>();

        for (String grade : grades) {
            String id = grade + "InputText";
            @SuppressLint("DiscouragedApi") int idInt = getResources().getIdentifier(id, "id", getPackageName());

            TextView textView = findViewById(idInt);
            int input = Integer.parseInt(textView.getText().toString());
            inputs.add(input);
        }

        double gpa = GPACalculateFormula.calculateGPA(inputs.get(0), inputs.get(1), inputs.get(2), inputs.get(3), inputs.get(4), inputs.get(5));

        TextView result = findViewById(R.id.gpaDisplay);
        result.setText(String.format("%.2f",gpa));

        if(!Double.isNaN(Double.parseDouble((String) result.getText())) || result.getText() == "0.00"){
            result.setVisibility(View.VISIBLE);
            }else{
            result.setVisibility(View.INVISIBLE);
        }
    }

    //save instance state for rotate screen
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        for (String grade : grades) {
            String id = grade + "InputText";
            @SuppressLint("DiscouragedApi") int idInt = getResources().getIdentifier(id, "id", getPackageName());

            TextView textView = findViewById(idInt);
            outState.putInt(id, Integer.parseInt(textView.getText().toString()));
        }

        TextView result = findViewById(R.id.gpaDisplay);
        outState.putDouble("result", Double.parseDouble(result.getText().toString()));
    }
}
