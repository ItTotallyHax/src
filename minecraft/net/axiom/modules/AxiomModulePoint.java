package net.axiom.modules;

import java.util.ArrayList;
import java.util.Iterator;
import net.axiom.AxiomHelper;
import net.axiom.AxiomWrapper;
import net.axiom.modules.addons.AxiomWaypoint;
import net.axiom.util.GuiUtil;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.MathHelper;
import net.minecraft.src.RenderManager;
import org.lwjgl.opengl.GL11;

public class AxiomModulePoint extends AxiomModule
{
    public ArrayList points = new ArrayList();
    AxiomModuleTracers Tracers;

    public AxiomModulePoint()
    {
        super("Waypoints", 16777215, true);
        this.enabled = true;
    }

    public void renderWaypoints()
    {
        this.drawPoints();
        this.drawNames();
        this.checkPoints();
    }

    public void drawNames()
    {
        AxiomWrapper.mcObj.entityRenderer.disableLightmap(0.0D);
        GL11.glPushMatrix();
        Iterator var1 = this.points.iterator();

        while (var1.hasNext())
        {
            AxiomWaypoint var2 = (AxiomWaypoint)var1.next();
            double var3 = (double)var2.posX;
            double var5 = (double)var2.posY;
            double var7 = (double)var2.posZ;
            double var9 = RenderManager.renderPosX;
            double var11 = RenderManager.renderPosY;
            double var13 = RenderManager.renderPosZ;
            double var15 = var9 - var3;
            double var17 = var11 - var5;
            double var19 = var13 - var7;
            var15 -= 0.5D;
            var19 -= 0.5D;
            GuiUtil.renderLabel(-var15, -var17, -var19, var2.pointName, 16711850);
        }

        GL11.glPopMatrix();
        AxiomWrapper.mcObj.entityRenderer.enableLightmap(0.0D);
    }

    public void drawPoints()
    {
        try
        {
            AxiomWrapper.mcObj.entityRenderer.disableLightmap(0.0D);
            GL11.glPushMatrix();
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glLineWidth(1.0F);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_LINE_SMOOTH);
            GL11.glDepthMask(false);
            Iterator var1 = this.points.iterator();

            while (var1.hasNext())
            {
                AxiomWaypoint var2 = (AxiomWaypoint)var1.next();
                double var3 = (double)var2.posX;
                double var5 = (double)var2.posY;
                double var7 = (double)var2.posZ;
                double var9 = RenderManager.renderPosX;
                double var11 = RenderManager.renderPosY;
                double var13 = RenderManager.renderPosZ;
                double var15 = var9 - var3;
                double var17 = var11 - var5;
                double var19 = var13 - var7;
                GL11.glColor4f(1.0F, 0.0F, 0.6F, 1.0F);
                GL11.glBegin(GL11.GL_LINES);
                GL11.glVertex3d(0.0D, 0.0D, 0.0D);
                GL11.glVertex3d(-var15 + 0.5D, -var17, -var19 + 0.5D);
                GL11.glEnd();
                GuiUtil.drawOutlinedBoundingBox(AxisAlignedBB.getBoundingBox(-var15, -var17, -var19, -var15 + 1.0D, -var17 + 1.0D, -var19 + 1.0D));
                GL11.glColor4f(1.0F, 0.0F, 0.6F, 0.2F);
                GuiUtil.drawBoundingBoxQuads(AxisAlignedBB.getBoundingBox(-var15, -var17, -var19, -var15 + 1.0D, -var17 + 1.0D, -var19 + 1.0D));
            }

            GL11.glPopMatrix();
        }
        catch (Exception var21)
        {
            var21.printStackTrace();
        }

        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        AxiomWrapper.mcObj.entityRenderer.enableLightmap(0.0D);
    }

    public void addWaypoint(int var1, int var2, int var3, String var4)
    {
        AxiomWaypoint var5 = new AxiomWaypoint(var1, var2, var3, var4);

        if (!this.points.contains(var5))
        {
            this.points.add(var5);
            AxiomHelper.chatMsg("Added waypoint.");
        }
        else
        {
            AxiomHelper.chatMsg("Waypoint already exists.");
        }
    }

    public boolean onCommand(String var1, String[] var2, String var3)
    {
        if (!var1.equalsIgnoreCase("point") && !var1.equalsIgnoreCase("p"))
        {
            if (var1.equalsIgnoreCase("phere"))
            {
                if (var2.length < 1)
                {
                    return true;
                }
                else
                {
                    EntityClientPlayerMP var6 = AxiomWrapper.mcObj.thePlayer;
                    String var5 = var2[0];

                    if (var5.equalsIgnoreCase(".phere"))
                    {
                        var5 = "Waypoint " + this.points.size();
                    }

                    this.addWaypoint((int)var6.posX, (int)var6.posY, (int)var6.posZ, var5);
                    return true;
                }
            }
            else if (var1.equalsIgnoreCase("pclear"))
            {
                this.points.clear();
                AxiomHelper.chatMsg("Waypoints cleared.");
                return true;
            }
            else
            {
                return false;
            }
        }
        else if (var2.length < 3)
        {
            return true;
        }
        else
        {
            String var4;

            if (var2.length < 4)
            {
                var4 = "Waypoint " + this.points.size();
            }
            else
            {
                var4 = var2[3];
            }

            this.addWaypoint(Integer.parseInt(var2[0]), Integer.parseInt(var2[1]), Integer.parseInt(var2[2]), var4);
            return true;
        }
    }

    public void checkPoints()
    {
        AxiomModule var1 = AxiomHelper.getModByName("Freecam");

        if (!var1.enabled)
        {
            int var2 = MathHelper.floor_double(AxiomWrapper.mcObj.thePlayer.posX);
            int var3 = MathHelper.floor_double(AxiomWrapper.mcObj.thePlayer.posY);
            int var4 = MathHelper.floor_double(AxiomWrapper.mcObj.thePlayer.posZ);

            for (int var5 = 0; var5 < this.points.size(); ++var5)
            {
                AxiomWaypoint var6 = (AxiomWaypoint)this.points.get(var5);

                if (this.withinRange(var2, var3, var4, var6.posX, var6.posY, var6.posZ))
                {
                    AxiomHelper.chatMsg("\'" + var6.pointName + "\' reached.");
                    this.points.remove(var5);
                }
            }
        }
    }

    private boolean withinRange(int var1, int var2, int var3, int var4, int var5, int var6)
    {
        double var7 = 2.0D;
        boolean var9 = false;
        boolean var10 = false;
        boolean var11 = false;

        for (int var12 = 0; (double)var12 <= var7; ++var12)
        {
            int var13 = var4 + var12;
            int var14 = var4 - var12;
            int var15 = var5 + var12;
            int var16 = var5 - var12;
            int var17 = var6 + var12;
            int var18 = var6 - var12;

            if (var1 == var13 || var1 == var14)
            {
                var9 = true;
            }

            if (var2 == var15 || var2 == var16)
            {
                var10 = true;
            }

            if (var3 == var17 || var3 == var18)
            {
                var11 = true;
            }

            if (var9 && var10 && var11)
            {
                return true;
            }
        }

        return false;
    }

    public void drawPointBox(AxisAlignedBB var1)
    {
        AxiomWrapper.mcObj.entityRenderer.disableLightmap(0.0D);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glLineWidth(1.5F);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDepthMask(false);
        GuiUtil.drawOutlinedBoundingBox(var1);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        AxiomWrapper.mcObj.entityRenderer.enableLightmap(0.0D);
    }
}
