package com.example.tipcalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class CalculatorActivity extends Activity {
	private EditText etInput;
	private BigDecimal amount;
	private BigDecimal tipPercent;
	private BigDecimal splitRate;
	private Spinner spinner;
	private ArrayAdapter<String> nPeopleListAdapter;
	private String[] nPeopleList = {"No split", "2", "3", "4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        
        /* Initialize */
        amount = new BigDecimal("0");
        tipPercent = new BigDecimal("0");
        splitRate = new BigDecimal("1");
        
        spinner = (Spinner)findViewById(R.id.mySpinner);
        nPeopleListAdapter = new ArrayAdapter<String>(getBaseContext(), 
        		android.R.layout.simple_spinner_item, nPeopleList);
        spinner.setAdapter(nPeopleListAdapter);
        setupSpinnerListener();
        
        etInput = (EditText) findViewById(R.id.etInput);
        setupTextChangeListener();
    }
    
    public void calculateTip10(View v){
    	tipPercent = new BigDecimal("0.1");
    	setTip();
    }
    
    public void calculateTip15(View v){
    	tipPercent = new BigDecimal("0.15");
    	setTip();
    }
    
    public void calculateTip20(View v){
    	tipPercent = new BigDecimal("0.2");
    	setTip();
    }
    
    public void calculateCustomizedTip(View v){
    	EditText etTipInput = (EditText) findViewById(R.id.etTipInput);
    	String tipInput = etTipInput.getText().toString();
    	if (!tipInput.isEmpty()) {
    		tipPercent = new BigDecimal(tipInput).multiply(new BigDecimal("0.01"));
    		setTip();
    	}
    }
    
    /* helper methods */
    private void setTip(){
    	double tip = amount.multiply(tipPercent).divide(splitRate, 2, RoundingMode.HALF_UP).doubleValue();
    	TextView tvTip = (TextView) findViewById(R.id.tvTipOutput);
    	String text = "Tip is: ";
    	if (tip != 0)
    		text += tip;
    	if (splitRate.intValue() != 1)
    		text += " per person";
    	tvTip.setText(text);
    	
    	TextView tvTotal = (TextView) findViewById(R.id.tvTotalOutput);
    	text = "Total after tip: ";
    	double total = amount.multiply(tipPercent).add(amount).setScale(2, RoundingMode.HALF_UP).doubleValue();
    	if (tip != 0)
    		text += total;
    	tvTotal.setText(text);
    }
    
    private void setupSpinnerListener(){
    	spinner.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				switch(position){
				case 1:
					splitRate = new BigDecimal("2");
					break;
				case 2:
					splitRate = new BigDecimal("3");
					break;
				case 3:
					splitRate = new BigDecimal("4");
					break;
				case 0:
				default:
					splitRate = new BigDecimal("1");
					break;
				}
				setTip();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {			
			}

        });
    }
    
    private void setupTextChangeListener(){
    	etInput.addTextChangedListener(new TextWatcher(){
    		@Override
    		public void afterTextChanged(Editable s){
    			if (s.toString().isEmpty()) 
    				amount = new BigDecimal("0");
    			else
    				amount = new BigDecimal(s.toString());
    			setTip();
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
