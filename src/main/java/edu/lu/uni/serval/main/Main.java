package edu.lu.uni.serval.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import edu.lu.uni.serval.avatar.AbstractFixer;
import edu.lu.uni.serval.avatar.Avatar;
import edu.lu.uni.serval.config.Configuration;

/**
 * Fix bugs with Fault Localization results.
 *
 * @author kui.liu
 *
 */
public class Main {

	public static void main(String[] args) {
		if (args.length != 5) {
			System.out.println("Arguments: <Buggy_Project_Path> <defects4j_Path> <Bug_ID> <FL_Metric>");
			System.exit(0);
		}
		String buggyProjectsPath = args[0];// "../Defects4JData/"
		Configuration.BUGGY_PROJECTS_PATH = args[0];
		String defects4jPath = args[1]; // "../defects4j/"
		String projectName = args[2]; // "Chart_1"
		Configuration.BUG_PROJECT = args[2];
		Configuration.faultLocalizationMetric = args[3];
		Configuration.outputPath += "FL/";
		System.out.println(projectName);
		Configuration.datasetCommandAndCompilePath = args[4];
		fixBug(buggyProjectsPath, defects4jPath, projectName);
	}

	public static void fixBug(String buggyProjectsPath, String defects4jPath, String buggyProjectName) {
		String suspiciousFileStr = Configuration.suspPositionsFilePath;

		String dataType = "AVATAR";
		String[] elements = buggyProjectName.split("-");
		String projectName = elements[0];
		int bugId;
		try {
			bugId = Integer.valueOf(elements[1]);
		} catch (NumberFormatException e) {
			System.err.println("Please input correct buggy project ID, such as \"Chart_1\".");
			return;
		}

		try {
			BufferedWriter br = new BufferedWriter(new FileWriter(new File(Configuration.failedTestCasesFilePath + Configuration.BUG_PROJECT + ".txt"), true));
			br.write("some text");
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		AbstractFixer fixer = new Avatar(buggyProjectsPath, projectName, bugId, defects4jPath);
		fixer.metric = Configuration.faultLocalizationMetric;
		fixer.dataType = dataType;
		fixer.suspCodePosFile = new File(suspiciousFileStr);
		if (Integer.MAX_VALUE == fixer.minErrorTest) {
			System.out.println("Failed to defects4j compile bug " + buggyProjectName);
			return;
		}

		fixer.fixProcess();

		int fixedStatus = fixer.fixedStatus;
		switch (fixedStatus) {
			case 0:
				System.out.println("Failed to fix bug " + buggyProjectName);
				break;
			case 1:
				System.out.println("Succeeded to fix bug " + buggyProjectName);
				break;
			case 2:
				System.out.println("Partial succeeded to fix bug " + buggyProjectName);
				break;
		}
	}

}
