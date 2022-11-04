package com.github.bartimaeusnek.cropspp.crops.witchery;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.abstracts.BasicWitcheryCrop;
import ic2.api.crops.ICropTile;
import net.minecraft.item.ItemStack;

public class SnowbellCrop extends BasicWitcheryCrop {
    public SnowbellCrop() {
        super();
        OreDict.BSget("seed" + name(), this);
    }

    @Override
    public String name() {
        return "Snowbell";
    }

    @Override
    public String[] attributes() {
        return new String[]{"White", "Flower", "Ice", "Toxic", "Ingredient"};
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        return CCropUtility.getCopiedOreStack("item" + name());
    }

    @Override
    public ItemStack getDisplayItem() {
        return OreDict.ISget("item" + this.name());
    }
}
