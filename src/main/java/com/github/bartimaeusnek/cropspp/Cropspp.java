package com.github.bartimaeusnek.cropspp;

import com.github.bartimaeusnek.croploadcore.ModsLoaded;
import com.github.bartimaeusnek.cropspp.GTHandler.CropItemList;
import com.github.bartimaeusnek.cropspp.GTHandler.GTHandler;
import com.github.bartimaeusnek.cropspp.GTHandler.machines.CropGeneExtractor;
import com.github.bartimaeusnek.cropspp.GTHandler.machines.CropReplicator;
import com.github.bartimaeusnek.cropspp.GTHandler.machines.CropSynthesiser;
import com.github.bartimaeusnek.cropspp.GTHandler.machines.CropWeedPicker;
import com.github.bartimaeusnek.cropspp.commands.EnableDebug;
import com.github.bartimaeusnek.cropspp.croploader.CropLoader;
import com.github.bartimaeusnek.cropspp.fluids.CppFluids;
import com.github.bartimaeusnek.cropspp.items.CppItems;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(
        modid = Cropspp.modID, name = Cropspp.name, version = Cropspp.version,
        dependencies = "required-after:IC2; "
                + "required-after:croploadcore@0.1.4; "
                + "after:GalacticraftCore; "
                + "after:Mantle; "
                + "after:Natura; "
                + "after:TConstruct; "
                + "after:BiomesOPlenty; "
                + "after:Thaumcraft; "
                + "after:ExtraTrees; "
                + "after:witchery; "
                + "after:gregtech; "
                + "after:TwilightForest; "
)

public final class Cropspp {
    public static final String name = "Crops++";
    public static final String version = "@version@";
    public static final String modID = "berriespp";
    public static final Logger cpplogger = LogManager.getLogger(name);

    @Instance(value = "Crops++")
    public static Cropspp instance;


    @EventHandler
    public void preInit(FMLPreInitializationEvent preinit) {
        CropLoader.load(preinit);
        if (ModsLoaded.GT) {
            new CropWeedPicker(12528, "Basic CropWeedPicker", "Basic CropWeedPicker", 1);
            CropItemList.cropGeneExtractorLV.set(new CropGeneExtractor(12501, "Basic Crop Gene Extractor", "Basic Crop Gene Extractor", 1).getStackForm(1L));
            CropItemList.cropGeneExtractorMV.set(new CropGeneExtractor(12502, "Advanced Crop Gene Extractor I", "Advanced Crop Gene Extractor I", 2).getStackForm(1L));
            CropItemList.cropGeneExtractorHV.set(new CropGeneExtractor(12503, "Advanced Crop Gene Extractor II", "Advanced Crop Gene Extractor II", 3).getStackForm(1L));
            CropItemList.cropGeneExtractorEV.set(new CropGeneExtractor(12504, "Advanced Crop Gene Extractor III", "Advanced Crop Gene Extractor III", 4).getStackForm(1L));
            CropItemList.cropGeneExtractorIV.set(new CropGeneExtractor(12505, "Advanced Crop Gene Extractor IV", "Advanced Crop Gene Extractor IV", 5).getStackForm(1L));
            CropItemList.cropGeneExtractorLuV.set(new CropGeneExtractor(12506, "Advanced Crop Gene Extractor V", "Advanced Crop Gene Extractor V", 6).getStackForm(1L));
            CropItemList.cropGeneExtractorZPM.set(new CropGeneExtractor(12507, "Advanced Crop Gene Extractor VI", "Advanced Crop Gene Extractor VI", 7).getStackForm(1L));
            CropItemList.cropGeneExtractorUV.set(new CropGeneExtractor(12508, "Advanced Crop Gene Extractor VII", "Advanced Crop Gene Extractor VII", 8).getStackForm(1L));
            if (ModsLoaded.dreamcraft)
                CropItemList.cropGeneExtractorUHV.set(new CropGeneExtractor(12509, "Advanced Crop Gene Extractor IIX", "Advanced Crop Gene Extractor IIX", 9).getStackForm(1L));

            CropItemList.cropReplicatorLV.set(new CropReplicator(12510, "Basic Crop Replicator", "Basic Crop Replicator", 1).getStackForm(1L));
            CropItemList.cropReplicatorMV.set(new CropReplicator(12511, "Advanced Crop Replicator I", "Advanced Crop Replicator I", 2).getStackForm(1L));
            CropItemList.cropReplicatorHV.set(new CropReplicator(12512, "Advanced Crop Replicator II", "Advanced Crop Replicator II", 3).getStackForm(1L));
            CropItemList.cropReplicatorEV.set(new CropReplicator(12513, "Advanced Crop Replicator III", "Advanced Crop Replicator III", 4).getStackForm(1L));
            CropItemList.cropReplicatorIV.set(new CropReplicator(12514, "Advanced Crop Replicator IV", "Advanced Crop Replicator IV", 5).getStackForm(1L));
            CropItemList.cropReplicatorLuV.set(new CropReplicator(12515, "Advanced Crop Replicator V", "Advanced Crop Replicator V", 6).getStackForm(1L));
            CropItemList.cropReplicatorZPM.set(new CropReplicator(12516, "Advanced Crop Replicator VI", "Advanced Crop Replicator VI", 7).getStackForm(1L));
            CropItemList.cropReplicatorUV.set(new CropReplicator(12517, "Advanced Crop Replicator VII", "Advanced Crop Replicator VII", 8).getStackForm(1L));
            if (ModsLoaded.dreamcraft)
                CropItemList.cropReplicatorUHV.set(new CropReplicator(12518, "Advanced Crop Replicator IIX", "Advanced Crop Replicator IIX", 9).getStackForm(1L));

            CropItemList.cropSynthesiserLV.set(new CropSynthesiser(12519, "Basic Crop Synthesiser", "Basic Crop Synthesiser", 1).getStackForm(1L));
            CropItemList.cropSynthesiserMV.set(new CropSynthesiser(12520, "Advanced Crop Synthesiser I", "Advanced Crop Synthesiser I", 2).getStackForm(1L));
            CropItemList.cropSynthesiserHV.set(new CropSynthesiser(12521, "Advanced Crop Synthesiser II", "Advanced Crop Synthesiser II", 3).getStackForm(1L));
            CropItemList.cropSynthesiserEV.set(new CropSynthesiser(12522, "Advanced Crop Synthesiser III", "Advanced Crop Synthesiser III", 4).getStackForm(1L));
            CropItemList.cropSynthesiserIV.set(new CropSynthesiser(12523, "Advanced Crop Synthesiser IV", "Advanced Crop Synthesiser IV", 5).getStackForm(1L));
            CropItemList.cropSynthesiserLuV.set(new CropSynthesiser(12524, "Advanced Crop Synthesiser V", "Advanced Crop Synthesiser V", 6).getStackForm(1L));
            CropItemList.cropSynthesiserZPM.set(new CropSynthesiser(12525, "Advanced Crop Synthesiser VI", "Advanced Crop Synthesiser VI", 7).getStackForm(1L));
            CropItemList.cropSynthesiserUV.set(new CropSynthesiser(12526, "Advanced Crop Synthesiser VII", "Advanced Crop Synthesiser VII", 8).getStackForm(1L));
            if (ModsLoaded.dreamcraft)
                CropItemList.cropSynthesiserUHV.set(new CropSynthesiser(12527, "Advanced Crop Synthesiser IIX", "Advanced Crop Synthesiser IIX", 9).getStackForm(1L));
        }
    }

    @EventHandler
    public void init(FMLInitializationEvent init) {
        if (ModsLoaded.GT) {
            final CppFluids CppFluids = new CppFluids();
        }
        CppItems.register_Items();
        CppItems.OreDictItems();
        CppItems.register_recipes();
        BiomeHumidityBonus.apply();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent postinit) {
        CropLoader.register();
        CropLoader.registerBaseSeed();
        if (ModsLoaded.GT) {
            final GTHandler GTHandler = new GTHandler();
            GTHandler.run();
        }
    }

    @EventHandler
    public void onFMLServerStart(FMLServerStartingEvent event) {
        event.registerServerCommand(new EnableDebug());
    }
}
