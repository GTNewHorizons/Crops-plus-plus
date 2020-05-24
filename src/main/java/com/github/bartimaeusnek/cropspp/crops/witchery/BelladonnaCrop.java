package com.github.bartimaeusnek.cropspp.crops.witchery;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.abstracts.BasicWitcheryCrop;
import ic2.api.crops.ICropTile;
import net.minecraft.item.ItemStack;

public class BelladonnaCrop extends BasicWitcheryCrop {

    public BelladonnaCrop() {
        super();
        OreDict.BSget("seed" + name(), this);
    }

    @Override
    public String name() {
        return "Belladonna";
    }

    @Override
    public String[] attributes() {
        return new String[]{"Purple", "Flower", "Toxic", "Ingredient"};
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        return OreDict.ISget("item" + name());
    }

    @Override
    public ItemStack getDisplayItem() {
        return OreDict.ISget("item" + this.name());
    }
}
