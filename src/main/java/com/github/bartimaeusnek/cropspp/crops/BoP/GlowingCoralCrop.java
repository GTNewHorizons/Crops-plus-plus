package com.github.bartimaeusnek.cropspp.crops.BoP;

import java.util.Arrays;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.cropspp.abstracts.BasicDecorationCrop;

import biomesoplenty.api.content.BOPCBlocks;
import ic2.api.crops.ICropTile;

public class GlowingCoralCrop extends BasicDecorationCrop {

    public GlowingCoralCrop() {
        super();
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemStack(BOPCBlocks.coral1, 1, 15);
    }

    @Override
    public String name() {
        return "Glowing Earth Coral";
    }

    @Override
    public int tier() {
        return super.tier() + 4;
    }

    @Override
    public String[] attributes() {
        return new String[] { "Water", "Light", "Shiny" };
    }

    @Override
    public List<String> getCropInformation() {
        return Arrays.asList("Needs a block of Glowstone below to incrase yield", "Emits light.");
    }

    @Override
    public int getEmittedLight(ICropTile crop) {
        return 7;
    }

    @Override
    public boolean canGrow(ICropTile crop) {
        return crop.getSize() < 3;
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        // glow stone below doubles the yield
        return new ItemStack(BOPCBlocks.coral1, crop.isBlockBelow(Blocks.glowstone) ? 2 : 1, 15);
    }
}
