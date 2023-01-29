package com.github.bartimaeusnek.cropspp.crops.cpp.trees;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import ic2.api.crops.CropCard;

public class OredictHandler {

    public OredictHandler() {
        // TODO Auto-generated constructor stub
    }

    private ArrayList<ItemStack> get_saplings() {
        if (!OreDictionary.getOres("treeSapling").isEmpty()) return OreDictionary.getOres("treeSapling");
        else return new ArrayList<ItemStack>();
    }

    private ArrayList<ItemStack> get_logs() {
        if (!OreDictionary.getOres("logWood").isEmpty()) return OreDictionary.getOres("logWood");
        else return new ArrayList<ItemStack>();
    }

    private LinkedHashMap<ItemStack, ItemStack> combine_logs_saplings() {
        String S = "";
        LinkedHashMap<ItemStack, ItemStack> ret = new LinkedHashMap<ItemStack, ItemStack>();
        for (ItemStack i : OreDictionary.getOres("logWood")) {
            for (ItemStack s : OreDictionary.getOres("treeSapling")) {
                if (i.getDisplayName().contains("Log")) S = "Log";
                else if (i.getDisplayName().contains("Wood")) S = "Wood";
                if (s.getDisplayName().replace("Sapling", "").equals(i.getDisplayName().replace(S, ""))) ret.put(s, i);
                else ret.put(s, new ItemStack(Blocks.log));
            }
        }
        return ret;
    }

    private ArrayList<CropCard> createCropCard(LinkedHashMap<ItemStack, ItemStack> saplinglogs) {
        ArrayList<CropCard> ret = new ArrayList<CropCard>();
        Iterator it = saplinglogs.entrySet().iterator();
        while (it.hasNext()) {
            ret.add(new BasicBonsaiCrop(null, null, null));
        }
        return null;
    }
}
