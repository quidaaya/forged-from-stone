package com.quidaaya.forged_from_stone.item;

import com.quidaaya.forged_from_stone.ForgedFromStone;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FfsItemRegister {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, ForgedFromStone.MODID);

    public static final DeferredHolder<Item, ?> BASE_LOG = ITEMS.register(
            "logs/base_log",
            () -> new Item(
                    new Item.Properties()
            )
    );

    public static void registerToEventBus(IEventBus bus) {
        ITEMS.register(bus);
    }
}