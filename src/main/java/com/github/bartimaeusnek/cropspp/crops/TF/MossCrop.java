package com.github.bartimaeusnek.cropspp.crops.TF;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import twilightforest.block.TFBlocks;
import biomesoplenty.api.content.BOPCBlocks;

import com.github.bartimaeusnek.croploadcore.MyRandom;
import com.github.bartimaeusnek.cropspp.abstracts.BasicDecorationCrop;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.crops.ICropTile;

public class MossCrop extends BasicDecorationCrop {

    @Override
    public String name() {
        return "Moss";
    }

    @Override
    public int tier() {
        return 4;
    }

    @Override
    public String[] attributes() {
        return new String[] { "Green", "Climbable" };
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        byte r = (byte) MyRandom.intrandom(0, 100);
        if (r < 5) {
            return new ItemStack(TFBlocks.plant, 1, 3);
        } else if (r > 5 && r < 35) {
            return new ItemStack(BOPCBlocks.moss, 1, 0);
        } else return new ItemStack(BOPCBlocks.treeMoss, 1, 0);
    }

    @SideOnly(Side.CLIENT)
    public void registerSprites(IIconRegister iconRegister) {
        textures = new IIcon[maxSize()];

        for (int i = 1; i <= textures.length; i++) {
            // ic2:crop/blockCrop.NAME.n is the legacy name for backwards compatiblity
            textures[i - 1] = iconRegister.registerIcon("bpp:crop/blockCrop.Spanish Moss." + i);
        }
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemStack(BOPCBlocks.treeMoss, 1, 0);
    }
}
