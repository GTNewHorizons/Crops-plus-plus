package com.github.bartimaeusnek.cropspp.crops.witchery;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.abstracts.BasicDecorationCrop;

import ic2.api.crops.ICropTile;

public class GarlicCrop extends BasicDecorationCrop {

    private static final String cropOreName = "cropGarlic";

    public GarlicCrop() {
        super();
        OreDict.BSget(cropOreName, this);
    }

    @Override
    public int tier() {
        return super.tier() + 2;
    }

    @Override
    public String name() {
        return "Garlic";
    }

    @Override
    public String[] attributes() {
        return new String[] { "Food", "Ingredient", "Healing" };
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
