package com.github.bartimaeusnek.cropspp.crops.TConstruct;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.cropspp.abstracts.BasicTinkerBerryCrop;

import tconstruct.world.TinkerWorld;

public class AluminiumOreBerryCrop extends BasicTConstructOreBerryCrop {

    public AluminiumOreBerryCrop() {
        super();
    }

    @Override
    public String name() {
        return "Aluminium " + BasicTinkerBerryCrop.OBname();
    }

    @Override
    public String[] attributes() {
        return new String[] { "OreBerry", "Aluminium", "Metal", "Aluminum" };
    }

    @Override
    protected String hasBlock() {
        return "blockAluminium";
    }

    @Override
    public ItemStack getDropItem(int amount) {
        return new ItemStack(TinkerWorld.oreBerries, amount, 4);
    }

}
