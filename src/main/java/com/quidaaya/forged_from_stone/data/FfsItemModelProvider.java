package com.quidaaya.forged_from_stone.data;

import com.quidaaya.forged_from_stone.ForgedFromStone;
import com.quidaaya.forged_from_stone.item.FfsItemRegister;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class FfsItemModelProvider extends ItemModelProvider {
    public FfsItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ForgedFromStone.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        getBuilder("item/logs/base_log")
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", modLoc("item/logs/natural/dark_bark_light_wood/bark"))
                .texture("layer1", modLoc("item/logs/natural/medium_bark_dark_wood/wood"));
//        ResourceLocation base_log = ;
//        this.basicItem(base_log);
    }
}
