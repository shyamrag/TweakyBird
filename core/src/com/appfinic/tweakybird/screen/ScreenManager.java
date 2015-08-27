package com.appfinic.tweakybird.screen;

public class ScreenManager {

	private static AbstractScreen currentScreen;
	
	public static void setScreen(AbstractScreen screen) {
		if (currentScreen != null)
			currentScreen.dispose();
		currentScreen = screen;
		currentScreen.create();
	}
	
	public static AbstractScreen getCurrentScreen() {
		return currentScreen;
	}
	
}
