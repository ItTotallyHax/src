package net.axiom.modules;

import java.util.Iterator;
import java.util.Map.Entry;
import net.axiom.AxiomHelper;
import net.minecraft.src.Packet3Chat;
import net.minecraft.src.StringUtils;

public class AxiomModuleAutoAccept extends AxiomModule
{
    public AxiomModuleAutoAccept()
    {
        super("AutoAccept", 16777215, true);
        this.enabled = true;
    }

    public boolean onChat(Packet3Chat var1)
    {
        if (StringUtils.stripControlCodes(var1.message).contains("has requested to teleport"))
        {
            Iterator var2 = ((AxiomModuleNames)AxiomHelper.getModByName("NameProtect")).nameList.entrySet().iterator();

            while (var2.hasNext())
            {
                Entry var3 = (Entry)var2.next();

                if (StringUtils.stripControlCodes(var1.message).contains((CharSequence)var3.getKey()))
                {
                    AxiomHelper.addPacket(new Packet3Chat("/tpaccept"));
                }
            }
        }

        return true;
    }
}
