/**
 * This class handles the HashMap of the individual translations.
 *
 * @author Zalán Tóth
 * @version 0.9
 */

package utils.lang;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;


public class Language {

	public Language() {

	}

	private HashMap<String, LangData> langDataList = new HashMap<>();

	private boolean disableDefaultColor = false;

	public void setDisableDefaultColor(boolean disableDefaultColor) {
		this.disableDefaultColor = disableDefaultColor;
	}

	public boolean isDisableDefaultColor() {
		return disableDefaultColor;
	}

	public HashMap<String, LangData> getLangDataList() {
		return langDataList;
	}

	public void setLangDataList(HashMap<String, LangData> langDataList) {
		if (langDataList != null) {
			this.langDataList = langDataList;
		}
	}

	/**
	 * Add language into the langDataList ArrayList
	 *
	 * @param str      Address
	 * @param langData Language data
	 * @return Returns true if the add was successful, false otherwise
	 */
	public boolean addLangData(String str, LangData langData) {
		if (langData == null) {
			return false;
		}
		langDataList.put(str, langData);
		return true;
	}

	/**
	 * This method gets a translation based on an ID
	 *
	 * @param str Identification string
	 * @return Returns the found translation
	 */
	public String getTranslation(String str) {

		if (langDataList.get(str) == null) {
			return Colors.WARNING + "No translation for " + str + Colors.RESET + " ";
		}
		int ID = langDataList.get(str).getID();
		if (ID == 1) {
			if (isDisableDefaultColor()) {
				return Colors.RESET + langDataList.get(str).getTranslation() + Colors.RESET;
			}
			return Colors.DEFAULT_COLOR + langDataList.get(str).getTranslation() + Colors.RESET;
		} else if (ID == 2) {
			return Colors.WARNING + langDataList.get(str).getTranslation() + Colors.RESET;
		} else if (ID == 3) {
			return Colors.ERROR + langDataList.get(str).getTranslation() + Colors.RESET;
		} else if (ID == 4) {
			return Colors.STARTUP + langDataList.get(str).getTranslation() + Colors.RESET;
		} else if (ID == 5) {
			return Colors.SUCCESS + langDataList.get(str).getTranslation() + Colors.RESET;
		} else if (ID == 6) {
			return Colors.SORT + langDataList.get(str).getTranslation() + Colors.RESET;
		} else if (ID == 7) {
			return Colors.TITLE + langDataList.get(str).getTranslation() + Colors.RESET;
		}
		return Colors.RESET + langDataList.get(str).getTranslation();
	}

	/**
	 * This method ads a translation
	 */
	public void addTranslation(String str, LangData langData) {
		langDataList.put(str, langData);
	}


	/**
	 * This method gets details based on an ID
	 *
	 * @param str Identification number
	 * @return Returns the found details
	 */
	public String getDetails(String str) {
		if (langDataList.get(str) == null) {
			return Colors.ERROR + "ERR" + Colors.RESET;
		}
		return langDataList.get(str).getDetails();
	}

	public void setDetails(String hashID, String strToSet) {
		langDataList.get(hashID).setDetails(strToSet);
	}

	public int getID(String str) {
		if (langDataList.get(str) == null) {
			return -1;
		}
		return langDataList.get(str).getID();
	}

	public void setID(String str, int ID) {
		langDataList.get(str).setID(ID);
	}

	/**
	 * This method gets title based on an ID
	 *
	 * @param str Identification number
	 * @return Returns the found title
	 */
	public String getTitle(String str) {
		return langDataList.get(str).getTitle();
	}

	/**
	 * This method returns the size of the loaded language's size
	 *
	 * @return Returns the size of the langDataList
	 */
	public int getLangDataListSize() {
		return langDataList.size();
	}

	/**
	 * This method loads languages
	 */
	public void load(String language) throws Exception {
		Class<?>[] classes = new Class[]{LangData.class, Language.class};

		XStream xstream = new XStream(new DomDriver());
		XStream.setupDefaultSecurity(xstream);
		xstream.allowTypes(classes);

		ObjectInputStream is = xstream.createObjectInputStream(new FileReader(language + ".xml"));
		langDataList = (HashMap<String, LangData>) is.readObject();
		is.close();
	}

	/**
	 * This method saves languages
	 */
	public void save(String language) throws Exception {
		XStream xstream = new XStream(new DomDriver());
		ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(language + ".xml"));
		out.writeObject(langDataList);
		out.close();
	}
}
