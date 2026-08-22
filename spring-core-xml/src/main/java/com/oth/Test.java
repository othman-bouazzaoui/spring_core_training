package com.oth;

import com.oth.business.BusinessImpl;
import com.oth.business.IBusiness;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Test {
	public static void main(String[] args)
			throws Exception {

		IBusiness business = BusinessImpl.class.getDeclaredConstructor().newInstance();
		business.fetchData(); // NullPointerException because dao is not initialized by DaoImpl Go to BusinessImpl class, then initialize dao with DaoImpl class and then run the program again

		IBusiness business2 = (IBusiness) Class.forName("com.oth.business.BusinessImpl").getDeclaredConstructor().newInstance();
		business2.fetchData();

		Map<String, String> beans = new HashMap<>();
		InputStream is = Test.class.getClassLoader().getResourceAsStream("beans.txt");
		try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				String[] parts = line.split("=");
				String key = parts[0].trim();
				String value = parts[1].trim();
				beans.put(key, value);
			}
		}

		IBusiness business3 = (IBusiness) Class.forName(beans.get(IBusiness.class.getSimpleName())).getDeclaredConstructor().newInstance();
		business3.fetchData();
	}
}
