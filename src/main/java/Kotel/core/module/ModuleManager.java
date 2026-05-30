package Kotel.core.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public final class ModuleManager {
    private static final List<Module> MODULES = new ArrayList<>();

    public ModuleManager() {
    }

    public static void clear() {
        MODULES.clear();
    }

    public static void register(Module module) {
        if (byName(module.name()).isPresent()) return;
        MODULES.add(module);
        MODULES.sort(Comparator.comparing(Module::category).thenComparing(Module::name));
    }

    public static List<Module> modules() {
        return Collections.unmodifiableList(MODULES);
    }

    public static Optional<Module> byName(String name) {
        return MODULES.stream().filter(module -> module.name().equalsIgnoreCase(name)).findFirst();
    }

    public static List<Module> byCategory(ModuleCategory category) {
        return MODULES.stream().filter(module -> module.category() == category).toList();
    }

    public void registerDefaults() {
        Kotel.client.KotelMod.registerModules();
    }

    public List<Module> getModules() {
        return modules();
    }
}
