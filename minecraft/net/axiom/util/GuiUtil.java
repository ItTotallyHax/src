package net.axiom.util;

import net.axiom.AxiomWrapper;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.Gui;
import net.minecraft.src.RenderManager;
import net.minecraft.src.Tessellator;
import org.lwjgl.opengl.GL11;

public class GuiUtil
{
    public static void drawBorderedRect(int var0, int var1, int var2, int var3, int var4, int var5, int var6)
    {
        Gui.drawRect(var0 + var4, var1 + var4, var2 - var4, var3 - var4, var6);
        Gui.drawRect(var0 + var4, var1 + var4, var2, var1, var5);
        Gui.drawRect(var0, var1, var0 + var4, var3, var5);
        Gui.drawRect(var2, var3, var2 - var4, var1 + var4, var5);
        Gui.drawRect(var0, var3 - var4, var2, var3, var5);
    }

    public static void drawOutlinedBoundingBox(AxisAlignedBB var0)
    {
        Tessellator var1 = Tessellator.instance;
        var1.startDrawing(3);
        var1.addVertex(var0.minX, var0.minY, var0.minZ);
        var1.addVertex(var0.maxX, var0.minY, var0.minZ);
        var1.addVertex(var0.maxX, var0.minY, var0.maxZ);
        var1.addVertex(var0.minX, var0.minY, var0.maxZ);
        var1.addVertex(var0.minX, var0.minY, var0.minZ);
        var1.draw();
        var1.startDrawing(3);
        var1.addVertex(var0.minX, var0.maxY, var0.minZ);
        var1.addVertex(var0.maxX, var0.maxY, var0.minZ);
        var1.addVertex(var0.maxX, var0.maxY, var0.maxZ);
        var1.addVertex(var0.minX, var0.maxY, var0.maxZ);
        var1.addVertex(var0.minX, var0.maxY, var0.minZ);
        var1.draw();
        var1.startDrawing(1);
        var1.addVertex(var0.minX, var0.minY, var0.minZ);
        var1.addVertex(var0.minX, var0.maxY, var0.minZ);
        var1.addVertex(var0.maxX, var0.minY, var0.minZ);
        var1.addVertex(var0.maxX, var0.maxY, var0.minZ);
        var1.addVertex(var0.maxX, var0.minY, var0.maxZ);
        var1.addVertex(var0.maxX, var0.maxY, var0.maxZ);
        var1.addVertex(var0.minX, var0.minY, var0.maxZ);
        var1.addVertex(var0.minX, var0.maxY, var0.maxZ);
        var1.draw();
    }

    public static void drawCrossedOutlinedBoundingBox(AxisAlignedBB var0)
    {
        Tessellator var1 = Tessellator.instance;
        var1.startDrawing(3);
        var1.addVertex(var0.minX, var0.minY, var0.minZ);
        var1.addVertex(var0.maxX, var0.minY, var0.minZ);
        var1.addVertex(var0.maxX, var0.minY, var0.maxZ);
        var1.addVertex(var0.minX, var0.minY, var0.maxZ);
        var1.addVertex(var0.minX, var0.minY, var0.minZ);
        var1.draw();
        var1.startDrawing(3);
        var1.addVertex(var0.minX, var0.maxY, var0.minZ);
        var1.addVertex(var0.maxX, var0.maxY, var0.minZ);
        var1.addVertex(var0.maxX, var0.maxY, var0.maxZ);
        var1.addVertex(var0.minX, var0.maxY, var0.maxZ);
        var1.addVertex(var0.minX, var0.maxY, var0.minZ);
        var1.draw();
        var1.startDrawing(1);
        var1.addVertex(var0.minX, var0.minY, var0.minZ);
        var1.addVertex(var0.minX, var0.maxY, var0.minZ);
        var1.addVertex(var0.maxX, var0.minY, var0.minZ);
        var1.addVertex(var0.maxX, var0.maxY, var0.minZ);
        var1.addVertex(var0.maxX, var0.minY, var0.maxZ);
        var1.addVertex(var0.maxX, var0.maxY, var0.maxZ);
        var1.addVertex(var0.minX, var0.minY, var0.maxZ);
        var1.addVertex(var0.minX, var0.maxY, var0.maxZ);
        var1.addVertex(var0.minX, var0.minY, var0.minZ);
        var1.addVertex(var0.minX, var0.maxY, var0.maxZ);
        var1.addVertex(var0.maxX, var0.minY, var0.minZ);
        var1.addVertex(var0.maxX, var0.maxY, var0.maxZ);
        var1.draw();
    }

    public static void drawBoundingBoxQuads(AxisAlignedBB var0)
    {
        Tessellator var1 = Tessellator.instance;
        var1.startDrawingQuads();
        var1.addVertex(var0.minX, var0.minY, var0.minZ);
        var1.addVertex(var0.minX, var0.maxY, var0.minZ);
        var1.addVertex(var0.maxX, var0.minY, var0.minZ);
        var1.addVertex(var0.maxX, var0.maxY, var0.minZ);
        var1.addVertex(var0.maxX, var0.minY, var0.maxZ);
        var1.addVertex(var0.maxX, var0.maxY, var0.maxZ);
        var1.addVertex(var0.minX, var0.minY, var0.maxZ);
        var1.addVertex(var0.minX, var0.maxY, var0.maxZ);
        var1.draw();
        var1.startDrawingQuads();
        var1.addVertex(var0.maxX, var0.maxY, var0.minZ);
        var1.addVertex(var0.maxX, var0.minY, var0.minZ);
        var1.addVertex(var0.minX, var0.maxY, var0.minZ);
        var1.addVertex(var0.minX, var0.minY, var0.minZ);
        var1.addVertex(var0.minX, var0.maxY, var0.maxZ);
        var1.addVertex(var0.minX, var0.minY, var0.maxZ);
        var1.addVertex(var0.maxX, var0.maxY, var0.maxZ);
        var1.addVertex(var0.maxX, var0.minY, var0.maxZ);
        var1.draw();
        var1.startDrawingQuads();
        var1.addVertex(var0.minX, var0.maxY, var0.minZ);
        var1.addVertex(var0.maxX, var0.maxY, var0.minZ);
        var1.addVertex(var0.maxX, var0.maxY, var0.maxZ);
        var1.addVertex(var0.minX, var0.maxY, var0.maxZ);
        var1.addVertex(var0.minX, var0.maxY, var0.minZ);
        var1.addVertex(var0.minX, var0.maxY, var0.maxZ);
        var1.addVertex(var0.maxX, var0.maxY, var0.maxZ);
        var1.addVertex(var0.maxX, var0.maxY, var0.minZ);
        var1.draw();
        var1.startDrawingQuads();
        var1.addVertex(var0.minX, var0.minY, var0.minZ);
        var1.addVertex(var0.maxX, var0.minY, var0.minZ);
        var1.addVertex(var0.maxX, var0.minY, var0.maxZ);
        var1.addVertex(var0.minX, var0.minY, var0.maxZ);
        var1.addVertex(var0.minX, var0.minY, var0.minZ);
        var1.addVertex(var0.minX, var0.minY, var0.maxZ);
        var1.addVertex(var0.maxX, var0.minY, var0.maxZ);
        var1.addVertex(var0.maxX, var0.minY, var0.minZ);
        var1.draw();
        var1.startDrawingQuads();
        var1.addVertex(var0.minX, var0.minY, var0.minZ);
        var1.addVertex(var0.minX, var0.maxY, var0.minZ);
        var1.addVertex(var0.minX, var0.minY, var0.maxZ);
        var1.addVertex(var0.minX, var0.maxY, var0.maxZ);
        var1.addVertex(var0.maxX, var0.minY, var0.maxZ);
        var1.addVertex(var0.maxX, var0.maxY, var0.maxZ);
        var1.addVertex(var0.maxX, var0.minY, var0.minZ);
        var1.addVertex(var0.maxX, var0.maxY, var0.minZ);
        var1.draw();
        var1.startDrawingQuads();
        var1.addVertex(var0.minX, var0.maxY, var0.maxZ);
        var1.addVertex(var0.minX, var0.minY, var0.maxZ);
        var1.addVertex(var0.minX, var0.maxY, var0.minZ);
        var1.addVertex(var0.minX, var0.minY, var0.minZ);
        var1.addVertex(var0.maxX, var0.maxY, var0.minZ);
        var1.addVertex(var0.maxX, var0.minY, var0.minZ);
        var1.addVertex(var0.maxX, var0.maxY, var0.maxZ);
        var1.addVertex(var0.maxX, var0.minY, var0.maxZ);
        var1.draw();
    }

    public static int fadeHex(int var0, int var1, float var2)
    {
        int var3 = (var0 & 255) << 16;
        int var4 = (var0 & 255) << 8;
        int var5 = var0 & 255;
        int var6 = (var0 & 255) << 16;
        int var7 = (var0 & 255) << 8;
        int var8 = var0 & 255;
        boolean var9 = var3 > var6;
        boolean var10 = var4 > var7;
        boolean var11 = var5 > var8;
        int var12 = var3;
        int var13 = var4;
        int var14 = var5;

        if (var9)
        {
            var12 = (int)((float)var3 - 255.0F * var2);
        }

        if (!var9)
        {
            var12 = (int)((float)var12 + 255.0F * var2);
        }

        if (var10)
        {
            var13 = (int)((float)var4 - 255.0F * var2);
        }

        if (!var10)
        {
            var13 = (int)((float)var13 + 255.0F * var2);
        }

        if (var11)
        {
            var14 = (int)((float)var5 - 255.0F * var2);
        }

        if (!var11)
        {
            var14 = (int)((float)var14 + 255.0F * var2);
        }

        if (var12 > 255)
        {
            var12 = 255;
        }

        if (var13 > 255)
        {
            var13 = 255;
        }

        if (var14 > 255)
        {
            var14 = 255;
        }

        if (var12 < 0)
        {
            var12 = 0;
        }

        if (var13 < 0)
        {
            var13 = 0;
        }

        if (var14 < 0)
        {
            var14 = 0;
        }

        return var12 << 16 | var13 << 8 | var14;
    }

    public static void renderLabel(double var0, double var2, double var4, String var6, int var7)
    {
        FontRenderer var8 = AxiomWrapper.mcObj.fontRenderer;
        float var9 = 0.05000001F;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)var0 + 0.0F, (float)var2 + 2.3F, (float)var4);
        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-RenderManager.instance.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(RenderManager.instance.playerViewX, 1.0F, 0.0F, 0.0F);
        GL11.glScalef(-var9, -var9, var9);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDepthMask(false);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        Tessellator var10 = Tessellator.instance;
        byte var11 = 0;
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        var10.startDrawingQuads();
        int var12 = var8.getStringWidth(var6) / 2;
        var10.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
        var10.addVertex((double)(-var12 - 1), (double)(-1 + var11), 0.0D);
        var10.addVertex((double)(-var12 - 1), (double)(8 + var11), 0.0D);
        var10.addVertex((double)(var12 + 1), (double)(8 + var11), 0.0D);
        var10.addVertex((double)(var12 + 1), (double)(-1 + var11), 0.0D);
        var10.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        var8.drawString(var6, -var8.getStringWidth(var6) / 2, var11, var7);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        var8.drawString(var6, -var8.getStringWidth(var6) / 2, var11, var7);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
    }

    public static void drawAxiomButton(int var0, int var1, int var2, int var3, int var4, int var5, int var6)
    {
        var0 *= 2;
        var1 *= 2;
        var2 *= 2;
        var3 *= 2;
        GL11.glPushMatrix();
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        drawGradientRect(var0 + 1, var1 + 1, var2 - 1, var3 - 1, var5, var6);
        drawVerticalLine(var0, var1, var3 - 1, var4);
        drawVerticalLine(var2 - 1, var1, var3 - 1, var4);
        drawHorizontalLine(var0 + 1, var2 - 2, var1, var4);
        drawHorizontalLine(var0 + 1, var2 - 2, var3 - 1, var4);
        GL11.glPopMatrix();
    }

    public static void drawGradientRect(int var0, int var1, int var2, int var3, int var4, int var5)
    {
        float var6 = (float)(var4 >> 24 & 255) / 255.0F;
        float var7 = (float)(var4 >> 16 & 255) / 255.0F;
        float var8 = (float)(var4 >> 8 & 255) / 255.0F;
        float var9 = (float)(var4 & 255) / 255.0F;
        float var10 = (float)(var5 >> 24 & 255) / 255.0F;
        float var11 = (float)(var5 >> 16 & 255) / 255.0F;
        float var12 = (float)(var5 >> 8 & 255) / 255.0F;
        float var13 = (float)(var5 & 255) / 255.0F;
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        Tessellator var14 = Tessellator.instance;
        var14.startDrawingQuads();
        var14.setColorRGBA_F(var7, var8, var9, var6);
        var14.addVertex((double)var2, (double)var1, 0.0D);
        var14.addVertex((double)var0, (double)var1, 0.0D);
        var14.setColorRGBA_F(var11, var12, var13, var10);
        var14.addVertex((double)var0, (double)var3, 0.0D);
        var14.addVertex((double)var2, (double)var3, 0.0D);
        var14.draw();
        GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    public static void drawHorizontalLine(int var0, int var1, int var2, int var3)
    {
        if (var1 < var0)
        {
            int var4 = var0;
            var0 = var1;
            var1 = var4;
        }

        Gui.drawRect(var0, var2, var1 + 1, var2 + 1, var3);
    }

    public static void drawVerticalLine(int var0, int var1, int var2, int var3)
    {
        if (var2 < var1)
        {
            int var4 = var1;
            var1 = var2;
            var2 = var4;
        }

        Gui.drawRect(var0, var1 + 1, var0 + 1, var2, var3);
    }
}
