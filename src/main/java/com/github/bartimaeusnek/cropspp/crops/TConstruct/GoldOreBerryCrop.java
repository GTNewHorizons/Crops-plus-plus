package com.github.bartimaeusnek.cropspp.crops.TConstruct;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.cropspp.abstracts.BasicTinkerBerryCrop;

import tconstruct.world.TinkerWorld;

public class GoldOreBerryCrop extends BasicTConstructOreBerryCrop {

    public GoldOreBerryCrop() {
        super();
    }

    @Override
    public String name() {
        return "Gold " + BasicTinkerBerryCrop.OBname();
    }

    @Override
    public String[] attributes() {
        return new String[] { "OreBerry", "Gold", "Metal" };
    }

    @Override
    protected String hasBlock() {
        return "blockGold";
    }

    @Override
    public ItemStack getDropItem(int amount) {
        return new ItemStack(TinkerWorld.oreBerries, amount, 1);
    }

}
