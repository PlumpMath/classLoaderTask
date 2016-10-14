package by.vonotirah.classLoaderTask.implementing;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import by.vonotirah.classLoaderTask.interfaces.CustomClassLoader;

public class JarClassLoader extends ClassLoader implements CustomClassLoader {

	private Map<String, Class<?>> cacheClass = new HashMap<String, Class<?>>();
	private ClassLoader parent;
	private ArrayList<String> jarList;
	private static final String PATH_TO_FOLDER = "myJars/";

	public JarClassLoader() {
		this.parent = JarClassLoader.class.getClassLoader();
	}

	public Class<?> loadClass(String name) throws ClassNotFoundException {
		Class<?> result = (Class<?>) this.cacheClass.get(name);
		if (result == null) {
			result = this.parent.loadClass(name);
		}
		return result;
	}

	public ArrayList<String> getListExitedJars() throws IOException {
		this.jarList = new ArrayList<String>(Arrays.asList(new File(
				PATH_TO_FOLDER).list(new FilenameFilter() {
			public boolean accept(File file, String s) {
				return s.toLowerCase().endsWith(".jar");
			}
		})));
		return this.jarList;
	}

	public ArrayList<String> loadClassesFromJar(String jarName)
			throws IOException {
		ArrayList<String> classesNames = new ArrayList<String>();

		JarFile jarFile = new JarFile(PATH_TO_FOLDER + jarName);

		Enumeration<JarEntry> jarEntries = jarFile.entries();
		while (jarEntries.hasMoreElements()) {
			JarEntry jarEntry = (JarEntry) jarEntries.nextElement();
			if (!jarEntry.isDirectory()) {
				if (jarEntry.getName().endsWith(".class")) {
					byte[] classData = loadClassData(jarFile, jarEntry);
					if (classData != null) {
						Class<?> classFromJar = defineClass(
								jarEntry.getName()
										.replace('/', '.')
										.substring(0,
												jarEntry.getName().length() - 6),
								classData, 0, classData.length);
						this.cacheClass.put(classFromJar.getName(),
								classFromJar);
						classesNames.add(classFromJar.getName());
					}
				}
			}
		}
		return classesNames;
	}

	private byte[] loadClassData(JarFile jarFile, JarEntry jarEntry)
			throws IOException {
		long jarSize = jarEntry.getSize();
		if (jarSize <= 0) {
			return null;
		}
		if (jarSize > Integer.MAX_VALUE) {
			throw new IOException("Class size is too long");
		}
		byte[] buffer = new byte[(int) jarSize];
		InputStream is = jarFile.getInputStream(jarEntry);
		is.read(buffer);
		return buffer;
	}
}