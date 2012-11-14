package net.axiom.modules;

import net.axiom.AxiomHelper;
import net.axiom.AxiomWrapper;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityBlaze;
import net.minecraft.src.EntityBoat;
import net.minecraft.src.EntityChicken;
import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.EntityCow;
import net.minecraft.src.EntityCreeper;
import net.minecraft.src.EntityEnderman;
import net.minecraft.src.EntityGhast;
import net.minecraft.src.EntityGolem;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityMinecart;
import net.minecraft.src.EntityOcelot;
import net.minecraft.src.EntityPig;
import net.minecraft.src.EntityPigZombie;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntitySheep;
import net.minecraft.src.EntitySilverfish;
import net.minecraft.src.EntitySkeleton;
import net.minecraft.src.EntitySlime;
import net.minecraft.src.EntitySpider;
import net.minecraft.src.EntitySquid;
import net.minecraft.src.EntityVillager;
import net.minecraft.src.EntityWolf;
import net.minecraft.src.EntityZombie;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.NetClientHandler;
import net.minecraft.src.Packet19EntityAction;
import net.minecraft.src.StringUtils;

public class AxiomModuleKillAura extends AxiomModule
{
    public double range = 4.25D;
    public boolean aimbot = false;
    public boolean silentAim = false;
    public boolean knockback = false;
    public boolean crit = false;
    public boolean passive = true;
    public boolean neutral = true;
    public boolean aggro = true;
    public boolean player = true;
    public boolean vehicle = true;
    public long currentms = 0L;
    public long threshold = 69L;
    public long lastHit = 0L;
    private float pitch = 0.0F;
    private float yaw = 0.0F;
    private float yawHead = 0.0F;
    private EntityLiving target = null;

    public AxiomModuleKillAura()
    {
        super("Kill Aura", 11141120, "R", false);
    }

    public void prepareMod()
    {
        this.currentms = System.nanoTime() / 1000000L;
    }

    public void onPlayerUpdate()
    {
        if (this.target == null)
        {
            this.target = this.getClosestLivingEntity(this.range);
        }
        else if (AxiomHelper.entityDistance(AxiomWrapper.mcObj.thePlayer, this.target) >= this.range)
        {
            this.target = null;
        }
        else if (!AxiomWrapper.mcObj.theWorld.loadedEntityList.contains(this.target))
        {
            this.target = null;
        }

        if (this.target != null)
        {
            boolean var1 = this.currentms - this.lastHit >= this.threshold;
            boolean var2 = this.shouldAttack(this.target);

            if (this.aimbot && var2)
            {
                this.aimPlayerAtTarget();
            }

            if (var1 && var2)
            {
                this.auraAttack(this.target);
            }
        }
    }

    public void preMotionUpdates()
    {
        if (this.silentAim)
        {
            EntityClientPlayerMP var1 = AxiomWrapper.getPlayer();
            this.pitch = var1.rotationPitch;
            this.yaw = var1.rotationYaw;
            this.yawHead = var1.rotationYawHead;
            this.aimPlayerAtTarget();
        }
    }

    public void postMotionUpdates()
    {
        if (this.silentAim)
        {
            EntityClientPlayerMP var1 = AxiomWrapper.getPlayer();
            var1.rotationPitch = this.pitch;
            var1.rotationYaw = this.yaw;
            var1.rotationYawHead = this.yawHead;
        }
    }

    private void aimPlayerAtTarget()
    {
        if (this.target != null)
        {
            double var1 = 0.0D;
            double var3 = this.target.posX - AxiomWrapper.mcObj.thePlayer.posX;
            double var5 = this.target.posZ - AxiomWrapper.mcObj.thePlayer.posZ;
            double var7 = this.target.posY - AxiomWrapper.mcObj.thePlayer.posY + (double)(this.target.height / 2.0F);

            if (var5 > 0.0D && var3 > 0.0D)
            {
                var1 = Math.toDegrees(-Math.atan(var3 / var5));
            }
            else if (var5 > 0.0D && var3 < 0.0D)
            {
                var1 = Math.toDegrees(-Math.atan(var3 / var5));
            }
            else if (var5 < 0.0D && var3 > 0.0D)
            {
                var1 = -90.0D + Math.toDegrees(Math.atan(var5 / var3));
            }
            else if (var5 < 0.0D && var3 < 0.0D)
            {
                var1 = 90.0D + Math.toDegrees(Math.atan(var5 / var3));
            }

            float var9 = (float)Math.sqrt(var5 * var5 + var3 * var3);
            float var10 = (float)(-Math.toDegrees(Math.atan(var7 / (double)var9)));
            AxiomWrapper.mcObj.thePlayer.rotationPitch = var10 - 2.0F;
            AxiomWrapper.mcObj.thePlayer.rotationYaw = (float)var1;
            AxiomWrapper.mcObj.thePlayer.rotationYawHead = (float)var1;
        }
    }

    public EntityLiving getClosestLivingEntity(double var1)
    {
        EntityClientPlayerMP var3 = AxiomWrapper.mcObj.thePlayer;
        EntityLiving var4 = null;
        double var5 = var1;

        for (int var7 = 0; var7 < AxiomWrapper.mcObj.theWorld.loadedEntityList.size(); ++var7)
        {
            Entity var8 = (Entity)AxiomWrapper.mcObj.theWorld.loadedEntityList.get(var7);

            if (var8 != AxiomWrapper.mcObj.thePlayer && var8 != null && var8 instanceof EntityLiving)
            {
                if (var8 instanceof EntityPlayer)
                {
                    EntityPlayer var9 = (EntityPlayer)var8;
                    AxiomModuleNames var10 = (AxiomModuleNames)AxiomHelper.getModByName("NameProtect");

                    if (var10 != null && var10.getAliasByName(var9.username) != null)
                    {
                        continue;
                    }
                }

                double var11 = AxiomHelper.entityDistance(AxiomWrapper.getPlayer(), var8);

                if (!AxiomWrapper.getPlayer().canEntityBeSeen(var8))
                {
                    var11 *= 2.0D;
                }

                if (var11 < var5)
                {
                    var5 = var11;
                    var4 = (EntityLiving)var8;
                }
            }
        }

        return var4;
    }

    public void auraAttack(Entity var1)
    {
        this.lastHit = System.nanoTime() / 1000000L;
        EntityClientPlayerMP var2 = AxiomWrapper.getPlayer();
        this.selectWeapon(var1);
        NetClientHandler var3 = AxiomWrapper.mcObj.getSendQueue();

        if (this.knockback)
        {
            var3.addToSendQueue(new Packet19EntityAction(AxiomWrapper.mcObj.thePlayer, 4));
        }

        var2.swingItem();
        AxiomWrapper.getController().attackEntity(var2, var1);

        if (this.knockback)
        {
            var3.addToSendQueue(new Packet19EntityAction(AxiomWrapper.mcObj.thePlayer, 5));
        }
    }

    public void selectWeapon(Entity var1)
    {
        EntityClientPlayerMP var2 = AxiomWrapper.mcObj.thePlayer;
        float var3 = 0.0F;
        int var4 = AxiomWrapper.mcObj.thePlayer.inventory.currentItem;

        for (int var5 = 36; var5 < 45; ++var5)
        {
            ItemStack var6 = var2.inventorySlots.getSlot(var5).getStack();

            if (var6 != null)
            {
                float var7 = (float)var6.getDamageVsEntity(var1);

                if (var7 > var3)
                {
                    var3 = var7;
                    var4 = var5 - 36;
                }
            }
        }

        AxiomWrapper.mcObj.thePlayer.inventory.currentItem = var4;
    }

    public boolean shouldAttack(Entity var1)
    {
        if (!(var1 instanceof EntityBoat) && !(var1 instanceof EntityMinecart))
        {
            if (!(var1 instanceof EntityLiving))
            {
                return false;
            }
            else if (!(var1 instanceof EntityPig) && !(var1 instanceof EntityCow) && !(var1 instanceof EntitySlime) && !(var1 instanceof EntityGolem) && !(var1 instanceof EntityChicken) && !(var1 instanceof EntitySheep) && !(var1 instanceof EntitySquid) && !(var1 instanceof EntityOcelot) && !(var1 instanceof EntityVillager))
            {
                if (!(var1 instanceof EntityEnderman) && !(var1 instanceof EntityPigZombie) && !(var1 instanceof EntityWolf))
                {
                    if (!(var1 instanceof EntityZombie) && !(var1 instanceof EntitySpider) && !(var1 instanceof EntitySkeleton) && !(var1 instanceof EntityGhast) && !(var1 instanceof EntityBlaze) && !(var1 instanceof EntityCreeper) && !(var1 instanceof EntitySilverfish))
                    {
                        if (var1 instanceof EntityPlayer)
                        {
                            AxiomModuleNames var2 = (AxiomModuleNames)AxiomHelper.getModByName("NameProtect");

                            if (var2 != null)
                            {
                                String var3 = StringUtils.stripControlCodes(((EntityPlayer)var1).username);

                                if (var2.getAliasByName(var3) != null)
                                {
                                    return false;
                                }
                            }

                            return this.player;
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return this.aggro;
                    }
                }
                else
                {
                    return this.neutral;
                }
            }
            else
            {
                return this.passive;
            }
        }
        else
        {
            return this.vehicle;
        }
    }

    public boolean onCommand(String var1, String[] var2, String var3)
    {
        String var10;

        if (var1.equalsIgnoreCase("aim"))
        {
            this.silentAim = false;
            this.aimbot = !this.aimbot;
            var10 = this.aimbot ? "Kill Aura aimbot enabled." : "Kill Aura aimbot disabled.";
            AxiomHelper.chatMsg(var10);
            return true;
        }
        else if (var1.equalsIgnoreCase("silent"))
        {
            this.aimbot = false;
            this.silentAim = !this.silentAim;
            var10 = this.silentAim ? "Kill Aura silent aimbot enabled." : "Kill Aura silent aimbot disabled.";
            AxiomHelper.chatMsg(var10);
            return true;
        }
        else if (var1.equalsIgnoreCase("knock"))
        {
            this.knockback = !this.knockback;
            var10 = this.knockback ? "Kill Aura knockback enabled." : "Kill Aura knockback disabled.";
            AxiomHelper.chatMsg(var10);
            return true;
        }
        else if (var1.equalsIgnoreCase("ar"))
        {
            if (var2.length < 1)
            {
                return true;
            }
            else
            {
                try
                {
                    double var11 = Double.parseDouble(var2[0]);
                    this.range = var11;
                    AxiomHelper.chatMsg("New Kill Aura range - " + var11 + " blocks.");
                }
                catch (Exception var8)
                {
                    AxiomHelper.chatMsg("Numbers only.");
                }

                return true;
            }
        }
        else if (var1.equalsIgnoreCase("crit"))
        {
            this.crit = !this.crit;
            var10 = this.crit ? "Kill Aura will now try to force criticals." : "Kill Aura no longer tries to force criticals.";
            AxiomHelper.chatMsg(var10);
            return true;
        }
        else if (var1.equalsIgnoreCase("ks"))
        {
            if (var2.length < 1)
            {
                return true;
            }
            else
            {
                try
                {
                    float var4 = Float.parseFloat(var2[0]);
                    float var5 = 1000.0F / var4;
                    var5 = (float)MathHelper.ceiling_float_int(var5);
                    var5 += 2.0F;
                    long var6 = (long)var5;
                    this.threshold = var6;
                    AxiomHelper.chatMsg("New Kill Aura speed - " + var2[0] + " attacks per second.");
                }
                catch (Exception var9)
                {
                    AxiomHelper.chatMsg("Numbers only.");
                }

                return true;
            }
        }
        else
        {
            return false;
        }
    }
}
