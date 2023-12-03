package com.github.bartimaeusnek.cropspp.crops.witchery;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.abstracts.BasicWitcheryCrop;

import ic2.api.crops.ICropTile;

public class BelladonnaCrop extends BasicWitcheryCrop {

    private static final String seedOreName = "seedBelladonna";
    private static final String cropOreName = "itemBelladonna";

    public BelladonnaCrop() {
        super();
        OreDict.BSget(seedOreName, this);
    }

    @Override
    public String name() {
        return "Belladonna";
    }

    @Override
    public String[] attributes() {
        return new String[] { "Purple", "Flower", "Toxic", "Ingredient" };
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
