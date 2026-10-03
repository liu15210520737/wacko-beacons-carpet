package net.scoobis.carpet;

import carpet.api.settings.Rule;
import carpet.api.settings.RuleCategory;

public class WackoBeaconSettings {
	public static final String WACKO = "wacko-beacons";

	@Rule(categories = {RuleCategory.FEATURE, RuleCategory.EXPERIMENTAL, WACKO})
	public static boolean wackoBeacons = false;
}
