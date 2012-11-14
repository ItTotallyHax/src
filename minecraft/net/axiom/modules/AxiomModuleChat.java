package net.axiom.modules;

import net.axiom.AxiomHelper;
import net.axiom.AxiomWrapper;
import net.axiom.gui.screens.AxiomGuiChat;
import net.minecraft.src.GuiNewChat;

public class AxiomModuleChat extends AxiomModule
{
    public AxiomModuleChat()
    {
        super("AxiomChat", 16777215, true);
    }

    public void onEnable()
    {
        AxiomWrapper.mcObj.ingameGUI.persistantChatGUI = new AxiomGuiChat(AxiomWrapper.mcObj);
        AxiomHelper.chatMsg("Axiom chat enabled.");
    }

    public void onDisable()
    {
        AxiomWrapper.mcObj.ingameGUI.persistantChatGUI = new GuiNewChat(AxiomWrapper.mcObj);
        AxiomHelper.chatMsg("Axiom chat disabled.");
    }

    public boolean onCommand(String var1, String[] var2, String var3)
    {
        if (var1.equalsIgnoreCase("chat"))
        {
            this.toggleState();
            return true;
        }
        else
        {
            return false;
        }
    }
}
