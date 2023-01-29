package com.github.bartimaeusnek.cropspp.crops.witchery;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.abstracts.BasicWitcheryCrop;
import ic2.api.crops.ICropTile;

public class WaterArtichokeCrop extends BasicWitcheryCrop {

    public WaterArtichokeCrop() {
        super();
        OreDict.BSget("cropArtichoke", this);
    }

    @Override
    public String name() {
        return "Artichoke";
    }

    @Override
    public String[] attributes() {
        return new String[] { "Flower", "Water", "Blue", "Ingredient" };
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        return CCropUtility.getCopiedOreStack("cropArtichoke");
    }

    @Override
    public ItemStack getDisplayItem() {
        return OreDict.ISget("cropArtichoke");
    }
}
