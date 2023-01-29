package com.github.bartimaeusnek.cropspp.crops.BoP;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.abstracts.BasicFoodCrop;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.crops.ICropTile;

public class TurnipCrop extends BasicFoodCrop {

    public TurnipCrop() {
        super();
        OreDict.BSget("crop" + this.name(), this);
        OreDict.BSget("seed" + this.name(), this);
    }

    @Override
    public String name() {
        return "Turnip";
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        return CCropUtility.getCopiedOreStack("crop" + this.name());
    }

    @Override
    public String[] attributes() {
        return new String[] { "Food", "Purple", "Carrots" };
    }

    @SideOnly(Side.CLIENT)
    public void registerSprites(IIconRegister iconRegister) {
        textures = new IIcon[maxSize()];

        for (int i = 1; i <= textures.length - 1; i++) {
            // ic2:crop/blockCrop.NAME.n is the legacy name for backwards compatiblity
            textures[i - 1] = iconRegister.registerIcon("ic2:crop/carrots." + i);
        }
        textures[2] = iconRegister.registerIcon("bpp:crop/blockCrop.Turnip.3");
    }

    @Override
    public ItemStack getDisplayItem() {
        return OreDict.ISget("crop" + this.name());
    }
}
