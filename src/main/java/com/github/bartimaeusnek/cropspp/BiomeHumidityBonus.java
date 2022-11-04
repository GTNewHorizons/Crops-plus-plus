package com.github.bartimaeusnek.cropspp;

import ic2.api.crops.Crops;
import net.minecraftforge.common.BiomeDictionary;

public class BiomeHumidityBonus {

    private BiomeHumidityBonus() {}

    public static void apply() {
        Crops.instance.addBiomehumidityBonus(BiomeDictionary.Type.HOT, -5);
        Crops.instance.addBiomehumidityBonus(BiomeDictionary.Type.COLD, -5);
        Crops.instance.addBiomehumidityBonus(BiomeDictionary.Type.SPARSE, -5);
        Crops.instance.addBiomehumidityBonus(BiomeDictionary.Type.WET, 10);
        Crops.instance.addBiomehumidityBonus(BiomeDictionary.Type.DRY, -10);
        Crops.instance.addBiomehumidityBonus(BiomeDictionary.Type.SAVANNA, -5);
        Crops.instance.addBiomehumidityBonus(BiomeDictionary.Type.CONIFEROUS, 7);
        Crops.instance.addBiomehumidityBonus(BiomeDictionary.Type.JUNGLE, 10);
        Crops.instance.addBiomehumidityBonus(BiomeDictionary.Type.SPOOKY, 7);
        Crops.instance.addBiomehumidityBonus(BiomeDictionary.Type.DEAD, -5);
        Crops.instance.addBiomehumidityBonus(BiomeDictionary.Type.LUSH, 7);
        Crops.instance.addBiomehumidityBonus(BiomeDictionary.Type.NETHER, -10);
        Crops.instance.addBiomehumidityBonus(BiomeDictionary.Type.END, -10);
        Crops.instance.addBiomehumidityBonus(BiomeDictionary.Type.MUSHROOM, 8);
        Crops.instance.addBiomehumidityBonus(BiomeDictionary.Type.MAGICAL, 6);
        Crops.instance.addBiomehumidityBonus(BiomeDictionary.Type.OCEAN, 10);
        Crops.instance.addBiomehumidityBonus(BiomeDictionary.Type.RIVER, 9);
        Crops.instance.addBiomehumidityBonus(BiomeDictionary.Type.WATER, 10);
        Crops.instance.addBiomehumidityBonus(BiomeDictionary.Type.MESA, -5);
        Crops.instance.addBiomehumidityBonus(BiomeDictionary.Type.FOREST, 6);
        Crops.instance.addBiomehumidityBonus(BiomeDictionary.Type.PLAINS, 0);
        Crops.instance.addBiomehumidityBonus(BiomeDictionary.Type.MOUNTAIN, 5);
        Crops.instance.addBiomehumidityBonus(BiomeDictionary.Type.HILLS, 4);
        Crops.instance.addBiomehumidityBonus(BiomeDictionary.Type.SWAMP, 10);
        Crops.instance.addBiomehumidityBonus(BiomeDictionary.Type.SANDY, -5);
        Crops.instance.addBiomehumidityBonus(BiomeDictionary.Type.SNOWY, -5);
        Crops.instance.addBiomehumidityBonus(BiomeDictionary.Type.WASTELAND, -5);
        Crops.instance.addBiomehumidityBonus(BiomeDictionary.Type.BEACH, 6);
    }
}
