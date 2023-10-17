package com.github.bartimaeusnek.cropspp.crops.cpp;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.abstracts.BasicBerryCrop;

import ic2.api.crops.ICropTile;

public class HuckleberryCrop extends BasicBerryCrop {

    private static final String cropOreName = "cropHuckleberry";

    public HuckleberryCrop() {
        super();
        OreDict.BSget(cropOreName, this);
    }

    @Override
    public String name() {
        return "Huckleberry";
    }

    @Override
    public String discoveredBy() {
        return "Ancient cultures";
    }

    @Override
    public String[] attributes() {
        return new String[] { "Berry", "Food", "Purple", "Leaves" }; // Purple like CropVenomilia, Leaves like CropFerru
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
