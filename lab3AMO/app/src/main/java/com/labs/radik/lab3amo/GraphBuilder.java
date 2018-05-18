package com.labs.radik.lab3amo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * Created by Radik on 04.04.2018.
 */

public class GraphBuilder extends AppCompatActivity {
    
    private GraphView graph;
    private GraphView errorGraph;
    double[] xt;
    double[] ytL;
    double[] ytN;
    double[] ytLE;
    double[] ytNE;
    int size;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_activity);
        initViews();
        initValues();
        initGraphs();
        buildGraphs();
    }

    private void buildGraphs() {
        DataPoint[] dataPoint1 = new DataPoint[size];
        for (int i = 0; i < dataPoint1.length; i++) {
            dataPoint1[i] = new DataPoint(xt[i], Math.exp(Math.cos(xt[i]) * Math.cos(xt[i] * xt[i])));
        }
        DataPoint[] dataPoint2 = new DataPoint[size];
        for (int i = 0; i < dataPoint2.length; i++) {
            dataPoint2[i] = new DataPoint(xt[i], ytL[i]);
        }

        DataPoint[] dataPoint3 = new DataPoint[size];
        for (int i = 0; i < dataPoint3.length; i++) {
            dataPoint3[i] = new DataPoint(xt[i], ytN[i]);
        }

        DataPoint[] dataPoint4 = new DataPoint[size];
        for (int i = 0; i < dataPoint4.length; i++) {
            dataPoint4[i] = new DataPoint(xt[i], ytLE[i]);
        }

        DataPoint[] dataPoint5 = new DataPoint[size];
        for (int i = 0; i < dataPoint5.length; i++) {
            dataPoint5[i] = new DataPoint(xt[i], ytNE[i]);
        }

        LineGraphSeries<DataPoint> series1 = new LineGraphSeries<>(dataPoint1);
        series1.setTitle("y = e^cos(x)*cos(x^2)");
        series1.setColor(Color.RED);
        LineGraphSeries<DataPoint> series2 = new LineGraphSeries<>(dataPoint2);
        series2.setTitle("Lagrange");
        series2.setColor(Color.GREEN);
        LineGraphSeries<DataPoint> series3 = new LineGraphSeries<>(dataPoint3);
        series3.setTitle("Newton");
        series3.setColor(Color.YELLOW);
        LineGraphSeries<DataPoint> series4 = new LineGraphSeries<>(dataPoint4);
        series4.setTitle("Lagrange error");
        series4.setColor(Color.RED);
        LineGraphSeries<DataPoint> series5 = new LineGraphSeries<>(dataPoint5);
        series5.setTitle("Newton error");
        series5.setColor(Color.GREEN);
        graph.addSeries(series1);
        graph.addSeries(series2);
        graph.addSeries(series3);
        errorGraph.addSeries(series4);
        errorGraph.addSeries(series5);

    }

    private void initGraphs() {
        graph.getViewport().setScalable(true);
        graph.getViewport().setScrollable(true);
        graph.getViewport().setScalableY(true);
        graph.getViewport().setScrollableY(true);
        errorGraph.getViewport().setScalable(true);
        errorGraph.getViewport().setScrollable(true);
        errorGraph.getViewport().setScalableY(true);
        errorGraph.getViewport().setScrollableY(true);
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        errorGraph.getLegendRenderer().setVisible(true);
        errorGraph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
    }

    private void initValues() {
        Intent intent = getIntent();
        size = intent.getIntExtra("size", 0);
        xt = new double[size];
        xt = intent.getDoubleArrayExtra("xt");
        ytL = new double[size];
        ytL = intent.getDoubleArrayExtra("ytL");
        ytN = new double[size];
        ytN = intent.getDoubleArrayExtra("ytN");
        ytLE = new double[size];
        ytLE = intent.getDoubleArrayExtra("ytLE");
        ytNE = new double[size];
        ytNE = intent.getDoubleArrayExtra("ytNE");
    }

    private void initViews() {
        graph = findViewById(R.id.graph_view);
        errorGraph = findViewById(R.id.graph_view1);
    }
}
