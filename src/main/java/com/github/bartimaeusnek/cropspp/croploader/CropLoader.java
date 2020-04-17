package com.github.bartimaeusnek.cropspp.croploader;

import com.github.bartimaeusnek.croploadcore.ModsLoaded;
import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.croploadcore.config;
import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.crops.cpp.*;
import com.github.bartimaeusnek.cropspp.crops.natura.*;
import com.github.bartimaeusnek.cropspp.crops.witchery.GarlicCrop;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import ic2.api.crops.CropCard;
import ic2.api.crops.Crops;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static com.github.bartimaeusnek.cropspp.ConfigValues.c;

//IC2API
//ItemsFromAPIs

public class CropLoader {
    private static List<Boolean> bHasCropObj = new ArrayList<Boolean>();
    private static List<CropLoader> list = cropLoader();
    private CropCard cropObj;
    private ItemStack baseseed;

    /*
     * This Class Loades Crops with Base Seed.
     * Call it with:
     * CropLoader.load(preinit);
     * at the preinit phase to load the crops into the config.
     * Then call it at postinit with
     * CropLoader.register();
     * to load the Crops into the game.
     */


    public CropLoader(CropCard cropObj) {
        this.cropObj = cropObj;
    }

    public CropLoader(CropCard cropObj, ItemStack baseseed) {
        this.cropObj = cropObj;
        this.baseseed = baseseed;
    }

    public static CropCard CropunpackerCC(CropLoader inp) {
        return inp.cropObj;
    }

    private static ItemStack CropunpackerCG(CropLoader inp) {
        return inp.baseseed;
    }

    private static CropLoader CropHelper(CropCard cropObj) {
        return new CropLoader(cropObj, OreDict.ISget("crop" + cropObj.name()));
    }

    public final static List<CropLoader> cropLoader() {

        List<CropLoader> p = new ArrayList<CropLoader>();

        /*
         * Add your crops with base seed here by
         * p.add(new CropLoader(new YourCropClass(),YourItem));
         */

        if (Loader.isModLoaded("TwilightForest")) {
            p.addAll(TwilightForestLoader.load());
        }
        if (ModsLoaded.dreamcraft) {
            p.addAll(DreamCraftLoader.load());
        }
        if (ModsLoaded.GT) {
            p.addAll(GTLoader.load());
        }
        if (ModsLoaded.Natura)
            p.addAll(NaturaLoader.load());
        else {
            p.add(new CropLoader(new SaguaroCrop(), null));
        }
        if (ModsLoaded.Natura || ModsLoaded.PHC) {
            p.add(new CropLoader(new CottonCrop(), null));
        }

        if (ModsLoaded.TConstruct) {
            p.addAll(TConstructLoader.load());
        }
        if (ModsLoaded.BoP) {
            p.addAll(BoPLoader.BoPLoaderList());
        }

        if (ModsLoaded.TC) {
            p.addAll(ThaumcraftLoader.load());
        }
        if (ModsLoaded.witchery) {
            p.addAll(WitcheryLoader.load());
        }
        if (ModsLoaded.witchery || ModsLoaded.PHC) {
            p.add(new CropLoader(new GarlicCrop(), null));
        }
        if (ModsLoaded.BoP || ModsLoaded.PHC) {
            p.add(new CropLoader(new BarleyCrop(), null));
        }

        p.add(new CropLoader(new VineCrop(), new ItemStack(Item.getItemById(106), 1, 0)));
        p.add(new CropLoader(new GrassCrop(), new ItemStack(Item.getItemById(31), 1, 1)));
        p.add(new CropLoader(new CactiCrop(), new ItemStack(Item.getItemById(81), 1, 0)));
        p.add(new CropLoader(new WaterlillyCrop(), new ItemStack(Item.getItemById(111), 2)));
        p.add(new CropLoader(new PapyrusCrop(), null));
        p.add(new CropLoader(new GoldfishCrop(), null));
        p.add(new CropLoader(new SugarBeetCrop(), null));

        p.add(new CropLoader(new HuckleberryCrop(), null));
        p.add(new CropLoader(new StrawberryCrop(), null));
        p.add(new CropLoader(new MaloberryCrop(), null));
        p.add(new CropLoader(new BlackberryCrop(), null));
        p.add(new CropLoader(new BlueberryCrop(), null));
        p.add(new CropLoader(new RaspberryCrop(), null));
        //p.add(new WeedCrop());

	/*if (lokko12.berriespp.ConfigValures.ayo_bonsai)
		if (InstalledTreesGetter.savedNames != null)	
	for(int i=0; i < InstalledTreesGetter.savedNames.size(); ++i) {
		if( Operators.AND(Operators.NOR( InstalledTreesGetter.savedNames.get(i).contains("Shimmerleaf"),InstalledTreesGetter.savedNames.get(i).contains("Cinderpearl") ) , Operators.NOR(InstalledTreesGetter.savedNames.get(i).contains("Vishroom"),InstalledTreesGetter.savedNames.get(i).contains("Ethereal Bloom")))) 
		{
			p.add(new CropLoader(new BasicBonsaiCrop(InstalledTreesGetter.savedNames.get(i),
					"bpp:"+InstalledTreesGetter.BaseSeed.get(i).getIconIndex().getIconName().replaceAll(":","_")
					,InstalledTreesGetter.savedDrop.get(i)),InstalledTreesGetter.BaseSeed.get(i)));
		}
		
			
	}
		else
			Berriespp.bpplogger.info("Something wrent wrong at getting Trees, BonsaiGen Disabled!");*/
        return p;
    }

    private final static List<CropCard> cropObjs() {
        List<CropCard> p = new ArrayList<CropCard>();
        for (int i = 0; i < list.size(); ++i) {
            p.add(CropunpackerCC(list.get(i)));
        }
        return p;
    }

    private final static List<ItemStack> setBaseSeed() {
        List<ItemStack> p = new ArrayList<ItemStack>();
        for (int i = 0; i < list.size(); ++i) {
            p.add(CropunpackerCG(list.get(i)));
        }
        return p;
    }

    private final static List<String> setnames() {
        List<String> s = new ArrayList<String>();
        for (int i = 0; i < list.size(); ++i) {
            s.add(cropObjs().get(i).name());
        }
        return s;
    }

    public static void load(FMLPreInitializationEvent preinit) {
        c = new config(preinit, "berriespp.cfg");
        c.tConfig.addCustomCategoryComment("System", "enable or disable system config:"
                + "\nDebug will set all crops groth duration to 1 and disable all requirements.(aka. \"Cheatmode\")"
                + "\nBonsai Generation will generate crops from saplings, WiP state. (disabled bc of bugs with metadata, but sure you can try it.)"
                + "\nWiP Items are not finished items."
                + "\nItems will enable/disable all items.");
        ConfigValues.debug = c.tConfig.get("System", "Debug", false).getBoolean(false);
        ConfigValues.WiPItems = c.tConfig.get("System", "WiP Items", false).getBoolean(false);
        ConfigValues.Items = c.tConfig.get("System", "Items", true).getBoolean(true);

        c.tConfig.addCustomCategoryComment("Crops", "enable single plants here:");

        for (int i = 0; i < list.size(); ++i) {
            bHasCropObj.add(c.tConfig.get("Crops", setnames().get(i), true).getBoolean(true));
        }
        bHasCropObj.add(c.tConfig.get("Crops", "Bonsai", true).getBoolean(true));
        c.tConfig.addCustomCategoryComment("Gain", "Set custom gain modifiers here:"
                + "\n Tinker's Construct Berries' Gain is not modified by All Crops."
                + "\n Primordial Berry's gain is absolut"
                + "\n Primordial Berry's growth time is divided by 4, in IC2 groth points. F.e. 10 = 40GP per groth-period"
                + "\n IC2 groth points are calculated by 3 + random 0-7 + statGrowth per 256ticks");
        ConfigValues.BerryGain = (float) c.tConfig.get("Gain", "All crops", (float) 1).getDouble(1);
        ConfigValues.TConstructBerryGain = (float) c.tConfig.get("Gain", "Tinker's Construct berries", (float) 1).getDouble(1);
        ConfigValues.PrimordialBerryGain = (float) c.tConfig.get("Gain", "Primordial Berry", (float) 0.5).getDouble(0.5);
        ConfigValues.PrimordialBerryGroth = c.tConfig.get("Gain", "Primordial Berry growth time", 125000).getInt(125000);
        if (c.tConfig.hasChanged())
            c.save();
    }

    public static void register() {
        for (int i = 0; i < list.size(); ++i) {
            if (bHasCropObj.get(i) && cropObjs().get(i) != null)
                Crops.instance.registerCrop(cropObjs().get(i));
        }
        if (bHasCropObj.get(bHasCropObj.size()-1)){
            Bonsais.registerAllBonais();
        }
    }

    public static void registerBaseSeed() {

        List<ItemStack> baseseed = new ArrayList<ItemStack>(setBaseSeed());

        for (int i = 0; i < list.size(); ++i) {
            if (baseseed.get(i) != null && cropObjs().get(i) != null)
                Crops.instance.registerBaseSeed(baseseed.get(i), cropObjs().get(i), 1, 1, 1, 1);
        }
    }
}