package trivago.magazineAutomation.commonUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
* The ReadPropertiesFile program reads values from constant.properites
*
* @author  Nayan Agawal
* @version 1.0
* @since   20121-02-19
*/
public class ReadPropertiesFile {

	private static File file = new File("constant.properties");

	private static Properties prop = new Properties();
	static {
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String propertyKey) {
		return prop.getProperty(propertyKey);
	}

}
