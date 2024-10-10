/**
 * This class handles Colors.
 * Structure is taken from stackoverflow from skakram02
 * Rewritten to match the translation system. Each color has a code in the translation system, assigning one to each translation and saved in a file.
 *
 * https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println/45444716#45444716
 * @author Zalán Tóth, shakram02
 * @version 1.0
 */

package utils.lang;
//Idea taken from stackOverflow
public class Colors {

    public static final String RESET = "\033[0m";  // Text Reset

    public static final String DEFAULT_COLOR = "\033[0;97m";  // WHITE
    public static final String WARNING = "\033[0;103m" + "\033[4;30m";// WARNING YELLOW
    public static final String ERROR = "\033[4;30m" + "\033[41m";// ERROR RED
    public static final String STARTUP = "\033[0;94m";// STARTUP BLUE
    public static final String SUCCESS = "\033[0;92m";// GREEN
    public static final String SORT = "\033[0;96m";// BRIGHT CYAN
    public static final String TITLE = "\033[0;95m";// BRIGHT PURPLE
}
