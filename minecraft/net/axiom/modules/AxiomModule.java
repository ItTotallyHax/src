package net.axiom.modules;

import net.axiom.AxiomWrapper;
import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Gui;
import net.minecraft.src.MathHelper;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet24MobSpawn;
import net.minecraft.src.Packet28EntityVelocity;
import net.minecraft.src.Packet34EntityTeleport;
import net.minecraft.src.Packet3Chat;
import net.minecraft.src.Packet51MapChunk;
import net.minecraft.src.Packet56MapChunks;
import org.lwjgl.input.Keyboard;

public class AxiomModule
{
    public String modName;
    public int modColor;
    public int modBind;
    public String modTag;
    public boolean enabled;
    public boolean hidden;

    public AxiomModule(String var1, int var2, int var3, boolean var4)
    {
        this.enabled = false;
        this.hidden = false;
        this.modName = var1;
        this.modTag = var1;
        this.modColor = var2;
        this.modBind = var3;
        this.hidden = var4;
    }

    public AxiomModule(String var1, int var2, String var3, boolean var4)
    {
        this(var1, var2, Keyboard.getKeyIndex(var3.toUpperCase()), var4);
    }

    public AxiomModule(String var1, int var2, boolean var3)
    {
        this(var1, var2, 0, var3);
    }

    public void toggleState()
    {
        this.enabled = !this.enabled;

        if (this.enabled)
        {
            this.onEnable();
        }
        else
        {
            this.onDisable();
        }
    }

    public boolean onCommand(String var1, String[] var2, String var3)
    {
        return false;
    }

    public void prepareMod() {}

    public void onEnable()
    {
        this.enabled = true;
    }

    public void onDisable()
    {
        this.enabled = false;
    }

    public void onRenderGUI() {}

    public boolean onChat(Packet3Chat var1)
    {
        return true;
    }

    public boolean onOpaqueCheck(EntityPlayer var1)
    {
        boolean var2 = false;

        for (int var3 = 0; var3 < 8; ++var3)
        {
            float var4 = ((float)((var3 >> 0) % 2) - 0.5F) * var1.width * 0.8F;
            float var5 = ((float)((var3 >> 1) % 2) - 0.5F) * 0.1F;
            float var6 = ((float)((var3 >> 2) % 2) - 0.5F) * var1.width * 0.8F;
            int var7 = MathHelper.floor_double(var1.posX + (double)var4);
            int var8 = MathHelper.floor_double(var1.posY + (double)var1.getEyeHeight() + (double)var5);
            int var9 = MathHelper.floor_double(var1.posZ + (double)var6);

            if (AxiomWrapper.mcObj.theWorld.isBlockNormalCube(var7, var8, var9))
            {
                var2 = true;
            }
        }

        return !var1.isPlayerSleeping() && var2;
    }

    public AxisAlignedBB onFluidBoundingBoxCreation(Block var1, int var2, int var3, int var4)
    {
        return null;
    }

    public boolean onSendQueueAdd(Packet var1)
    {
        return true;
    }

    public void onPlayerUpdate() {}

    public void onMotionUpdates() {}

    public void onGlobalRender(float var1) {}

    public void onPlayerClick(int var1) {}

    public void onPlayerDeath() {}

    public boolean onEntityVelocity(Packet28EntityVelocity var1)
    {
        return true;
    }

    public void onClickBlock(int var1, int var2, int var3, int var4) {}

    public int onRightClickDelaySet(int var1)
    {
        return var1;
    }

    public float onBlockBrightnessFloatSet(float var1)
    {
        return var1;
    }

    public int onBlockRenderPassSet(int var1, Block var2)
    {
        return var1;
    }

    public boolean onPushOutOfBlocks()
    {
        return true;
    }

    public boolean onRenderHandItem()
    {
        return true;
    }

    public float onGammaSet(float var1)
    {
        return var1;
    }

    public String onTabListNameSet(String var1)
    {
        return var1;
    }

    public int onBlockHitDelaySet(int var1)
    {
        return var1;
    }

    public void onRenderBlock(Block var1, int var2, int var3, int var4) {}

    public boolean onRenderAllFacesSet(Block var1, int var2, int var3, int var4)
    {
        return false;
    }

    public float onCurBlockDamageSet(Block var1, int var2, int var3, int var4, float var5)
    {
        return var1.getPlayerRelativeBlockHardness(AxiomWrapper.mcObj.thePlayer, AxiomWrapper.mcObj.thePlayer.worldObj, var2, var3, var4);
    }

    public void onRenderLiving(double var1, double var3, double var5, EntityLiving var7) {}

    public float onLabelScale(EntityLiving var1)
    {
        return 1.6F;
    }

    public int onNameColor(EntityLiving var1)
    {
        return 16777215;
    }

    public float onLabelDistanceFloatSet(EntityPlayer var1)
    {
        return var1.isSneaking() ? 32.0F : 64.0F;
    }

    public String onPlayerNameSet(String var1)
    {
        return var1;
    }

    public int onLabelDistanceIntegerSet(EntityPlayer var1)
    {
        return 64;
    }

    public int onColorOpacitySet()
    {
        return 255;
    }

    public int onBlockBrightnessIntegerSet(int var1)
    {
        return var1;
    }

    public String onChatLineRender(String var1)
    {
        return var1;
    }

    public void onEntityTeleport(Packet34EntityTeleport var1) {}

    public void onBobbingFinish(float var1) {}

    public void preMotionUpdates() {}

    public void postMotionUpdates() {}

    public void onGuiCreation(Gui var1) {}

    public boolean onMapChunk(Packet51MapChunk var1)
    {
        return true;
    }

    public boolean onMapChunks(Packet56MapChunks var1)
    {
        return true;
    }

    public void onMobSpawn(Packet24MobSpawn var1) {}

    public boolean onEntityRendered(Entity var1)
    {
        return true;
    }
}
