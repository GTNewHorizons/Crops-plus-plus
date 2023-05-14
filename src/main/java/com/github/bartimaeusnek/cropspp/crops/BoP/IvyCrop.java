package com.github.bartimaeusnek.cropspp.crops.BoP;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.cropspp.crops.cpp.VineCrop;

import biomesoplenty.api.content.BOPCBlocks;
import ic2.api.crops.ICropTile;

public class IvyCrop extends VineCrop {

    public IvyCrop() {
        super();
    }

    @Override
    public int tier() {
        return 2;
    }

    @Override
    public boolean canGrow(ICropTile crop) {
        return crop.getSize() < 3;
    }

    @Override
    public int maxSize() {
        return 3;
    }

    @Override
    public String name() {
        return "Ivy";
    }

    @Override
    public String discoveredBy() {
        return "bartimaeusnek";
    }

    @Override
    public String[] attributes() {
        return new String[] { "Green", "Tendrilly", "Flower", "Bad", "Poison" };
    }

    @Override
    public boolean canBeHarvested(ICropTile crop) {
        return crop.getSize() == 3;
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        return new ItemStack(BOPCBlocks.ivy, 2, 0);
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemStack(BOPCBlocks.ivy, 2, 0);
    }
}
