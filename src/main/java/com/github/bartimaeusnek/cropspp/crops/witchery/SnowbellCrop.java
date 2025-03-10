package com.github.bartimaeusnek.cropspp.crops.witchery;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.abstracts.BasicWitcheryCrop;

import ic2.api.crops.ICropTile;

public class SnowbellCrop extends BasicWitcheryCrop {

    private static final String seedOreName = "seedSnowbell";
    private static final String cropOreName = "itemSnowbell";

    public SnowbellCrop() {
        super();
        OreDict.BSget(seedOreName, this);
    }

    @Override
    public String name() {
        return "Snowbell";
    }

    @Override
    public String[] attributes() {
        return new String[] { "White", "Flower", "Ice", "Toxic", "Ingredient" };
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
