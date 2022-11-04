package com.github.bartimaeusnek.cropspp.crops.witchery;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.abstracts.BasicDecorationCrop;
import ic2.api.crops.ICropTile;
import net.minecraft.item.ItemStack;

public class GarlicCrop extends BasicDecorationCrop {
    public GarlicCrop() {
        super();
        OreDict.BSget("crop" + name(), this);
    }

    @Override
    public String name() {
        return "Garlic";
    }

    @Override
    public int tier() {
        return super.tier() + 2;
    }

    @Override
    public String[] attributes() {
        return new String[] {"Food", "Ingredient", "Healing"};
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        return CCropUtility.getCopiedOreStack("crop" + name());
    }

    @Override
    public ItemStack getDisplayItem() {
        return OreDict.ISget("crop" + this.name());
    }
}
