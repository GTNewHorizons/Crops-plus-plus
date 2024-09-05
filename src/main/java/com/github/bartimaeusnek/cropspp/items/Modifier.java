package com.github.bartimaeusnek.cropspp.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Modifier extends Item {

    public IIcon[] icons;
    public String[] textureNames = new String[] { "Space", "Magic",
            "Trophy" /* , "Anti","Growth","Gain","Resistance" */ };

    public Modifier() {
        super();
        this.setCreativeTab(CreativeTab.cpp);
        this.bFull3D = false;
        this.hasSubtypes = true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        if (meta < textureNames.length) return icons[meta];
        else return icons[0];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4) {
        switch (stack.getItemDamage() % textureNames.length) {
            case 0 -> list.add("Space Radiation caught in a Flower!");
            case 1 -> list.add("Pure Magic!");
            case 2 -> list.add("Challenge Accepted!");
            default -> {}
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.icons = new IIcon[textureNames.length];

        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = iconRegister.registerIcon("bpp:item_Modifier_" + textureNames[i]);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        if (itemstack.getItemDamage() < textureNames.length) return (new StringBuilder()).append("item_Modifier_")
                .append(textureNames[itemstack.getItemDamage()]).toString();
        else return (new StringBuilder()).append("item_Modifier_").append(textureNames[0]).toString();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List<ItemStack> list) {
        for (int i = 0; i < textureNames.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    /*
     * @Override public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z,
     * int side, float hitX, float hitY, float hitZ) { if (!IC2.platform.isSimulating()) return false; TileEntity te =
     * world.getTileEntity(x, y, z); if (te instanceof TileEntityCrop) { TileEntityCrop crop = (TileEntityCrop)te; if
     * (crop.getCrop() != null) { if (crop.getCrop() instanceof ic2.api.crops.CropCard && crop.getCrop().tier()>=1) {
     * switch (stack.getItemDamage() % textureNames.length) { case 4: if (crop.statGrowth < 31)
     * crop.statGrowth=crop.statGrowth+1; crop.updateState(); stack.stackSize=stack.stackSize-1; break; case 5: if
     * (crop.statGain < 31) crop.statGain=crop.statGain+1; crop.updateState(); stack.stackSize=stack.stackSize-1; break;
     * case 6: if (crop.statResistance < 31) crop.statResistance=crop.statResistance+1; crop.updateState();
     * stack.stackSize=stack.stackSize-1; break; } return true; } } } return false; }
     */
}
