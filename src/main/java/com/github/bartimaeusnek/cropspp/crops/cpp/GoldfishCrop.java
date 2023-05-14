package com.github.bartimaeusnek.cropspp.crops.cpp;

import java.util.Collections;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.croploadcore.MyRandom;
import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.abstracts.BasicDecorationCrop;
import com.github.bartimaeusnek.cropspp.items.CppItems;

import ic2.api.crops.ICropTile;

public class GoldfishCrop extends BasicDecorationCrop {

    public GoldfishCrop() {
        super();
    }

    @Override
    public int tier() {
        return 4;
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemStack(CppItems.Goldfisch);
    }

    @Override
    public String name() {
        return "Goldfish Plant";
    }

    @Override
    public String[] attributes() {
        return new String[] { "Nether", "Fish", "Food", "Bad", "Water" };
    }

    @Override
    public int growthDuration(ICropTile crop) {
        return ConfigValues.debug ? 1 : 225;
    }

    @Override
    public boolean rightclick(ICropTile crop, EntityPlayer player) {
        if (!ConfigValues.ILoveScreaming) return crop.harvest(true);

        boolean ret;
        if (((int) crop.getSize()) != this.maxSize()) {
            player.playSound("mob.ghast.scream", crop.getSize(), (maxSize() + 1) + (-1) * crop.getSize());
            ret = crop.harvest(true);
        } else if (((int) crop.getSize()) == this.maxSize()) {
            player.playSound("mob.ghast.scream", 5, (float) 0.5);
            ret = crop.harvest(true);
        } else ret = false;
        return ret;
    }

    @Override
    public boolean leftclick(ICropTile crop, EntityPlayer player) {
        if (!ConfigValues.ILoveScreaming) return crop.pick(true);

        boolean ret;
        if (((int) crop.getSize()) != this.maxSize()) {
            player.playSound("mob.ghast.scream", crop.getSize(), (maxSize() + 1) + (-1) * crop.getSize());
            ret = crop.pick(true);
        } else if (((int) crop.getSize()) == this.maxSize()) {
            player.playSound("mob.ghast.scream", 5, (float) 0.5);
            ret = crop.pick(true);
        } else ret = false;
        return ret;
    }

    @Override
    public boolean onEntityCollision(ICropTile crop, Entity entity) {
        if (!ConfigValues.ILoveScreaming) return super.onEntityCollision(crop, entity);

        if (entity instanceof EntityLivingBase) {
            if (entity instanceof EntityPlayer) {
                if (((int) crop.getSize()) != this.maxSize())
                    entity.playSound("mob.ghast.scream", crop.getSize(), (maxSize() + 1) + (-1) * crop.getSize());
                else if (((int) crop.getSize()) == this.maxSize()) entity.playSound("mob.ghast.scream", 5, (float) 0.5);
            }
            return ((EntityLivingBase) entity).isSprinting();
        }
        return false;
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        return new ItemStack(CppItems.Goldfisch);
    }

    @Override
    public List<String> getCropInformation() {
        return Collections.singletonList("Screams.");
    }

    @Override
    public void tick(ICropTile crop) {
        if (ConfigValues.ILoveScreaming) {
            if (MyRandom.intrandom(512, 0) == 42) {
                if (crop.getSize() != this.maxSize()) crop.getWorld().playSoundEffect(
                        crop.getLocation().posX,
                        crop.getLocation().posY,
                        crop.getLocation().posZ,
                        "mob.ghast.scream",
                        crop.getSize(),
                        (maxSize() + 1) + (-1) * crop.getSize());
                else if (crop.getSize() == this.maxSize()) crop.getWorld().playSoundEffect(
                        crop.getLocation().posX,
                        crop.getLocation().posY,
                        crop.getLocation().posZ,
                        "mob.ghast.scream",
                        crop.getSize(),
                        (maxSize() + 1) + (-1) * crop.getSize());
            }
        }
    }
}
