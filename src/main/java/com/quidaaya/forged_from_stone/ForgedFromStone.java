package com.quidaaya.forged_from_stone;

import com.quidaaya.forged_from_stone.client.gui.creative.FfsCreativeModeTabs;
import com.quidaaya.forged_from_stone.item.FfsItemRegister;
import com.quidaaya.forged_from_stone.loot.FfsLootRegister;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(ForgedFromStone.MODID)
public class ForgedFromStone
{
    public static final String MODID = "forged_from_stone";

    public static final Logger LOGGER = LogUtils.getLogger();

    public ForgedFromStone(IEventBus modBus, ModContainer modContainer) {
        modBus.addListener(this::commonSetup);
        FfsCreativeModeTabs.registerToEventBus(modBus);
        FfsItemRegister.registerToEventBus(modBus);
        FfsLootRegister.registerToEventBus(modBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }
}
