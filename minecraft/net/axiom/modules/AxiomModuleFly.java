package net.axiom.modules;

import net.axiom.AxiomHelper;
import net.axiom.AxiomWrapper;
import org.lwjgl.input.Keyboard;

public class AxiomModuleFly extends AxiomModule
{
    public double modifier = 2.0D;
    public boolean creative = false;
    public boolean nocheat = false;
    boolean state;
    double fakePos = 0.0D;
    double realY;
    double realBBMY;
    int tickTimes = 0;

    public AxiomModuleFly()
    {
        super("Flight", 52479, "F", false);
    }

    public void prepareMod()
    {
        if (this.creative)
        {
            this.modTag = "Flight (Creative)";
        }
        else
        {
            this.modTag = "Flight";
        }
    }

    public void onEnable()
    {
        if (this.creative)
        {
            AxiomWrapper.mcObj.thePlayer.capabilities.isFlying = true;
        }
    }

    public void onDisable()
    {
        if (this.creative)
        {
            AxiomWrapper.mcObj.thePlayer.capabilities.isFlying = false;
        }
    }

    public void onPlayerUpdate()
    {
        if (this.nocheat)
        {
            ++this.tickTimes;

            if (this.tickTimes % 2 == 0)
            {
                this.state = !this.state;
            }

            if (this.state)
            {
                this.fakePos = -0.095D;
            }
            else
            {
                this.fakePos = 0.096D;
            }
        }

        if (this.creative)
        {
            AxiomWrapper.mcObj.thePlayer.capabilities.isFlying = true;
        }
        else
        {
            this.handleAxiomFly();
        }
    }

    public void handleAxiomFly()
    {
        AxiomWrapper.mcObj.thePlayer.capabilities.isFlying = false;
        AxiomWrapper.mcObj.thePlayer.motionY = 0.0D;
        AxiomWrapper.mcObj.thePlayer.motionX = 0.0D;
        AxiomWrapper.mcObj.thePlayer.motionZ = 0.0D;
        AxiomWrapper.mcObj.thePlayer.jumpMovementFactor = (float)(this.modifier / 2.0D);

        if (Keyboard.isKeyDown(57) && AxiomWrapper.mcObj.inGameHasFocus)
        {
            AxiomWrapper.mcObj.thePlayer.motionY += this.modifier / 4.0D;
        }

        if (Keyboard.isKeyDown(42) && AxiomWrapper.mcObj.inGameHasFocus)
        {
            AxiomWrapper.mcObj.thePlayer.motionY -= this.modifier / 4.0D;
        }
    }

    public void preMotionUpdates()
    {
        this.realBBMY = AxiomWrapper.mcObj.thePlayer.boundingBox.minY;
        this.realY = AxiomWrapper.mcObj.thePlayer.posY;
        AxiomWrapper.mcObj.thePlayer.boundingBox.minY += this.fakePos;
        AxiomWrapper.mcObj.thePlayer.posY += this.fakePos;
    }

    public void postMotionUpdates()
    {
        AxiomWrapper.mcObj.thePlayer.boundingBox.minY = this.realBBMY;
        AxiomWrapper.mcObj.thePlayer.posY = this.realY;
    }

    public boolean onCommand(String var1, String[] var2, String var3)
    {
        if (var1.equalsIgnoreCase("fs"))
        {
            try
            {
                double var7 = Double.parseDouble(var2[0]);
                this.modifier = var7;
                AxiomHelper.chatMsg("Flight speed changed to " + var2[0] + " (non-creative).");
            }
            catch (Exception var6)
            {
                if (var2[0].equalsIgnoreCase("default"))
                {
                    this.modifier = 2.0D;
                }
                else
                {
                    AxiomHelper.chatMsg("Numbers only.");
                }
            }

            return true;
        }
        else
        {
            String var4;

            if (var1.equalsIgnoreCase("fly"))
            {
                this.creative = !this.creative;
                var4 = this.creative ? "Flight mode is now creative." : "Flight mode is now custom.";
                AxiomHelper.chatMsg(var4);
                return true;
            }
            else if (var1.equalsIgnoreCase("ncf"))
            {
                this.nocheat = !this.nocheat;
                var4 = this.nocheat ? "NoCheat fly enabled" : "NoCheat fly disabled";
                this.modifier = 0.2D;
                AxiomHelper.chatMsg(var4);
                return true;
            }
            else
            {
                return false;
            }
        }
    }
}
