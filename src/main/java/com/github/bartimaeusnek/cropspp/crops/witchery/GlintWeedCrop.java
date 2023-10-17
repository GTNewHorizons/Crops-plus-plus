package com.github.bartimaeusnek.cropspp.crops.witchery;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.abstracts.BasicWitcheryCrop;

import ic2.api.crops.ICropTile;

public class GlintWeedCrop extends BasicWitcheryCrop {

    private static final String cropOreName = "cropGlintWeed";

    public GlintWeedCrop() {
        super();
        OreDict.BSget(cropOreName, this);
    }

    @Override
    public String name() {
        return "Glint Weed";
    }

    @Override
    public String[] attributes() {
        return new String[] { "Orange", "Flower", "Magic" };
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
