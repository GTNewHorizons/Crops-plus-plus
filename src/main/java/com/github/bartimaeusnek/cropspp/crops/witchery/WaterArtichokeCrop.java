package com.github.bartimaeusnek.cropspp.crops.witchery;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.abstracts.BasicWitcheryCrop;
import ic2.api.crops.ICropTile;
import net.minecraft.item.ItemStack;

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
        return new String[]{"Flower", "Water", "Blue", "Ingredient"};
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        return OreDict.ISget("cropArtichoke");
    }

    @Override
    public ItemStack getDisplayItem() {
        return OreDict.ISget("cropArtichoke");
    }

}
