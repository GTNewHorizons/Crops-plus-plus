package com.github.bartimaeusnek.cropspp.items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.enums.ItemList;
import gregtech.api.util.GTUtility;

public class CppPotions extends ItemPotion {

    public static final String[] textureNames = { "FWheat", "Korn", "DKorn", "FReed", "SWhine", "Mash", "Wash", "GHP",
            "jagi", "njagi" /* , more names */
    };
    public IIcon[] icons;

    public CppPotions() {
        super();
        this.setCreativeTab(CreativeTab.cpp);
    }

    @Override
    public IIcon getIconFromDamage(int meta) {
        if (meta < textureNames.length) return icons[meta];
        else return icons[0];
    }

    @Override
    public IIcon getIconFromDamageForRenderPass(int p_77618_1_, int p_77618_2_) {
        return this.getIconFromDamage(p_77618_1_);
    }

    @Override
    public int getColorFromDamage(int p_77620_1_) {
        return 0xFFFFFF;
    }

    @Override
    public int getColorFromItemStack(ItemStack p_82790_1_, int p_82790_2_) {
        return 0xFFFFFF;
    }

    @Override
    public boolean requiresMultipleRenderPasses() {
        return false;
    }

    @Override
    public boolean hasEffect(ItemStack p_77636_1_) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.icons = new IIcon[textureNames.length];
        this.icons[0] = ItemList.Bottle_Apple_Juice.get(1).getIconIndex();
        this.icons[1] = ItemList.Bottle_Vodka.get(1).getIconIndex();
        this.icons[2] = ItemList.Bottle_Vodka.get(1).getIconIndex();
        this.icons[3] = ItemList.Bottle_Apple_Juice.get(1).getIconIndex();
        this.icons[4] = ItemList.Bottle_Vodka.get(1).getIconIndex();
        this.icons[5] = ItemList.Bottle_Hops_Juice.get(1).getIconIndex();
        this.icons[6] = ItemList.Bottle_Apple_Juice.get(1).getIconIndex();
        this.icons[7] = ItemList.Bottle_Vodka.get(1).getIconIndex();
        this.icons[8] = iconRegister.registerIcon("bpp:potion_jagi_bottled");
        this.icons[9] = iconRegister.registerIcon("bpp:potion_jagi_bottled");
    }

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        int meta = itemstack.getItemDamage();
        if (meta >= textureNames.length) {
            meta = 0;
        }
        return "potion." + textureNames[meta] + ".bottled";
    }

    @Override
    public String getItemStackDisplayName(ItemStack p_77653_1_) {
        return (StatCollector.translateToLocal(this.getUnlocalizedNameInefficiently(p_77653_1_) + ".name")).trim();
    }

    @Override
    public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List<ItemStack> list) {
        for (int i = 0; i < textureNames.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4) {
        switch (stack.getItemDamage()) {
            case 0, 3, 6 -> list.add(StatCollector.translateToLocal("bpp.tooltip.potion.0"));
            case 1 -> list.add(StatCollector.translateToLocal("bpp.tooltip.potion.1"));
            case 2 -> list.add(StatCollector.translateToLocal("bpp.tooltip.potion.2"));
            case 4, 7 -> list.add(StatCollector.translateToLocal("bpp.tooltip.potion.3"));
            case 5 -> list.add(StatCollector.translateToLocal("bpp.tooltip.potion.4"));
            case 8, 9 -> {
                Collections.addAll(list, GTUtility.breakLines(StatCollector.translateToLocal("bpp.tooltip.potion.5")));
                list.add("");
                if (stack.getItemDamage() == 8) {
                    list.add(StatCollector.translateToLocal("bpp.tooltip.potion.6"));
                } else {
                    list.add(StatCollector.translateToLocal("bpp.tooltip.potion.7"));
                }

            }
            // other cases
            default -> {}
        }
    }

    @Override
    public List<PotionEffect> getEffects(ItemStack p_77832_1_) {
        return p_77832_1_ == null ? Collections.emptyList() : this.getEffects(p_77832_1_.getItemDamage());
    }

    @Override
    public List<PotionEffect> getEffects(int p_77834_1_) {
        List<PotionEffect> effects = new ArrayList<>();
        switch (p_77834_1_) {
            case 0, 3, 6 -> effects.add(new PotionEffect(Potion.regeneration.id, 2 * 20, 1));
            case 1 -> {
                effects.add(new PotionEffect(Potion.regeneration.id, 5 * 20, 1));
                effects.add(new PotionEffect(Potion.confusion.id, 15 * 20, 2));
            }
            case 2 -> {
                effects.add(new PotionEffect(Potion.regeneration.id, 10 * 20, 2));
                effects.add(new PotionEffect(Potion.confusion.id, 15 * 20, 1));
            }
            case 4, 7 -> {
                effects.add(new PotionEffect(Potion.harm.id, 20 * 2, 0));
                effects.add(new PotionEffect(Potion.poison.id, 20 * 8, 0));
                effects.add(new PotionEffect(Potion.confusion.id, 15 * 20, 4));
            }
            case 5 -> effects.add(new PotionEffect(Potion.regeneration.id, 20 * 4, 0));
            case 8 -> {
                effects.add(new PotionEffect(Potion.regeneration.id, 20 * 60 * 60, 100));
                effects.add(new PotionEffect(Potion.damageBoost.id, 20 * 60 * 60, 100));
                effects.add(new PotionEffect(Potion.digSpeed.id, 20 * 60 * 60, 100));
                effects.add(new PotionEffect(Potion.fireResistance.id, 20 * 60 * 60, 100));
                effects.add(new PotionEffect(Potion.waterBreathing.id, 20 * 60 * 60, 100));
                effects.add(new PotionEffect(Potion.resistance.id, 20 * 60 * 60, 100));
                effects.add(new PotionEffect(Potion.confusion.id, 20 * 60 * 60, 4));
            }
            case 9 -> {
                effects.add(new PotionEffect(Potion.harm.id, 20 * 2, 0));
                effects.add(new PotionEffect(Potion.regeneration.id, 20 * 20, 4));
                effects.add(new PotionEffect(Potion.confusion.id, 15 * 20, 4));
            }
        }
        return effects;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player) {
        player.setItemInUse(itemStackIn, this.getMaxItemUseDuration(itemStackIn));
        return itemStackIn;
    }
}
