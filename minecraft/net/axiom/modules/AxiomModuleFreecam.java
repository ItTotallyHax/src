package net.axiom.modules;

import net.axiom.AxiomWrapper;
import net.axiom.modules.addons.AxiomFreecamInput;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerSP;
import org.lwjgl.input.Keyboard;

public class AxiomModuleFreecam extends AxiomModule
{
    private EntityPlayerSP flyCam;

    public AxiomModuleFreecam()
    {
        super("Freecam", 16777215, "U", true);
    }

    public boolean onPushOutOfBlocks()
    {
        return false;
    }

    public boolean onRenderHandItem()
    {
        return false;
    }

    public boolean onOpaqueCheck(EntityPlayer var1)
    {
        return false;
    }

    public void onEnable()
    {
        this.flyCam = new EntityPlayerSP(AxiomWrapper.mcObj, AxiomWrapper.getWorld(), AxiomWrapper.mcObj.session, AxiomWrapper.getPlayer().dimension);
        this.flyCam.noClip = true;
        AxiomWrapper.getWorld().addEntityToWorld(-1, this.flyCam);
        AxiomWrapper.mcObj.renderViewEntity = this.flyCam;
        this.flyCam.setPosition(AxiomWrapper.getPlayer().posX, AxiomWrapper.getPlayer().posY, AxiomWrapper.getPlayer().posZ);
        this.flyCam.movementInput = new AxiomFreecamInput();
    }

    public void onPlayerUpdate()
    {
        this.flyCam.capabilities.isFlying = false;
        this.flyCam.motionY = 0.0D;
        this.flyCam.motionX = 0.0D;
        this.flyCam.motionZ = 0.0D;
        this.flyCam.jumpMovementFactor = 1.0F;
        boolean var1 = AxiomWrapper.mcObj.inGameHasFocus;

        if (Keyboard.isKeyDown(200) && var1)
        {
            this.flyCam.rotationPitch = (float)((double)this.flyCam.rotationPitch - 6.0D);
        }

        if (Keyboard.isKeyDown(208) && var1)
        {
            this.flyCam.rotationPitch = (float)((double)this.flyCam.rotationPitch + 6.0D);
        }

        if (Keyboard.isKeyDown(203) && var1)
        {
            this.flyCam.rotationYaw = (float)((double)this.flyCam.rotationYaw - 6.0D);
            this.flyCam.rotationYawHead = (float)((double)this.flyCam.rotationYawHead - 6.0D);
        }

        if (Keyboard.isKeyDown(205) && var1)
        {
            this.flyCam.rotationYaw = (float)((double)this.flyCam.rotationYaw + 6.0D);
            this.flyCam.rotationYawHead = (float)((double)this.flyCam.rotationYawHead + 6.0D);
        }

        if (Keyboard.isKeyDown(51) && var1)
        {
            this.flyCam.motionY += 0.5D;
        }

        if (Keyboard.isKeyDown(52) && var1)
        {
            this.flyCam.motionY -= 0.5D;
        }
    }

    public void onDisable()
    {
        AxiomWrapper.getWorld().removeEntityFromWorld(-1);
        AxiomWrapper.mcObj.renderViewEntity = AxiomWrapper.getPlayer();
        this.flyCam = null;
        this.enabled = false;
    }

    public void onPlayerDeath()
    {
        this.onDisable();
    }
}
