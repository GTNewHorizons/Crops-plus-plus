package com.github.bartimaeusnek.cropspp.crops.TConstruct;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.cropspp.abstracts.BasicTinkerBerryCrop;

import tconstruct.world.TinkerWorld;

public class IronOreBerryCrop extends BasicTConstructOreBerryCrop {

    public IronOreBerryCrop() {
        super();
    }

    @Override
    public String name() {
        return "Iron " + BasicTinkerBerryCrop.OBname();
    }

    @Override
    public String[] attributes() {
        return new String[] { "OreBerry", "Gray", "Metal" };
    }

    @Override
    protected String hasBlock() {
        return "blockIron";
    }

    @Override
    public ItemStack getDropItem(int amount) {
        return new ItemStack(TinkerWorld.oreBerries, amount, 0);
    }

}
