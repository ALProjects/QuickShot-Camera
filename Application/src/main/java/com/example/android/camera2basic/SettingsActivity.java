package com.example.android.camera2basic;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/*
SettingsActivity is everything in the "Settings" screen (i.e. the cog on the bottom right corner).
 */
public class SettingsActivity extends AppCompatActivity {



    static protected Integer mFrameRate = new Integer(0);
    Spinner mSpinner = null;
    static EditText mToleranceEdit = null;
    static Spinner mResSpinner = null;
    static int spinnerVal = 0;
    static int spinnerVal2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item,getResources().getStringArray(R.array.frame_rate_array)
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);



        mSpinner = (Spinner) findViewById(R.id.spinner);
        mSpinner.setAdapter(spinnerArrayAdapter);
        mSpinner.setSelection(spinnerVal);

        //This is the listener for the frame rate spinner
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                spinnerVal = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                mSpinner.setSelection(spinnerVal);
            }

        });

        ArrayList<String> resArrayList = new ArrayList<>();

        // Setup for Resolution spinner
        for (int i = 0; i < Camera2BasicFragment.mResList.size(); i++){
            resArrayList.add(Camera2BasicFragment.mResList.get(i).toString());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, R.layout.spinner_item, resArrayList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mResSpinner = (Spinner) findViewById(R.id.resSpinner);
        mResSpinner.setAdapter(adapter);


        mToleranceEdit = (EditText) findViewById(R.id.toleranceChoice);







    }



    public void closeButtonHandler(View v){
        mFrameRate = Integer.parseInt(mSpinner.getSelectedItem().toString());
        finish();
    }



}


