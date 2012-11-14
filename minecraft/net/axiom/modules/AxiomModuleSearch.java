package net.axiom.modules;

import java.util.ArrayList;
import java.util.Iterator;
import net.axiom.AxiomHelper;
import net.axiom.AxiomWrapper;
import net.axiom.util.ColorUtil;
import net.axiom.util.GuiUtil;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.RenderManager;
import org.lwjgl.opengl.GL11;

public class AxiomModuleSearch extends AxiomModule
{
    private int defaultColor = 16777215;
    private int radius = -1;
    public ArrayList blockList = new ArrayList();
    public ArrayList searchList = new ArrayList();

    public AxiomModuleSearch()
    {
        super("Search", 16777215, true);
        this.enabled = true;
    }

    public void onRenderBlock(Block var1, int var2, int var3, int var4)
    {
        if (this.shouldSearch(var1.blockID, var2, var3, var4))
        {
            this.addRender(var2, var3, var4);
        }
    }

    public void blockSkin(double var1, double var3, double var5, int[] var7)
    {
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glLineWidth(1.0F);
        ColorUtil var8 = new ColorUtil(this.selectColor(var7[0], var7[1], var7[2]));
        GL11.glColor4f(var8.redf, var8.greenf, var8.bluef, 0.2F);
        GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDepthMask(false);
        int var9 = AxiomWrapper.getWorld().getBlockId(var7[0], var7[1], var7[2]);

        if (var9 >= 1)
        {
            Block.blocksList[var9].setBlockBoundsBasedOnState(AxiomWrapper.getWorld(), var7[0], var7[1], var7[2]);
            AxisAlignedBB var10 = AxisAlignedBB.getBoundingBox(var1 + Block.blocksList[var9].minX, var3 + Block.blocksList[var9].minY, var5 + Block.blocksList[var9].minZ, var1 + Block.blocksList[var9].maxX, var3 + Block.blocksList[var9].maxY, var5 + Block.blocksList[var9].maxZ);
            GuiUtil.drawBoundingBoxQuads(var10);
            GL11.glColor4f(var8.redf, var8.greenf, var8.bluef, 1.0F);
            GuiUtil.drawCrossedOutlinedBoundingBox(var10);
            GL11.glDepthMask(true);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glDisable(GL11.GL_LINE_SMOOTH);
        }
    }

    public void onGlobalRender(float var1)
    {
        this.checkSearch();
        this.renderSearch();
    }

    public void renderSearch()
    {
        AxiomWrapper.mcObj.entityRenderer.disableLightmap(0.0D);
        GL11.glPushMatrix();
        Iterator var1 = this.blockList.iterator();

        while (var1.hasNext())
        {
            int[] var2 = (int[])var1.next();
            double var3 = RenderManager.renderPosX - (double)var2[0];
            double var5 = RenderManager.renderPosY - (double)var2[1];
            double var7 = RenderManager.renderPosZ - (double)var2[2];
            this.blockSkin(-var3, -var5, -var7, var2);
        }

        GL11.glPopMatrix();
        AxiomWrapper.mcObj.entityRenderer.enableLightmap(0.0D);
    }

    public void checkSearch()
    {
        EntityClientPlayerMP var1 = AxiomWrapper.mcObj.thePlayer;

        for (int var2 = 0; var2 < this.blockList.size(); ++var2)
        {
            int[] var3 = (int[])this.blockList.get(var2);
            int var4 = AxiomWrapper.mcObj.theWorld.getBlockId(var3[0], var3[1], var3[2]);

            if (!this.shouldSearch(var4, var3[0], var3[1], var3[2]))
            {
                this.blockList.remove(var2);
            }
        }
    }

    public void addRender(int var1, int var2, int var3)
    {
        int[] var4 = new int[] {var1, var2, var3};

        if (!this.willRender(var1, var2, var3))
        {
            this.blockList.add(var4);
        }
    }

    public boolean willRender(int var1, int var2, int var3)
    {
        int[] var4 = new int[] {var1, var2, var3};
        Iterator var5 = this.blockList.iterator();
        boolean var7;
        boolean var8;
        boolean var9;

        do
        {
            if (!var5.hasNext())
            {
                return false;
            }

            int[] var6 = (int[])var5.next();
            var7 = var6[0] == var4[0];
            var8 = var6[1] == var4[1];
            var9 = var6[2] == var4[2];
        }
        while (!var7 || !var8 || !var9);

        return true;
    }

    public boolean addBlock(int var1)
    {
        if (!this.shouldSearch(var1))
        {
            this.searchList.add(Integer.valueOf(var1));
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean delBlock(int var1)
    {
        if (this.shouldSearch(var1))
        {
            for (int var2 = 0; var2 < this.searchList.size(); ++var2)
            {
                int var3 = ((Integer)this.searchList.get(var2)).intValue();

                if (var3 == var1)
                {
                    this.searchList.remove(var2);
                    return true;
                }
            }
        }

        return false;
    }

    public boolean shouldSearch(int var1, int var2, int var3, int var4)
    {
        Iterator var5 = this.searchList.iterator();
        int var6;
        boolean var7;

        do
        {
            if (!var5.hasNext())
            {
                return false;
            }

            var6 = ((Integer)var5.next()).intValue();
            var7 = this.radius != -1 ? AxiomWrapper.mcObj.thePlayer.getDistance((double)var2, (double)var3, (double)var4) <= (double)this.radius : true;
        }
        while (var6 != var1 || !var7);

        return true;
    }

    public boolean shouldSearch(int var1)
    {
        Iterator var2 = this.searchList.iterator();
        int var3;

        do
        {
            if (!var2.hasNext())
            {
                return false;
            }

            var3 = ((Integer)var2.next()).intValue();
        }
        while (var3 != var1);

        return true;
    }

    public int selectColor(int var1, int var2, int var3)
    {
        int var4 = AxiomWrapper.getWorld().getBlockId(var1, var2, var3);

        switch (var4)
        {
            case 17:
            case 18:
                return 65380;

            case 46:
            case 81:
                return 16729088;

            case 54:
            case 130:
                return 11975244;

            case 56:
            case 57:
                return 52479;

            case 59:
            case 60:
            case 103:
            case 105:
                return 14548736;

            case 61:
            case 62:
                return 11491279;

            default:
                return this.defaultColor;
        }
    }

    public boolean onCommand(String var1, String[] var2, String var3)
    {
        int var4;

        if (!var1.equalsIgnoreCase("search"))
        {
            if (var1.equalsIgnoreCase("sr"))
            {
                if (var2.length < 1)
                {
                    return true;
                }
                else
                {
                    try
                    {
                        var4 = Integer.parseInt(var2[0]);
                        this.radius = var4;
                        AxiomHelper.chatMsg("Search radius updated.");
                        return true;
                    }
                    catch (Exception var8)
                    {
                        AxiomHelper.chatMsg("Numbers only.");
                        return true;
                    }
                }
            }
            else
            {
                return false;
            }
        }
        else if (var3.trim().equalsIgnoreCase(".search"))
        {
            this.searchList.clear();
            this.blockList.clear();
            AxiomHelper.chatMsg("Search cleared.");
            return true;
        }
        else
        {
            var4 = 0;

            for (int var5 = 0; var5 < var2.length; ++var5)
            {
                try
                {
                    int var6 = Integer.parseInt(var2[var5]);
                    this.addBlock(var6);
                    ++var4;
                }
                catch (NumberFormatException var9)
                {
                    int var7 = AxiomHelper.blockNameToID(var2[var5]);

                    if (var7 != -1)
                    {
                        this.addBlock(var7);
                        ++var4;
                    }
                }
                catch (Exception var10)
                {
                    ;
                }
            }

            AxiomHelper.renderBlocks();
            AxiomHelper.chatMsg("Searched " + var4 + " block type(s).");
            return true;
        }
    }
}
