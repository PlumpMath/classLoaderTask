package by.vonotirah.classLoaderTask.implementing;

import org.apache.log4j.Logger;

import by.vonotirah.classLoaderTask.interfaces.LClass;

public class SecondClass4Loading implements LClass {

	private static final Logger LOGGER = Logger
			.getLogger(SecondClass4Loading.class);

	public SecondClass4Loading() {
		LOGGER.info(getClass().getName() + " - is created");
	}

	public void run() {
		LOGGER.info(getClass().getName() + " - run");
	}

}
