package com.labs.radik.lab4amo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private TextView mResultTextView;
    private Button mCalculateButton;
    private EditText mPrecisionEditText, mLeftEditText, mRightEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mResultTextView = findViewById(R.id.tv_result);
        mPrecisionEditText = findViewById(R.id.et_value_x);
        mLeftEditText = findViewById(R.id.et_left_num);
        mRightEditText = findViewById(R.id.et_right_num);
        mCalculateButton = findViewById(R.id.btn_newton);
        mCalculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });
    }

    private void calculateResult() {
        double a = 0, b = 0;
        double precision = 0;
        String result = "";
        double x;
        int iteration = 0;
        try{
            a = Double.parseDouble(mLeftEditText.getText().toString());
            b = Double.parseDouble(mRightEditText.getText().toString());
            precision = Double.parseDouble(mPrecisionEditText.getText().toString());
        }catch (NumberFormatException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error input", Toast.LENGTH_SHORT).show();
        }
        if(function(a) * function(b) > 0) {
            Toast.makeText(this, "No results", Toast.LENGTH_SHORT).show();
        }else {
            double tmp;
            if(function(b) * -1 < 0) {
                tmp = a;
                a = b;
                b = tmp;
            }
            do{
                x = a - (function(a)*(b - a)/(function(b) - function(a)));
                iteration++;
                result += ("x = " + x + " on [" + a + ";" + b + "] : " + iteration + "\n");
                tmp = a;
                a = x;
                mResultTextView.setText(result);
            }while(Math.abs(x - tmp) > precision);
        }

    }
    public double function(double x) {
        double result;
        result = Math.log10(x) - 1/(x*x);
        return result;
    }
}
