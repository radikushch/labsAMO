package com.labs.radik.lab3amo;

/**
 * Created by Radik on 04.04.2018.
 */

public class Lagrange {

    public static double getBlurinessLagrange(double x, int a, int b, int r) {
        return getErrorOfErrorLagrange(x, a, b, r)/getErrorLagrange(x, a, b, r);
    }

    public static double getErrorOfErrorLagrange(double x, int a, int b, int r) {
        double result2 = Math.exp(Math.sin(x));
        double[] xt = MainActivity.getXt(a, b, r + 1);
        double[] ft = MainActivity.getYt(xt, r + 1);
        double result1 = getInterpolationLagrange(x, r + 1, xt, ft);
        xt = MainActivity.getXt(a, b, r);
        ft = MainActivity.getYt(xt, r);
        double result = getInterpolationLagrange(x, r, xt, ft);
        return (result2 - result1) / (result1 - result) * Math.pow(10, -7);
    }

    public static double getErrorLagrange(double x, int a, int b, int r) {
        double[] xt = MainActivity.getXt(a, b, r);
        double[] ft = MainActivity.getYt(xt, r);
        double result = getInterpolationLagrange(x, r, xt, ft);
        double result1 = Math.exp(Math.sin(x));
        return result1 - result;
    }

    public static double getInterpolationLagrange(double x, int r, double[] xt, double[] yt) {
        double result = 0;
        for (int i = 0; i <  r; i++) {
            double temp = 1;
            for (int j = 0; j <  r; j++) {
                if(j == i) continue;
                temp = temp * (x - xt[j]) / (xt[i] - xt[j]);
            }
            result = result + temp * yt[i];
        }
        return result;
    }
}
