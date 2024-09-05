package com.github.bartimaeusnek.cropspp.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import ic2.core.IC2;
import ic2.core.crop.TileEntityCrop;

public class Weedmaker extends Item {

    public Weedmaker() {
        super();
        this.setUnlocalizedName("Weedmaker");
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTab.cpp);
        this.setMaxDamage(0);
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
            float hitX, float hitY, float hitZ) {
        if (!IC2.platform.isSimulating()) return false;
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileEntityCrop crop) {
            crop.setCrop(ic2.core.crop.IC2Crops.weed);
            ++crop.weedlevel;
        }
        return false;
    }
}
