package com.github.bartimaeusnek.cropspp.crops.BoP;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.abstracts.BasicDecorationCrop;

import biomesoplenty.api.content.BOPCBlocks;
import ic2.api.crops.ICropTile;

public class EyebulbCrop extends BasicDecorationCrop {

    public EyebulbCrop() {
        super();
        OreDict.BSget("cropEyebulb", this);
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemStack(BOPCBlocks.flowers, 1, 13);
    }

    @Override
    public String name() {
        return "Eyebulb";
    }

    @Override
    public String[] attributes() {
        return new String[] { "Nether", "Evil", "Bad" };
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        return new ItemStack(BOPCBlocks.flowers, 1, 13);
    }
}
