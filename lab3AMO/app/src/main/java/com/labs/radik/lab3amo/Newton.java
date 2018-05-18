package com.labs.radik.lab3amo;

/**
 * Created by Radik on 04.04.2018.
 */

public class Newton {
    
    public static double getInterpolationNewton(double x, int r, double[] xt, double[] yt) {
        double result = yt[0];
        double buf = 1;
        for (int k = 1; k < r; k++) {
            double tempSum = 0;
            for (int i = 0; i <= k; i++) {
                double temp = 1;
                for (int j = 0; j < i; j++) {
                    temp = temp * (xt[i] - xt[j]);
                }
                for (int j = i + 1; j <= k; j++) {
                    temp = temp * (xt[i] - xt[j]);
                }
                temp = yt[i] / temp;
                tempSum += temp;
            }
            buf = buf * (x - xt[k - 1]);
            result = result + tempSum * buf;
        }
        return result;
    }

    public static double getErrorNewton(double x, int a, int b, int r) {
        double[] xt = MainActivity.getXt(a, b, r);
        double[] yt = MainActivity.getYt(xt, r);
        double result = getInterpolationNewton(x, r, xt, yt);
        double result1 = Math.exp(Math.sin(x));
        return result1 - result;
    }

    public static double getErrorOfErrorNewton(double x, int a, int b, int r) {
        double result2 = Math.exp(Math.sin(x));
        double[] xt = MainActivity.getXt(a, b, r + 1);
        double[] ft = MainActivity.getYt(xt, r + 1);
        double result1 = getInterpolationNewton(x, r + 1, xt, ft);
        xt = MainActivity.getXt(a, b, r);
        ft = MainActivity.getYt(xt, r);
        double result = getInterpolationNewton(x, r, xt, ft);
        return (result2 - result1) / (result1 - result) * Math.pow(10, -7);
    }

    public static double getBlurinessNewton(double x, int a, int b, int r) {
        return getErrorOfErrorNewton(x, a, b, r)/getErrorNewton(x, a, b, r);
    }
}
