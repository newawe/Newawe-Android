package com.inmobi.rendering;

public class RenderingProperties {
    private PlacementType f1422a;

    public enum PlacementType {
        FULL_SCREEN,
        INLINE
    }

    public RenderingProperties(PlacementType placementType) {
        this.f1422a = placementType;
    }

    public PlacementType m1685a() {
        return this.f1422a;
    }
}
