/**
 * This class handles languages and the settings for them.
 * Every value is saved to a file.
 * Languages are saved in a format of "[languageName].xml"
 * Settings are saved in a format of "settings.xml"
 * Read README.md for more information
 *
 * @author Zalán Tóth
 * @version 0.5
 */

package utils.lang;

import java.util.ArrayList;

public class Settings {

	Language language = new Language();
	private String selected_language = "en";
	private String default_language = "en";
	private ArrayList<String> loadedLanguages;

	public boolean validLanguage(String str) {
		return (loadedLanguages.contains(str));
	}

	public ArrayList<String> getLoadedLanguages() {
		return loadedLanguages;
	}

	public void addLanguage(String str) {
		loadedLanguages.add(str);
	}

	public String getSelected_language() {
		return selected_language;
	}

	public void setSelected_language(String selected_language) {
		this.selected_language = selected_language;
	}

	public String getDefault_language() {
		return default_language;
	}

	public void setDefault_language(String default_language) {
		this.default_language = default_language;
	}

	public void keepSettingsFromFile() {
		setSelected_language(language.getDetails("selected_language"));
		setDefault_language(language.getDetails("default_language"));
		loadedLanguages = new ArrayList<>() {
			{

				for (int i = 0; i < language.getID("langSize"); i++) {
					add(language.getDetails("lang" + i));

				}
			}

		};
	}

	public void saveSettingsToFile() {
		loadLanguage("settings");
		language.setDetails("selected_language", selected_language);
		language.setDetails("default_language", default_language);
		for (int i = 0; i < loadedLanguages.size(); i++) {
			LangData langData = new LangData(null, loadedLanguages.get(i), null, -1);
			language.addLangData("lang" + i, langData);

		}
		language.setID("langSize", loadedLanguages.size());
		saveLanguage("settings");
		loadLanguage(selected_language);
	}

	public Settings() {
	}

	public void switchColor() {
		if (language.isDisableDefaultColor()) {
			language.setDisableDefaultColor(false);
		} else {
			language.setDisableDefaultColor(true);
		}
	}

	public void addTranslation(String str, LangData langData) {
		language.addTranslation(str, langData);
	}

	public String getTranslation(String str) {
		return language.getTranslation(str);
	}

	public void loadLanguage(String str) {
		try {
			language.load(str);
		} catch (Exception e) {
			System.err.println("Error reading from file: " + e);
		}
	}

	public void saveLanguage(String str) {
		try {
			language.save(str);
		} catch (Exception e) {
			System.err.println("Error writing to file: " + e);
		}
	}
}
