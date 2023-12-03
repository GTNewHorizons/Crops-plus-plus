package com.github.bartimaeusnek.cropspp.crops.TConstruct;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.cropspp.abstracts.BasicTinkerBerryCrop;

import tconstruct.world.TinkerWorld;

public class TinOreBerryCrop extends BasicTConstructOreBerryCrop {

    public TinOreBerryCrop() {
        super();
    }

    @Override
    public int tier() {
        return 4;
    }

    @Override
    public String name() {
        return "Tin " + BasicTinkerBerryCrop.OBname();
    }

    @Override
    public String[] attributes() {
        return new String[] { "OreBerry", "Tin", "Metal", "Shiny" };
    }

    @Override
    protected String hasBlock() {
        return "blockTin";
    }

    @Override
    public ItemStack getDropItem(int amount) {
        return new ItemStack(TinkerWorld.oreBerries, amount, 3);
    }

}
