package by.vonotirah.classLoaderTask.interfaces;

import java.io.IOException;
import java.util.ArrayList;

public interface CustomClassLoader {

	public ArrayList<String> getListExitedJars() throws IOException;

	public ArrayList<String> loadClassesFromJar(String var1) throws IOException;

	public Class<?> loadClass(String var1) throws ClassNotFoundException;
}
