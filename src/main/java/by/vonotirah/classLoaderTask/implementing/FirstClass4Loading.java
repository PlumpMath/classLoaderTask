package by.vonotirah.classLoaderTask.implementing;

import org.apache.log4j.Logger;

import by.vonotirah.classLoaderTask.interfaces.LClass;

public class FirstClass4Loading implements LClass {

	private static final Logger LOGGER = Logger
			.getLogger(FirstClass4Loading.class);

	public FirstClass4Loading() {
		LOGGER.info(getClass().getName() + " - is created");
	}

	public void run() {
		LOGGER.info(getClass().getName() + " - run");
	}
}
