package com.github.genehyde.ai_blocks;

import com.github.genehyde.ai_blocks.init.ModEntities;
import com.github.genehyde.ai_blocks.render.HandRender;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = com.github.genehyde.ai_blocks.Main.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSideEventBusSubscriber {

    @SubscribeEvent
    public static void clientSetup(FMLCommonSetupEvent setupEvent) {
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.HAND_ENTITY, HandRender.RenderHandFactory.INSTANCE);
    }
}
