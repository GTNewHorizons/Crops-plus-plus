package com.github.bartimaeusnek.cropspp.crops.TConstruct;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.abstracts.BasicTinkerBerryCrop;

import ic2.api.crops.ICropTile;

public abstract class BasicTConstructOreBerryCrop extends BasicTinkerBerryCrop {

    public BasicTConstructOreBerryCrop() {
        super();
    }

    public abstract ItemStack getDropItem(int count);

    @Override
    public int growthDuration(ICropTile crop) {
        if (ConfigValues.debug) return 1;
        return crop.getSize() >= this.maxSize() - 2 ? 3000 : 500;
    }

    public boolean isBlockBelow(ICropTile crop) {
        return crop.isBlockBelow(hasBlock());
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        return getDropItem(crop.getSize() >= this.maxSize() && this.isBlockBelow(crop) ? 6 : 2);
    }

    @Override
    public ItemStack getDisplayItem() {
        return getDropItem(6);
    }

}
