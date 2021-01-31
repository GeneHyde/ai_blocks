package com.github.genehyde.ai_blocks;

import com.github.genehyde.ai_blocks.block.SimpleAiBlock;
import com.github.genehyde.ai_blocks.init.ModEntities;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistryEntry;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(Main.MODID)
public class Main {
    public static final String MODID = "ai_blocks";

    public Main() {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class
    // (this is subscribing to the MOD Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {

        @SubscribeEvent
        public static void onRegisterEntity(final RegistryEvent.Register<EntityType<?>> event) {
            ModEntities.HAND_ENTITY.setRegistryName(MODID, "hand");

            event.getRegistry().registerAll(ModEntities.HAND_ENTITY);
        }

        @SubscribeEvent
        public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
            event.getRegistry().registerAll(
                    setup(new SimpleAiBlock(Block.Properties.create(Material.ROCK)
                            .hardnessAndResistance(3.0F, 3.0F)), "ai_block")
            );
        }

        public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
            return setup(entry, new ResourceLocation(MODID, name));
        }

        public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
            entry.setRegistryName(registryName);
            return entry;
        }
    }
}
