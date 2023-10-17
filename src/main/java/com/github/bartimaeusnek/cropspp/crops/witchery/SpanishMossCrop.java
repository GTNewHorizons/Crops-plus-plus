package com.github.bartimaeusnek.cropspp.crops.witchery;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.crops.BoP.FloweringVinesCrop;

import ic2.api.crops.ICropTile;

public class SpanishMossCrop extends FloweringVinesCrop {

    private static final String cropOreName = "cropSpanishMoss";

    public SpanishMossCrop() {
        super();
        OreDict.BSget(cropOreName, this);
    }

    @Override
    public int tier() {
        return 7;
    }

    @Override
    public String name() {
        return "Spanish Moss";
    }

    @Override
    public String[] attributes() {
        return new String[] { "Green", "Climbable", "Magic" };
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
