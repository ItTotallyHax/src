package net.axiom.modules;

import java.util.ArrayList;
import net.axiom.AxiomHelper;
import net.axiom.thread.AxiomThreadNuker;

public class AxiomModuleNuker extends AxiomModule
{
    public int mode = 0;
    public ArrayList list = new ArrayList();

    public AxiomModuleNuker()
    {
        super("Nuker", 361752, "N", false);
    }

    public void prepareMod()
    {
        if (this.mode == 0)
        {
            this.modColor = 361752;
        }
        else
        {
            this.modColor = 11141120;
        }
    }

    public void onPlayerClick(int var1)
    {
        if (var1 == 0)
        {
            Thread var2 = new Thread(new AxiomThreadNuker(this), "Axiom Nuker Thread");
            var2.start();
        }
    }

    public boolean onCommand(String var1, String[] var2, String var3)
    {
        if (!var1.equalsIgnoreCase("nuke"))
        {
            if (var1.equalsIgnoreCase("nm"))
            {
                this.mode = this.mode == 0 ? 1 : 0;
                String var10 = this.mode == 0 ? "Nuker mode changed to blacklist." : "Nuker mode changed to whitelist.";
                AxiomHelper.chatMsg(var10);
                return true;
            }
            else
            {
                return false;
            }
        }
        else if (var3.trim().equalsIgnoreCase(".nuke"))
        {
            this.list.clear();
            AxiomHelper.chatMsg("Nuker list cleared.");
            return true;
        }
        else
        {
            int var4 = 0;

            for (int var5 = 0; var5 < var2.length; ++var5)
            {
                try
                {
                    int var6 = Integer.parseInt(var2[var5]);
                    this.list.add(Integer.valueOf(var6));
                    ++var4;
                }
                catch (NumberFormatException var8)
                {
                    int var7 = AxiomHelper.blockNameToID(var2[var5]);

                    if (var7 != -1)
                    {
                        this.list.add(Integer.valueOf(var7));
                        ++var4;
                    }
                }
                catch (Exception var9)
                {
                    ;
                }
            }

            AxiomHelper.chatMsg("Nuker targeting " + var4 + " block type(s).");
            return true;
        }
    }
}
