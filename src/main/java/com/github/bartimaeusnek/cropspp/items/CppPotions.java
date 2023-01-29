package com.github.bartimaeusnek.cropspp.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CppPotions extends Item {

    public static final String[] textureNames = new String[] { "FWheat", "Korn", "DKorn", "FReed", "SWhine", "Mash",
            "Wash", "GHP", "jagi", "njagi" /* , more names */
    };
    public IIcon[] icons;

    public CppPotions() {
        super();
        this.setHasSubtypes(true);
        this.setCreativeTab(CreativeTab.cpp);
        this.setMaxDamage(0);
        this.setMaxStackSize(1);
        this.bFull3D = false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        if (meta < textureNames.length) return icons[meta];
        else return icons[0];
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.icons = new IIcon[textureNames.length];

        /*
         * for (int i = 0; i < this.icons.length; ++i) { this.icons[i] = iconRegister.registerIcon("bpp:potion_" +
         * textureNames[i]+"_bottled"); }
         */
        this.icons[0] = gregtech.api.enums.ItemList.Bottle_Apple_Juice.get(1).getIconIndex();
        this.icons[1] = gregtech.api.enums.ItemList.Bottle_Vodka.get(1).getIconIndex();
        this.icons[2] = gregtech.api.enums.ItemList.Bottle_Vodka.get(1).getIconIndex();
        this.icons[3] = gregtech.api.enums.ItemList.Bottle_Apple_Juice.get(1).getIconIndex();
        this.icons[4] = gregtech.api.enums.ItemList.Bottle_Vodka.get(1).getIconIndex();
        this.icons[5] = gregtech.api.enums.ItemList.Bottle_Hops_Juice.get(1).getIconIndex();
        this.icons[6] = gregtech.api.enums.ItemList.Bottle_Apple_Juice.get(1).getIconIndex();
        this.icons[7] = gregtech.api.enums.ItemList.Bottle_Vodka.get(1).getIconIndex();
        this.icons[8] = iconRegister.registerIcon("bpp:potion_jagi_bottled");
        this.icons[9] = iconRegister.registerIcon("bpp:potion_jagi_bottled");
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        if (itemstack.getItemDamage() < textureNames.length) return (new StringBuilder()).append("potion.")
                .append(textureNames[itemstack.getItemDamage()]).append(".bottled").toString();
        else return (new StringBuilder()).append("potion.").append(textureNames[0]).append(".bottled").toString();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List list) {
        for (int i = 0; i < textureNames.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        switch (stack.getItemDamage()) {
            case 0: {
                list.add("It stinks.");
                break;
            }
            case 1: {
                list.add("Korn? Eww, you'll get a headache!");
                break;
            }
            case 2: {
                list.add("Doppelkorn? German Vodka!");
                break;
            }
            case 3: {
                list.add("It stinks.");
                break;
            }
            case 4: {
                list.add("Too Strong.");
                break;
            }
            case 5: {
                list.add("Soo Sweet");
                break;
            }
            case 6: {
                list.add("It stinks.");
                break;
            }
            case 7: {
                list.add("Too Strong.");
                break;
            }
            case 8: {
                list.add("Das ist des J\u00e4gers' Eherenschild,");
                list.add("dass er besch\u00fctzt und hegt sein Wild,");
                list.add("weidm\u00e4nnisch jagt, wie sich's geh\u00f6rt,");
                list.add("den Sch\u00f6pfer im Gesch\u00f6pfe ehrt.");
                list.add("");
                list.add("It will give 1h potioneffects!");
                break;
            }
            case 9: {
                list.add("Das ist des J\u00e4gers' Eherenschild,");
                list.add("dass er besch\u00fctzt und hegt sein Wild,");
                list.add("weidm\u00e4nnisch jagt, wie sich's geh\u00f6rt,");
                list.add("den Sch\u00f6pfer im Gesch\u00f6pfe ehrt.");
                list.add("");
                list.add("It smells like fake J\u00e4germeister...");
                break;
            }
            // other cases
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer player) {

        switch (par1ItemStack.getItemDamage()) {
            case 0: {
                player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 2 * 20, 1));
                break;
            }
            case 1: {
                player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 5 * 20, 1));
                player.addPotionEffect(new PotionEffect(Potion.confusion.id, 15 * 20, 2));
                break;
            }
            case 2: {
                player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 10 * 20, 2));
                player.addPotionEffect(new PotionEffect(Potion.confusion.id, 15 * 20, 1));
                break;
            }
            case 3: {
                player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 2 * 20, 1));
                break;
            }
            case 4: {
                player.addPotionEffect(new PotionEffect(Potion.harm.id, 20 * 2, 0));
                player.addPotionEffect(new PotionEffect(Potion.poison.id, 20 * 8, 0));
                player.addPotionEffect(new PotionEffect(Potion.confusion.id, 15 * 20, 4));
                break;
            }
            case 5: {
                player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 20 * 4, 0));
                break;
            }
            case 6: {
                player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 2 * 20, 1));
                break;
            }
            case 7: {
                player.addPotionEffect(new PotionEffect(Potion.harm.id, 20 * 2, 0));
                player.addPotionEffect(new PotionEffect(Potion.poison.id, 20 * 8, 0));
                player.addPotionEffect(new PotionEffect(Potion.confusion.id, 15 * 20, 4));
                break;
            }
            case 8: {
                player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 20 * 60 * 60, 100));
                player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 20 * 60 * 60, 100));
                player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 20 * 60 * 60, 100));
                player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 20 * 60 * 60, 100));
                player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 20 * 60 * 60, 100));
                player.addPotionEffect(new PotionEffect(Potion.resistance.id, 20 * 60 * 60, 100));
                player.addPotionEffect(new PotionEffect(Potion.confusion.id, 20 * 60 * 60, 4));
                break;
            }
            case 9: {
                player.addPotionEffect(new PotionEffect(Potion.harm.id, 20 * 2, 0));
                player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 20 * 20, 4));
                player.addPotionEffect(new PotionEffect(Potion.confusion.id, 15 * 20, 4));
                break;
            }
        }
        return new ItemStack(Items.glass_bottle);
    }
}
