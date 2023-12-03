package com.github.bartimaeusnek.cropspp.GTHandler;

import java.util.Locale;

import gregtech.api.enums.TierEU;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.fluids.CppFluids;
import com.github.bartimaeusnek.cropspp.items.CppItems;
import com.github.bartimaeusnek.cropspp.items.CppPotions;

import gregtech.api.GregTech_API;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.Mods;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import ic2.api.item.IC2Items;
import ic2.core.Ic2Items;

import static gregtech.api.util.GT_Recipe.GT_Recipe_Map.sCentrifugeRecipes;
import static gregtech.api.util.GT_Recipe.GT_Recipe_Map.sCompressorRecipes;
import static gregtech.api.util.GT_Recipe.GT_Recipe_Map.sDistillationRecipes;
import static gregtech.api.util.GT_Recipe.GT_Recipe_Map.sDistilleryRecipes;
import static gregtech.api.util.GT_Recipe.GT_Recipe_Map.sFermentingRecipes;
import static gregtech.api.util.GT_Recipe.GT_Recipe_Map.sFluidCannerRecipes;
import static gregtech.api.util.GT_Recipe.GT_Recipe_Map.sMixerRecipes;
import static gregtech.api.util.GT_RecipeBuilder.SECONDS;
import static gregtech.api.util.GT_RecipeBuilder.TICKS;

public class GTNHMachineRecipeLoader implements Runnable {

    public GTNHMachineRecipeLoader() {
        super();
    }

    @Override
    public final void run() {

        // Space Modifier = Space Plant (Tier13, Naquadah plant +1 tier)
        // Magic Modifier = Primordial Pearl,

        // StonePlant
        GT_Values.RA.stdBuilder()
                .itemInputs(
                        Materials.Marble.getDust(9)
                )
                .itemOutputs(
                        Materials.Marble.getBlocks(1)
                )
                .duration(15 * SECONDS)
                .eut(2)
                .addTo(sCompressorRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        Materials.GraniteRed.getDust(1)
                )
                .itemOutputs(
                        Materials.GraniteRed.getPlates(1)
                )
                .duration(15 * SECONDS)
                .eut(2)
                .addTo(sCompressorRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        Materials.GraniteBlack.getDust(1)
                )
                .itemOutputs(
                        Materials.GraniteBlack.getPlates(1)
                )
                .duration(15 * SECONDS)
                .eut(2)
                .addTo(sCompressorRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        Materials.Stone.getPlates(9)
                )
                .itemOutputs(
                        new ItemStack(Blocks.stone)
                )
                .duration(15 * SECONDS)
                .eut(2)
                .addTo(sCompressorRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        Materials.GraniteRed.getPlates(9)
                )
                .itemOutputs(
                        new ItemStack(GregTech_API.sBlockGranites, 1, 8)
                )
                .duration(15 * SECONDS)
                .eut(2)
                .addTo(sCompressorRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        Materials.GraniteBlack.getPlates(9)
                )
                .itemOutputs(
                        new ItemStack(GregTech_API.sBlockGranites)
                )
                .duration(15 * SECONDS)
                .eut(2)
                .addTo(sCompressorRecipes);


        // honey related
        GT_Values.RA.stdBuilder()
                .itemInputs(
                        GT_Utility.getIntegratedCircuit(9)
                )
                .itemOutputs(
                        new ItemStack(Items.sugar, 9, 0)
                )
                .fluidInputs(
                        new FluidStack(FluidRegistry.getFluid("for.honey"), 1000)
                )
                .duration(51 * SECONDS)
                .eut(8)
                .addTo(sCentrifugeRecipes);

        if (Mods.BiomesOPlenty.isModLoaded()) {

            GT_Values.RA.stdBuilder()
                    .itemInputs(
                            GT_Utility.getIntegratedCircuit(9)
                    )
                    .itemOutputs(
                            new ItemStack(Items.sugar, 9, 0)
                    )
                    .fluidInputs(
                            new FluidStack(FluidRegistry.getFluid("honey"), 1000)
                    )
                    .duration(51 * SECONDS)
                    .eut(8)
                    .addTo(sCentrifugeRecipes);

            GT_Values.RA.stdBuilder()
                    .itemInputs(
                            GT_Utility.getIntegratedCircuit(1)
                    )
                    .fluidInputs(
                            new FluidStack(FluidRegistry.getFluid("honey"), 1000)
                    )
                    .fluidOutputs(
                            new FluidStack(FluidRegistry.getFluid("for.honey"), 1000)
                    )
                    .duration(51 * SECONDS)
                    .eut(8)
                    .addTo(sCentrifugeRecipes);

        }

        // Ethanol related

        for (int i = 0; i < CppPotions.textureNames.length; i++) {

            GT_Values.RA.stdBuilder()
                    .itemInputs(
                            new ItemStack(CppItems.CppPotions, 1, i)
                    )
                    .itemOutputs(
                            new ItemStack(Items.glass_bottle)
                    )
                    .fluidOutputs(
                            new FluidStack(FluidRegistry.getFluid("potion." + CppPotions.textureNames[i].toLowerCase(Locale.ENGLISH)), 250)
                    )
                    .duration(4 * TICKS)
                    .eut(1)
                    .addTo(sFluidCannerRecipes);

            GT_Values.RA.stdBuilder()
                    .itemInputs(
                            new ItemStack(Items.glass_bottle)
                    )
                    .itemOutputs(
                            new ItemStack(CppItems.CppPotions, 1, i)
                    )
                    .fluidInputs(
                            new FluidStack(FluidRegistry.getFluid("potion." + CppPotions.textureNames[i].toLowerCase(Locale.ENGLISH)), 250)
                    )
                    .duration(4 * TICKS)
                    .eut(1)
                    .addTo(sFluidCannerRecipes);

        }


        GT_Values.RA.stdBuilder()
                .fluidInputs(
                        new FluidStack(CppFluids.Mash, 10)
                )
                .fluidOutputs(
                        new FluidStack(CppFluids.Wash, 8)
                )
                .duration(50 * SECONDS)
                .eut(2)
                .addTo(sFermentingRecipes);

        GT_Values.RA.stdBuilder()
                .fluidInputs(
                        new FluidStack(CppFluids.Wash, 20)
                )
                .fluidOutputs(
                        new FluidStack(FluidRegistry.getFluid("potion.wine"), 8)
                )
                .duration(50 * SECONDS)
                .eut(2)
                .addTo(sFermentingRecipes);

        GT_Values.RA.stdBuilder()
                .fluidInputs(
                        new FluidStack(FluidRegistry.getFluid("potion.wheatyjuice"), 10)
                )
                .fluidOutputs(
                        new FluidStack(CppFluids.FWheat, 8)
                )
                .duration(51 * SECONDS)
                .eut(2)
                .addTo(sFermentingRecipes);

        GT_Values.RA.stdBuilder()
                .fluidInputs(
                        new FluidStack(FluidRegistry.getFluid("potion.reedwater"), 10)
                )
                .fluidOutputs(
                        new FluidStack(CppFluids.FReed, 8)
                )
                .duration(51 * SECONDS)
                .eut(2)
                .addTo(sFermentingRecipes);



        GT_Values.RA.stdBuilder()
                .fluidInputs(
                        new FluidStack(FluidRegistry.getFluid("potion.rum"), 1000)
                )
                .fluidOutputs(
                        Materials.Ethanol.getFluid(580L), Materials.Water.getFluid(420L))
                .duration(4 * SECONDS)
                .eut(180)
                .addTo(sDistillationRecipes);

        GT_Values.RA.stdBuilder()
                .fluidInputs(
                        new FluidStack(FluidRegistry.getFluid("potion.piratebrew"), 1000)
                )
                .fluidOutputs(
                        Materials.Ethanol.getFluid(800L), Materials.Water.getFluid(200L))
                .duration(4 * SECONDS)
                .eut(180)
                .addTo(sDistillationRecipes);

        GT_Values.RA.stdBuilder()
                .fluidInputs(
                        new FluidStack(FluidRegistry.getFluid("potion.beer"), 1000)
                )
                .fluidOutputs(
                        Materials.Ethanol.getFluid(45L), Materials.Water.getFluid(955L))
                .duration(4 * SECONDS)
                .eut(180)
                .addTo(sDistillationRecipes);

        GT_Values.RA.stdBuilder()
                .fluidInputs(
                        new FluidStack(FluidRegistry.getFluid("potion.darkbeer"), 1000)
                )
                .fluidOutputs(
                        Materials.Ethanol.getFluid(75L), Materials.Water.getFluid(925L))
                .duration(4 * SECONDS)
                .eut(180)
                .addTo(sDistillationRecipes);

        GT_Values.RA.stdBuilder()
                .fluidInputs(
                        new FluidStack(FluidRegistry.getFluid("potion.cider"), 1000)
                )
                .fluidOutputs(
                        Materials.Ethanol.getFluid(100L), Materials.Water.getFluid(900L))
                .duration(4 * SECONDS)
                .eut(180)
                .addTo(sDistillationRecipes);

        GT_Values.RA.stdBuilder()
                .fluidInputs(
                        new FluidStack(FluidRegistry.getFluid("potion.wine"), 1000)
                )
                .fluidOutputs(
                        Materials.Ethanol.getFluid(120L), Materials.Water.getFluid(880L))
                .duration(4 * SECONDS)
                .eut(180)
                .addTo(sDistillationRecipes);

        GT_Values.RA.stdBuilder()
                .fluidInputs(
                        new FluidStack(FluidRegistry.getFluid("potion.vodka"), 1000)
                )
                .fluidOutputs(
                        Materials.Ethanol.getFluid(400L), Materials.Water.getFluid(600L))
                .duration(4 * SECONDS)
                .eut(180)
                .addTo(sDistillationRecipes);

        GT_Values.RA.stdBuilder()
                .fluidInputs(
                        new FluidStack(FluidRegistry.getFluid("potion.alcopops"), 1000)
                )
                .fluidOutputs(
                        Materials.Ethanol.getFluid(160L), Materials.Water.getFluid(840L))
                .duration(4 * SECONDS)
                .eut(180)
                .addTo(sDistillationRecipes);

        GT_Values.RA.stdBuilder()
                .fluidInputs(
                        new FluidStack(CppFluids.Korn, 1000)
                )
                .fluidOutputs(
                        Materials.Ethanol.getFluid(320L), Materials.Water.getFluid(680L))
                .duration(4 * SECONDS)
                .eut(180)
                .addTo(sDistillationRecipes);

        GT_Values.RA.stdBuilder()
                .fluidInputs(
                        new FluidStack(CppFluids.DKorn, 1000)
                )
                .fluidOutputs(
                        Materials.Ethanol.getFluid(380L), Materials.Water.getFluid(620L))
                .duration(4 * SECONDS)
                .eut(180)
                .addTo(sDistillationRecipes);

        GT_Values.RA.stdBuilder()
                .fluidInputs(
                        new FluidStack(CppFluids.SWhine, 1000)
                )
                .fluidOutputs(
                        Materials.Ethanol.getFluid(700L), Materials.Water.getFluid(300L))
                .duration(4 * SECONDS)
                .eut(180)
                .addTo(sDistillationRecipes);

        GT_Values.RA.stdBuilder()
                .fluidInputs(
                        new FluidStack(CppFluids.GHP, 1000)
                )
                .fluidOutputs(
                        Materials.Ethanol.getFluid(750L), Materials.Water.getFluid(250L))
                .duration(4 * SECONDS)
                .eut(180)
                .addTo(sDistillationRecipes);

        GT_Values.RA.stdBuilder()
                .fluidInputs(
                        new FluidStack(CppFluids.jagi, 1000)
                )
                .fluidOutputs(
                        Materials.Ethanol.getFluid(350L), Materials.Water.getFluid(650L))
                .duration(4 * SECONDS)
                .eut(180)
                .addTo(sDistillationRecipes);

        GT_Values.RA.stdBuilder()
                .fluidInputs(
                        new FluidStack(CppFluids.njagi, 1000)
                )
                .fluidOutputs(
                        Materials.Ethanol.getFluid(350L), Materials.Water.getFluid(650L))
                .duration(4 * SECONDS)
                .eut(180)
                .addTo(sDistillationRecipes);



        GT_Values.RA.stdBuilder()
                .itemInputs(
                        GT_Utility.getIntegratedCircuit(1)
                )
                .fluidInputs(
                        new FluidStack(FluidRegistry.getFluid("potion.rum"), 100)
                )
                .fluidOutputs(
                        Materials.Ethanol.getFluid(50L)
                )
                .duration(16 * TICKS)
                .eut(24)
                .addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        GT_Utility.getIntegratedCircuit(2)
                )
                .fluidInputs(
                        new FluidStack(FluidRegistry.getFluid("potion.rum"), 100)
                )
                .fluidOutputs(
                        Materials.Water.getFluid(42L)
                )
                .duration(16 * TICKS)
                .eut(24)
                .addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        GT_Utility.getIntegratedCircuit(1)
                )
                .fluidInputs(
                        new FluidStack(FluidRegistry.getFluid("potion.vodka"), 100)
                )
                .fluidOutputs(
                        Materials.Ethanol.getFluid(35L)
                )
                .duration(16 * TICKS)
                .eut(24)
                .addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        GT_Utility.getIntegratedCircuit(2)
                )
                .fluidInputs(
                        new FluidStack(FluidRegistry.getFluid("potion.vodka"), 100)
                )
                .fluidOutputs(
                        Materials.Water.getFluid(60L)
                )
                .duration(16 * TICKS)
                .eut(24)
                .addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        GT_Utility.getIntegratedCircuit(1)
                )
                .fluidInputs(
                        new FluidStack(CppFluids.Korn, 100)
                )
                .fluidOutputs(
                        Materials.Ethanol.getFluid(25L)
                )
                .duration(16 * TICKS)
                .eut(24)
                .addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        GT_Utility.getIntegratedCircuit(2)
                )
                .fluidInputs(
                        new FluidStack(CppFluids.Korn, 100)
                )
                .fluidOutputs(
                        Materials.Water.getFluid(68L)
                )
                .duration(16 * TICKS)
                .eut(24)
                .addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        GT_Utility.getIntegratedCircuit(1)
                )
                .fluidInputs(
                        new FluidStack(CppFluids.DKorn, 100)
                )
                .fluidOutputs(
                        Materials.Ethanol.getFluid(30L)
                )
                .duration(16 * TICKS)
                .eut(24)
                .addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        GT_Utility.getIntegratedCircuit(2)
                )
                .fluidInputs(
                        new FluidStack(CppFluids.DKorn, 100)
                )
                .fluidOutputs(
                        Materials.Water.getFluid(62L)
                )
                .duration(16 * TICKS)
                .eut(24)
                .addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        GT_Utility.getIntegratedCircuit(1)
                )
                .fluidInputs(
                        new FluidStack(CppFluids.FWheat, 80)
                )
                .fluidOutputs(
                        new FluidStack(CppFluids.Korn, 1)
                )
                .duration(1 * SECONDS + 2 * TICKS)
                .eut(24)
                .addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        GT_Utility.getIntegratedCircuit(2)
                )
                .fluidInputs(
                        new FluidStack(CppFluids.FWheat, 95)
                )
                .fluidOutputs(
                        new FluidStack(CppFluids.DKorn, 1)
                )
                .duration(1 * SECONDS + 4 * TICKS)
                .eut(24)
                .addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        GT_Utility.getIntegratedCircuit(3)
                )
                .fluidInputs(
                        new FluidStack(CppFluids.FWheat, 100)
                )
                .fluidOutputs(
                        new FluidStack(FluidRegistry.getFluid("potion.vodka"), 1)
                )
                .duration(1 * SECONDS + 8 * TICKS)
                .eut(64)
                .addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        GT_Utility.getIntegratedCircuit(4)
                )
                .fluidInputs(
                        new FluidStack(CppFluids.FWheat, 200)
                )
                .fluidOutputs(
                        new FluidStack(FluidRegistry.getFluid("fermentedbiomass"), 3)
                )
                .duration(1 * SECONDS + 8 * TICKS)
                .eut(64)
                .addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        GT_Utility.getIntegratedCircuit(5)
                )
                .fluidInputs(
                        new FluidStack(CppFluids.FWheat, 1000)
                )
                .fluidOutputs(
                        Materials.Ethanol.getFluid(4L)
                )
                .duration(11 * SECONDS)
                .eut(TierEU.RECIPE_MV)
                .addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        GT_Utility.getIntegratedCircuit(1)
                )
                .fluidInputs(
                        new FluidStack(CppFluids.FReed, 100)
                )
                .fluidOutputs(
                        new FluidStack(CppFluids.SWhine, 7)
                )
                .duration(1 * SECONDS + 2 * TICKS)
                .eut(24)
                .addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        GT_Utility.getIntegratedCircuit(4)
                )
                .fluidInputs(
                        new FluidStack(CppFluids.FReed, 200)
                )
                .fluidOutputs(
                        new FluidStack(FluidRegistry.getFluid("fermentedbiomass"), 4)
                )
                .duration(1 * SECONDS + 4 * TICKS)
                .eut(24)
                .addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        GT_Utility.getIntegratedCircuit(5)
                )
                .fluidInputs(
                        new FluidStack(CppFluids.FReed, 1000)
                )
                .fluidOutputs(
                        Materials.Ethanol.getFluid(5L)
                )
                .duration(11 * SECONDS)
                .eut(TierEU.RECIPE_MV)
                .addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        GT_Utility.getIntegratedCircuit(4)
                )
                .fluidInputs(
                        new FluidStack(CppFluids.Mash, 200)
                )
                .fluidOutputs(
                        new FluidStack(FluidRegistry.getFluid("biomass"), 4)
                )
                .duration(1 * SECONDS + 4 * TICKS)
                .eut(24)
                .addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        GT_Utility.getIntegratedCircuit(1)
                )
                .fluidInputs(
                        new FluidStack(CppFluids.Wash, 100)
                )
                .fluidOutputs(
                        new FluidStack(CppFluids.GHP, 6)
                )
                .duration(1 * SECONDS + 2 * TICKS)
                .eut(24)
                .addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        GT_Utility.getIntegratedCircuit(4)
                )
                .fluidInputs(
                        new FluidStack(CppFluids.Wash, 100)
                )
                .fluidOutputs(
                        new FluidStack(FluidRegistry.getFluid("fermentedbiomass"), 14)
                )
                .duration(1 * SECONDS + 4 * TICKS)
                .eut(24)
                .addTo(sDistilleryRecipes);



        GT_Values.RA.stdBuilder()
                .itemInputs(
                        new ItemStack(Items.sugar, 32, 0),
                        new ItemStack(Items.dye, 4, 1),
                        new ItemStack(Items.dye, 4, 11),
                        new ItemStack(Items.dye, 4, 2),
                        Materials.Water.getCells(4)
                )
                .itemOutputs(
                        Materials.Empty.getCells(4)
                )
                .fluidInputs(
                        new FluidStack(CppFluids.GHP, 375)
                )
                .fluidOutputs(
                        new FluidStack(FluidRegistry.getFluid("potion.alcopops"), 4375)
                )
                .duration(10 * TICKS)
                .eut(8)
                .addTo(sMixerRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        new ItemStack(Items.sugar, 8, 0),
                        new ItemStack(Items.dye, 1, 1),
                        new ItemStack(Items.dye, 1, 11),
                        new ItemStack(Items.dye, 1, 2),
                        Materials.Water.getCells(1)
                )
                .itemOutputs(
                        Materials.Empty.getCells(1)
                )
                .fluidInputs(
                        new FluidStack(FluidRegistry.getFluid("potion.vodka"), 500)
                )
                .fluidOutputs(
                        new FluidStack(FluidRegistry.getFluid("potion.alcopops"), 1500)
                )
                .duration(10 * TICKS)
                .eut(8)
                .addTo(sMixerRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        new ItemStack(Items.sugar, 8, 0),
                        new ItemStack(Items.dye, 1, 1),
                        new ItemStack(Items.dye, 1, 11),
                        new ItemStack(Items.dye, 1, 2),
                        Materials.Water.getCells(1)
                )
                .itemOutputs(
                        Materials.Empty.getCells(1)
                )
                .fluidInputs(
                        new FluidStack(CppFluids.Korn, 1000)
                )
                .fluidOutputs(
                        new FluidStack(FluidRegistry.getFluid("potion.alcopops"), 2000)
                )
                .duration(10 * TICKS)
                .eut(8)
                .addTo(sMixerRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        new ItemStack(Items.sugar, 8, 0),
                        new ItemStack(Items.dye, 1, 1),
                        new ItemStack(Items.dye, 1, 11),
                        new ItemStack(Items.dye, 1, 2),
                        Materials.Water.getCells(1)
                )
                .itemOutputs(
                        Materials.Empty.getCells(1)
                )
                .fluidInputs(
                        new FluidStack(CppFluids.DKorn, 750)
                )
                .fluidOutputs(
                        new FluidStack(FluidRegistry.getFluid("potion.alcopops"), 1750)
                )
                .duration(10 * TICKS)
                .eut(8)
                .addTo(sMixerRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        new ItemStack(Items.sugar, 8, 0),
                        OreDict.ISget("cropSpiceleaf"),
                        OreDict.ISget("cropGinger"),
                        new ItemStack(Items.dye, 1, 2),
                        Materials.Water.getCells(1)
                )
                .itemOutputs(
                        Materials.Empty.getCells(1)
                )
                .fluidInputs(
                        new FluidStack(FluidRegistry.getFluid("potion.vodka"), 4000)
                )
                .fluidOutputs(
                        new FluidStack(CppFluids.njagi, 5000)
                )
                .duration(10 * TICKS)
                .eut(8)
                .addTo(sMixerRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        ItemList.Crop_Drop_Chilly.get(1),
                        Materials.CosmicNeutronium.getDustTiny(1),
                        ItemList.Crop_Drop_Lemon.get(64),
                        ItemList.Crop_Drop_TeaLeaf.get(64),
                        CppItems.ModifierMagic.splitStack(8),
                        CppItems.ModifierSpace.splitStack(9)
                )
                .fluidInputs(
                        new FluidStack(CppFluids.njagi, 50000)
                )
                .fluidOutputs(
                        new FluidStack(CppFluids.jagi, 250)
                )
                .duration(1 * TICKS)
                .eut(TierEU.RECIPE_LuV)
                .addTo(sMixerRecipes);


        GT_Values.RA.stdBuilder()
                .itemInputs(
                        Materials.Water.getCells(1)
                )
                .itemOutputs(
                        Materials.Empty.getCells(1)
                )
                .fluidInputs(
                        Materials.Ethanol.getFluid(1000L)
                )
                .fluidOutputs(
                        new FluidStack(FluidRegistry.getFluid("potion.vodka"), 2500)
                )
                .duration(10 * TICKS)
                .eut(8)
                .addTo(sMixerRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        Materials.Water.getCells(1),
                        new ItemStack(Items.sugar)
                )
                .itemOutputs(
                        Materials.Empty.getCells(1)
                )
                .fluidInputs(
                        new FluidStack(CppFluids.SWhine, 5000)
                )
                .fluidOutputs(
                        new FluidStack(FluidRegistry.getFluid("potion.rum"), 6000)
                )
                .duration(10 * TICKS)
                .eut(8)
                .addTo(sMixerRecipes);



        // Brewery
        if (OreDictionary.getOres("listAllberry").size() >= 1)
            for (int i = 0; i < OreDictionary.getOres("listAllberry").size(); i++) GT_Values.RA.addBrewingRecipe(
                    OreDictionary.getOres("listAllberry").get(i).splitStack(16),
                    Materials.Water.getFluid(1000L).getFluid(),
                    CppFluids.Mash,
                    false);
        GT_Values.RA.addBrewingRecipe(
                new ItemStack(Items.sugar, 8),
                FluidRegistry.getFluid("potion.weakness"),
                CppFluids.Mash,
                false);

        // Sugar Related
        GT_Values.RA
                .addExtractorRecipe(new ItemStack(CppItems.CppBerries, 1, 1), new ItemStack(Items.sugar, 8, 0), 160, 8);

        // Dyes from Plants
        for (int i = 0; i < OreDictionary.getOres("cropBlackberry").size(); i++) GT_Values.RA.addChemicalRecipe(
                OreDictionary.getOres("cropBlackberry").get(i).splitStack(16),
                Materials.Salt.getDust(2),
                Materials.SulfuricAcid.getFluid(432L),
                new FluidStack(FluidRegistry.getFluid("dye.chemical.dyeblack"), 288),
                new ItemStack(Items.sugar),
                600,
                48);
        for (int i = 0; i < OreDictionary.getOres("cropBlueberry").size(); i++) GT_Values.RA.addChemicalRecipe(
                OreDictionary.getOres("cropBlueberry").get(i).splitStack(16),
                Materials.Salt.getDust(2),
                Materials.SulfuricAcid.getFluid(432L),
                new FluidStack(FluidRegistry.getFluid("dye.chemical.dyeblue"), 288),
                new ItemStack(Items.sugar),
                600,
                48);
        for (int i = 0; i < OreDictionary.getOres("cropRaspberry").size(); i++) GT_Values.RA.addChemicalRecipe(
                OreDictionary.getOres("cropRaspberry").get(i).splitStack(16),
                Materials.Salt.getDust(2),
                Materials.SulfuricAcid.getFluid(432L),
                new FluidStack(FluidRegistry.getFluid("dye.chemical.dyepink"), 288),
                new ItemStack(Items.sugar),
                600,
                48);
        for (int i = 0; i < OreDictionary.getOres("cropVine").size(); i++) {
            if (!OreDictionary.getOres("cropVine").get(i).getUnlocalizedName().equals("tile.Thornvines"))
                GT_Values.RA.addChemicalRecipe(
                        OreDictionary.getOres("cropVine").get(i).splitStack(16),
                        Materials.Salt.getDust(2),
                        Materials.SulfuricAcid.getFluid(432L),
                        new FluidStack(FluidRegistry.getFluid("dye.chemical.dyegreen"), 288),
                        GT_Values.NI,
                        600,
                        48);
            else GT_Values.RA.addChemicalRecipe(
                    OreDictionary.getOres("cropVine").get(i).splitStack(16),
                    Materials.Salt.getDust(2),
                    Materials.SulfuricAcid.getFluid(432L),
                    new FluidStack(FluidRegistry.getFluid("dye.chemical.dyeyellow"), 288),
                    GT_Values.NI,
                    600,
                    48);
        }
        for (int i = 0; i < OreDictionary.getOres("cropCacti").size(); i++) GT_Values.RA.addChemicalRecipe(
                OreDictionary.getOres("cropCacti").get(i).splitStack(16),
                Materials.Salt.getDust(2),
                Materials.SulfuricAcid.getFluid(432L),
                new FluidStack(FluidRegistry.getFluid("dye.chemical.dyegreen"), 288),
                GT_Values.NI,
                600,
                48);
        for (int i = 0; i < OreDictionary.getOres("cropGooseberry").size(); i++) GT_Values.RA.addChemicalRecipe(
                OreDictionary.getOres("cropGooseberry").get(i).splitStack(16),
                Materials.Salt.getDust(2),
                Materials.SulfuricAcid.getFluid(432L),
                new FluidStack(FluidRegistry.getFluid("dye.chemical.dyeyellow"), 288),
                new ItemStack(Items.sugar),
                600,
                48);
        for (int i = 0; i < OreDictionary.getOres("cropStrawberry").size(); i++) GT_Values.RA.addChemicalRecipe(
                OreDictionary.getOres("cropStrawberry").get(i).splitStack(16),
                Materials.Salt.getDust(2),
                Materials.SulfuricAcid.getFluid(432L),
                new FluidStack(FluidRegistry.getFluid("dye.chemical.dyered"), 288),
                new ItemStack(Items.sugar),
                600,
                48);

        if (Mods.BiomesOPlenty.isModLoaded()) {
            GT_Values.RA.addChemicalRecipe(
                    GT_ModHandler.getModItem("BiomesOPlenty", "food", 16L),
                    Materials.Salt.getDust(2),
                    Materials.SulfuricAcid.getFluid(432L),
                    new FluidStack(FluidRegistry.getFluid("dye.chemical.dyered"), 288),
                    new ItemStack(Items.sugar),
                    600,
                    48);
        }

        GT_Values.RA.addChemicalRecipe(
                new ItemStack(CppItems.CppBerries, 16, 0),
                Materials.Salt.getDust(2),
                Materials.SulfuricAcid.getFluid(432L),
                new FluidStack(FluidRegistry.getFluid("dye.chemical.dyepurple"), 288),
                new ItemStack(Items.sugar),
                600,
                48);

        if (Mods.Natura.isModLoaded()) {
            GT_Values.RA.addChemicalRecipe(
                    GT_ModHandler.getModItem("Natura", "berry.nether", 16L, 0),
                    Materials.Salt.getDust(2),
                    Materials.SulfuricAcid.getFluid(432L),
                    new FluidStack(FluidRegistry.getFluid("dye.chemical.dyelime"), 288),
                    new ItemStack(Items.sugar),
                    600,
                    48);
            GT_Values.RA.addChemicalRecipe(
                    GT_ModHandler.getModItem("Natura", "berry.nether", 16L, 1),
                    Materials.Salt.getDust(2),
                    Materials.SulfuricAcid.getFluid(432L),
                    new FluidStack(FluidRegistry.getFluid("dye.chemical.dyelightgray"), 288),
                    new ItemStack(Items.sugar),
                    600,
                    48);
            GT_Values.RA.addChemicalRecipe(
                    GT_ModHandler.getModItem("Natura", "berry.nether", 16L, 2),
                    Materials.Salt.getDust(2),
                    Materials.SulfuricAcid.getFluid(432L),
                    new FluidStack(FluidRegistry.getFluid("dye.chemical.dyelightblue"), 288),
                    new ItemStack(Items.sugar),
                    600,
                    48);
            GT_Values.RA.addChemicalRecipe(
                    GT_ModHandler.getModItem("Natura", "berry.nether", 16L, 3),
                    Materials.Salt.getDust(2),
                    Materials.SulfuricAcid.getFluid(432L),
                    new FluidStack(FluidRegistry.getFluid("dye.chemical.dyelime"), 288),
                    new ItemStack(Items.sugar),
                    600,
                    48);
        }

        // Goldfish
        GT_Values.RA.addFluidExtractionRecipe(
                CppItems.GoldfischS,
                new ItemStack(Items.gold_nugget),
                Materials.FishOil.getFluid(100L),
                1,
                16,
                8);
        GT_Values.RA.addPulveriserRecipe(
                CppItems.GoldfischS,
                new ItemStack[] { Materials.MeatRaw.getDust(1), Materials.Gold.getDustTiny(1) },
                new int[] { 10000, 100 },
                3,
                8,
                false);

        // Space Modifier
        GT_Values.RA.addMultiblockChemicalRecipe(
                new ItemStack[] { Materials.Iron.getDust(1), new ItemStack(CppItems.Modifier, 16, 0) },
                new FluidStack[] { Materials.UUMatter
                        .getFluid(Materials.MeteoricIron.getNeutrons() + Materials.MeteoricIron.getProtons()) },
                new FluidStack[] {},
                new ItemStack[] { Materials.MeteoricIron.getDust(1) },
                240,
                480);
        GT_Values.RA.addMultiblockChemicalRecipe(
                new ItemStack[] { Materials.Steel.getDust(1), new ItemStack(CppItems.Modifier, 16, 0) },
                new FluidStack[] { Materials.UUMatter
                        .getFluid(Materials.MeteoricSteel.getNeutrons() + Materials.MeteoricSteel.getProtons()) },
                new FluidStack[] {},
                new ItemStack[] { Materials.MeteoricSteel.getDust(1) },
                240,
                480);
        GT_Values.RA.addMultiblockChemicalRecipe(
                new ItemStack[] { Materials.Bismuth.getDust(1), new ItemStack(CppItems.Modifier, 16, 0) },
                new FluidStack[] { Materials.UUMatter
                        .getFluid(Materials.Oriharukon.getNeutrons() + Materials.Oriharukon.getProtons()) },
                new FluidStack[] {},
                new ItemStack[] { Materials.Oriharukon.getDust(1) },
                240,
                480);
        GT_Values.RA.addMultiblockChemicalRecipe(
                new ItemStack[] { Materials.Titanium.getDust(1), new ItemStack(CppItems.Modifier, 16, 0) },
                new FluidStack[] {
                        Materials.UUMatter.getFluid(Materials.Desh.getNeutrons() + Materials.Desh.getProtons()) },
                new FluidStack[] {},
                new ItemStack[] { Materials.Desh.getDust(1) },
                240,
                480);
        // Non-elements
        GT_Values.RA.addMultiblockChemicalRecipe(
                new ItemStack[] { Materials.Ice.getDust(1), new ItemStack(CppItems.Modifier, 16, 0) },
                new FluidStack[] { Materials.UUMatter
                        .getFluid((Materials.Water.getProtons() + Materials.Water.getNeutrons()) * 10) },
                new FluidStack[] {},
                new ItemStack[] { Materials.CallistoIce.getDust(1) },
                240,
                7680);
        GT_Values.RA.addMultiblockChemicalRecipe(
                new ItemStack[] { Materials.Lead.getDust(1), new ItemStack(CppItems.Modifier, 16, 0) },
                new FluidStack[] { Materials.UUMatter
                        .getFluid((Materials.Water.getProtons() + Materials.Water.getNeutrons()) * 10) },
                new FluidStack[] {},
                new ItemStack[] { Materials.Ledox.getDust(1) },
                240,
                7680);
        if (Mods.Avaritia.isModLoaded()) {
            GT_Values.RA.addMultiblockChemicalRecipe(
                    new ItemStack[] { GT_ModHandler.getModItem("Avaritia", "Resource", 1L, 1),
                            new ItemStack(CppItems.Modifier, 32, 0) },
                    new FluidStack[] { Materials.UUMatter.getFluid(100L) },
                    null,
                    new ItemStack[] { Materials.MysteriousCrystal.getDust(1) },
                    240,
                    122880);
        }
        GT_Values.RA.addMultiblockChemicalRecipe(
                new ItemStack[] { Materials.MeteoricIron.getDust(1), new ItemStack(CppItems.Modifier, 64, 0) },
                new FluidStack[] { Materials.UUMatter
                        .getFluid((Materials.Neutronium.getNeutrons() + Materials.Neutronium.getProtons())) },
                new FluidStack[] {},
                new ItemStack[] { Materials.DeepIron.getDust(1) },
                120,
                122880);
        GT_Values.RA.addMultiblockChemicalRecipe(
                new ItemStack[] { Materials.Plutonium241.getDust(1), new ItemStack(CppItems.Modifier, 64, 0) },
                new FluidStack[] { Materials.UUMatter
                        .getFluid((Materials.Neutronium.getNeutrons() + Materials.Neutronium.getProtons())) },
                new FluidStack[] {},
                new ItemStack[] { Materials.BlackPlutonium.getDust(1) },
                120,
                500000);

        if (Mods.Thaumcraft.isModLoaded()) {
            // Magic Modifier PrimP
            GT_Values.RA.addExtractorRecipe(
                    GT_ModHandler.getModItem("Thaumcraft", "ItemEldritchObject", 1, 3),
                    new ItemStack(CppItems.Modifier, 8, 1),
                    2000,
                    128);
            GT_Values.RA.addExtractorRecipe(
                    new ItemStack(CppItems.Modifier, 1, 1),
                    GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 16L, 14),
                    2400,
                    128);

            GT_Values.RA.addAutoclaveRecipe(
                    new ItemStack(CppItems.Modifier, 16, 1),
                    Materials.UUMatter.getFluid(52L),
                    com.dreammaster.item.ItemList.PrimordialPearlFragment.getIS().splitStack(3),
                    10000,
                    24000,
                    384,
                    false);

            GT_Values.RA.addAutoclaveRecipe(
                    GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 32L, 14),
                    Materials.UUMatter.getFluid(500L),
                    new ItemStack(CppItems.Modifier, 1, 1),
                    3300,
                    2400,
                    120,
                    false);
            // Magic Modifier
            GT_Values.RA.addMultiblockChemicalRecipe(
                    new ItemStack[] { Materials.Iron.getDust(1), new ItemStack(CppItems.Modifier, 1, 1) },
                    new FluidStack[] { Materials.UUMatter
                            .getFluid(Materials.Thaumium.getNeutrons() + Materials.Thaumium.getProtons()) },
                    new FluidStack[] {},
                    new ItemStack[] { Materials.Thaumium.getDust(1) },
                    240,
                    120);
            GT_Values.RA.addMultiblockChemicalRecipe(
                    new ItemStack[] { Materials.Thaumium.getDust(1), new ItemStack(CppItems.Modifier, 1, 1) },
                    new FluidStack[] { Materials.UUMatter
                            .getFluid(Materials.Arsenic.getNeutrons() + Materials.Arsenic.getProtons()) },
                    new FluidStack[] {},
                    new ItemStack[] { Materials.Void.getDust(1) },
                    240,
                    480);
            GT_Values.RA.addMultiblockChemicalRecipe(
                    new ItemStack[] { Materials.Void.getDust(1), new ItemStack(CppItems.Modifier, 2, 1) },
                    new FluidStack[] { Materials.UUMatter
                            .getFluid(Materials.Indium.getNeutrons() + Materials.Indium.getProtons()) },
                    new FluidStack[] {},
                    new ItemStack[] { Materials.Shadow.getDust(1) },
                    240,
                    7680);
            GT_Values.RA.addMultiblockChemicalRecipe(
                    new ItemStack[] { Materials.Shadow.getDust(1), new ItemStack(CppItems.Modifier, 16, 1) },
                    new FluidStack[] { Materials.UUMatter
                            .getFluid((Materials.Osmium.getNeutrons() + Materials.Osmium.getProtons()) * 1000) },
                    new FluidStack[] {},
                    new ItemStack[] { Materials.Ichorium.getIngots(1) },
                    1800,
                    30720);
        }

        // Magic Modifier + Space Modifier
        GT_Values.RA.addMultiblockChemicalRecipe(
                new ItemStack[] { new ItemStack(CppItems.Modifier, 16, 0), new ItemStack(CppItems.Modifier, 4, 1) },
                new FluidStack[] { Materials.Platinum.getMolten(288L), Materials.MeteoricIron.getMolten(144L),
                        Materials.UUMatter
                                .getFluid(Materials.Platinum.getProtons() + Materials.Platinum.getNeutrons()) },
                new FluidStack[] {},
                new ItemStack[] { Materials.Mytryl.getDust(1) },
                1800,
                7680);
        // coral buff
        GT_Values.RA.addAutoclaveSpaceRecipe(
                GT_ModHandler.getModItem("BiomesOPlenty", "coral1", 64L, 15),
                Materials.UUMatter.getFluid(2L),
                Materials.Sunnarium.getDust(4),
                10000,
                15000,
                7680,
                true);

        if (Mods.BiomesOPlenty.isModLoaded()) {
            GT_ModHandler.addCompressionRecipe(
                    GT_ModHandler.getModItem("BiomesOPlenty", "treeMoss", 8L),
                    Ic2Items.plantBall.copy());
        }

        // Not a noob trophy.
        GT_Values.RA.addExtruderRecipe(
                Materials.Neutronium.getBlocks(64),
                Materials.Neutronium.getBlocks(64),
                CppItems.Trophy,
                2147483647,
                8);

        GT_Values.RA.addFluidExtractionRecipe(
                new ItemStack(CppItems.Modifier, 1, 0),
                GT_Values.NI,
                Materials.UUMatter.getFluid(2L),
                5000,
                128,
                4);

        // Chem Refine
        GT_Values.RA.addMultiblockChemicalRecipe(
                new ItemStack[] { new ItemStack(CppItems.Modifier, 9, 0),
                        GT_ModHandler.getModItem("GalacticraftCore", "item.meteoricIronRaw", 1L, 0) },
                new FluidStack[] { Materials.Water.getFluid(1000L) },
                new FluidStack[] {},
                new ItemStack[] { Materials.MeteoricIron.getDust(4) },
                240,
                480);
        GT_Values.RA.addMultiblockChemicalRecipe(
                new ItemStack[] { new ItemStack(CppItems.Modifier, 9, 0),
                        GT_ModHandler.getModItem("GalacticraftMars", "item.null", 1L, 0) },
                new FluidStack[] { Materials.Water.getFluid(1000L) },
                new FluidStack[] {},
                new ItemStack[] { Materials.Desh.getDust(4) },
                240,
                480);
        GT_Values.RA.addMultiblockChemicalRecipe(
                new ItemStack[] { new ItemStack(CppItems.Modifier, 9, 0),
                        GT_OreDictUnificator.get(OrePrefixes.crushed.get(Materials.MeteoricIron), 1) },
                new FluidStack[] { Materials.Water.getFluid(1000L) },
                new FluidStack[] {},
                new ItemStack[] {
                        GT_OreDictUnificator.get(OrePrefixes.crushedPurified.get(Materials.MeteoricIron), 4) },
                240,
                480);
        GT_Values.RA.addMultiblockChemicalRecipe(
                new ItemStack[] { new ItemStack(CppItems.Modifier, 9, 0),
                        GT_OreDictUnificator.get(OrePrefixes.crushed.get(Materials.Desh), 1) },
                new FluidStack[] { Materials.Water.getFluid(1000L) },
                new FluidStack[] {},
                new ItemStack[] { GT_OreDictUnificator.get(OrePrefixes.crushedPurified.get(Materials.Desh), 4) },
                240,
                480);
        GT_Values.RA.addMultiblockChemicalRecipe(
                new ItemStack[] { new ItemStack(CppItems.Modifier, 9, 0),
                        GT_OreDictUnificator.get(OrePrefixes.crushed.get(Materials.Oriharukon), 1) },
                new FluidStack[] { Materials.Water.getFluid(1000L) },
                new FluidStack[] {},
                new ItemStack[] { GT_OreDictUnificator.get(OrePrefixes.crushedPurified.get(Materials.Oriharukon), 4) },
                240,
                480);
        GT_Values.RA.addMultiblockChemicalRecipe(
                new ItemStack[] { new ItemStack(CppItems.Modifier, 9, 0),
                        GT_OreDictUnificator.get(OrePrefixes.crushed.get(Materials.Ledox), 1) },
                new FluidStack[] { Materials.Water.getFluid(1000L) },
                new FluidStack[] {},
                new ItemStack[] { GT_OreDictUnificator.get(OrePrefixes.crushedPurified.get(Materials.Ledox), 4) },
                240,
                480);
        GT_Values.RA.addMultiblockChemicalRecipe(
                new ItemStack[] { new ItemStack(CppItems.Modifier, 9, 0),
                        GT_OreDictUnificator.get(OrePrefixes.crushed.get(Materials.CallistoIce), 1) },
                new FluidStack[] { Materials.Water.getFluid(1000L) },
                new FluidStack[] {},
                new ItemStack[] { GT_OreDictUnificator.get(OrePrefixes.crushedPurified.get(Materials.CallistoIce), 4) },
                240,
                480);
        GT_Values.RA.addMultiblockChemicalRecipe(
                new ItemStack[] { new ItemStack(CppItems.Modifier, 9, 0),
                        GT_OreDictUnificator.get(OrePrefixes.crushed.get(Materials.BlackPlutonium), 1) },
                new FluidStack[] { Materials.Water.getFluid(1000L) },
                new FluidStack[] {},
                new ItemStack[] {
                        GT_OreDictUnificator.get(OrePrefixes.crushedPurified.get(Materials.BlackPlutonium), 4) },
                240,
                30720);
        GT_Values.RA.addMultiblockChemicalRecipe(
                new ItemStack[] { new ItemStack(CppItems.Modifier, 9, 0),
                        GT_OreDictUnificator.get(OrePrefixes.crushed.get(Materials.DeepIron), 1) },
                new FluidStack[] { Materials.Water.getFluid(1000L) },
                new FluidStack[] {},
                new ItemStack[] { GT_OreDictUnificator.get(OrePrefixes.crushedPurified.get(Materials.DeepIron), 4) },
                240,
                30720);

        // Potions from Netherberries
        GT_Values.RA.addBrewingRecipe(
                GT_ModHandler.getModItem("Natura", "berry.nether", 16L, 0),
                Materials.Water.getFluid(1000L).getFluid(),
                FluidRegistry.getFluid("potion.regen"),
                false);
        GT_Values.RA.addBrewingRecipe(
                GT_ModHandler.getModItem("Natura", "berry.nether", 16L, 1),
                Materials.Water.getFluid(1000L).getFluid(),
                FluidRegistry.getFluid("potion.nightvision"),
                false);
        GT_Values.RA.addBrewingRecipe(
                GT_ModHandler.getModItem("Natura", "berry.nether", 16L, 2),
                Materials.Water.getFluid(1000L).getFluid(),
                FluidRegistry.getFluid("potion.speed"),
                false);
        GT_Values.RA.addBrewingRecipe(
                GT_ModHandler.getModItem("Natura", "berry.nether", 16L, 3),
                Materials.Water.getFluid(1000L).getFluid(),
                FluidRegistry.getFluid("potion.strength"),
                false);
    }
}
