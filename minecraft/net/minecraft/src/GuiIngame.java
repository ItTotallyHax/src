package net.minecraft.src;

import java.awt.Color;
import java.util.List;
import java.util.Random;
import net.axiom.AxiomHook;
import net.axiom.AxiomSetup;
import net.axiom.AxiomWrapper;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class GuiIngame extends Gui
{
    private static final RenderItem itemRenderer = new RenderItem();
    private final Random rand = new Random();
    private final Minecraft mc;

    /** ChatGUI instance that retains all previous chat data */
    public GuiNewChat persistantChatGUI;
    private int updateCounter = 0;

    /** The string specifying which record music is playing */
    private String recordPlaying = "";

    /** How many ticks the record playing message will be displayed */
    private int recordPlayingUpFor = 0;
    private boolean recordIsPlaying = false;

    /** Previous frame vignette brightness (slowly changes by 1% each frame) */
    public float prevVignetteBrightness = 1.0F;

    public GuiIngame(Minecraft par1Minecraft)
    {
        this.mc = par1Minecraft;
        this.persistantChatGUI = new GuiNewChat(par1Minecraft);
    }

    /**
     * Render the ingame overlay with quick icon bar, ...
     */
    public void renderGameOverlay(float par1, boolean par2, int par3, int par4)
    {
        ScaledResolution var5 = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
        AxiomWrapper.scaledGui = var5;
        int var6 = var5.getScaledWidth();
        int var7 = var5.getScaledHeight();
        FontRenderer var8 = this.mc.fontRenderer;
        this.mc.entityRenderer.setupOverlayRendering();
        GL11.glEnable(GL11.GL_BLEND);

        if (Minecraft.isFancyGraphicsEnabled())
        {
            this.renderVignette(this.mc.thePlayer.getBrightness(par1), var6, var7);
        }
        else
        {
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        }

        ItemStack var9 = this.mc.thePlayer.inventory.armorItemInSlot(3);

        if (this.mc.gameSettings.thirdPersonView == 0 && var9 != null && var9.itemID == Block.pumpkin.blockID)
        {
            this.renderPumpkinBlur(var6, var7);
        }

        if (!this.mc.thePlayer.isPotionActive(Potion.confusion))
        {
            float var10 = this.mc.thePlayer.prevTimeInPortal + (this.mc.thePlayer.timeInPortal - this.mc.thePlayer.prevTimeInPortal) * par1;

            if (var10 > 0.0F)
            {
                this.renderPortalOverlay(var10, var6, var7);
            }
        }

        int var11;
        int var12;
        int var13;
        int var14;
        int var15;
        int var17;
        int var16;
        int var19;
        int var18;
        byte var30;
        boolean var33;

        if (!this.mc.playerController.func_78747_a())
        {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/gui/gui.png"));
            InventoryPlayer var20 = this.mc.thePlayer.inventory;
            this.zLevel = -90.0F;
            this.drawTexturedModalRect(var6 / 2 - 91, var7 - 22, 0, 0, 182, 22);
            this.drawTexturedModalRect(var6 / 2 - 91 - 1 + var20.currentItem * 20, var7 - 22 - 1, 0, 22, 24, 22);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/gui/icons.png"));
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_COLOR, GL11.GL_ONE_MINUS_SRC_COLOR);
            this.drawTexturedModalRect(var6 / 2 - 7, var7 / 2 - 7, 0, 0, 16, 16);
            GL11.glDisable(GL11.GL_BLEND);
            var33 = this.mc.thePlayer.hurtResistantTime / 3 % 2 == 1;

            if (this.mc.thePlayer.hurtResistantTime < 10)
            {
                var33 = false;
            }

            var11 = this.mc.thePlayer.getHealth();
            var12 = this.mc.thePlayer.prevHealth;
            this.rand.setSeed((long)(this.updateCounter * 312871));
            boolean var21 = false;
            FoodStats var22 = this.mc.thePlayer.getFoodStats();
            var14 = var22.getFoodLevel();
            var13 = var22.getPrevFoodLevel();
            this.mc.mcProfiler.startSection("bossHealth");
            this.renderBossHealth();
            this.mc.mcProfiler.endSection();
            int var23;

            if (this.mc.playerController.shouldDrawHUD())
            {
                var23 = var6 / 2 - 91;
                var15 = var6 / 2 + 91;
                this.mc.mcProfiler.startSection("expBar");
                var16 = this.mc.thePlayer.xpBarCap();

                if (var16 > 0)
                {
                    short var24 = 182;
                    var18 = (int)(this.mc.thePlayer.experience * (float)(var24 + 1));
                    var17 = var7 - 32 + 3;
                    this.drawTexturedModalRect(var23, var17, 0, 64, var24, 5);

                    if (var18 > 0)
                    {
                        this.drawTexturedModalRect(var23, var17, 0, 69, var18, 5);
                    }
                }

                var19 = var7 - 39;
                var18 = var19 - 10;
                var17 = this.mc.thePlayer.getTotalArmorValue();
                int var41 = -1;

                if (this.mc.thePlayer.isPotionActive(Potion.regeneration))
                {
                    var41 = this.updateCounter % 25;
                }

                this.mc.mcProfiler.endStartSection("healthArmor");
                int var25;
                int var27;
                int var26;
                int var28;

                for (var25 = 0; var25 < 10; ++var25)
                {
                    if (var17 > 0)
                    {
                        var26 = var23 + var25 * 8;

                        if (var25 * 2 + 1 < var17)
                        {
                            this.drawTexturedModalRect(var26, var18, 34, 9, 9, 9);
                        }

                        if (var25 * 2 + 1 == var17)
                        {
                            this.drawTexturedModalRect(var26, var18, 25, 9, 9, 9);
                        }

                        if (var25 * 2 + 1 > var17)
                        {
                            this.drawTexturedModalRect(var26, var18, 16, 9, 9, 9);
                        }
                    }

                    var26 = 16;

                    if (this.mc.thePlayer.isPotionActive(Potion.poison))
                    {
                        var26 += 36;
                    }

                    byte var29 = 0;

                    if (var33)
                    {
                        var29 = 1;
                    }

                    var28 = var23 + var25 * 8;
                    var27 = var19;

                    if (var11 <= 4)
                    {
                        var27 = var19 + this.rand.nextInt(2);
                    }

                    if (var25 == var41)
                    {
                        var27 -= 2;
                    }

                    var30 = 0;

                    if (this.mc.theWorld.getWorldInfo().isHardcoreModeEnabled())
                    {
                        var30 = 5;
                    }

                    this.drawTexturedModalRect(var28, var27, 16 + var29 * 9, 9 * var30, 9, 9);

                    if (var33)
                    {
                        if (var25 * 2 + 1 < var12)
                        {
                            this.drawTexturedModalRect(var28, var27, var26 + 54, 9 * var30, 9, 9);
                        }

                        if (var25 * 2 + 1 == var12)
                        {
                            this.drawTexturedModalRect(var28, var27, var26 + 63, 9 * var30, 9, 9);
                        }
                    }

                    if (var25 * 2 + 1 < var11)
                    {
                        this.drawTexturedModalRect(var28, var27, var26 + 36, 9 * var30, 9, 9);
                    }

                    if (var25 * 2 + 1 == var11)
                    {
                        this.drawTexturedModalRect(var28, var27, var26 + 45, 9 * var30, 9, 9);
                    }
                }

                this.mc.mcProfiler.endStartSection("food");
                int var47;

                for (var25 = 0; var25 < 10; ++var25)
                {
                    var26 = var19;
                    var47 = 16;
                    var30 = 0;

                    if (this.mc.thePlayer.isPotionActive(Potion.hunger))
                    {
                        var47 += 36;
                        var30 = 13;
                    }

                    if (this.mc.thePlayer.getFoodStats().getSaturationLevel() <= 0.0F && this.updateCounter % (var14 * 3 + 1) == 0)
                    {
                        var26 = var19 + (this.rand.nextInt(3) - 1);
                    }

                    if (var21)
                    {
                        var30 = 1;
                    }

                    var27 = var15 - var25 * 8 - 9;
                    this.drawTexturedModalRect(var27, var26, 16 + var30 * 9, 27, 9, 9);

                    if (var21)
                    {
                        if (var25 * 2 + 1 < var13)
                        {
                            this.drawTexturedModalRect(var27, var26, var47 + 54, 27, 9, 9);
                        }

                        if (var25 * 2 + 1 == var13)
                        {
                            this.drawTexturedModalRect(var27, var26, var47 + 63, 27, 9, 9);
                        }
                    }

                    if (var25 * 2 + 1 < var14)
                    {
                        this.drawTexturedModalRect(var27, var26, var47 + 36, 27, 9, 9);
                    }

                    if (var25 * 2 + 1 == var14)
                    {
                        this.drawTexturedModalRect(var27, var26, var47 + 45, 27, 9, 9);
                    }
                }

                this.mc.mcProfiler.endStartSection("air");

                if (this.mc.thePlayer.isInsideOfMaterial(Material.water))
                {
                    var25 = this.mc.thePlayer.getAir();
                    var26 = MathHelper.ceiling_double_int((double)(var25 - 2) * 10.0D / 300.0D);
                    var47 = MathHelper.ceiling_double_int((double)var25 * 10.0D / 300.0D) - var26;

                    for (var28 = 0; var28 < var26 + var47; ++var28)
                    {
                        if (var28 < var26)
                        {
                            this.drawTexturedModalRect(var15 - var28 * 8 - 9, var18, 16, 18, 9, 9);
                        }
                        else
                        {
                            this.drawTexturedModalRect(var15 - var28 * 8 - 9, var18, 25, 18, 9, 9);
                        }
                    }
                }

                this.mc.mcProfiler.endSection();
            }

            GL11.glDisable(GL11.GL_BLEND);
            this.mc.mcProfiler.startSection("actionBar");
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            RenderHelper.enableGUIStandardItemLighting();

            for (var23 = 0; var23 < 9; ++var23)
            {
                var15 = var6 / 2 - 90 + var23 * 20 + 2;
                var16 = var7 - 16 - 3;
                this.renderInventorySlot(var23, var15, var16, par1);
            }

            RenderHelper.disableStandardItemLighting();
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            this.mc.mcProfiler.endSection();
        }

        float var34;
        int var36;

        if (this.mc.thePlayer.getSleepTimer() > 0)
        {
            this.mc.mcProfiler.startSection("sleep");
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            var36 = this.mc.thePlayer.getSleepTimer();
            var34 = (float)var36 / 100.0F;

            if (var34 > 1.0F)
            {
                var34 = 1.0F - (float)(var36 - 100) / 10.0F;
            }

            var11 = (int)(220.0F * var34) << 24 | 1052704;
            drawRect(0, 0, var6, var7, var11);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            this.mc.mcProfiler.endSection();
        }

        int var35;
        String var40;

        if (this.mc.playerController.func_78763_f() && this.mc.thePlayer.experienceLevel > 0)
        {
            this.mc.mcProfiler.startSection("expLevel");
            var33 = false;
            var11 = var33 ? 16777215 : 8453920;
            var40 = "" + this.mc.thePlayer.experienceLevel;
            var35 = (var6 - var8.getStringWidth(var40)) / 2;
            var36 = var7 - 31 - 4;
            var8.drawString(var40, var35 + 1, var36, 0);
            var8.drawString(var40, var35 - 1, var36, 0);
            var8.drawString(var40, var35, var36 + 1, 0);
            var8.drawString(var40, var35, var36 - 1, 0);
            var8.drawString(var40, var35, var36, var11);
            this.mc.mcProfiler.endSection();
        }

        if (this.mc.isDemo())
        {
            this.mc.mcProfiler.startSection("demo");
            var40 = "";

            if (this.mc.theWorld.getWorldTime() >= 120500L)
            {
                var40 = StatCollector.translateToLocal("demo.demoExpired");
            }
            else
            {
                var40 = String.format(StatCollector.translateToLocal("demo.remainingTime"), new Object[] {StringUtils.ticksToElapsedTime((int)(120500L - this.mc.theWorld.getWorldTime()))});
            }

            var11 = var8.getStringWidth(var40);
            var8.drawStringWithShadow(var40, var6 - var11 - 10, 5, 16777215);
            this.mc.mcProfiler.endSection();
        }

        if (this.mc.gameSettings.showDebugInfo)
        {
            this.mc.mcProfiler.startSection("debug");
            GL11.glPushMatrix();
            var8.drawStringWithShadow("Minecraft 1.3.2 (" + this.mc.debug + ")", 2, 2, 16777215);
            var8.drawStringWithShadow(this.mc.debugInfoRenders(), 2, 12, 16777215);
            var8.drawStringWithShadow(this.mc.getEntityDebug(), 2, 22, 16777215);
            var8.drawStringWithShadow(this.mc.debugInfoEntities(), 2, 32, 16777215);
            var8.drawStringWithShadow(this.mc.getWorldProviderName(), 2, 42, 16777215);
            long var38 = Runtime.getRuntime().maxMemory();
            long var42 = Runtime.getRuntime().totalMemory();
            long var45 = Runtime.getRuntime().freeMemory();
            long var49 = var42 - var45;
            String var31 = "Used memory: " + var49 * 100L / var38 + "% (" + var49 / 1024L / 1024L + "MB) of " + var38 / 1024L / 1024L + "MB";
            this.drawString(var8, var31, var6 - var8.getStringWidth(var31) - 2, 2, 14737632);
            var31 = "Allocated memory: " + var42 * 100L / var38 + "% (" + var42 / 1024L / 1024L + "MB)";
            this.drawString(var8, var31, var6 - var8.getStringWidth(var31) - 2, 12, 14737632);
            this.drawString(var8, String.format("x: %.5f", new Object[] {Double.valueOf(this.mc.thePlayer.posX)}), 2, 64, 14737632);
            this.drawString(var8, String.format("y: %.3f (feet pos, %.3f eyes pos)", new Object[] {Double.valueOf(this.mc.thePlayer.boundingBox.minY), Double.valueOf(this.mc.thePlayer.posY)}), 2, 72, 14737632);
            this.drawString(var8, String.format("z: %.5f", new Object[] {Double.valueOf(this.mc.thePlayer.posZ)}), 2, 80, 14737632);
            this.drawString(var8, "f: " + (MathHelper.floor_double((double)(this.mc.thePlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3), 2, 88, 14737632);
            var19 = MathHelper.floor_double(this.mc.thePlayer.posX);
            var18 = MathHelper.floor_double(this.mc.thePlayer.posY);
            var17 = MathHelper.floor_double(this.mc.thePlayer.posZ);

            if (this.mc.theWorld != null && this.mc.theWorld.blockExists(var19, var18, var17))
            {
                Chunk var32 = this.mc.theWorld.getChunkFromBlockCoords(var19, var17);
                this.drawString(var8, "lc: " + (var32.getTopFilledSegment() + 15) + " b: " + var32.getBiomeGenForWorldCoords(var19 & 15, var17 & 15, this.mc.theWorld.getWorldChunkManager()).biomeName + " bl: " + var32.getSavedLightValue(EnumSkyBlock.Block, var19 & 15, var18, var17 & 15) + " sl: " + var32.getSavedLightValue(EnumSkyBlock.Sky, var19 & 15, var18, var17 & 15) + " rl: " + var32.getBlockLightValue(var19 & 15, var18, var17 & 15, 0), 2, 96, 14737632);
            }

            this.drawString(var8, String.format("ws: %.3f, fs: %.3f, g: %b", new Object[] {Float.valueOf(this.mc.thePlayer.capabilities.getWalkSpeed()), Float.valueOf(this.mc.thePlayer.capabilities.getFlySpeed()), Boolean.valueOf(this.mc.thePlayer.onGround)}), 2, 104, 14737632);
            GL11.glPopMatrix();
            this.mc.mcProfiler.endSection();
        }
        else
        {
            AxiomSetup.renderIGGUI();
        }

        if (this.recordPlayingUpFor > 0)
        {
            this.mc.mcProfiler.startSection("overlayMessage");
            var34 = (float)this.recordPlayingUpFor - par1;
            var11 = (int)(var34 * 256.0F / 20.0F);

            if (var11 > 255)
            {
                var11 = 255;
            }

            if (var11 > 0)
            {
                GL11.glPushMatrix();
                GL11.glTranslatef((float)(var6 / 2), (float)(var7 - 48), 0.0F);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                var12 = 16777215;

                if (this.recordIsPlaying)
                {
                    var12 = Color.HSBtoRGB(var34 / 50.0F, 0.7F, 0.6F) & 16777215;
                }

                var8.drawString(this.recordPlaying, -var8.getStringWidth(this.recordPlaying) / 2, -4, var12 + (var11 << 24));
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glPopMatrix();
            }

            this.mc.mcProfiler.endSection();
        }

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, (float)(var7 - 48), 0.0F);
        this.mc.mcProfiler.startSection("chat");
        this.persistantChatGUI.func_73762_a(this.updateCounter);
        this.mc.mcProfiler.endSection();
        GL11.glPopMatrix();

        if (this.mc.gameSettings.keyBindPlayerList.pressed && (!this.mc.isIntegratedServerRunning() || this.mc.thePlayer.sendQueue.playerInfoList.size() > 1))
        {
            this.mc.mcProfiler.startSection("playerList");
            NetClientHandler var39 = this.mc.thePlayer.sendQueue;
            List var37 = var39.playerInfoList;
            var12 = var39.currentServerMaxPlayers;
            var35 = var12;

            for (var36 = 1; var35 > 20; var35 = (var12 + var36 - 1) / var36)
            {
                ++var36;
            }

            var14 = 300 / var36;

            if (var14 > 150)
            {
                var14 = 150;
            }

            var13 = (var6 - var36 * var14) / 2;
            byte var43 = 10;
            drawRect(var13 - 1, var43 - 1, var13 + var14 * var36, var43 + 9 * var35, Integer.MIN_VALUE);

            for (var15 = 0; var15 < var12; ++var15)
            {
                var16 = var13 + var15 % var36 * var14;
                var19 = var43 + var15 / var36 * 9;
                drawRect(var16, var19, var16 + var14 - 1, var19 + 8, 553648127);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glEnable(GL11.GL_ALPHA_TEST);

                if (var15 < var37.size())
                {
                    GuiPlayerInfo var48 = (GuiPlayerInfo)var37.get(var15);
                    String var44 = AxiomHook.tabListNameSetHook(var48.name);
                    var8.drawStringWithShadow(var44, var16, var19, 16777215);
                    this.mc.renderEngine.bindTexture(this.mc.renderEngine.getTexture("/gui/icons.png"));
                    byte var46 = 0;
                    boolean var50 = false;

                    if (var48.responseTime < 0)
                    {
                        var30 = 5;
                    }
                    else if (var48.responseTime < 150)
                    {
                        var30 = 0;
                    }
                    else if (var48.responseTime < 300)
                    {
                        var30 = 1;
                    }
                    else if (var48.responseTime < 600)
                    {
                        var30 = 2;
                    }
                    else if (var48.responseTime < 1000)
                    {
                        var30 = 3;
                    }
                    else
                    {
                        var30 = 4;
                    }

                    this.zLevel += 100.0F;
                    this.drawTexturedModalRect(var16 + var14 - 12, var19, 0 + var46 * 10, 176 + var30 * 8, 10, 8);
                    this.zLevel -= 100.0F;
                }
            }
        }

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
    }

    /**
     * Renders dragon's (boss) health on the HUD
     */
    private void renderBossHealth()
    {
        if (RenderDragon.entityDragon != null)
        {
            EntityDragon var1 = RenderDragon.entityDragon;
            RenderDragon.entityDragon = null;
            FontRenderer var2 = this.mc.fontRenderer;
            ScaledResolution var3 = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
            int var4 = var3.getScaledWidth();
            short var5 = 182;
            int var6 = var4 / 2 - var5 / 2;
            int var7 = (int)((float)var1.getDragonHealth() / (float)var1.getMaxHealth() * (float)(var5 + 1));
            byte var8 = 12;
            this.drawTexturedModalRect(var6, var8, 0, 74, var5, 5);
            this.drawTexturedModalRect(var6, var8, 0, 74, var5, 5);

            if (var7 > 0)
            {
                this.drawTexturedModalRect(var6, var8, 0, 79, var7, 5);
            }

            String var9 = "Boss health";
            var2.drawStringWithShadow(var9, var4 / 2 - var2.getStringWidth(var9) / 2, var8 - 10, 16711935);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/gui/icons.png"));
        }
    }

    private void renderPumpkinBlur(int par1, int par2)
    {
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("%blur%/misc/pumpkinblur.png"));
        Tessellator var3 = Tessellator.instance;
        var3.startDrawingQuads();
        var3.addVertexWithUV(0.0D, (double)par2, -90.0D, 0.0D, 1.0D);
        var3.addVertexWithUV((double)par1, (double)par2, -90.0D, 1.0D, 1.0D);
        var3.addVertexWithUV((double)par1, 0.0D, -90.0D, 1.0D, 0.0D);
        var3.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
        var3.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    /**
     * Renders the vignette. Args: vignetteBrightness, width, height
     */
    private void renderVignette(float par1, int par2, int par3)
    {
        par1 = 1.0F - par1;

        if (par1 < 0.0F)
        {
            par1 = 0.0F;
        }

        if (par1 > 1.0F)
        {
            par1 = 1.0F;
        }

        this.prevVignetteBrightness = (float)((double)this.prevVignetteBrightness + (double)(par1 - this.prevVignetteBrightness) * 0.01D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(GL11.GL_ZERO, GL11.GL_ONE_MINUS_SRC_COLOR);
        GL11.glColor4f(this.prevVignetteBrightness, this.prevVignetteBrightness, this.prevVignetteBrightness, 1.0F);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("%blur%/misc/vignette.png"));
        Tessellator var4 = Tessellator.instance;
        var4.startDrawingQuads();
        var4.addVertexWithUV(0.0D, (double)par3, -90.0D, 0.0D, 1.0D);
        var4.addVertexWithUV((double)par2, (double)par3, -90.0D, 1.0D, 1.0D);
        var4.addVertexWithUV((double)par2, 0.0D, -90.0D, 1.0D, 0.0D);
        var4.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
        var4.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    }

    /**
     * Renders the portal overlay. Args: portalStrength, width, height
     */
    private void renderPortalOverlay(float par1, int par2, int par3)
    {
        if (par1 < 1.0F)
        {
            par1 *= par1;
            par1 *= par1;
            par1 = par1 * 0.8F + 0.2F;
        }

        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, par1);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture("/terrain.png"));
        float var4 = (float)(Block.portal.blockIndexInTexture % 16) / 16.0F;
        float var5 = (float)(Block.portal.blockIndexInTexture / 16) / 16.0F;
        float var6 = (float)(Block.portal.blockIndexInTexture % 16 + 1) / 16.0F;
        float var7 = (float)(Block.portal.blockIndexInTexture / 16 + 1) / 16.0F;
        Tessellator var8 = Tessellator.instance;
        var8.startDrawingQuads();
        var8.addVertexWithUV(0.0D, (double)par3, -90.0D, (double)var4, (double)var7);
        var8.addVertexWithUV((double)par2, (double)par3, -90.0D, (double)var6, (double)var7);
        var8.addVertexWithUV((double)par2, 0.0D, -90.0D, (double)var6, (double)var5);
        var8.addVertexWithUV(0.0D, 0.0D, -90.0D, (double)var4, (double)var5);
        var8.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    /**
     * Renders the specified item of the inventory slot at the specified location. Args: slot, x, y, partialTick
     */
    private void renderInventorySlot(int par1, int par2, int par3, float par4)
    {
        ItemStack var5 = this.mc.thePlayer.inventory.mainInventory[par1];

        if (var5 != null)
        {
            float var6 = (float)var5.animationsToGo - par4;

            if (var6 > 0.0F)
            {
                GL11.glPushMatrix();
                float var7 = 1.0F + var6 / 5.0F;
                GL11.glTranslatef((float)(par2 + 8), (float)(par3 + 12), 0.0F);
                GL11.glScalef(1.0F / var7, (var7 + 1.0F) / 2.0F, 1.0F);
                GL11.glTranslatef((float)(-(par2 + 8)), (float)(-(par3 + 12)), 0.0F);
            }

            itemRenderer.renderItemIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, var5, par2, par3);

            if (var6 > 0.0F)
            {
                GL11.glPopMatrix();
            }

            itemRenderer.renderItemOverlayIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, var5, par2, par3);
        }
    }

    /**
     * The update tick for the ingame UI
     */
    public void updateTick()
    {
        if (this.recordPlayingUpFor > 0)
        {
            --this.recordPlayingUpFor;
        }

        ++this.updateCounter;
    }

    public void setRecordPlayingMessage(String par1Str)
    {
        this.recordPlaying = "Now playing: " + par1Str;
        this.recordPlayingUpFor = 60;
        this.recordIsPlaying = true;
    }

    /**
     * returns a pointer to the persistant Chat GUI, containing all previous chat messages and such
     */
    public GuiNewChat getChatGUI()
    {
        return this.persistantChatGUI;
    }

    public int getUpdateCounter()
    {
        return this.updateCounter;
    }
}
