package com.github.bartimaeusnek.cropspp;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.oredict.OreDictionary;

import ic2.api.crops.ICropTile;
import ic2.core.Ic2Items;

public class CCropUtility {

    public static ItemStack getCopiedOreStack(String name) {
        return getCopiedOreStack(name, 1);
    }

    public static ItemStack getCopiedOreStack(String name, int count) {
        ArrayList<ItemStack> ores = OreDictionary.getOres(name);
        if (ores.isEmpty()) return null;
        ItemStack stack = ores.get(ores.size() - 1).copy();
        // always define stack size since the ore item stacks
        // in the ore dictionary are mutable and therefore prone to UB
        stack.stackSize = count;
        return stack;
    }

    public static boolean isRainingOn(ICropTile crop) {
        ChunkCoordinates cord = crop.getLocation();
        World world = crop.getWorld();

        if (cord == null || world == null) {
            return false;
        }

        // If somthing goin on
        if (world.isRaining()) {
            BiomeGenBase biomegenbase = world.getBiomeGenForCoords(cord.posX, cord.posZ);

            if (biomegenbase == null) {
                return false;
            }

            // No rain
            if (!biomegenbase.canSpawnLightningBolt()) {
                return false;
            }

            // Is raining on block
            if (world.canBlockSeeTheSky(cord.posX, cord.posY + 1, cord.posZ)) {
                return true;
            }
        }

        return false;
    }

    public static void damageEntity(Entity ent, float damage) {

        if (!(ent instanceof EntityLivingBase)) {
            return;
        }

        // Boots Protection Disabled just damage
        if (!ConfigValues.BootsProtect) {
            ent.attackEntityFrom(DamageSource.cactus, damage);
            return;
        }

        // If Damage disabled Stop doing this
        if (ent instanceof EntityPlayer) {
            EntityPlayer pl = (EntityPlayer) ent;
            if (pl.capabilities.disableDamage) {
                return;
            }
        }

        // Getting Equipment to check
        EntityLivingBase elTarget = (EntityLivingBase) ent;
        ItemStack boots = elTarget.getEquipmentInSlot(1);

        if (boots != null) {

            Item item = boots.getItem();
            // No Damage
            if (item == Ic2Items.nanoBoots.getItem() || item == Ic2Items.quantumBoots.getItem()) {
                return;
            }

            if (item == Ic2Items.hazmatBoots.getItem()) {

                // No damage while sneaking
                if (elTarget.isSneaking()) {
                    return;
                }

                // If Player stops moving prevent damage
                if (elTarget.prevPosX == elTarget.posX && elTarget.prevPosZ == elTarget.posZ) {
                    return;
                }

                // Damage boots
                if (!elTarget.worldObj.isRemote) {

                    // Trying to damage
                    if (CCropUtility.getRandom(elTarget.getRNG(), ConfigValues.BootsDamageChance)) {
                        // Do particles
                        makeParticles(elTarget, boots);
                        boots.damageItem(1, elTarget);
                    }

                    // Destroy item
                    if (boots.stackSize == 0) {
                        // Make SOme sounds
                        ent.worldObj.playSoundAtEntity(
                                ent,
                                "random.break",
                                0.8F,
                                0.8F + elTarget.getRNG().nextFloat() * 0.4F);
                        elTarget.setCurrentItemOrArmor(1, null);
                    }
                }

                // No dmg
                return;
            }
        }
        // Else Do damage to player
        elTarget.attackEntityFrom(DamageSource.cactus, damage);
    }

    public static void makeParticles(EntityLivingBase ent, ItemStack itemStack) {
        WorldServer s = (WorldServer) ent.worldObj;
        Vec3 vec31 = Vec3.createVectorHelper(ent.posX, (ent.posY + 1) - (double) ent.getEyeHeight() / 2, ent.posZ);
        s.func_147487_a(
                "iconcrack_" + Item.getIdFromItem(itemStack.getItem()),
                vec31.xCoord,
                vec31.yCoord,
                vec31.zCoord,
                3,
                0,
                0,
                0,
                0.1);
    }

    public static boolean getRandom(Random r, double chanceMul) {
        return ((r.nextDouble() * 100d) < chanceMul);
    }
}
