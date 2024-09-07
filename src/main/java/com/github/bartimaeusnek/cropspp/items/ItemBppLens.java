package com.github.bartimaeusnek.cropspp.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.crop.TileEntityCrop;

public class ItemBppLens extends Item {

    public ItemBppLens() {
        super();
        this.setMaxDamage(0);
        this.setCreativeTab(CreativeTab.cpp);
        this.setUnlocalizedName("cppLens");
        this.setMaxStackSize(1);
        this.setTextureName("bpp:itemLens");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List<String> list, boolean p_77624_4_) {
        list.add("A Simple Lens to determine Crop Species");
        list.add("Indestructible.");
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer aPlayer, World world, int x, int y, int z, int side,
            float hitX, float hitY, float hitZ) {
        if (world.isRemote) return false;
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileEntityCrop) {
            if (aPlayer != null && ((TileEntityCrop) te).getCrop() != null
                    && ((TileEntityCrop) te).getCrop().name() != null)
                aPlayer.addChatComponentMessage(
                        new ChatComponentText("This is a " + ((TileEntityCrop) te).getCrop().name()));
            else return false;
            if (((TileEntityCrop) te).getScanLevel() < 1) {
                ((TileEntityCrop) te).setScanLevel((byte) 1);
                te.markDirty();
            }
            return true;
        }
        return false;
    }
}
