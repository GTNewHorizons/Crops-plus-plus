package com.github.bartimaeusnek.cropspp.crops.BoP;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.abstracts.BasicFoodCrop;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.crops.ICropTile;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class WildCarrotsCrop extends BasicFoodCrop {
    public WildCarrotsCrop() {
        super();
        OreDict.BSget("cropWildcarrots", this);
        OreDict.BSget("seedWildcarrots", this);
    }

    @Override
    public String name() {
        return "Wild Carrots";
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        return CCropUtility.getCopiedOreStack("cropWildcarrots");
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
    public String[] attributes() {
        return new String[]{"Food", "White", "Carrots"};
    }

    @Override
    public ItemStack getDisplayItem() {
        return OreDict.ISget("cropWildcarrots");
    }
}
