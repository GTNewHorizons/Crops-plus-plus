package com.github.bartimaeusnek.cropspp.crops.cpp;

import java.util.Arrays;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.croploadcore.MyRandom;
import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.abstracts.BasicDecorationCrop;

import ic2.api.crops.ICropTile;

public class GrassCrop extends BasicDecorationCrop {

    public GrassCrop() {
        super();
        OreDict.BSget("cropGrass", this);
    }

    @Override
    public int tier() {
        return 0;
    }

    @Override
    public int stat(int n) {
        switch (n) {
            case 0:
                return 0; // not chemical
            case 1:
                return 0; // not edible
            case 2:
                return 0; // no defensive properties
            case 3:
                return 3; // primarily decorative
            case 4:
                return 4; // weed-like
            default:
                return 0;
        }
    }

    @Override
    public String name() {
        return "Grass";
    }

    @Override
    public int getOptimalHavestSize(ICropTile crop) {
        return 1;
    }

    @Override
    public boolean isWeed(ICropTile crop) {
        return true;
    }

    @Override
    public boolean onEntityCollision(ICropTile crop, Entity entity) {
        if (crop.getSize() == 4) {
            CCropUtility.damageEntity(entity, 1);
        }
        return false;
    }

    @Override
    public boolean leftclick(ICropTile crop, EntityPlayer player) {
        return false;
    }

    @Override
    public float dropGainChance() {
        return (float) 1;
    }

    @Override
    public int maxSize() {
        return 4;
    }

    @Override
    public boolean canBeHarvested(ICropTile crop) {
        return crop.getSize() > 1;
    }

    @Override
    public String[] attributes() {
        return new String[] { "Green", "Bad" };
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        // 32 = dead bush
        if (crop.getSize() == 4) return new ItemStack(Item.getItemById(32), 1, 0);
        if (crop.getSize() == 3) {
            // 175:3 = large fern
            // 31:2 = Tall Grass (Fern)
            return MyRandom.intrandom(0, 10) == 9 ? new ItemStack(Item.getItemById(175), 1, 3) : new ItemStack(Item.getItemById(31), 1, 2);
        }
        // 175:3 = Double Tall Grass
        // 31:1 = Tall Grass
        return MyRandom.intrandom(0, 10) == 9 ? new ItemStack(Item.getItemById(175), 1, 2) : new ItemStack(Item.getItemById(31), 1, 1);
    }

    @Override
    public boolean canGrow(ICropTile crop) {
        return crop.getSize() < this.maxSize();
    }

    @Override
    public byte getSizeAfterHarvest(ICropTile crop) {
        return (byte) ((int) crop.getSize() - 1);
    }

    @Override
    public List<String> getCropInformation() {
        return Arrays.asList("Is a weed", "Hurt Player on collision, when fully grown");
    }

    @Override
    public ItemStack getDisplayItem() {
        // 31:1 Tall Grass
        return new ItemStack(Item.getItemById(31), 1, 1);
    }
}
