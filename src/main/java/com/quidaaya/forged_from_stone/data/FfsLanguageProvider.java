package com.quidaaya.forged_from_stone.data;

import com.quidaaya.forged_from_stone.ForgedFromStone;
import com.quidaaya.forged_from_stone.item.FfsItemRegister;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class FfsLanguageProvider extends LanguageProvider {
    public FfsLanguageProvider(PackOutput output) {
        super(output, ForgedFromStone.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        this.add(FfsItemRegister.BASE_LOG.get(), "Base Log");
    }
}
