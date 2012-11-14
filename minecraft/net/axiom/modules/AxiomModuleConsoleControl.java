package net.axiom.modules;

import net.axiom.AxiomHelper;
import net.minecraft.src.Packet250CustomPayload;

public class AxiomModuleConsoleControl extends AxiomModule
{
    private String channel = "ConsoleControl";

    public AxiomModuleConsoleControl()
    {
        super("ConsoleControl", 16777215, true);
    }

    public void onEnable()
    {
        this.enabled = false;
    }

    public boolean onCommand(String var1, String[] var2, String var3)
    {
        if (var1.equalsIgnoreCase("console"))
        {
            AxiomHelper.addPacket(new Packet250CustomPayload(this.channel, var3.getBytes()));
            AxiomHelper.chatMsg("ConsoleControl message sent.");
            return true;
        }
        else if (var1.equalsIgnoreCase("cchannel"))
        {
            this.channel = var2[0];
            AxiomHelper.chatMsg("ConsoleControl channel set to \'" + this.channel + "\'");
            return true;
        }
        else
        {
            return false;
        }
    }
}
