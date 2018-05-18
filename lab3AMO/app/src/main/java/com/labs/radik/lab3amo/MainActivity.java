package com.labs.radik.lab3amo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView mResultTextView, mErrorTextView, mBlurinessTextView;
    private EditText mXValueEditText, mDivideEditText, mLeftEditText, mRightEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mBlurinessTextView = findViewById(R.id.tv_error_error);
        mErrorTextView = findViewById(R.id.tv_value_error);
        mResultTextView = findViewById(R.id.tv_value_y);
        Button mLagrangeButton = findViewById(R.id.btn_lagrange);
        mLagrangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateLagrange();
            }
        });
        Button mNewtonButton = findViewById(R.id.btn_newton);
        mNewtonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateNewton();
            }
        });
        Button mGraphButton = findViewById(R.id.btn_graph);
        mGraphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buildGraphs();
            }
        });
        mXValueEditText = findViewById(R.id.et_value_x);
        mDivideEditText = findViewById(R.id.et_divide);
        mLeftEditText = findViewById(R.id.et_left_num);
        mRightEditText = findViewById(R.id.et_right_num);
    }

    private void buildGraphs() {
        double[] xt;
        double[] yt;
        double x = 0;
        int a = 0;
        int b = 0;
        int r = 0;
        try {
            x = Double.parseDouble(mXValueEditText.getText().toString());
            a = Integer.parseInt(mLeftEditText.getText().toString());
            b = Integer.parseInt(mRightEditText.getText().toString());
            r = Integer.parseInt(mDivideEditText.getText().toString());
        }catch (NumberFormatException e){
            e.printStackTrace();
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
        if((a == b) || (x < a) || (x > b) || (a > b) || (r == 0)){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, GraphBuilder.class);
            xt = getXt(a, b, r);
            yt = getYt(xt, r);
            intent.putExtra("xt", xt);
            intent.putExtra("size", xt.length);
            double[] ytL = new double[xt.length];
            double[] ytN = new double[xt.length];
            double[] ytLE = new double[xt.length];
            double[] ytNE = new double[xt.length];
            for (int i = 0; i < xt.length; i++) {
                ytL[i] = Lagrange.getInterpolationLagrange(xt[i], r, xt, yt);
                ytN[i] = Newton.getInterpolationNewton(xt[i], r, xt, yt);
            }
            for (int i = 1; i < xt.length; i++) {
                ytLE[i] = Lagrange.getErrorOfErrorLagrange(xt[i], a, b, r);
                ytNE[i] = Newton.getErrorOfErrorNewton(xt[i], a, b, r);
            }
            intent.putExtra("ytL", ytL);
            intent.putExtra("ytN", ytN);
            intent.putExtra("ytLE", ytLE);
            intent.putExtra("ytNE", ytNE);
            startActivity(intent);
        }
    }

    public static double[] getXt(double a, double b, int r) {
        double h = (b - a)/(r);
        double[] xt = new double[r + 1];
        xt[0] = a;
        for (int i = 1; i < xt.length - 1; i++) {
            xt[i] = xt[i - 1] + h;
        }
        xt[r] = b;
        return xt;
    }

    public static double[] getYt(double[] xt, int r){
        double[] yt = new double[r + 1];
        for (int i = 0; i < xt.length; i++) {
            yt[i] = Math.exp(Math.cos(xt[i]) * Math.cos(xt[i] * xt[i]));
        }
        return yt;
    }
    
    @SuppressLint("SetTextI18n")
    public void calculateLagrange() {
        double[] xt;
        double[] yt;
        double x = 0;
        int a = 0;
        int b = 0;
        int r = 0;
        try {
            x = Double.parseDouble(mXValueEditText.getText().toString());
            a = Integer.parseInt(mLeftEditText.getText().toString());
            b = Integer.parseInt(mRightEditText.getText().toString());
            r = Integer.parseInt(mDivideEditText.getText().toString());
        }catch (NumberFormatException e){
            e.printStackTrace();
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
        if((a == b) || (x < a) || (x > b) || (a > b) || (r == 0)){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        } else {
            xt = getXt(a, b, r);
            yt = getYt(xt, r);
            double result = Lagrange.getInterpolationLagrange(x, r, xt, yt);
            double error = Lagrange.getErrorLagrange(x, a, b, r);
            double errorOfError = Lagrange.getErrorOfErrorLagrange(x, a, b, r);
            double bluriness = Lagrange.getBlurinessLagrange(x, a, b, r);
            mResultTextView.setText("y = " + String.valueOf(result));
            mErrorTextView.setText("Похибка: " + String.valueOf(errorOfError));
            mBlurinessTextView.setText("Розмитість похибки: " + String.valueOf(bluriness));
        }
    }

    @SuppressLint("SetTextI18n")
    public void calculateNewton() {
        double[] xt;
        double[] yt;
        double x = 0;
        int a = 0;
        int b = 0;
        int r = 0;
        try {
            x = Double.parseDouble(mXValueEditText.getText().toString());
            a = Integer.parseInt(mLeftEditText.getText().toString());
            b = Integer.parseInt(mRightEditText.getText().toString());
            r = Integer.parseInt(mDivideEditText.getText().toString());
        }catch (NumberFormatException e){
            e.printStackTrace();
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
        if((a == b) || (x < a) || (x > b) || (a > b) || (r == 0)){
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        } else {
            xt = getXt(a, b, r);
            yt = getYt(xt, r);
            double result = Newton.getInterpolationNewton(x, r, xt, yt);
            double error = Newton.getErrorNewton(x, a, b, r);
            double errorOfError = Newton.getErrorOfErrorNewton(x, a, b, r);
            double bluriness = Newton.getBlurinessNewton(x, a, b, r);
            mResultTextView.setText("y = " + String.valueOf(result));
            mErrorTextView.setText("Похибка: " + String.valueOf(errorOfError));
            mBlurinessTextView.setText("Розмитість похибки: " + String.valueOf(bluriness));
        }
    }
}
