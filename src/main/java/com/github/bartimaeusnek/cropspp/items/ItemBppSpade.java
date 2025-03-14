package com.github.bartimaeusnek.cropspp.items;

import java.util.List;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;

import com.github.bartimaeusnek.croploadcore.MyRandom;
import com.github.bartimaeusnek.croploadcore.Operators;
import com.google.common.collect.Sets;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.IC2;
import ic2.core.Ic2Items;
import ic2.core.crop.TileEntityCrop;
import ic2.core.util.StackUtil;

public class ItemBppSpade extends ItemTool {

    private static final Set<Block> BlocksAffected = Sets.newHashSet(
            Blocks.grass,
            Blocks.dirt,
            Blocks.snow_layer,
            Blocks.farmland,
            Blocks.mycelium,
            StackUtil.getBlock(Ic2Items.crop));

    public ItemBppSpade() {
        super(1.0F, Item.ToolMaterial.IRON, BlocksAffected);
        this.setUnlocalizedName("Spade");
        this.setTextureName("bpp:itemSpade");
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTab.cpp);
        this.setMaxDamage(0);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean par4) {
        list.add("Weeding Trowel, Shovel and Hoe in one Item!");
        list.add("Has a higher chanche of yielding seedbags!");
        list.add("Indestructible");
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
            float hitX, float hitY, float hitZ) {
        if (!IC2.platform.isSimulating()) return false;
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileEntityCrop crop) {
            if (crop.getCrop() != null) {
                if (crop.getCrop() != null && crop.getCrop().tier() >= 1) {
                    float i = crop.getCrop().tier()
                            + 5 * ((((-crop.getResistance()) / 2) + crop.getGain() + crop.getGrowth()) / 21);
                    if (MyRandom.intrandom(100, 0) <= 100 * Operators.csig(i, 12, false)) {
                        if (crop.getCrop().getGain(crop) != null && crop.getCrop().canBeHarvested(crop))
                            StackUtil.dropAsEntity(world, x, y, z, crop.getCrop().getGain(crop));
                        StackUtil.dropAsEntity(
                                world,
                                x,
                                y,
                                z,
                                crop.generateSeeds(
                                        crop.getCrop(),
                                        crop.getGrowth(),
                                        crop.getGain(),
                                        crop.getResistance(),
                                        crop.getScanLevel()));
                    }
                } else {
                    StackUtil.dropAsEntity(world, x, y, z, new ItemStack(Ic2Items.weed.getItem(), crop.size));
                    if (!(crop.getCrop().name().equals("weed"))) {
                        if (crop.getSize() == crop.getCrop().maxSize()) StackUtil.dropAsEntity(
                                world,
                                x,
                                y,
                                z,
                                crop.generateSeeds(
                                        crop.getCrop(),
                                        crop.getGrowth(),
                                        crop.getGain(),
                                        crop.getResistance(),
                                        crop.getScanLevel()));
                    } else if (crop.getCrop().getGain(crop) != null && crop.getCrop().canBeHarvested(crop))
                        StackUtil.dropAsEntity(world, x, y, z, crop.getCrop().getGain(crop));

                }
                crop.reset();
                return true;
            }
            crop.reset();
            return true;
        }
        return false;
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
            float hitX, float hitY, float hitZ) {
        if (!player.canPlayerEdit(x, y, z, side, stack)) {
            return false;
        }

        var event = new UseHoeEvent(player, stack, world, x, y, z);
        if (MinecraftForge.EVENT_BUS.post(event)) {
            return false;
        }

        if (event.getResult() == Event.Result.ALLOW) {
            return true;
        }

        if (side != 0 && world.getBlock(x, y + 1, z).getMaterial() == Material.air) {
            if (world.getBlock(x, y, z) == Blocks.grass || world.getBlock(x, y, z) == Blocks.dirt
                    || world.getBlock(x, y, z) == Blocks.mycelium
                    || world.getBlock(x, y, z) == Blocks.farmland) {
                var spadeBlock = Blocks.farmland;
                world.playSoundEffect(
                        x + 0.5f,
                        y + 0.5f,
                        z + 0.5f,
                        spadeBlock.stepSound.getStepResourcePath(),
                        (spadeBlock.stepSound.getVolume() + 1f) / 2f,
                        spadeBlock.stepSound.getPitch() * 0.8f);
                if (!world.isRemote) {
                    world.setBlock(
                            x,
                            y,
                            z,
                            (world.getBlock(x, y, z) == Blocks.farmland) ? Blocks.dirt : Blocks.farmland);
                }
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World p_150894_2_, Block p_150894_3_, int p_150894_4_,
            int p_150894_5_, int p_150894_6_, EntityLivingBase player) {
        return true;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase p_77644_2_, EntityLivingBase player) {
        return true;
    }
}
