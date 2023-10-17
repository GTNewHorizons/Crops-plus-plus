package com.github.bartimaeusnek.cropspp.crops.cpp;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.abstracts.BasicBerryCrop;

import ic2.api.crops.ICropTile;

public class StrawberryCrop extends BasicBerryCrop {

    private static final String cropOreName = "cropStrawberry";

    public StrawberryCrop() {
        super();
        OreDict.BSget(cropOreName, this);
    }

    @Override
    public String name() {
        return "Strawberry";
    }

    @Override
    public String discoveredBy() {
        return "Ancient cultures";
    }

    @Override
    public String[] attributes() {
        return new String[] { "Berry", "Food", "Red" }; // Red like CropRedWheat, CropNetherWart
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        return CCropUtility.getCopiedOreStack(cropOreName);
    }

    @Override
    public ItemStack getDisplayItem() {
        return OreDict.ISget(cropOreName);
    }
}
