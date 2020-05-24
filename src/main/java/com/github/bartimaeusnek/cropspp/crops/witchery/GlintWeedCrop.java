package com.github.bartimaeusnek.cropspp.crops.witchery;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.abstracts.BasicWitcheryCrop;
import ic2.api.crops.ICropTile;
import net.minecraft.item.ItemStack;

public class GlintWeedCrop extends BasicWitcheryCrop {
    public GlintWeedCrop() {
        super();
        OreDict.BSget("crop" + name().replaceAll(" ", ""), this);
    }

    @Override
    public String name() {
        return "Glint Weed";
    }

    @Override
    public String[] attributes() {
        return new String[]{"Orange", "Flower", "Magic"};
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        return OreDict.ISget("crop" + name().replaceAll(" ", ""));
    }

    @Override
    public ItemStack getDisplayItem() {
        return OreDict.ISget("crop" + this.name().replaceAll(" ", ""));
    }
}
