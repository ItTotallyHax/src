package net.axiom.modules;

import java.util.ArrayList;
import java.util.Iterator;
import net.axiom.AxiomHelper;
import net.axiom.AxiomWrapper;
import net.axiom.util.ColorUtil;
import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.RenderManager;
import org.lwjgl.opengl.GL11;

public class AxiomModuleCrumb extends AxiomModule
{
    public ArrayList crumbs = new ArrayList();
    public double lastX = 0.0D;
    public double lastY = 0.0D;
    public double lastZ = 0.0D;
    private boolean record = true;

    public AxiomModuleCrumb()
    {
        super("Breadcrumb", 10556749, "B", false);
    }

    public void onEnable()
    {
        EntityClientPlayerMP var1 = AxiomWrapper.mcObj.thePlayer;
        this.lastX = var1.posX;
        this.lastY = var1.posY;
        this.lastZ = var1.posZ;
    }

    public void onPlayerUpdate()
    {
        EntityClientPlayerMP var1 = AxiomWrapper.mcObj.thePlayer;
        boolean var2 = var1.posX != this.lastX;
        boolean var3 = var1.posY - (double)var1.getEyeHeight() != this.lastY - (double)var1.getEyeHeight();
        boolean var4 = var1.posZ != this.lastZ;

        if ((var2 || var3 || var4) && this.record)
        {
            double[] var5 = new double[] {this.lastX, this.lastY - (double)var1.getEyeHeight(), this.lastZ, var1.posX, var1.posY - (double)var1.getEyeHeight(), var1.posZ};
            this.crumbs.add(var5);
        }

        this.lastX = var1.posX;
        this.lastY = var1.posY;
        this.lastZ = var1.posZ;
    }

    public void onGlobalRender(float var1)
    {
        AxiomWrapper.mcObj.entityRenderer.disableLightmap(0.0D);
        GL11.glPushMatrix();
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glLineWidth(1.5F);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDepthMask(false);
        ColorUtil var2 = new ColorUtil(this.modColor);
        GL11.glColor3f(var2.redf, var2.greenf, var2.bluef);
        Iterator var3 = this.crumbs.iterator();

        while (var3.hasNext())
        {
            double[] var4 = (double[])var3.next();
            double var5 = RenderManager.renderPosX - var4[0];
            double var7 = RenderManager.renderPosY - var4[1];
            double var9 = RenderManager.renderPosZ - var4[2];
            double var11 = RenderManager.renderPosX - var4[3];
            double var13 = RenderManager.renderPosY - var4[4];
            double var15 = RenderManager.renderPosZ - var4[5];
            GL11.glBegin(GL11.GL_LINES);
            GL11.glVertex3d(-var5, -var7, -var9);
            GL11.glVertex3d(-var11, -var13, -var15);
            GL11.glEnd();
        }

        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glPopMatrix();
        AxiomWrapper.mcObj.entityRenderer.enableLightmap(0.0D);
    }

    public boolean onCommand(String var1, String[] var2, String var3)
    {
        if (var1.equalsIgnoreCase("bcc"))
        {
            this.crumbs.clear();
            AxiomHelper.chatMsg("Crumbs cleared.");
            return true;
        }
        else if (var1.equalsIgnoreCase("bcr"))
        {
            this.record = !this.record;
            String var4 = this.record ? "Started recording breadcrumbs." : "Stopped recording breadcrumbs.";
            AxiomHelper.chatMsg(var4);
            return true;
        }
        else
        {
            return false;
        }
    }
}
