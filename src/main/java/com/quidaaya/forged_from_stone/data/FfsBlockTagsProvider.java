package com.quidaaya.forged_from_stone.data;

import com.quidaaya.forged_from_stone.ForgedFromStone;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static com.quidaaya.forged_from_stone.tags.FfsBlockTagsRegister.STRIPPED_LOGS;

public class FfsBlockTagsProvider extends BlockTagsProvider {
    public FfsBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ForgedFromStone.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        HolderLookup.RegistryLookup<Block> blockRegistryLookup = pProvider.lookupOrThrow(Registries.BLOCK);
        blockRegistryLookup = blockRegistryLookup.filterElements(block -> block.getName().getString().contains("stripped"));
        this.tag(STRIPPED_LOGS).addAll(blockRegistryLookup.listElementIds().toList());
    }
}
