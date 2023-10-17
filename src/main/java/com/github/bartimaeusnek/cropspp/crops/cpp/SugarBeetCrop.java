package com.github.bartimaeusnek.cropspp.crops.cpp;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.github.bartimaeusnek.cropspp.abstracts.BasicFoodCrop;
import com.github.bartimaeusnek.cropspp.items.CppItems;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.crops.ICropTile;

public class SugarBeetCrop extends BasicFoodCrop {

    public SugarBeetCrop() {
        super();
    }

    @Override
    public int tier() {
        return 4;
    }

    @Override
    public String name() {
        return "Sugar Beet";
    }

    @Override
    public String[] attributes() {
        return new String[] { "Food", "White", "Ingredient" };
    }

    @Override
    public ItemStack getGain(ICropTile var1) {
        return new ItemStack(CppItems.CppBerries, 1, 1);
    }

    @SideOnly(Side.CLIENT)
    public void registerSprites(IIconRegister iconRegister) {
        textures = new IIcon[maxSize()];

        for (int i = 1; i <= textures.length - 1; i++) {
            // ic2:crop/blockCrop.NAME.n is the legacy name for backwards compatiblity
            textures[i - 1] = iconRegister.registerIcon("ic2:crop/carrots." + i);
        }
        textures[2] = iconRegister.registerIcon("bpp:crop/blockCrop.Wild Carrots.3");
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemStack(CppItems.CppBerries, 1, 1);
    }
}
