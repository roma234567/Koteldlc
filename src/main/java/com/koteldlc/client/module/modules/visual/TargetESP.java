package com.koteldlc.client.module.modules.visual;

public class TargetESP extends BaseEspModule {
    public TargetESP() {
        super("TargetESP", "Яркая подсветка текущей цели KillAura/AimAssist", 0xFFFF00FF);
        blink.setValue(true);
        thickness.setValue(3.5);
    }
}
