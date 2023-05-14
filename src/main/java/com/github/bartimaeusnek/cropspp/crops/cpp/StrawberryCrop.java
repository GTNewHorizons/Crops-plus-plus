package com.github.bartimaeusnek.cropspp.crops.cpp;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.abstracts.BasicBerryCrop;

import ic2.api.crops.ICropTile;

public class StrawberryCrop extends BasicBerryCrop {

    public StrawberryCrop() {
        super();
        OreDict.BSget("crop" + this.name(), this);
    }

    public String name() {
        return "Strawberry";
    }

    public String[] attributes() {
        return new String[] { "Berry", "Food", "Red" }; // Red like CropRedWheat, CropNetherWart
    }

    public String discoveredBy() {
        return "Ancient cultures";
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        return CCropUtility.getCopiedOreStack("crop" + this.name());
    }

    @Override
    public ItemStack getDisplayItem() {
        return OreDict.ISget("crop" + this.name());
    }
}
