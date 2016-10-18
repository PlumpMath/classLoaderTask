package by.vonotirah.classLoaderTask;

import by.vonotirah.classLoaderTask.implementing.JarClassLoader;
import by.vonotirah.classLoaderTask.implementing.MenuEngineImpl;
import by.vonotirah.classLoaderTask.interfaces.CustomClassLoader;
import by.vonotirah.classLoaderTask.interfaces.LClass;
import by.vonotirah.classLoaderTask.interfaces.MenuEngine;

public class App {
	public static void main(String[] args) throws Exception {
		MenuEngine menu = new MenuEngineImpl(
				(CustomClassLoader) new JarClassLoader());
		LClass loadedClass = (LClass) menu.startEngine().newInstance();
		loadedClass.run();
	}
}
