package com.github.bartimaeusnek.cropspp.crops.cpp.trees;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.github.bartimaeusnek.cropspp.ConfigValues;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.crops.ICropTile;
import ic2.core.Ic2Items;

public class BasicBonsaiCrop extends ic2.api.crops.CropCard {

    private String name;
    private ItemStack drop;
    private String ICNNAME;

    public BasicBonsaiCrop(String name, String ICNNAME, ItemStack drop) {
        super();
        this.name = name + " Wood";
        this.name = this.name.replace("Sapling", "");
        this.drop = drop;
        this.ICNNAME = ICNNAME;
    }

    @Override
    public String owner() {
        return "berriespp";
    }

    @Override
    public float dropGainChance() {
        return (float) ((Math.pow(0.95, (float) tier())) * ConfigValues.BerryGain);
    }

    @Override
    public String name() {
        if (!name.contains("Rubber")) return name.replace(" Wood", "Bonsai");
        else return "Rubber Wood Bonsai";
    }

    @Override
    public int stat(int n) {
        switch (n) {
            case 0:
                return 0; // not chemical
            case 1:
                return 0; // not edible
            case 2:
                return 0; // no defensive properties
            case 3:
                return 1; // a bit colorful
            case 4:
                return 0; // not particularly weed-like
            default:
                return 0;
        }
    }

    @Override
    public int tier() {
        // TODO Auto-generated method stub
        return 1;
    }

    @Override
    public String[] attributes() {
        return new String[] { "Tree", "Bonsai", "Leavy" };
    }

    @Override
    public String discoveredBy() {
        return "bartimaeusnek";
    }

    @SideOnly(Side.CLIENT)
    public void registerSprites(IIconRegister iconRegister) {
        textures = new IIcon[maxSize()];
        for (int i = 0; i <= textures.length - 1; i++) textures[i] = iconRegister.registerIcon(ICNNAME);
        textures[2] = iconRegister.registerIcon(drop.getIconIndex().getIconName());
    }

    @Override
    public int maxSize() {
        // TODO Auto-generated method stub
        return 3;
    }

    @Override
    public boolean canGrow(ICropTile crop) {
        // TODO Auto-generated method stub
        return crop.getSize() < 3;
    }

    @Override
    public int getOptimalHavestSize(ICropTile crop) {
        // TODO Auto-generated method stub
        return 3;
    }

    @Override
    public boolean canBeHarvested(ICropTile crop) {
        // TODO Auto-generated method stub
        return crop.getSize() == 3;
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        if (name().equals("Rubber Wood Bonsai")) return new ItemStack(Ic2Items.rubberWood.getItem(), 1);
        return drop;
    }
}
