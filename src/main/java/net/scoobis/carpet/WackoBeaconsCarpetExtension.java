package net.scoobis.carpet;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.api.settings.SettingsManager;
import carpet.utils.Translations;
import net.fabricmc.api.ModInitializer;

import java.util.Map;

/**
 * Wacko Beacons 的 Carpet 附属（服务端）。
 *
 * 26.1/26.2 起 Mojang 在服务端新增了 BeaconBlockEntity#validateEffects 校验，
 * 修复了「低层信标任意选择高层效果」的漏洞（wacko-beacons 客户端 PoC 因此失效，
 * 且校验失败会被踢出服务器）。本附属通过 Carpet 规则 wackoBeacons 恢复
 * 1.21.11 及以前的服务端行为。
 */
public class WackoBeaconsCarpetExtension implements CarpetExtension, ModInitializer {
	public static final String MOD_ID = "wacko-beacons-carpet";

	private static final SettingsManager SETTINGS_MANAGER =
			new SettingsManager("1.0.0", MOD_ID, "Wacko Beacons");

	@Override
	public void onInitialize() {
		// 在 Carpet 自身初始化（server/client 入口点）之前注册扩展
		CarpetServer.manageExtension(this);
	}

	@Override
	public void onGameStarted() {
		SETTINGS_MANAGER.parseSettingsClass(WackoBeaconSettings.class);
	}

	@Override
	public SettingsManager extensionSettingsManager() {
		return SETTINGS_MANAGER;
	}

	@Override
	public Map<String, String> canHasTranslations(String lang) {
		// 服务端不会加载资源包，Carpet 通过此方法获取扩展的规则翻译
		return Translations.getTranslationFromResourcePath("assets/wacko-beacons-carpet/lang/%s.json".formatted(lang));
	}

	@Override
	public String version() {
		return "1.0.0+26.2";
	}
}
