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
        int r;
        if (ConfigValues.debug)
            r = 1;
            // Same growth stages as melons and pumpkins
        else if (crop.getSize() >= 2) {

            // Ripens "quickly"
            r = 3000;
        } else if (crop.getSize() == 3 && crop.isBlockBelow("blockAluminium"))
            r = 1500;
        else {
            // Takes a while to grow from seed
            r = 500;
        }
        return r;
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

