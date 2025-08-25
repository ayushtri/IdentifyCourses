package com.identifycourses.utils;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AllureReportOpener {
	
	private static final Logger logger = LogManager.getLogger(AllureReportOpener.class);
	
	public static void cleanAllureResults() {
		File resultsDir = new File("target/allure-results");
		if (resultsDir.exists() && resultsDir.isDirectory()) {
			for (File file : resultsDir.listFiles()) {
				logger.info("Deleting old files");
				file.delete();
			}
		}
	}

	public static void openAllureReport() {
		try {
			// Step 1: Generate Allure report
			String allureBatPath = ConfigReader.getAllureProperty("allure.bin.directory");
			String allureResultsPath = ConfigReader.getAllureProperty("allure.results.directory");
			String allureReportPath = ConfigReader.getAllureProperty("allure.report.directory");
			

			ProcessBuilder generate = new ProcessBuilder(allureBatPath, "generate", allureResultsPath, "-o",
					allureReportPath, "--clean");
			logger.info("Generating allure report");
			generate.inheritIO();
			Process genProcess = generate.start();
			genProcess.waitFor();

			// Step 2: Open Allure report in browser
			ProcessBuilder open = new ProcessBuilder(allureBatPath, "open", allureReportPath);
			logger.info("Opening allure report");
			open.inheritIO();
			open.start();

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
