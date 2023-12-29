package com.github.bartimaeusnek.cropspp.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Goldfish extends ItemFood {

    public Goldfish() {
        super(1, 2, false);
        this.setCreativeTab(CreativeTab.cpp);
        this.setUnlocalizedName("foodGoldfish");
        this.setTextureName("bpp:foodGoldfish");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4) {
        list.add("Hozuki no Reitetsu! A truely hellish planimal?!");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
            float hitX, float hitY, float hitZ) {
        player.playSound("mob.ghast.scream", 5, (float) 0.5);
        return true;
    }

    @Override
    public ItemStack onEaten(ItemStack p_77654_1_, World p_77654_2_, EntityPlayer p_77654_3_) {
        p_77654_3_.playSound("mob.ghast.scream", 5, (float) 0.5);
        return super.onEaten(p_77654_1_, p_77654_2_, p_77654_3_);
    }
}
