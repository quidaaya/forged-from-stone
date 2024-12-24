package com.quidaaya.forged_from_stone.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class FfsBlockTagsRegister {
    public static final TagKey<Block> STRIPPED_LOGS = TagKey.create(
            Registries.BLOCK,
            ResourceLocation.fromNamespaceAndPath("forged_from_stone", "stripped_logs")
    );
}
