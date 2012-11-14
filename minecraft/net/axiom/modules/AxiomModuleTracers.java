package net.axiom.modules;

import net.axiom.AxiomHelper;
import net.axiom.AxiomWrapper;
import net.axiom.util.ColorUtil;
import net.axiom.util.GuiUtil;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityEnderman;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntitySkeleton;
import net.minecraft.src.EntityZombie;
import net.minecraft.src.MathHelper;
import net.minecraft.src.RenderManager;
import org.lwjgl.opengl.GL11;

public class AxiomModuleTracers extends AxiomModule
{
    public boolean players = true;
    public boolean mobs = true;
    public boolean protect = true;
    public boolean points = true;
    public float partialTickTime = 0.0F;

    public AxiomModuleTracers()
    {
        super("Tracers", 15790320, "BACK", true);
    }

    public void onEnable()
    {
        AxiomWrapper.mcObj.gameSettings.viewBobbing = false;
    }

    public void onDisable()
    {
        AxiomWrapper.mcObj.gameSettings.viewBobbing = true;
    }

    public boolean shouldTrace(EntityLiving var1)
    {
        if (var1.entityId == AxiomWrapper.mcObj.thePlayer.entityId)
        {
            return false;
        }
        else if (var1 instanceof EntityPlayer)
        {
            EntityPlayer var2 = (EntityPlayer)var1;
            AxiomModuleNames var3 = (AxiomModuleNames)AxiomHelper.getModByName("NameProtect");
            return var3.getAliasByName(var2.username) != null ? this.protect : this.players;
        }
        else
        {
            return this.mobs;
        }
    }

    public void setColorByState(EntityLiving var1, float var2)
    {
        AxiomModuleNames var3 = (AxiomModuleNames)AxiomHelper.getModByName("nameprotect");

        if (var1.hurtTime > 0)
        {
            GL11.glColor4f(1.0F, 0.0F, 0.0F, var2);
        }
        else if (var1 instanceof EntityPlayer)
        {
            EntityPlayer var4 = (EntityPlayer)var1;
            ColorUtil var5;

            if (var3.getAliasByName(var4.username) != null)
            {
                var5 = new ColorUtil(52479);
                GL11.glColor4f(var5.redf, var5.greenf, var5.bluef, var2);
            }
            else
            {
                var5 = new ColorUtil(6065198);
                GL11.glColor4f(var5.redf, var5.greenf, var5.bluef, var2);
            }
        }
        else
        {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, var2);
        }
    }

    public void setColorByDistance(EntityLiving var1, float var2)
    {
        AxiomModuleNames var3 = (AxiomModuleNames)AxiomHelper.getModByName("nameprotect");

        if (var1 instanceof EntityPlayer)
        {
            EntityPlayer var4 = (EntityPlayer)var1;

            if (var3.getAliasByName(var4.username) != null)
            {
                ColorUtil var5 = new ColorUtil(52479);
                GL11.glColor4f(var5.redf, var5.greenf, var5.bluef, var2);
                return;
            }
        }

        double var7 = (double)AxiomWrapper.mcObj.thePlayer.getDistanceToEntity(var1);
        ColorUtil var6;

        if (var7 <= 30.0D)
        {
            var6 = new ColorUtil(16711680);
            GL11.glColor4f(var6.redf, var6.greenf, var6.bluef, var2);
        }
        else if (var7 <= 70.0D)
        {
            var6 = new ColorUtil(255);
            GL11.glColor4f(var6.redf, var6.greenf, var6.bluef, var2);
        }
        else
        {
            var6 = new ColorUtil(6065198);
            GL11.glColor4f(var6.redf, var6.greenf, var6.bluef, var2);
        }
    }

    public void drawESP(double var1, double var3, double var5, EntityLiving var7)
    {
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glLineWidth(2.0F);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDepthMask(false);
        boolean var8 = true;

        if (!(var7 instanceof EntityPlayer))
        {
            var8 = false;

            if (AxiomWrapper.mcObj.thePlayer.getDistanceToEntity(var7) <= 30.0F)
            {
                var8 = true;
            }
        }

        if (var8)
        {
            GL11.glPushMatrix();
            GL11.glTranslated(var1, var3, var5);
            GL11.glRotatef(var7.rotationYaw, 0.0F, (float)var3, 0.0F);
            GL11.glTranslated(-var1, -var3, -var5);
            GL11.glTranslated(0.0D, var7.isSneaking() ? 0.2D : 0.1D, 0.0D);
            AxisAlignedBB var9 = AxisAlignedBB.getBoundingBox(var1 - (double)var7.width, var3, var5 - (double)var7.width, var1 + (double)var7.width, var3 + (double)var7.height, var5 + (double)var7.width);

            if (var7 instanceof EntityPlayer || var7 instanceof EntityZombie || var7 instanceof EntitySkeleton || var7 instanceof EntityEnderman)
            {
                var9 = var9.contract(0.25D, 0.0D, 0.25D);
            }

            this.setColorByState(var7, 1.0F);
            GuiUtil.drawCrossedOutlinedBoundingBox(var9);
            GL11.glPopMatrix();
        }

        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_LIGHTING);
    }

    public void renderTracer(Entity var1)
    {
        GL11.glPushMatrix();
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDepthMask(false);
        EntityLiving var2 = (EntityLiving)var1;
        double var3 = var2.posX;
        double var5 = var2.posY;
        double var7 = var2.posZ;
        double var9 = RenderManager.instance.viewerPosX;
        double var11 = RenderManager.instance.viewerPosY;
        double var13 = RenderManager.instance.viewerPosZ;
        double var15 = var9 - var3;
        double var17 = var11 - var5;
        double var19 = var13 - var7;
        boolean var21 = true;

        if (!(var2 instanceof EntityPlayer))
        {
            var21 = false;

            if (AxiomWrapper.mcObj.thePlayer.getDistanceToEntity(var2) <= 30.0F)
            {
                var21 = true;
            }
        }

        if (var21)
        {
            this.setColorByDistance(var2, 0.6F);
            GL11.glLineWidth(3.0F);
            GL11.glBegin(GL11.GL_LINES);
            GL11.glVertex3d(-var15 * 10.0D, -var17 * 10.0D, -var19 * 10.0D);
            GL11.glVertex2d(0.0D, 0.0D);
            GL11.glEnd();
        }

        GL11.glScalef(0.1F, 0.1F, 0.1F);
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glPopMatrix();
    }

    public void drawTracers()
    {
        AxiomWrapper.mcObj.entityRenderer.disableLightmap(0.0D);

        for (int var1 = 0; var1 < AxiomWrapper.mcObj.theWorld.loadedEntityList.size(); ++var1)
        {
            Entity var2 = (Entity)AxiomWrapper.mcObj.theWorld.loadedEntityList.get(var1);

            if (var2 instanceof EntityLiving && var2 != AxiomWrapper.mcObj.thePlayer && this.shouldTrace((EntityLiving)var2))
            {
                EntityLiving var3 = (EntityLiving)var2;
                double var4 = var3.posX;
                double var6 = var3.posY;
                double var8 = var3.posZ;
                double var10 = RenderManager.instance.viewerPosX;
                double var12 = RenderManager.instance.viewerPosY;
                double var14 = RenderManager.instance.viewerPosZ;
                double var16 = var10 - var4;
                double var18 = var12 - var6;
                double var20 = var14 - var8;
                this.renderTracer(var3);
                this.drawESP(-var16, -var18, -var20, var3);
            }
        }

        AxiomWrapper.mcObj.entityRenderer.enableLightmap(0.0D);
    }

    public void onGlobalRender(float var1)
    {
        this.partialTickTime = var1;
        this.drawTracers();

        if (this.points)
        {
            AxiomModulePoint var2 = (AxiomModulePoint)AxiomHelper.getModByName("Waypoints");
            var2.renderWaypoints();
        }
    }

    public void onBobbingFinish(float var1)
    {
        EntityPlayer var2 = (EntityPlayer)AxiomWrapper.mcObj.renderViewEntity;
        float var3 = var2.distanceWalkedModified - var2.prevDistanceWalkedModified;
        float var4 = -(var2.distanceWalkedModified + var3 * var1);
        float var5 = var2.prevCameraYaw + (var2.cameraYaw - var2.prevCameraYaw) * var1;
        float var6 = var2.prevCameraPitch + (var2.cameraPitch - var2.prevCameraPitch) * var1;
        GL11.glTranslatef(MathHelper.sin(var4 * (float)Math.PI) * var5 * 0.5F, -Math.abs(MathHelper.cos(var4 * (float)Math.PI) * var5), 0.0F);
        GL11.glRotatef(MathHelper.sin(var4 * (float)Math.PI) * var5 * 3.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(Math.abs(MathHelper.cos(var4 * (float)Math.PI - 0.2F) * var5) * 5.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(var6, 1.0F, 0.0F, 0.0F);
    }
}
