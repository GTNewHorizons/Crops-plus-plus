package com.github.bartimaeusnek.cropspp.crops.cpp.trees;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.croploadcore.config;
import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.Cropspp;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import ic2.api.crops.CropCard;

// This class is no longer in use
@Deprecated
public class InstalledTreesGetter {

    public static List<CropCard> saved;
    public static List<ItemStack> BaseSeed;
    public static List<String> savedNames;
    public static List<ItemStack> savedDrop;
    public static BonsaiRenderer renderer;
    private static String cfg;

    public static boolean check_bonsai(FMLPreInitializationEvent preinit) {
        cfg = preinit.getModConfigurationDirectory().getPath();
        cfg = cfg.replace("config", "BppBonsais_");
        config c = new config(preinit, "berriespp.cfg");
        ConfigValues.ayo_bonsai = c.tConfig.get("System", "Bonsai Generation", false).getBoolean(true);
        return ConfigValues.ayo_bonsai;
    }

    static BufferedImage make_crop_IIcons(ItemStack Sapling, String name) throws IOException {

        // Berriespp.bpplogger.info("IIcon: "+IIconpath);
        // Berriespp.bpplogger.info("Name: "+name);

        BufferedImage overlay;
        BufferedImage image = ImageIO
                .read(InstalledTreesGetter.class.getResource("/assets/bpp/textures/blocks/crop/blockCrop.empty.1.png"));
        // if (IICON.getIconName().contains(":"))
        overlay = ImageIO.read(
                Sapling.getClass().getResource(
                        new String(
                                "/assets/" + Sapling.getItem().getClass().getName().replace(":", "/textures/blocks/")
                                        + ".png")));
        // else //Vanilla workaround
        // overlay = ImageIO.read(IICON.getClass().getResource(new
        // String("/assets/minecraft/textures/blocks/"+IICON.getIconName()+".png")));
        // Berriespp.bpplogger.info("Read this image: "+new String("/assets/"+IIconpath.getIconName().replace(":",
        // "/textures/blocks/")+".png"));

        int w = Math.max(image.getWidth(), overlay.getWidth());
        int h = Math.max(image.getHeight(), overlay.getHeight());
        BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics g = combined.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.drawImage(overlay, 0, 0, null);

        // Berriespp.bpplogger.info(combined.toString());
        // ImageIO.write(combined, "PNG", new File(cfg+name.replaceAll(":", "_")+".png"));
        // Berriespp.bpplogger.info(cfg+name+".png");
        // return cfg+name+".png";
        return combined;
    }

    public static void InstalledTreesGet() {

        Cropspp.cpplogger.info("BonsaiGen Started");

        List<Item> subtypes = new ArrayList<Item>();
        List<Item> subtypesLog = new ArrayList<Item>();

        for (int i = 0; i < OreDictionary.getOres("treeSapling").size(); i++) {
            subtypes.add(i, OreDictionary.getOres("treeSapling").get(i).getItem());
        }

        for (int i = 0; i < OreDictionary.getOres("logWood").size(); i++) {
            subtypesLog.add(i, OreDictionary.getOres("logWood").get(i).getItem());
        }

        List<String> SubItemNames = new ArrayList<String>();
        List<ItemStack> helplist = OreDict.get_subtypes(subtypes, false);
        for (int i = 0; i < helplist.size(); i++) {
            SubItemNames.add(helplist.get(i).getDisplayName());
        }

        BaseSeed = new ArrayList<ItemStack>(helplist);

        List<ItemStack> helplistLog = synch_wood(helplist, OreDict.get_subtypes(subtypesLog, false));

        List<CropCard> CropCards = new ArrayList<CropCard>();

        for (int i = 0; i < SubItemNames.size(); i++) {

            // prevent TC from making Shimmerleaf,Cinderpearl,Vishroom or Ethereal Bloom Bonsais
            if (!(SubItemNames.get(i).contains("Shimmerleaf") || SubItemNames.get(i).contains("Cinderpearl"))
                    && !(SubItemNames.get(i).contains("Vishroom") || SubItemNames.get(i).contains("Ethereal Bloom"))) {

                // rename ic2's Rubberwood
                if (SubItemNames.get(i).equals("ic2.blockRubSapling")) SubItemNames.set(i, "Rubber Wood Sapling");
                if (helplist.size() != helplistLog.size()) {
                    Cropspp.cpplogger.error(
                            "Saplings/Logs out of synch!" + " Bonsaigen disabled!"
                                    + "\n helplist contains: "
                                    + Integer.toString(helplist.size())
                                    + "\n helplistLog contains: "
                                    + Integer.toString(helplistLog.size())
                                    + "\n helplist contains: ");
                    for (int j = 0; j < helplist.size(); j++) Cropspp.cpplogger.info(helplist.get(j).getDisplayName());
                    Cropspp.cpplogger.info("helplistLog contains: ");
                    for (int j = 0; j < helplistLog.size(); j++)
                        Cropspp.cpplogger.info(helplistLog.get(j).getDisplayName());
                    break;
                }
                // Berriespp.bpplogger.info(SubItemNames.get(i)+" CropCard was created");
            }
        }
        savedNames = new ArrayList<String>(SubItemNames);
        savedDrop = new ArrayList<ItemStack>(helplistLog);
        Set<CropCard> s = new HashSet<CropCard>(CropCards);
        saved = new ArrayList<CropCard>(s);
        Cropspp.cpplogger.info("preparing to create bonsais!");
    }

    private static List<ItemStack> synch_wood(List<ItemStack> subtypes1, List<ItemStack> subtypes2) {
        List<ItemStack> itemsgot = new ArrayList<ItemStack>();

        // <ItemStack> s1 = new HashSet<ItemStack>(subtypes1);
        // List<ItemStack> cleansubtypes1 = new ArrayList<ItemStack>(s1);

        // Set<ItemStack> s2 = new HashSet<ItemStack>(subtypes2);
        // List<ItemStack> cleansubtypes2 = new ArrayList<ItemStack>(s2);

        boolean synched = false;

        for (int i = 0; i < subtypes1.size(); i++) {
            for (int j = 0; j < subtypes2.size(); j++) {

                if (!synched && (subtypes2.get(j).getDisplayName().contains("Wood")
                        && !subtypes2.get(j).getDisplayName().contains("Log"))) {
                    // Berriespp.bpplogger.info(subtypes2.get(j).getDisplayName()+" conatins Wood.");
                    if (subtypes1.get(i).getDisplayName().replace("Sapling", "")
                            .equals(subtypes2.get(j).getDisplayName().replace("Wood", ""))) {
                        itemsgot.add(subtypes2.get(j));
                        synched = true;
                        // Berriespp.bpplogger.info(subtypes2.get(j).getDisplayName()+" synched to
                        // "+subtypes1.get(i).getDisplayName());
                    }
                } else if (!synched && (subtypes2.get(j).getDisplayName().contains("Log")
                        && !subtypes2.get(j).getDisplayName().contains("Wood"))) {
                            // Berriespp.bpplogger.info(subtypes2.get(j).getDisplayName()+" conatins Log.");
                            if (subtypes1.get(i).getDisplayName().replace("Sapling", "")
                                    .equals(subtypes2.get(j).getDisplayName().replace("Log", ""))) {
                                itemsgot.add(subtypes2.get(j));
                                synched = true;
                                // Berriespp.bpplogger.info(subtypes2.get(j).getDisplayName()+" synched to
                                // "+subtypes1.get(i).getDisplayName());
                            }
                        }
            }
            if (!synched) itemsgot.add(subtypes2.get(0));
            synched = false;
        }

        // Berriespp.bpplogger.info("synch completed");
        /*
         * for (int k = 0; k < itemsgot.size(); k++) {
         * //Berriespp.bpplogger.info("item synched: "+itemsgot.get(k).getDisplayName()); }
         */
        return itemsgot;
    }
}
