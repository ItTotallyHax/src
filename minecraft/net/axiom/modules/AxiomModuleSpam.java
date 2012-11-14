package net.axiom.modules;

import net.axiom.AxiomHelper;
import net.axiom.AxiomWrapper;
import net.minecraft.src.MathHelper;
import net.minecraft.src.Packet3Chat;
import net.minecraft.src.StringUtils;

public class AxiomModuleSpam extends AxiomModule
{
    public String message = "LOLSPAM";
    public long currentms = 0L;
    public long threshold = 69L;
    public long lastMsg = 0L;

    public AxiomModuleSpam()
    {
        super("Spam", 16777215, true);
    }

    public void prepareMod()
    {
        this.currentms = System.nanoTime() / 1000000L;
    }

    public void onPlayerUpdate()
    {
        boolean var1 = this.currentms - this.lastMsg >= this.threshold;

        if (var1)
        {
            AxiomWrapper.mcObj.getSendQueue().addToSendQueue(new Packet3Chat(this.message));
            this.lastMsg = System.nanoTime() / 1000000L;
        }
    }

    public boolean onChat(Packet3Chat var1)
    {
        String var2 = StringUtils.stripControlCodes(var1.message);

        if (var2.toLowerCase().contains("please type"))
        {
            String[] var3 = var2.split("\'");

            if (var3.length >= 2)
            {
                AxiomWrapper.getPlayer().sendChatMessage(var3[1]);
                AxiomHelper.consoleMsg("Bypassing NC+ spam captcha: " + var3[1]);
            }
        }

        return true;
    }

    public boolean onCommand(String var1, String[] var2, String var3)
    {
        String var4;

        if (var1.equalsIgnoreCase("sm"))
        {
            if (!var3.contains("\""))
            {
                return true;
            }
            else
            {
                var4 = var3.split("\"")[1];
                this.message = var4;
                AxiomHelper.chatMsg("Spam message - \'" + var4 + "\'.");
                return true;
            }
        }
        else if (var1.equalsIgnoreCase("ss"))
        {
            if (var2.length < 1)
            {
                return true;
            }
            else
            {
                try
                {
                    float var9 = Float.parseFloat(var2[0]);
                    float var5 = 1000.0F / var9;
                    var5 = (float)MathHelper.ceiling_float_int(var5);
                    var5 += 2.0F;
                    long var6 = (long)var5;
                    this.threshold = var6;
                    AxiomHelper.chatMsg("New spam speed - " + var2[0] + " messages per second.");
                }
                catch (Exception var8)
                {
                    AxiomHelper.chatMsg("Numbers only.");
                }

                return true;
            }
        }
        else if (var1.equalsIgnoreCase("spam"))
        {
            this.enabled = !this.enabled;
            var4 = this.enabled ? "Started spamming." : "Stopped spamming.";
            AxiomHelper.chatMsg(var4);
            return true;
        }
        else
        {
            return false;
        }
    }
}
