package com.quidaaya.forged_from_stone.loot;

import com.mojang.serialization.MapCodec;
import com.quidaaya.forged_from_stone.ForgedFromStone;
import com.quidaaya.forged_from_stone.loot.modifiers.FfsLogTagLootModifier;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class FfsLootRegister {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ForgedFromStone.MODID);

    public static final Supplier<MapCodec<FfsLogTagLootModifier>> TAG_LOG_REPLACEMENTS =
            GLOBAL_LOOT_MODIFIER_SERIALIZERS.register(
                "tag_log_replacements",
                () -> FfsLogTagLootModifier.FFS_LOG_LOOT_MODIFIER_MAP_CODEC
            );

    public static final Supplier<MapCodec<FfsLogTagLootModifier>> ITEM_LOG_REPLACEMENTS =
            GLOBAL_LOOT_MODIFIER_SERIALIZERS.register(
                "item_log_replacements",
                () -> FfsLogTagLootModifier.FFS_LOG_LOOT_MODIFIER_MAP_CODEC
            );

    public static void registerToEventBus(IEventBus bus) {
        GLOBAL_LOOT_MODIFIER_SERIALIZERS.register(bus);
    }
}
