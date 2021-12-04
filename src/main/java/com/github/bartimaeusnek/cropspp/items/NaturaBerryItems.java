package com.github.bartimaeusnek.cropspp.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;


/*
 * code by progwml6, Natura
 * modified by lokko12
 *
 *
 *
 *
 */

public class NaturaBerryItems extends ItemFood {
    public IIcon[] icons;

    public String[] textureNames = new String[]{"rasp", "blue", "black", "geo", "cactus"};

    public NaturaBerryItems(int heal) {
        super(heal, 0.4F, false);
        setHasSubtypes(true);
        setMaxDamage(0);
        this.setMaxStackSize(64);
        this.setCreativeTab(CreativeTab.cpp);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer player) {
        if (player.canEat(true) && player.getFoodStats().getSaturationLevel() < 18F) {
            player.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        }

        return par1ItemStack;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemstack) {
        return 16;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int meta) {
        if (meta < textureNames.length)
            return icons[meta];
        else
            return null;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.icons = new IIcon[textureNames.length];

        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = iconRegister.registerIcon("bpp:berry_" + textureNames[i]);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        return (new StringBuilder()).append("item.berry.").append(textureNames[itemstack.getItemDamage()]).toString();
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int var4 = 0; var4 < this.icons.length; ++var4) {
            par3List.add(new ItemStack(par1, 1, var4));
        }
    }
}