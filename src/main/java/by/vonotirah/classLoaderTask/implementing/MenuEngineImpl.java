package by.vonotirah.classLoaderTask.implementing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import by.vonotirah.classLoaderTask.interfaces.CustomClassLoader;
import by.vonotirah.classLoaderTask.interfaces.MenuEngine;

public class MenuEngineImpl implements MenuEngine {
	private CustomClassLoader classLoader;
	private ArrayList<String> jarsMenuOptions;
	private ArrayList<String> classesMenuOptions;

	private static final Logger LOGGER = Logger.getLogger(MenuEngineImpl.class);

	public MenuEngineImpl(CustomClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	public Class<?> startEngine() throws IOException, ClassNotFoundException {
		this.jarsMenuOptions = this.classLoader.getListExitedJars();
		if (showExitedJars()) {
			this.classesMenuOptions = this.classLoader
					.loadClassesFromJar(choseJar());
		}
		showExitedClasses();

		return this.classLoader.loadClass(choseClass());
	}

	private boolean showExitedJars() throws IOException {
		int menuOption = 1;
		if (!this.jarsMenuOptions.isEmpty()) {
			for (String option : this.jarsMenuOptions) {
				System.out.print(menuOption + ". ");
				System.out.println(option);
				menuOption++;
			}
			return true;
		}
		LOGGER.info("Folder is empty");
		return false;
	}

	private boolean showExitedClasses() throws IOException {
		int menuOption = 1;
		if (!this.classesMenuOptions.isEmpty()) {
			for (String option : this.classesMenuOptions) {
				System.out.print(menuOption + ". ");
				System.out.println(option);
				menuOption++;
			}
			return true;
		}
		LOGGER.info("Jar is empty");
		return false;
	}

	private String choseJar() {
		System.out.println("Chose jar");
		Scanner in = new Scanner(System.in);
		while (!in.hasNextInt()) {
			LOGGER.info("Invalid input");
			in.next();
		}
		return (String) this.jarsMenuOptions.get(in.nextInt() - 1);
	}

	private String choseClass() {
		System.out.println("Chose class");
		Scanner in = new Scanner(System.in);
		while (!in.hasNextInt()) {
			LOGGER.info("Invalid input");
			in.next();
		}
		int menuOption = in.nextInt();

		return (String) this.classesMenuOptions.get(menuOption - 1);
	}
}
