package net.axiom.util;

import java.awt.Color;

public final class ColorUtil
{
    public double red;
    public double green;
    public double blue;
    public float redf;
    public float greenf;
    public float bluef;
    public int hex;

    public ColorUtil(int var1)
    {
        this.hex = var1;
        double[] var2 = this.hexToDouble(this.hex);
        this.red = var2[0];
        this.green = var2[1];
        this.blue = var2[2];
        float[] var3 = this.doubleToFloat(var2);
        this.redf = var3[0];
        this.greenf = var3[1];
        this.bluef = var3[2];
    }

    public ColorUtil(double var1, double var3, double var5)
    {
        this.hex = this.doubleToHex(var1, var3, var5);
        double[] var7 = this.hexToDouble(this.hex);
        this.red = var7[0];
        this.green = var7[1];
        this.blue = var7[2];
        float[] var8 = this.doubleToFloat(var7);
        this.redf = var8[0];
        this.greenf = var8[1];
        this.bluef = var8[2];
    }

    public int doubleToHex(double var1, double var3, double var5)
    {
        Color var7 = new Color((int)var1, (int)var3, (int)var5);
        return var7.getRGB();
    }

    public double[] hexToDouble(int var1)
    {
        double var2 = (double)((var1 & 16711680) >> 16);
        double var4 = (double)((var1 & 65280) >> 8);
        double var6 = (double)(var1 & 255);
        double[] var8 = new double[] {var2, var4, var6};
        return var8;
    }

    public float[] doubleToFloat(double[] var1)
    {
        float var2 = (float)(var1[0] / 255.0D);
        float var3 = (float)(var1[1] / 255.0D);
        float var4 = (float)(var1[2] / 255.0D);
        float[] var5 = new float[] {var2, var3, var4};
        return var5;
    }

    public static int getColor(boolean var0)
    {
        int var1 = var0 ? -16724737 : -10592674;
        return var1;
    }
}
