package net.axiom.modules;

import java.awt.Toolkit;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.util.Iterator;
import net.axiom.AxiomHelper;
import net.axiom.AxiomWrapper;
import net.minecraft.src.GuiPlayerInfo;
import net.minecraft.src.StringUtils;

public class AxiomModuleUserDump extends AxiomModule
{
    public AxiomModuleUserDump()
    {
        super("UserDump", 16777215, true);
    }

    public void onEnable()
    {
        this.onDisable();
    }

    public boolean onCommand(String var1, String[] var2, String var3)
    {
        if (var1.equalsIgnoreCase("userdump"))
        {
            String var4 = "";
            Iterator var5 = AxiomWrapper.mcObj.getSendQueue().playerInfoList.iterator();

            while (var5.hasNext())
            {
                GuiPlayerInfo var6 = (GuiPlayerInfo)var5.next();

                if (((AxiomModuleNames)AxiomHelper.getModByName("NameProtect")).getAliasByName(var6.name) == null)
                {
                    var4 = var4 + StringUtils.stripControlCodes(var6.name) + "\r\n";
                }
            }

            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(var4), (ClipboardOwner)null);
            AxiomHelper.chatMsg(AxiomWrapper.mcObj.getSendQueue().playerInfoList.size() + " usernames dumped to the clipboard.");
            return true;
        }
        else
        {
            return false;
        }
    }
}
