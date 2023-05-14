package com.github.bartimaeusnek.cropspp.crops.cpp;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.abstracts.BasicBerryCrop;

import ic2.api.crops.ICropTile;

public class HuckleberryCrop extends BasicBerryCrop {

    public HuckleberryCrop() {
        super();
        OreDict.BSget("crop" + this.name(), this);
    }

    public String name() {
        return "Huckleberry";
    }

    public String[] attributes() {
        return new String[] { "Berry", "Food", "Purple", "Leaves" }; // Purple like CropVenomilia, Leaves like CropFerru
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
