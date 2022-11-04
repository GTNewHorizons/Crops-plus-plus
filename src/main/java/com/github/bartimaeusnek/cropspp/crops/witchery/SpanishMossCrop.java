package com.github.bartimaeusnek.cropspp.crops.witchery;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.crops.BoP.FloweringVinesCrop;
import ic2.api.crops.ICropTile;
import net.minecraft.item.ItemStack;

public class SpanishMossCrop extends FloweringVinesCrop {

    public SpanishMossCrop() {
        super();
        OreDict.BSget("crop" + name().replaceAll(" ", ""), this);
    }

    @Override
    public String name() {
        return "Spanish Moss";
    }

    @Override
    public int tier() {
        return 7;
    }

    @Override
    public String[] attributes() {
        return new String[]{"Green", "Climbable", "Magic"};
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        return CCropUtility.getCopiedOreStack("crop" + name().replaceAll(" ", ""));
    }

    @Override
    public ItemStack getDisplayItem() {
        return OreDict.ISget("crop" + this.name().replaceAll(" ", ""));
    }
}
