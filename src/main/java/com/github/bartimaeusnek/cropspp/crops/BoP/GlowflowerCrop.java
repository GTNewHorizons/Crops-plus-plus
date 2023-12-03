package com.github.bartimaeusnek.cropspp.crops.BoP;

import java.util.Arrays;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.cropspp.abstracts.BasicDecorationCrop;

import biomesoplenty.api.content.BOPCBlocks;
import ic2.api.crops.ICropTile;

public class GlowflowerCrop extends BasicDecorationCrop {

    public GlowflowerCrop() {
        super();
    }

    @Override
    public int tier() {
        return super.tier() + 2;
    }

    @Override
    public String name() {
        return "Glowflower";
    }

    @Override
    public String[] attributes() {
        return new String[] { "Nether", "Light", "Shiny" };
    }

    @Override
    public int getEmittedLight(ICropTile crop) {
        if (crop.getSize() >= this.maxSize()) return 7;
        else return 0;
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        // glow stone below doubles the yield
        return new ItemStack(BOPCBlocks.flowers, crop.isBlockBelow(Blocks.glowstone) ? 2 : 1, 3);
    }

    @Override
    public List<String> getCropInformation() {
        return Arrays.asList("Needs a block of Glowstone below to increase yield", "Emits light when fully grown.");
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemStack(BOPCBlocks.flowers, 1, 3);
    }
}
