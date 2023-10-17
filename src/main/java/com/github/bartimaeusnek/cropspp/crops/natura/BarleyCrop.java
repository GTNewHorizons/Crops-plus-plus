package com.github.bartimaeusnek.cropspp.crops.natura;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.abstracts.BasicFoodCrop;

import ic2.api.crops.ICropTile;

public class BarleyCrop extends BasicFoodCrop {

    private static final String cropOreName = "cropBarley";

    public BarleyCrop() {
        super();
        OreDict.BSget(cropOreName, this);
    }

    @Override
    public int tier() {
        return 2;
    }

    @Override
    public String name() {
        return "Barley";
    }

    @Override
    public String[] attributes() {
        return new String[] { "Green", "Food", "Wheat" };
    }

    @Override
    public int maxSize() {
        return 4;
    }

    @Override
    public boolean canBeHarvested(ICropTile crop) {
        return crop.getSize() >= this.maxSize();
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
