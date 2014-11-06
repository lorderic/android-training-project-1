package com.example.tipcalculator;

import java.math.BigDecimal;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.widget.EditText;
import android.widget.TextView;


public class CalculatorActivity extends Activity {
	EditText etInput;
	BigDecimal amount;
	BigDecimal tipPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        amount = new BigDecimal("0");
        tipPercent = new BigDecimal("0");
        
        etInput = (EditText) findViewById(R.id.etInput);
        setupTextChangeListener();
    }
    
    public void calculateTip10(View v){
    	tipPercent = new BigDecimal("0.1");
    	setTip(amount.multiply(tipPercent).doubleValue());
    }
    
    public void calculateTip15(View v){
    	tipPercent = new BigDecimal("0.15");
    	setTip(amount.multiply(tipPercent).doubleValue());
    }
    
    public void calculateTip20(View v){
    	tipPercent = new BigDecimal("0.2");
    	setTip(amount.multiply(tipPercent).doubleValue());
    }
    
    /* helper methods */
    private void setTip(double tip){
    	TextView tvTip = (TextView) findViewById(R.id.tvTipOutput);
    	String text = "Tip is: ";
    	if (tip != 0)
    		text += tip;
    	tvTip.setText(text);
    }
    
    private void setupTextChangeListener(){
    	etInput.addTextChangedListener(new TextWatcher(){
    		@Override
    		public void afterTextChanged(Editable s){
    			if (s.toString().isEmpty() == true) 
    				amount = new BigDecimal("0");
    			else
    				amount = new BigDecimal(s.toString());
    			setTip(amount.multiply(tipPercent).doubleValue());
    		}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				
			}
    	});
    }
}
