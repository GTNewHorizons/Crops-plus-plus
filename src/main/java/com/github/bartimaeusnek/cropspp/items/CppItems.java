package com.github.bartimaeusnek.cropspp.items;

import com.github.bartimaeusnek.croploadcore.ModsLoaded;
import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.ConfigValues;
import cpw.mods.fml.common.registry.GameRegistry;
import ic2.core.Ic2Items;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public final class CppItems {
    public final static Item BerryItems = new NaturaBerryItems(1).setUnlocalizedName("berry");
    public final static Item itemSpade = new ItemBppSpade();
    public final static Item itemLens = new ItemBppLens();
    public final static ItemStack itemSpadeStack = new ItemStack(itemSpade);
    public final static Item Goldfisch = new Goldfish();
    public final static ItemStack GoldfischS = new ItemStack(Goldfisch);
    public final static Item Modifier = new Modifier();
    public final static ItemStack ModifierSpace = new ItemStack(Modifier, 1, 0);
    public final static ItemStack ModifierMagic = new ItemStack(Modifier, 1, 1);
    //public final static ItemStack ModifierAnti = new ItemStack(Modifier, 1, 3);
    public final static ItemStack Trophy = new ItemStack(Modifier, 1, 2);
    //public final static ItemStack GigaTrophy = new ItemStack(Modifier, 1, 4);
    //public final static ItemStack Gr = new ItemStack(Modifier, 1, 4);
    //public final static ItemStack Ga = new ItemStack(Modifier, 1, 5);
    //public final static ItemStack Re = new ItemStack(Modifier, 1, 6);
    public final static Item CppBerries = new CppBerries();
    public final static Item CppPotions = new CppPotions();

    public final static void register_Items() {
        if (ConfigValues.Items) {
            GameRegistry.registerItem(itemLens, "itemLens");
            GameRegistry.registerItem(itemSpade, "itemSpade");
            GameRegistry.registerItem(Goldfisch, "foodGoldfish");
            GameRegistry.registerItem(Modifier, "Modifier");
            GameRegistry.registerCustomItemStack("ModifierSpace", ModifierSpace);
            GameRegistry.registerCustomItemStack("ModifierMagic", ModifierMagic);
            //GameRegistry.registerCustomItemStack("ModifierAnti", ModifierAnti);
            GameRegistry.registerCustomItemStack("Trophy", Trophy);
//            GameRegistry.registerCustomItemStack("gigaTrophy", GigaTrophy);
            if (ModsLoaded.GT)
                GameRegistry.registerItem(CppPotions, "BppPotions");
            GameRegistry.registerItem(CppBerries, "foodBerries");
            GameRegistry.registerCustomItemStack("berryHuckle", new ItemStack(CppBerries, 1, 0));
            GameRegistry.registerCustomItemStack("sugarbeet", new ItemStack(CppBerries, 1, 1));
            if (ConfigValues.WiPItems) {
                GameRegistry.registerItem(new ItemBppWateringCan(), "itemWateringCan");
                GameRegistry.registerItem(new Weedmaker(), "debugitemWeedmaker");
            }
            if (!ModsLoaded.Natura) {
                GameRegistry.registerItem(BerryItems, "berry");
                GameRegistry.registerCustomItemStack("berryRasp", new ItemStack(BerryItems, 1, 0));
                GameRegistry.registerCustomItemStack("berryBlue", new ItemStack(BerryItems, 1, 1));
                GameRegistry.registerCustomItemStack("berryBlack", new ItemStack(BerryItems, 1, 2));
                GameRegistry.registerCustomItemStack("berryMalo", new ItemStack(BerryItems, 1, 3));
                GameRegistry.registerCustomItemStack("berrySaguaro", new ItemStack(BerryItems, 1, 4));
            }
        }
    }

    public final static void register_recipes() {
        if (ConfigValues.Items) {
            if (!ModsLoaded.GT) {
                GameRegistry.addRecipe(itemSpadeStack, " P ", "PWP", " S ", 'P', OreDict.ISget("plateDenseSteel"), 'W', Ic2Items.weedingTrowel.getItem(), 'S', Items.stick);
                GameRegistry.addShapelessRecipe(new ItemStack(Items.sugar, 8), new ItemStack(CppBerries, 1, 1));
                GameRegistry.addShapelessRecipe(new ItemStack(Items.dye, 2, 5), new ItemStack(CppBerries, 1, 0));
                GameRegistry.addShapelessRecipe(new ItemStack(Items.gold_nugget, 9), new ItemStack(Goldfisch), new ItemStack(Items.flint));
            }
            if (!ModsLoaded.PHC) {
                GameRegistry.addShapelessRecipe(new ItemStack(Items.fish), new ItemStack(Goldfisch));
                GameRegistry.addSmelting(new ItemStack(Goldfisch), new ItemStack(Items.cooked_fished), 0);
            }
        }
    }

    public final static void OreDictItems() {
        if (ConfigValues.Items) {
            OreDictionary.registerOre("listAllfishraw", new ItemStack(Goldfisch));
            OreDictionary.registerOre("listAllberry", new ItemStack(CppBerries, 1, 0));
            OreDictionary.registerOre("listAllfruit", new ItemStack(CppBerries, 1, 0));
            OreDictionary.registerOre("cropHuckleberry", new ItemStack(CppBerries, 1, 0));
            OreDictionary.registerOre("listAllveggie", new ItemStack(CppBerries, 1, 1));
            OreDictionary.registerOre("listAllrootveggie", new ItemStack(CppBerries, 1, 1));
            OreDictionary.registerOre("cropSugarbeet", new ItemStack(CppBerries, 1, 1));
        }

        if (!ModsLoaded.Natura) {
            ItemStack[] BerryItemsS = new ItemStack[5];
            for (int i = 0; i < 5; ++i) {
                BerryItemsS[i] = new ItemStack(BerryItems, i);
                OreDictionary.registerOre("listAllberry", BerryItemsS[i]);
                OreDictionary.registerOre("listAllfruit", BerryItemsS[i]);
            }
            OreDictionary.registerOre("cropRaspberry", BerryItemsS[0]);
            OreDictionary.registerOre("cropBlueberry", BerryItemsS[1]);
            OreDictionary.registerOre("cropBlackberry", BerryItemsS[2]);
            OreDictionary.registerOre("cropMaloberry", BerryItemsS[3]);
            OreDictionary.registerOre("cropSaguaroBerry", BerryItemsS[4]);
        }
    }
}
