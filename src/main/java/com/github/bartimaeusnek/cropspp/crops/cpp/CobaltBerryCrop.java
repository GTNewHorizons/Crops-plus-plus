package com.github.bartimaeusnek.cropspp.crops.cpp;

import com.github.bartimaeusnek.cropspp.CCropUtility;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.abstracts.BasicTinkerBerryCrop;

import ic2.api.crops.ICropTile;

public class CobaltBerryCrop extends BasicTinkerBerryCrop {

    public CobaltBerryCrop() {
        super();
    }

    @Override
    public String name() {
        return "Cobalt Berry";
    }

    @Override
    public int tier() {
        return 7;
    }

    @Override
    protected String hasBlock() {
        return "blockCobalt";
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        return CCropUtility.getCopiedOreStack("nuggetArdite");
    }

    @Override
    public int growthDuration(ICropTile crop) {
        if (ConfigValues.debug) return 1;
        return crop.getSize() >= 2 ? 3000 : 500;
    }

    @Override
    public String[] attributes() {
        return new String[] { "OreBerry", "Cobalt", "Metal", "Blue" };
    }

    @Override
    public ItemStack getDisplayItem() {
        return OreDict.ISget("nuggetCobalt");
    }
}
