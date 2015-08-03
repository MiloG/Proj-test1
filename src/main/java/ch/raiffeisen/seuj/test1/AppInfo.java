package ch.raiffeisen.seuj.test1;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class AppInfo {

	public static final String PROP_PROJECT_NAME 	= "project.name";
	public static final String PROP_PROJECT_VERSION = "project.version";
	
	
	private static final String FILE_NAME = "/META-INF/app-info.properties";
	private Properties properties;
	
	@PostConstruct
	private void init() {
		InputStream is = getClass().getResourceAsStream(FILE_NAME);
		properties = new Properties();
		try {
			properties.load(is);
		} catch (IOException e) {
			System.err.println("Error Initializing AppInfo");
			e.printStackTrace();
		}
	}

	public String get(String name) {
		return properties.getProperty(name);
	}
	public String getProjectVersion(){
		return get(PROP_PROJECT_VERSION);
	}
	public String getProjectName() {
		return get(PROP_PROJECT_NAME);
	}
}
