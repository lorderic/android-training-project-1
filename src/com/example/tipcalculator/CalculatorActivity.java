package com.example.tipcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import android.widget.EditText;
import android.widget.TextView;

public class CalculatorActivity extends Activity {
	private double amount;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        amount = 0;
    }
    
    public void calculateTip10(View v){
    	EditText etInput = (EditText) findViewById(R.id.etInput);
    	amount = Double.parseDouble(etInput.getText().toString());
    	setTip(amount * 0.1);
    }
    
    public void calculateTip15(View v){
    	EditText etInput = (EditText) findViewById(R.id.etInput);
    	amount = Double.parseDouble(etInput.getText().toString());
    	setTip(amount * 0.15);
    }
    
    public void calculateTip20(View v){
    	EditText etInput = (EditText) findViewById(R.id.etInput);
    	amount = Double.parseDouble(etInput.getText().toString());
    	setTip(amount * 0.20);
    }
    
    /* helper methods */
    private void setTip(double tip){
    	TextView tvTip = (TextView) findViewById(R.id.tvTipOutput);
    	String text = "Tip is: " + tip;
    	tvTip.setText(text);
    }
}
