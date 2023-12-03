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

    // be DRY, not WET
    private static final String screamSoundId = "mob.ghast.scream";

    private boolean ShouldInteract(ICropTile crop, EntityPlayer player) {
        if (!ConfigValues.ILoveScreaming) return true;
        player.playSound(screamSoundId, getScreamVolume(crop), getScreamPitch(crop));
        return true;
    }

    private float getScreamVolume(ICropTile crop) {
        return crop.getSize() >= this.maxSize() ? 5.0f : crop.getSize();
    }

    private float getScreamPitch(ICropTile crop) {
        return crop.getSize() >= this.maxSize() ? 0.5f : (this.maxSize() + 1) + (-1) * crop.getSize();
    }

    public GoldfishCrop() {
        super();
    }

    @Override
    public int tier() {
        return 4;
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
    public ItemStack getGain(ICropTile crop) {
        return new ItemStack(CppItems.Goldfisch);
    }

    @Override
    public void tick(ICropTile crop) {
        if (ConfigValues.ILoveScreaming) {
            if (MyRandom.intrandom(512, 0) == 42) {
                crop.getWorld().playSoundEffect(
                        crop.getLocation().posX,
                        crop.getLocation().posY,
                        crop.getLocation().posZ,
                        screamSoundId,
                        this.getScreamVolume(crop),
                        this.getScreamPitch(crop));
            }
        }
    }

    @Override
    public boolean rightclick(ICropTile crop, EntityPlayer player) {
        return ShouldInteract(crop, player) && crop.harvest(true);
    }

    @Override
    public boolean leftclick(ICropTile crop, EntityPlayer player) {
        return ShouldInteract(crop, player) && crop.pick(true);
    }

    @Override
    public boolean onEntityCollision(ICropTile crop, Entity entity) {
        if (!ConfigValues.ILoveScreaming) return super.onEntityCollision(crop, entity);

        if (entity instanceof EntityLivingBase) {
            if (entity instanceof EntityPlayer) {
                entity.playSound(screamSoundId, this.getScreamVolume(crop), this.getScreamPitch(crop));
            }
            return entity.isSprinting();
        }
        return false;
    }

    @Override
    public List<String> getCropInformation() {
        return Collections.singletonList("Screams.");
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemStack(CppItems.Goldfisch);
    }

}
