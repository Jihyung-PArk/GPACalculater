package com.jihyungpark.gpacalculater;

import static com.jihyungpark.gpacalculater.DatabaseHelper.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SubjectCalculatorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseHelper subjectDB;
    Button dataSaveButton, dataDeleteButton;
    Spinner subjectSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_calculator);

        Log.i("test12345", ((EditText) findViewById(R.id.subjectName)).getText().toString());

        if (savedInstanceState != null){

            EditText test1 = findViewById(R.id.itemName1);
            test1.setText(savedInstanceState.getString("test1"));



            for(int i = 1; i < 9; i++) {
                String idItem = "itemName" + i;
                String idMark = "mark" + i;
                String idFullMark = "fullMark" + i;
                String percentageId = "percentage" + i;
                String resultId = "resultEach" + i;

                @SuppressLint("DiscouragedApi") int idItemInt = getResources().getIdentifier(idItem, "id", getPackageName());
                @SuppressLint("DiscouragedApi") int idMarkInt = getResources().getIdentifier(idMark, "id", getPackageName());
                @SuppressLint("DiscouragedApi") int idFullMarkInt = getResources().getIdentifier(idFullMark, "id", getPackageName());
                @SuppressLint("DiscouragedApi") int percentageIdInt = getResources().getIdentifier(percentageId, "id", getPackageName());
                @SuppressLint("DiscouragedApi") int resultIdInt = getResources().getIdentifier(resultId, "id", getPackageName());

                ((EditText) findViewById(idItemInt)).setText(savedInstanceState.getString(idItem));
                ((EditText) findViewById(idMarkInt)).setText(savedInstanceState.getString(idMark));
                ((EditText) findViewById(idFullMarkInt)).setText(savedInstanceState.getString(idFullMark));
                ((EditText) findViewById(percentageIdInt)).setText(savedInstanceState.getString(percentageId));
                ((TextView) findViewById(resultIdInt)).setText(savedInstanceState.getString(resultId));
            }

            ((EditText) findViewById(R.id.subjectName)).setText("subjectName");
            ((TextView) findViewById(R.id.subjectID)).setText("subjectID");


        }

        dataSaveButton = findViewById(R.id.dataSaveButton);
        dataDeleteButton = findViewById(R.id.dataDeleteButton);
        subjectSpinner = findViewById(R.id.subjectSpinner);
        subjectSpinner.setOnItemSelectedListener(this);
       subjectDB = new DatabaseHelper(this);

       AddData();
       DeleteData();
       loadSubjectSpinnerData();


    }

    public void  AddData(){
        dataSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String subjectName = ((EditText) findViewById(R.id.subjectName)).getText().toString();
                String subjectId = ((TextView) findViewById(R.id.subjectID)).getText().toString();

                ArrayList<String> itemNameValues = new ArrayList<>();
                ArrayList<String> markValues = new ArrayList<>();
                ArrayList<String> fullMarkValues = new ArrayList<>();
                ArrayList<String> percentageValues = new ArrayList<>();
                ArrayList<String> resultValues = new ArrayList<>();

                int temp = ((TextView) findViewById(R.id.subjectID)).getText().toString().length();
                Log.i("temp", String.valueOf(temp));

                for(int i = 1; i < 9; i++){
                    String idItem = "itemName" + i;
                    String idMark = "mark" + i;
                    String idFullMark = "fullMark" + i;
                    String percentageId = "percentage" + i;
                    String resultId = "resultEach" + i;

                    @SuppressLint("DiscouragedApi") int idItemInt = getResources().getIdentifier(idItem, "id", getPackageName());
                    @SuppressLint("DiscouragedApi") int idMarkInt = getResources().getIdentifier(idMark, "id", getPackageName());
                    @SuppressLint("DiscouragedApi") int idFullMarkInt = getResources().getIdentifier(idFullMark, "id", getPackageName());
                    @SuppressLint("DiscouragedApi") int percentageIdInt = getResources().getIdentifier(percentageId, "id", getPackageName());
                    @SuppressLint("DiscouragedApi") int resultIdInt = getResources().getIdentifier(resultId, "id", getPackageName());

                    itemNameValues.add(((EditText) findViewById(idItemInt)).getText().toString());
                    markValues.add(((EditText) findViewById(idMarkInt)).getText().toString());
                    fullMarkValues.add(((EditText) findViewById(idFullMarkInt)).getText().toString());
                    percentageValues.add((((EditText) findViewById(percentageIdInt)).getText().toString()).isEmpty() ? "0" : ((TextView) findViewById(percentageIdInt)).getText().toString());
                    resultValues.add(((TextView) findViewById(resultIdInt)).getText().toString());
                }
                if(!subjectName.isEmpty()) {
                    if (!subjectId.isEmpty()) {
                        boolean update = subjectDB.updateData(subjectId, subjectName,
                                itemNameValues.get(0), markValues.get(0), fullMarkValues.get(0), percentageValues.get(0), resultValues.get(0),
                                itemNameValues.get(1), markValues.get(1), fullMarkValues.get(1), percentageValues.get(1), resultValues.get(1),
                                itemNameValues.get(2), markValues.get(2), fullMarkValues.get(2), percentageValues.get(2), resultValues.get(2),
                                itemNameValues.get(3), markValues.get(3), fullMarkValues.get(3), percentageValues.get(3), resultValues.get(3),
                                itemNameValues.get(4), markValues.get(4), fullMarkValues.get(4), percentageValues.get(4), resultValues.get(4),
                                itemNameValues.get(5), markValues.get(5), fullMarkValues.get(5), percentageValues.get(5), resultValues.get(5),
                                itemNameValues.get(6), markValues.get(6), fullMarkValues.get(6), percentageValues.get(6), resultValues.get(6),
                                itemNameValues.get(7), markValues.get(7), fullMarkValues.get(7), percentageValues.get(7), resultValues.get(7));

                        if (update == true) {
                            Toast.makeText(SubjectCalculatorActivity.this, "Data successfully Updated", Toast.LENGTH_LONG).show();
                            loadSubjectSpinnerData();
                        } else {
                            Toast.makeText(SubjectCalculatorActivity.this, "Something went wrong!", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        boolean insertData = subjectDB.addData(subjectName,
                                itemNameValues.get(0), markValues.get(0), fullMarkValues.get(0), percentageValues.get(0), resultValues.get(0),
                                itemNameValues.get(1), markValues.get(1), fullMarkValues.get(1), percentageValues.get(1), resultValues.get(1),
                                itemNameValues.get(2), markValues.get(2), fullMarkValues.get(2), percentageValues.get(2), resultValues.get(2),
                                itemNameValues.get(3), markValues.get(3), fullMarkValues.get(3), percentageValues.get(3), resultValues.get(3),
                                itemNameValues.get(4), markValues.get(4), fullMarkValues.get(4), percentageValues.get(4), resultValues.get(4),
                                itemNameValues.get(5), markValues.get(5), fullMarkValues.get(5), percentageValues.get(5), resultValues.get(5),
                                itemNameValues.get(6), markValues.get(6), fullMarkValues.get(6), percentageValues.get(6), resultValues.get(6),
                                itemNameValues.get(7), markValues.get(7), fullMarkValues.get(7), percentageValues.get(7), resultValues.get(7));

                        if (insertData == true) {
                            Toast.makeText(SubjectCalculatorActivity.this, "Data successfully Inserted", Toast.LENGTH_LONG).show();
                            loadSubjectSpinnerData();
                        } else {
                            Toast.makeText(SubjectCalculatorActivity.this, "Something went wrong!", Toast.LENGTH_LONG).show();
                        }
                    }
                }else{
                    Toast.makeText(SubjectCalculatorActivity.this, "Please insert subject name.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void loadSubjectSpinnerData(){
        List<String> subjectName = subjectDB.getAllSubjectName();
        Log.i("Test666", subjectName.toString());

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, subjectName);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectSpinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String subjectName = parent.getItemAtPosition(position).toString();

        ViewData(subjectName);

        Log.i("test000", subjectName);

        if(subjectName == "Select Subject"){
            ((TextView) findViewById(R.id.subjectID)).setText("");
            ((EditText) findViewById(R.id.subjectName)).setText("");

            for(int i = 1; i < 9; i++){
                String idItem = "itemName" + i;
                String idMark = "mark" + i;
                String idFullMark = "fullMark" + i;
                String idPercentage = "percentage" + i;
                String idResultEach = "resultEach" + i;

                @SuppressLint("DiscouragedApi") int idItemInt = getResources().getIdentifier(idItem, "id", getPackageName());
                @SuppressLint("DiscouragedApi") int idMarkInt = getResources().getIdentifier(idMark, "id", getPackageName());
                @SuppressLint("DiscouragedApi") int idFullMarkInt = getResources().getIdentifier(idFullMark, "id", getPackageName());
                @SuppressLint("DiscouragedApi") int idPercentageInt = getResources().getIdentifier(idPercentage, "id", getPackageName());
                @SuppressLint("DiscouragedApi") int idResultEachInt = getResources().getIdentifier(idResultEach, "id", getPackageName());

                ((EditText) findViewById(idItemInt)).setText("");
                ((EditText) findViewById(idMarkInt)).setText("");
                ((EditText) findViewById(idFullMarkInt)).setText("");
                ((EditText) findViewById(idPercentageInt)).setText("");
                ((TextView) findViewById(idResultEachInt)).setText("");
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void ViewData(String subjectName){


        Cursor data = subjectDB.showData(subjectName);

        Log.i("Text1", data.toString());
        StringBuffer buffer = new StringBuffer();
        while (data.moveToNext()){

            ((TextView) findViewById(R.id.subjectID)).setText(data.getString(0));
            ((EditText) findViewById(R.id.subjectName)).setText(data.getString(1));

            Log.i("test1234", ((TextView) findViewById(R.id.subjectID)).getText().toString());

                int row = 1;

                for(int i = 1; i < 9; i++){
                    String idItem = "itemName" + i;
                    String idMark = "mark" + i;
                    String idFullMark = "fullMark" + i;
                    String idPercentage = "percentage" + i;
                    String idResultEach = "resultEach" + i;

                    @SuppressLint("DiscouragedApi") int idItemInt = getResources().getIdentifier(idItem, "id", getPackageName());
                    @SuppressLint("DiscouragedApi") int idMarkInt = getResources().getIdentifier(idMark, "id", getPackageName());
                    @SuppressLint("DiscouragedApi") int idFullMarkInt = getResources().getIdentifier(idFullMark, "id", getPackageName());
                    @SuppressLint("DiscouragedApi") int idPercentageInt = getResources().getIdentifier(idPercentage, "id", getPackageName());
                    @SuppressLint("DiscouragedApi") int idResultEachInt = getResources().getIdentifier(idResultEach, "id", getPackageName());

                    ((EditText) findViewById(idItemInt)).setText(data.getString(row + i));
                    ((EditText) findViewById(idMarkInt)).setText(data.getString(row + i + 1));
                    ((EditText) findViewById(idFullMarkInt)).setText(data.getString(row + i + 2));
                    ((EditText) findViewById(idPercentageInt)).setText(data.getString(row + i + 3));
                    ((TextView) findViewById(idResultEachInt)).setText(data.getString(row + i + 4));

                    row += 4;
                }

        }

    }

    public void DeleteData(){
        dataDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = ((TextView) findViewById(R.id.subjectID)).getText().toString();
                int temp = ((TextView) findViewById(R.id.subjectID)).getText().toString().length();

                if(temp > 0){
                    Integer deleteRow = subjectDB.deleteData(id);
                    if(deleteRow > 0){
                        Toast.makeText(SubjectCalculatorActivity.this, "Successfully Deleted the data", Toast.LENGTH_LONG).show();
                        loadSubjectSpinnerData();
                    }else{
                        Toast.makeText(SubjectCalculatorActivity.this, "Something went wrong!", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(SubjectCalculatorActivity.this, "You must choose subject to Delete.", Toast.LENGTH_LONG).show();
                }

                ((TextView) findViewById(R.id.resultTextView)).setVisibility(View.INVISIBLE);
                ((TextView) findViewById(R.id.resultTextView2)).setVisibility(View.INVISIBLE);
            }
        });
    }

    public void backToMenuClicked(View view){
        Intent backToMenu = new Intent(this, MenuPageActivity.class);
        startActivity(backToMenu);
    }
    public void convertButtonClicked(View view){

        int getId = view.getId();
        ArrayList<String> itemNameValues = new ArrayList<>();
        ArrayList<String> markValues = new ArrayList<>();
        ArrayList<String> fullMarkValues = new ArrayList<>();
        ArrayList<String> percentageValues = new ArrayList<>();

        for(int i = 1; i < 9; i++){
            String idItem = "itemName" + i;
            String idMark = "mark" + i;
            String idFullMark = "fullMark" + i;
            String idPercentage = "percentage" + i;

            @SuppressLint("DiscouragedApi") int idItemInt = getResources().getIdentifier(idItem, "id", getPackageName());
            @SuppressLint("DiscouragedApi") int idMarkInt = getResources().getIdentifier(idMark, "id", getPackageName());
            @SuppressLint("DiscouragedApi") int idFullMarkInt = getResources().getIdentifier(idFullMark, "id", getPackageName());
            @SuppressLint("DiscouragedApi") int idPercentageInt = getResources().getIdentifier(idPercentage, "id", getPackageName());

            itemNameValues.add(((TextView) findViewById(idItemInt)).getText().toString());
            markValues.add(((TextView) findViewById(idMarkInt)).getText().toString());
            fullMarkValues.add(((TextView) findViewById(idFullMarkInt)).getText().toString());
            percentageValues.add(((TextView) findViewById(idPercentageInt)).getText().toString());
        }

        if(getId == R.id.convertButton1){
            ((TextView) findViewById(R.id.resultEach1)).setText(SubjectCalculateFormula.subjectCalculate(itemNameValues.get(0), markValues.get(0), fullMarkValues.get(0), percentageValues.get(0)));
            ((LinearLayout) findViewById(R.id.convertResult1)).setVisibility(View.VISIBLE);
        } else if (getId == R.id.convertButton2) {
            ((TextView) findViewById(R.id.resultEach2)).setText(SubjectCalculateFormula.subjectCalculate(itemNameValues.get(1), markValues.get(1), fullMarkValues.get(1), percentageValues.get(1)));
            ((LinearLayout) findViewById(R.id.convertResult2)).setVisibility(View.VISIBLE);
        } else if (getId == R.id.convertButton3) {
            ((TextView) findViewById(R.id.resultEach3)).setText(SubjectCalculateFormula.subjectCalculate(itemNameValues.get(2), markValues.get(2), fullMarkValues.get(2), percentageValues.get(2)));
            ((LinearLayout) findViewById(R.id.convertResult3)).setVisibility(View.VISIBLE);
        } else if (getId == R.id.convertButton4) {
            ((TextView) findViewById(R.id.resultEach4)).setText(SubjectCalculateFormula.subjectCalculate(itemNameValues.get(3), markValues.get(3), fullMarkValues.get(3), percentageValues.get(3)));
            ((LinearLayout) findViewById(R.id.convertResult4)).setVisibility(View.VISIBLE);
        } else if (getId == R.id.convertButton5) {
            ((TextView) findViewById(R.id.resultEach5)).setText(SubjectCalculateFormula.subjectCalculate(itemNameValues.get(4), markValues.get(4), fullMarkValues.get(4), percentageValues.get(4)));
            ((LinearLayout) findViewById(R.id.convertResult5)).setVisibility(View.VISIBLE);
        } else if (getId == R.id.convertButton6) {
            ((TextView) findViewById(R.id.resultEach6)).setText(SubjectCalculateFormula.subjectCalculate(itemNameValues.get(5), markValues.get(5), fullMarkValues.get(5), percentageValues.get(5)));
            ((LinearLayout) findViewById(R.id.convertResult6)).setVisibility(View.VISIBLE);
        }else if (getId == R.id.convertButton7) {
            ((TextView) findViewById(R.id.resultEach7)).setText(SubjectCalculateFormula.subjectCalculate(itemNameValues.get(6), markValues.get(6), fullMarkValues.get(6), percentageValues.get(6)));
            ((LinearLayout) findViewById(R.id.convertResult7)).setVisibility(View.VISIBLE);
        }else if (getId == R.id.convertButton8) {
            ((TextView) findViewById(R.id.resultEach8)).setText(SubjectCalculateFormula.subjectCalculate(itemNameValues.get(7), markValues.get(7), fullMarkValues.get(7), percentageValues.get(7)));
            ((LinearLayout) findViewById(R.id.convertResult8)).setVisibility(View.VISIBLE);
        }
    }

    public void returnCovertButtonClicked(View view){
        int getId = view.getId();

        if(getId == R.id.returnCovertButton1){
            ((LinearLayout) findViewById(R.id.convertResult1)).setVisibility(View.INVISIBLE);
        } else if (getId == R.id.returnCovertButton2) {
            ((LinearLayout) findViewById(R.id.convertResult2)).setVisibility(View.INVISIBLE);
        } else if (getId == R.id.returnCovertButton3) {
            ((LinearLayout) findViewById(R.id.convertResult3)).setVisibility(View.INVISIBLE);
        } else if (getId == R.id.returnCovertButton4) {
            ((LinearLayout) findViewById(R.id.convertResult4)).setVisibility(View.INVISIBLE);
        } else if (getId == R.id.returnCovertButton5) {
            ((LinearLayout) findViewById(R.id.convertResult5)).setVisibility(View.INVISIBLE);
        } else if (getId == R.id.returnCovertButton6) {
            ((LinearLayout) findViewById(R.id.convertResult6)).setVisibility(View.INVISIBLE);
        }else if (getId == R.id.returnCovertButton7) {
            ((LinearLayout) findViewById(R.id.convertResult7)).setVisibility(View.INVISIBLE);
        }else if (getId == R.id.returnCovertButton8) {
            ((LinearLayout) findViewById(R.id.convertResult8)).setVisibility(View.INVISIBLE);
        }
    }

    public void calculateButtonClicked(View view){

        ArrayList<String> percentageValues = new ArrayList<>();
        ArrayList<Double> resultValues = new ArrayList<>();
        int totalPercentageValue = 0;
        double totalResultValue = 0;

        for(int i = 1; i < 9; i++){

            String percentageId = "percentage" + i;
            String resultId = "resultEach" + i;
            String idItem = "itemName" + i;
            String idMark = "mark" + i;
            String idFullMark = "fullMark" + i;
            String idPercentage = "percentage" + i;

            @SuppressLint("DiscouragedApi") int percentageIdInt = getResources().getIdentifier(percentageId, "id", getPackageName());
            @SuppressLint("DiscouragedApi") int resultIdInt = getResources().getIdentifier(resultId, "id", getPackageName());
            @SuppressLint("DiscouragedApi") int idItemInt = getResources().getIdentifier(idItem, "id", getPackageName());
            @SuppressLint("DiscouragedApi") int idMarkInt = getResources().getIdentifier(idMark, "id", getPackageName());
            @SuppressLint("DiscouragedApi") int idFullMarkInt = getResources().getIdentifier(idFullMark, "id", getPackageName());
            @SuppressLint("DiscouragedApi") int idPercentageInt = getResources().getIdentifier(idPercentage, "id", getPackageName());

            percentageValues.add((((TextView) findViewById(percentageIdInt)).getText().toString()).isEmpty() ? "0" : ((TextView) findViewById(percentageIdInt)).getText().toString());

            String item = ((TextView) findViewById(idItemInt)).getText().toString();
            String mark = ((TextView) findViewById(idMarkInt)).getText().toString();
            String fullMark = ((TextView) findViewById(idFullMarkInt)).getText().toString();
            String percentage = ((TextView) findViewById(idPercentageInt)).getText().toString();

            String subjectCalculate = SubjectCalculateFormula.subjectCalculateBeforeConvert(item, mark, fullMark, percentage);

            resultValues.add(subjectCalculate.isEmpty() ? 0 : Double.parseDouble(subjectCalculate));
        }

        for (int i = 0; i < percentageValues.size(); i++){
            totalPercentageValue += Integer.parseInt(percentageValues.get(i));
            totalResultValue += resultValues.get(i);
        }

        TextView totalResult = findViewById(R.id.resultTextView);
        TextView expectResult = findViewById(R.id.resultTextView2);
        if(totalPercentageValue == 100){
            totalResult.setText(SubjectCalculateFormula.subjectGrade(totalResultValue));
            expectResult.setVisibility(View.INVISIBLE);
            totalResult.setVisibility(View.VISIBLE);
        }else if(0 < totalPercentageValue && totalPercentageValue < 100){
            expectResult.setText(SubjectCalculateFormula.predictGrade(totalPercentageValue, totalResultValue));
            totalResult.setVisibility(View.INVISIBLE);
            expectResult.setVisibility(View.VISIBLE);
        }else{
            expectResult.setText("Please fill it up.");
            totalResult.setVisibility(View.INVISIBLE);
            expectResult.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        EditText test = findViewById(R.id.itemName1);
        String test1 = test.getText().toString();

        outState.putString("test1", test1);

        for(int i = 1; i < 9; i++) {
            String idItem = "itemName" + i;
            String idMark = "mark" + i;
            String idFullMark = "fullMark" + i;
            String percentageId = "percentage" + i;
            String resultId = "resultEach" + i;

            @SuppressLint("DiscouragedApi") int idItemInt = getResources().getIdentifier(idItem, "id", getPackageName());
            @SuppressLint("DiscouragedApi") int idMarkInt = getResources().getIdentifier(idMark, "id", getPackageName());
            @SuppressLint("DiscouragedApi") int idFullMarkInt = getResources().getIdentifier(idFullMark, "id", getPackageName());
            @SuppressLint("DiscouragedApi") int percentageIdInt = getResources().getIdentifier(percentageId, "id", getPackageName());
            @SuppressLint("DiscouragedApi") int resultIdInt = getResources().getIdentifier(resultId, "id", getPackageName());

            String idItemString = ((EditText) findViewById(idItemInt)).getText().toString();
            String idMarkString = ((EditText) findViewById(idMarkInt)).getText().toString();
            String idFullMarkString = ((EditText) findViewById(idFullMarkInt)).getText().toString();
            String percentageIdString = (((EditText) findViewById(percentageIdInt)).getText().toString()).isEmpty() ? "0" : ((TextView) findViewById(percentageIdInt)).getText().toString();
            String resultIdString = ((TextView) findViewById(resultIdInt)).getText().toString();
            outState.putString(idItem, idItemString);
            outState.putString(idMark, idMarkString);
            outState.putString(idFullMark, idFullMarkString);
            outState.putString(percentageId, percentageIdString);
            outState.putString(resultId, resultIdString);
        }

        String subjectName = ((EditText) findViewById(R.id.subjectName)).getText().toString();
        String subjectId = ((TextView) findViewById(R.id.subjectID)).getText().toString();

        outState.putString("subjectID", subjectId);
        outState.putString("subjectName", subjectName);

        super.onSaveInstanceState(outState);
    }

}