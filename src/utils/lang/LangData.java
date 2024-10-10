
/**
 * This class handles individual translations.
 *
 * @author Zalán Tóth
 * @version 1.0
 */

package utils.lang;

public class LangData {
    public LangData(String title, String details, String translation, int ID) {
        this.title = title;
        this.details = details;
        this.translation = translation;
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
    private int ID = -1; //reworked for coloring

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    private String title = "";
    private String details = "";
    private String translation = "";
}
