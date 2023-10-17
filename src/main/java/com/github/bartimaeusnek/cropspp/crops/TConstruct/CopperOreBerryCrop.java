package com.github.bartimaeusnek.cropspp.crops.TConstruct;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.cropspp.abstracts.BasicTinkerBerryCrop;

import tconstruct.world.TinkerWorld;

public class CopperOreBerryCrop extends BasicTConstructOreBerryCrop {

    public CopperOreBerryCrop() {
        super();
    }

    @Override
    public String name() {
        return "Copper " + BasicTinkerBerryCrop.OBname();
    }

    @Override
    public String[] attributes() {
        return new String[] { "OreBerry", "Copper", "Metal", "Shiny" };
    }

    @Override
    protected String hasBlock() {
        return "blockCopper";
    }

    @Override
    public ItemStack getDropItem(int amount) {
        return new ItemStack(TinkerWorld.oreBerries, amount, 2);
    }

}
