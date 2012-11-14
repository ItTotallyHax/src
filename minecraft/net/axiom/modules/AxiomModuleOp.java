package net.axiom.modules;

import java.util.Iterator;
import net.axiom.AxiomHelper;
import net.axiom.AxiomWrapper;
import net.minecraft.src.GuiPlayerInfo;
import net.minecraft.src.StringUtils;

public class AxiomModuleOp extends AxiomModule
{
    public AxiomModuleOp()
    {
        super("Op", 16777215, true);
    }

    private void sendOpKill()
    {
        AxiomModuleNames var1 = (AxiomModuleNames)AxiomHelper.getModByName("NameProtect");
        Iterator var2 = AxiomWrapper.mcObj.getSendQueue().playerInfoList.iterator();

        while (var2.hasNext())
        {
            GuiPlayerInfo var3 = (GuiPlayerInfo)var2.next();
            String var4 = StringUtils.stripControlCodes(var3.name);

            if (!var4.equalsIgnoreCase(AxiomWrapper.getPlayer().username) && var1.getAliasByName(var4) == null)
            {
                AxiomWrapper.getPlayer().sendChatMessage("/kill " + var4);
            }
        }
    }

    private void sendOpSudo(String var1)
    {
        AxiomModuleNames var2 = (AxiomModuleNames)AxiomHelper.getModByName("NameProtect");
        Iterator var3 = AxiomWrapper.mcObj.getSendQueue().playerInfoList.iterator();

        while (var3.hasNext())
        {
            GuiPlayerInfo var4 = (GuiPlayerInfo)var3.next();
            String var5 = StringUtils.stripControlCodes(var4.name);

            if (!var5.equalsIgnoreCase(AxiomWrapper.getPlayer().username) && var2.getAliasByName(var5) == null)
            {
                AxiomWrapper.getPlayer().sendChatMessage("/sudo " + var5 + " " + var1);
            }
        }
    }

    private void sendOpBan()
    {
        AxiomModuleNames var1 = (AxiomModuleNames)AxiomHelper.getModByName("NameProtect");
        Iterator var2 = AxiomWrapper.mcObj.getSendQueue().playerInfoList.iterator();

        while (var2.hasNext())
        {
            GuiPlayerInfo var3 = (GuiPlayerInfo)var2.next();
            String var4 = StringUtils.stripControlCodes(var3.name);

            if (!var4.equalsIgnoreCase(AxiomWrapper.getPlayer().username) && var1.getAliasByName(var4) == null)
            {
                AxiomWrapper.getPlayer().sendChatMessage("/ban " + var4);
            }
        }
    }

    private void sendOpClear()
    {
        AxiomModuleNames var1 = (AxiomModuleNames)AxiomHelper.getModByName("NameProtect");
        Iterator var2 = AxiomWrapper.mcObj.getSendQueue().playerInfoList.iterator();

        while (var2.hasNext())
        {
            GuiPlayerInfo var3 = (GuiPlayerInfo)var2.next();
            String var4 = StringUtils.stripControlCodes(var3.name);

            if (!var4.equalsIgnoreCase(AxiomWrapper.getPlayer().username) && var1.getAliasByName(var4) == null)
            {
                AxiomWrapper.getPlayer().sendChatMessage("/clear " + var4);
            }
        }
    }

    public boolean onCommand(String var1, String[] var2, String var3)
    {
        if (var1.equalsIgnoreCase("opkill"))
        {
            this.sendOpKill();
            return true;
        }
        else if (var1.equalsIgnoreCase("opsudo"))
        {
            if (var3.contains("\"") && var3.endsWith("\""))
            {
                String var9 = var3.split("\"")[1];
                this.sendOpSudo(var9);
                return true;
            }
            else
            {
                AxiomHelper.chatMsg("Invalid syntax.");
                return true;
            }
        }
        else if (var1.equalsIgnoreCase("opban"))
        {
            this.sendOpBan();
            return true;
        }
        else if (var1.equalsIgnoreCase("opclear"))
        {
            this.sendOpClear();
            return true;
        }
        else if (var1.equalsIgnoreCase("wcube"))
        {
            if (var2.length < 1)
            {
                return true;
            }
            else
            {
                try
                {
                    Integer var8 = Integer.valueOf(Integer.parseInt(var2[0]));
                    AxiomWrapper.getPlayer().sendChatMessage("//pos1");
                    AxiomWrapper.getPlayer().sendChatMessage("//pos2");
                    AxiomWrapper.getPlayer().sendChatMessage("//expand " + var8 + " up");
                    AxiomWrapper.getPlayer().sendChatMessage("//expand " + var8 + " down");
                    AxiomWrapper.getPlayer().sendChatMessage("//expand " + var8 + " north");
                    AxiomWrapper.getPlayer().sendChatMessage("//expand " + var8 + " south");
                    AxiomWrapper.getPlayer().sendChatMessage("//expand " + var8 + " east");
                    AxiomWrapper.getPlayer().sendChatMessage("//expand " + var8 + " west");
                }
                catch (Exception var6)
                {
                    AxiomHelper.chatMsg("Numbers only.");
                }

                return true;
            }
        }
        else if (var1.equalsIgnoreCase("wsp"))
        {
            if (var2.length < 1)
            {
                return true;
            }
            else
            {
                try
                {
                    double var4 = Double.parseDouble(var2[0]);
                    AxiomWrapper.getPlayer().sendChatMessage("//sphere 0 " + var4);
                }
                catch (NumberFormatException var7)
                {
                    AxiomHelper.chatMsg("Numbers only.");
                }

                return true;
            }
        }
        else if (var1.equalsIgnoreCase("wugly"))
        {
            AxiomWrapper.getPlayer().sendChatMessage("//set 4,1,19,56,52,48,20,121,110,7,73,88");
            return true;
        }
        else
        {
            return false;
        }
    }
}
