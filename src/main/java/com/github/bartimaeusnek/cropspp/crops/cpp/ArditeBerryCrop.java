package com.github.bartimaeusnek.cropspp.crops.cpp;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.abstracts.BasicTinkerBerryCrop;

import ic2.api.crops.ICropTile;

public class ArditeBerryCrop extends BasicTinkerBerryCrop {

    public ArditeBerryCrop() {
        super();
    }

    @Override
    public String name() {
        return "Ardite Berry";
    }

    @Override
    public int tier() {
        return 7;
    }

    @Override
    protected String hasBlock() {
        return "blockArdite";
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        if ((crop.isBlockBelow("blockArdite") || ConfigValues.debug)
                && OreDictionary.getOres("nuggetArdite").size() != 0) {
            return OreDictionary.getOres("nuggetArdite").get(OreDictionary.getOres("nuggetArdite").size() - 1);
        } else return null;
    }

    @Override
    public int growthDuration(ICropTile crop) {
        if (ConfigValues.debug) return 1;
        return crop.getSize() >= 2 ? 3000 : 500;
    }

    @Override
    public String[] attributes() {
        return new String[] { "OreBerry", "Ardite", "Metal", "Orange" };
    }

    @Override
    public ItemStack getDisplayItem() {
        return OreDict.ISget("nuggetArdite");
    }
}
