package com.labs.radik.lab5amo;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText a11, a12, a13, a21, a22, a23, a31, a32, a33, b1, b2, b3;
    private TextView x1, x2, x3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        a11 = findViewById(R.id.a11);
        a12 = findViewById(R.id.a12);
        a13 = findViewById(R.id.a13);
        a21 = findViewById(R.id.a21);
        a22 = findViewById(R.id.a22);
        a23 = findViewById(R.id.a23);
        a31 = findViewById(R.id.a31);
        a32 = findViewById(R.id.a32);
        a33 = findViewById(R.id.a33);
        x1 = findViewById(R.id.tv_x1);
        x2 = findViewById(R.id.tv_x2);
        x3 = findViewById(R.id.tv_x3);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        Button mCalculateButton = findViewById(R.id.btn_calc);
        mCalculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCalculating();
            }
        });
    }

    private void startCalculating() {
        double[][] A = getAMatrix();
        double[] B  = getBMatrix();
        ArrayList<Double> x = calculateX(A, B);
        outputResults(x);
    }

    @SuppressLint("SetTextI18n")
    private void outputResults(ArrayList<Double> x) {
        x1.setText(x.get(0).toString());
        x2.setText(x.get(1).toString());
        x3.setText(x.get(2).toString());
    }


    public double[][] getAMatrix() {
        double[][] AMatrix = new double[3][3];
        try {
            AMatrix[0][0] = Double.parseDouble(a11.getText().toString());
            AMatrix[0][1] = Double.parseDouble(a12.getText().toString());
            AMatrix[0][2] = Double.parseDouble(a13.getText().toString());
            AMatrix[1][0] = Double.parseDouble(a21.getText().toString());
            AMatrix[1][1] = Double.parseDouble(a22.getText().toString());
            AMatrix[1][2] = Double.parseDouble(a23.getText().toString());
            AMatrix[2][0] = Double.parseDouble(a31.getText().toString());
            AMatrix[2][1] = Double.parseDouble(a32.getText().toString());
            AMatrix[2][2] = Double.parseDouble(a33.getText().toString());
        }catch(NumberFormatException e) {
            Toast.makeText(this, "Input correct values", Toast.LENGTH_SHORT).show();
        }
        return AMatrix;
    }

    public double[] getBMatrix() {
        double[] BMatrix = new double[3];
        try {
            BMatrix[0] = Double.parseDouble(b1.getText().toString());
            BMatrix[1] = Double.parseDouble(b2.getText().toString());
            BMatrix[2] = Double.parseDouble(b3.getText().toString());
        }catch(NumberFormatException e) {
            Toast.makeText(this, "Input correct values", Toast.LENGTH_SHORT).show();
        }
        return BMatrix;
    }

    private ArrayList<Double> calculateX(double[][] a, double[] b) {
        ArrayList<Double> x = new ArrayList<>(3);
        Matrix matrix = new Matrix(a);
        Vector vector = new Vector(b);
        Vector result = Gauss.solve(matrix,vector,4);
        for (int i = 0; i < result.getLength(); i++) {
            x.add(result.getElement(i));
        }
        return x;
    }
}
