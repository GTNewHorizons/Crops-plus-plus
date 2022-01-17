package com.github.bartimaeusnek.cropspp.crops.TConstruct;

import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.abstracts.BasicTinkerBerryCrop;
import ic2.api.crops.ICropTile;
import net.minecraft.item.ItemStack;
import tconstruct.world.TinkerWorld;

public class AluminiumOreBerryCrop extends BasicTinkerBerryCrop {


    public AluminiumOreBerryCrop() {
        super();
    }

    @Override
    public String name() {
        return "Aluminium " + BasicTinkerBerryCrop.OBname();
    }

    @Override
    protected String hasBlock() {
        return "blockAluminium";
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        if (crop.getSize() == 4 && crop.isBlockBelow("blockAluminium")) {
            return new ItemStack(TinkerWorld.oreBerries, 6, 4);
        } else return new ItemStack(TinkerWorld.oreBerries, 2, 4);
    }

    @Override
    public int growthDuration(ICropTile crop) {
        if (ConfigValues.debug) return 1;
        return crop.getSize() >= 2 ? 3000 : 500;
        }

    @Override
    public String[] attributes() {
        return new String[]{"OreBerry", "Aluminium", "Metal", "Aluminum"};
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemStack(TinkerWorld.oreBerries, 6, 4);
    }

}

