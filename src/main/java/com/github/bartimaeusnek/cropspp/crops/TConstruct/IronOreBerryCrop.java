package com.github.bartimaeusnek.cropspp.crops.TConstruct;

import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.abstracts.BasicTinkerBerryCrop;
import ic2.api.crops.ICropTile;
import net.minecraft.item.ItemStack;
import tconstruct.world.TinkerWorld;

public class IronOreBerryCrop extends BasicTinkerBerryCrop {

    public IronOreBerryCrop() {
        super();
    }

    @Override
    public String name() {
        return "Iron " + BasicTinkerBerryCrop.OBname();
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        if (crop.getSize() == 4 && crop.isBlockBelow("blockIron")) {
            return new ItemStack(TinkerWorld.oreBerries, 6, 0);
        } else return new ItemStack(TinkerWorld.oreBerries, 2, 0);
    }

    @Override
    protected String hasBlock() {
        return "blockIron";
    }

    @Override
    public int growthDuration(ICropTile crop) {
        if (ConfigValues.debug) return 1;
        return crop.getSize() >= 2 ? 3000 : 500;
    }

    @Override
    public String[] attributes() {
        return new String[] {"OreBerry", "Gray", "Metal"};
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemStack(TinkerWorld.oreBerries, 6, 0);
    }
}
