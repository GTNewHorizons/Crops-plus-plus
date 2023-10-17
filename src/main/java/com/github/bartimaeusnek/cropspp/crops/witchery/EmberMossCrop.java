package com.github.bartimaeusnek.cropspp.crops.witchery;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.abstracts.BasicWitcheryCrop;

import ic2.api.crops.ICropTile;

public class EmberMossCrop extends BasicWitcheryCrop {

    private static final String cropOreName = "cropEmberMoss";

    public EmberMossCrop() {
        super();
        OreDict.BSget(cropOreName, this);
    }

    @Override
    public int tier() {
        return 7;
    }

    @Override
    public String name() {
        return "Ember Moss";
    }

    @Override
    public String[] attributes() {
        return new String[] { "Fire", "Ingredient", "Bad", "Climbable" };
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
