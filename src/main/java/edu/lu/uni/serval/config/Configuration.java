package edu.lu.uni.serval.config;

public class Configuration {

	/*
	 * Data path of Defects4J bugs.
	 */
	public static String BUGGY_PROJECTS_PATH = "/Users/gan/Desktop/AVATAR/projects/";
	public static String BUG_PROJECT = "";

	public static final String TEMP_FILES_PATH = ".temp/";
	public static final long SHELL_RUN_TIMEOUT = 10800L;

	public static String knownBugPositions = "BugPositions.txt";
	public static String suspPositionsFilePath = "SuspiciousCodePositions/";
	public static String failedTestCasesFilePath = "FailedTestCases/";
	public static String faultLocalizationMetric = "Ochiai";
	public static String outputPath = "OUTPUT/";
	public static String datasetCommandAndCompilePath = "";

}
