package screens;

/**
 * interface that allows screens to switch between each other and properly displayed to user
 * manages all the screen switching aspects of game
 *
 */
public interface ScreenSwitcher {
	public static final int INTRO_SCREEN = 0;
	public static final int PAINTING_SCREEN = 1;
	public static final int INSTRUCTIONS_SCREEN = 2;
	public static final int TYPING_SCREEN = 3; 
	public static final int MIXING_SCREEN = 5; 
	public void switchScreen(int i);
	
}
