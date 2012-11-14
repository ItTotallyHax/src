package net.axiom;

import java.util.Iterator;
import net.axiom.modules.AxiomModule;
import net.axiom.modules.AxiomModuleNoRender;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Gui;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet24MobSpawn;
import net.minecraft.src.Packet28EntityVelocity;
import net.minecraft.src.Packet34EntityTeleport;
import net.minecraft.src.Packet3Chat;

public class AxiomHook
{
    public static void mobSpawnHook(Packet24MobSpawn var0)
    {
        Iterator var1 = AxiomSetup.modules.iterator();

        while (var1.hasNext())
        {
            AxiomModule var2 = (AxiomModule)var1.next();

            if (var2.enabled)
            {
                var2.onMobSpawn(var0);
            }
        }
    }

    public static void guiCreationHook(Gui var0)
    {
        Iterator var1 = AxiomSetup.modules.iterator();

        while (var1.hasNext())
        {
            AxiomModule var2 = (AxiomModule)var1.next();

            if (var2.enabled)
            {
                var2.onGuiCreation(var0);
            }
        }
    }

    public static void postMotionUpdatesHook()
    {
        Iterator var0 = AxiomSetup.modules.iterator();

        while (var0.hasNext())
        {
            AxiomModule var1 = (AxiomModule)var0.next();

            if (var1.enabled)
            {
                var1.postMotionUpdates();
            }
        }
    }

    public static void preMotionUpdatesHook()
    {
        Iterator var0 = AxiomSetup.modules.iterator();

        while (var0.hasNext())
        {
            AxiomModule var1 = (AxiomModule)var0.next();

            if (var1.enabled)
            {
                var1.preMotionUpdates();
            }
        }
    }

    public static void postBobbingHook(float var0)
    {
        Iterator var1 = AxiomSetup.modules.iterator();

        while (var1.hasNext())
        {
            AxiomModule var2 = (AxiomModule)var1.next();

            if (var2.enabled)
            {
                var2.onBobbingFinish(var0);
            }
        }
    }

    public static void entityTeleportHook(Packet34EntityTeleport var0)
    {
        Iterator var1 = AxiomSetup.modules.iterator();

        while (var1.hasNext())
        {
            AxiomModule var2 = (AxiomModule)var1.next();
            var2.onEntityTeleport(var0);
        }
    }

    public static float gammaSetHook(float var0)
    {
        Iterator var1 = AxiomSetup.modules.iterator();
        AxiomModule var2;

        do
        {
            if (!var1.hasNext())
            {
                return var0;
            }

            var2 = (AxiomModule)var1.next();
        }
        while (!var2.enabled || var2.onGammaSet(var0) == var0);

        return var2.onGammaSet(var0);
    }

    public static boolean renderHandItemHook()
    {
        boolean var0 = true;
        Iterator var1 = AxiomSetup.modules.iterator();

        while (var1.hasNext())
        {
            AxiomModule var2 = (AxiomModule)var1.next();

            if (var2.enabled && !var2.onRenderHandItem())
            {
                var0 = false;
            }
        }

        return var0;
    }

    public static boolean pushOutOfBlocksHook()
    {
        boolean var0 = true;
        Iterator var1 = AxiomSetup.modules.iterator();

        while (var1.hasNext())
        {
            AxiomModule var2 = (AxiomModule)var1.next();

            if (var2.enabled && !var2.onPushOutOfBlocks())
            {
                var0 = false;
            }
        }

        return var0;
    }

    public static int blockRenderPassSetHook(int var0, Block var1)
    {
        Iterator var2 = AxiomSetup.modules.iterator();
        AxiomModule var3;

        do
        {
            if (!var2.hasNext())
            {
                return var0;
            }

            var3 = (AxiomModule)var2.next();
        }
        while (!var3.enabled || var3.onBlockRenderPassSet(var0, var1) == var0);

        return var3.onBlockRenderPassSet(var0, var1);
    }

    public static int blockBrightnessIntegerSetHook(int var0)
    {
        Iterator var1 = AxiomSetup.modules.iterator();
        AxiomModule var2;

        do
        {
            if (!var1.hasNext())
            {
                return var0;
            }

            var2 = (AxiomModule)var1.next();
        }
        while (!var2.enabled || var2.onBlockBrightnessIntegerSet(var0) == var0);

        return var2.onBlockBrightnessIntegerSet(var0);
    }

    public static float blockBrightnessFloatSetHook(float var0)
    {
        Iterator var1 = AxiomSetup.modules.iterator();
        AxiomModule var2;

        do
        {
            if (!var1.hasNext())
            {
                return var0;
            }

            var2 = (AxiomModule)var1.next();
        }
        while (!var2.enabled || var2.onBlockBrightnessFloatSet(var0) == var0);

        return var2.onBlockBrightnessFloatSet(var0);
    }

    public static int rightClickDelaySetHook(int var0)
    {
        Iterator var1 = AxiomSetup.modules.iterator();
        AxiomModule var2;

        do
        {
            if (!var1.hasNext())
            {
                return var0;
            }

            var2 = (AxiomModule)var1.next();
        }
        while (!var2.enabled || var2.onRightClickDelaySet(var0) == var0);

        return var2.onRightClickDelaySet(var0);
    }

    public static void playerClickBlockHook(int var0, int var1, int var2, int var3)
    {
        Iterator var4 = AxiomSetup.modules.iterator();

        while (var4.hasNext())
        {
            AxiomModule var5 = (AxiomModule)var4.next();

            if (var5.enabled)
            {
                var5.onClickBlock(var0, var1, var2, var3);
            }
        }
    }

    public static boolean chatPacketHook(Packet3Chat var0)
    {
        boolean var1 = true;
        Iterator var2 = AxiomSetup.modules.iterator();

        while (var2.hasNext())
        {
            AxiomModule var3 = (AxiomModule)var2.next();

            if (var3.enabled)
            {
                boolean var4 = var3.onChat(var0);

                if (!var4)
                {
                    var1 = false;
                }
            }
        }

        return var1;
    }

    public static boolean opaqueBlockCheckHook(EntityPlayer var0)
    {
        Iterator var1 = AxiomSetup.modules.iterator();

        while (var1.hasNext())
        {
            AxiomModule var2 = (AxiomModule)var1.next();

            if (var2.enabled)
            {
                boolean var3 = var2.onOpaqueCheck(var0);

                if (!var3)
                {
                    return false;
                }
            }
        }

        return true;
    }

    public static AxisAlignedBB fluidBoundingBoxCreationHook(Block var0, int var1, int var2, int var3)
    {
        AxisAlignedBB var4 = null;
        Iterator var5 = AxiomSetup.modules.iterator();

        while (var5.hasNext())
        {
            AxiomModule var6 = (AxiomModule)var5.next();

            if (var6.enabled)
            {
                AxisAlignedBB var7 = var6.onFluidBoundingBoxCreation(var0, var1, var2, var3);

                if (var7 != null)
                {
                    var4 = var7;
                }
            }
        }

        return var4;
    }

    public static boolean sendQueueAddHook(Packet var0)
    {
        boolean var1 = true;
        Iterator var2 = AxiomSetup.modules.iterator();
        AxiomModule var3;

        do
        {
            if (!var2.hasNext())
            {
                return var1;
            }

            var3 = (AxiomModule)var2.next();
        }
        while (!var3.enabled || var3.onSendQueueAdd(var0));

        return false;
    }

    public static void playerUpdateHook()
    {
        Iterator var0 = AxiomSetup.modules.iterator();

        while (var0.hasNext())
        {
            AxiomModule var1 = (AxiomModule)var0.next();

            if (var1.enabled)
            {
                var1.onPlayerUpdate();
            }
        }
    }

    public static void motionUpdatesHook()
    {
        Iterator var0 = AxiomSetup.modules.iterator();

        while (var0.hasNext())
        {
            AxiomModule var1 = (AxiomModule)var0.next();

            if (var1.enabled)
            {
                var1.onMotionUpdates();
            }
        }
    }

    public static void globalRenderHook(float var0)
    {
        Iterator var1 = AxiomSetup.modules.iterator();

        while (var1.hasNext())
        {
            AxiomModule var2 = (AxiomModule)var1.next();

            if (var2.enabled)
            {
                var2.onGlobalRender(var0);
            }
        }
    }

    public static void playerClickHook(int var0)
    {
        Iterator var1 = AxiomSetup.modules.iterator();

        while (var1.hasNext())
        {
            AxiomModule var2 = (AxiomModule)var1.next();

            if (var2.enabled)
            {
                var2.onPlayerClick(var0);
            }
        }
    }

    public static void playerDeathHook(Entity var0)
    {
        if (var0 == AxiomWrapper.mcObj.thePlayer)
        {
            Iterator var1 = AxiomSetup.modules.iterator();

            while (var1.hasNext())
            {
                AxiomModule var2 = (AxiomModule)var1.next();

                if (var2.enabled)
                {
                    var2.onPlayerDeath();
                }
            }
        }
    }

    public static boolean entityVelocityHook(Packet28EntityVelocity var0)
    {
        Iterator var1 = AxiomSetup.modules.iterator();

        while (var1.hasNext())
        {
            AxiomModule var2 = (AxiomModule)var1.next();

            if (var2.enabled)
            {
                boolean var3 = var2.onEntityVelocity(var0);

                if (!var3)
                {
                    return false;
                }
            }
        }

        return true;
    }

    public static String tabListNameSetHook(String var0)
    {
        Iterator var1 = AxiomSetup.modules.iterator();

        while (var1.hasNext())
        {
            AxiomModule var2 = (AxiomModule)var1.next();

            if (var2.enabled)
            {
                String var3 = var2.onTabListNameSet(var0);

                if (!var0.equalsIgnoreCase(var0))
                {
                    return var3;
                }
            }
        }

        return var0;
    }

    public static String chatLineRenderHook(String var0)
    {
        Iterator var1 = AxiomSetup.modules.iterator();

        while (var1.hasNext())
        {
            AxiomModule var2 = (AxiomModule)var1.next();

            if (var2.enabled)
            {
                String var3 = var2.onChatLineRender(var0);

                if (!var0.equalsIgnoreCase(var3))
                {
                    return var3;
                }
            }
        }

        return var0;
    }

    public static int blockHitDelaySetHook(int var0)
    {
        Iterator var1 = AxiomSetup.modules.iterator();
        AxiomModule var2;

        do
        {
            if (!var1.hasNext())
            {
                return var0;
            }

            var2 = (AxiomModule)var1.next();
        }
        while (!var2.enabled || var2.onBlockHitDelaySet(var0) == var0);

        return var2.onBlockHitDelaySet(var0);
    }

    public static void renderBlockHook(Block var0, int var1, int var2, int var3)
    {
        Iterator var4 = AxiomSetup.modules.iterator();

        while (var4.hasNext())
        {
            AxiomModule var5 = (AxiomModule)var4.next();

            if (var5.enabled)
            {
                var5.onRenderBlock(var0, var1, var2, var3);
            }
        }
    }

    public static boolean renderAllFacesSetHook(Block var0, int var1, int var2, int var3)
    {
        Iterator var4 = AxiomSetup.modules.iterator();
        AxiomModule var5;

        do
        {
            if (!var4.hasNext())
            {
                return false;
            }

            var5 = (AxiomModule)var4.next();
        }
        while (!var5.enabled || !var5.onRenderAllFacesSet(var0, var1, var2, var3));

        return true;
    }

    public static float curBlockDamageSetHook(Block var0, int var1, int var2, int var3, float var4)
    {
        float var5 = var0.getPlayerRelativeBlockHardness(AxiomWrapper.mcObj.thePlayer, AxiomWrapper.mcObj.thePlayer.worldObj, var1, var2, var3);
        Iterator var6 = AxiomSetup.modules.iterator();
        AxiomModule var7;

        do
        {
            if (!var6.hasNext())
            {
                return var5;
            }

            var7 = (AxiomModule)var6.next();
        }
        while (!var7.enabled || var7.onCurBlockDamageSet(var0, var1, var2, var3, var4) == var5);

        return var7.onCurBlockDamageSet(var0, var1, var2, var3, var4);
    }

    public static void renderLivingHook(double var0, double var2, double var4, EntityLiving var6)
    {
        Iterator var7 = AxiomSetup.modules.iterator();

        while (var7.hasNext())
        {
            AxiomModule var8 = (AxiomModule)var7.next();

            if (var8.enabled)
            {
                var8.onRenderLiving(var0, var2, var4, var6);
            }
        }
    }

    public static float labelScaleHook(EntityLiving var0)
    {
        Iterator var1 = AxiomSetup.modules.iterator();
        AxiomModule var2;

        do
        {
            if (!var1.hasNext())
            {
                return 1.6F;
            }

            var2 = (AxiomModule)var1.next();
        }
        while (!var2.enabled || var2.onLabelScale(var0) == 1.6F);

        return var2.onLabelScale(var0);
    }

    public static int nameColorHook(EntityLiving var0)
    {
        Iterator var1 = AxiomSetup.modules.iterator();
        AxiomModule var2;

        do
        {
            if (!var1.hasNext())
            {
                return 16777215;
            }

            var2 = (AxiomModule)var1.next();
        }
        while (!var2.enabled || var2.onNameColor(var0) == 16777215);

        return var2.onNameColor(var0);
    }

    public static float labelDistanceFloatSetHook(EntityPlayer var0)
    {
        float var1 = var0.isSneaking() ? 32.0F : 64.0F;
        Iterator var2 = AxiomSetup.modules.iterator();
        AxiomModule var3;

        do
        {
            if (!var2.hasNext())
            {
                return var1;
            }

            var3 = (AxiomModule)var2.next();
        }
        while (!var3.enabled || var3.onLabelDistanceFloatSet(var0) == var1);

        return var3.onLabelDistanceFloatSet(var0);
    }

    public static String playerNameSetHook(String var0)
    {
        Iterator var1 = AxiomSetup.modules.iterator();

        while (var1.hasNext())
        {
            AxiomModule var2 = (AxiomModule)var1.next();

            if (var2.enabled)
            {
                String var3 = var2.onPlayerNameSet(var0);

                if (!var0.equalsIgnoreCase(var3))
                {
                    return var3;
                }
            }
        }

        return var0;
    }

    public static int labelDistanceIntegerSetHook(EntityPlayer var0)
    {
        byte var1 = 64;
        Iterator var2 = AxiomSetup.modules.iterator();
        AxiomModule var3;

        do
        {
            if (!var2.hasNext())
            {
                return var1;
            }

            var3 = (AxiomModule)var2.next();
        }
        while (!var3.enabled || var3.onLabelDistanceIntegerSet(var0) == var1);

        return var3.onLabelDistanceIntegerSet(var0);
    }

    public static int colorOpacitySetHook()
    {
        Iterator var0 = AxiomSetup.modules.iterator();
        AxiomModule var1;

        do
        {
            if (!var0.hasNext())
            {
                return 255;
            }

            var1 = (AxiomModule)var0.next();
        }
        while (!var1.enabled || var1.onColorOpacitySet() == 255);

        return var1.onColorOpacitySet();
    }

    public static boolean onEntityRender(Entity var0)
    {
        Iterator var1 = AxiomSetup.modules.iterator();
        AxiomModule var2;

        do
        {
            if (!var1.hasNext())
            {
                return true;
            }

            var2 = (AxiomModule)var1.next();
        }
        while (!var2.enabled || !(var2 instanceof AxiomModuleNoRender));

        return var2.onEntityRendered(var0);
    }
}
