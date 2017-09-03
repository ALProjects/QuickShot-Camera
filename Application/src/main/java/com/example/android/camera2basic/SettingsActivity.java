package com.example.android.camera2basic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

/*
SettingsActivity is everything in the "Settings" screen (i.e. the cog on the bottom right corner).
 */
public class SettingsActivity extends AppCompatActivity {

    //Settings may not have been instantiated earlier, hence they are all static instead
    static protected Integer mFrameRate = new Integer(0);
    Spinner mFrameRateSpinner = null;
    static EditText mToleranceEdit = null;
    static Spinner mResSpinner = null;
    static int spinnerVal = 0;
    static int resSpinnerVal = 0;
    static Integer toleranceVisualString = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.spinner_item, getResources().getStringArray(R.array.frame_rate_array)
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);

        mFrameRateSpinner = (Spinner) findViewById(R.id.spinner);
        mFrameRateSpinner.setAdapter(spinnerArrayAdapter);
        mFrameRateSpinner.setSelection(spinnerVal);

        //This is the listener for the frame rate spinner (this will save away the value such that
        //the next time the user opens up settings again, it looks the same)
        mFrameRateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                spinnerVal = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                mFrameRateSpinner.setSelection(spinnerVal);
            }
        });

        ArrayList<String> resArrayList = new ArrayList<>();

        // Setup for Resolution spinner
        for (int i = 0; i < Camera2BasicFragment.mResList.size(); i++) {
            resArrayList.add(Camera2BasicFragment.mResList.get(i).toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, R.layout.spinner_item, resArrayList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mResSpinner = (Spinner) findViewById(R.id.resSpinner);
        mResSpinner.setAdapter(adapter);
        mResSpinner.setSelection(resSpinnerVal);

        //Listener for Resolution Spinner (this will save away the value such that the next time
        //the user opens up settings again, it looks the same)
        mResSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                resSpinnerVal = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                mResSpinner.setSelection(resSpinnerVal);
            }
        });

        mToleranceEdit = (EditText) findViewById(R.id.toleranceChoice);
        mToleranceEdit.setText(toleranceVisualString.toString());
    }

    public void closeButtonHandler(View v){
        mFrameRate = Integer.parseInt(mFrameRateSpinner.getSelectedItem().toString());
        toleranceVisualString = Integer.parseInt(mToleranceEdit.getText().toString());
        finish();
    }
}


