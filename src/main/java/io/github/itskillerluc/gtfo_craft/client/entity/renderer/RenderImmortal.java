package io.github.itskillerluc.gtfo_craft.client.entity.renderer;

import io.github.itskillerluc.gtfo_craft.client.entity.model.ModelImmortal;
import io.github.itskillerluc.gtfo_craft.client.entity.model.ModelTank;
import io.github.itskillerluc.gtfo_craft.entity.EntityImmortal;
import io.github.itskillerluc.gtfo_craft.entity.EntityTank;
import net.minecraft.client.renderer.entity.RenderManager;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RenderImmortal extends GeoEntityRenderer<EntityImmortal> {
    public RenderImmortal(RenderManager renderManager) {
        super(renderManager, new ModelImmortal());
    }
}
