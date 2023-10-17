package com.github.bartimaeusnek.cropspp.crops.witchery;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.abstracts.BasicWitcheryCrop;

import ic2.api.crops.ICropTile;

public class WolfsBaneCrop extends BasicWitcheryCrop {

    private final static String seedOreName = "seedWolfsBane";
    private static final String cropOreName = "itemWolfsBane";

    public WolfsBaneCrop() {
        super();
        OreDict.BSget(seedOreName, this);
    }

    @Override
    public String name() {
        return "Wolf's Bane";
    }

    @Override
    public String[] attributes() {
        return new String[] { "Flower", "Toxic", "Purple", "Ingredient" };
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
