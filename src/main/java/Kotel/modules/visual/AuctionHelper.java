package Kotel.modules.visual;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class AuctionHelper extends PremiumModule {
    public AuctionHelper() {
        super("AuctionHelper", "Auction sorting, price notes, and listing reminders.", ModuleCategory.VISUAL);
        mode("Sort", "Profit", "Profit", "Price", "Time");
        bool("Highlight Deals", true);
        status("Configured");
    }
}
