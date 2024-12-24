package com.quidaaya.forged_from_stone.client.gui.creative;

import com.quidaaya.forged_from_stone.ForgedFromStone;
import com.quidaaya.forged_from_stone.item.FfsItemRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FfsCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(
            Registries.CREATIVE_MODE_TAB,
            ForgedFromStone.MODID
    );

    public static final DeferredHolder<CreativeModeTab, ?> FFS_CREATIVE_TAB = CREATIVE_MODE_TABS.register(
            ForgedFromStone.MODID,
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("Forged From Stone"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(FfsItemRegister.BASE_LOG.get());
                    })
                    .build()
    );

    public static void registerToEventBus(IEventBus bus) {
        CREATIVE_MODE_TABS.register(bus);
    }
}
