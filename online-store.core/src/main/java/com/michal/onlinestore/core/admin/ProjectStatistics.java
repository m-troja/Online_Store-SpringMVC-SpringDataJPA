package com.michal.onlinestore.core.admin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ProjectStatistics {

	private static final String SRC_FOLDER_NAME = "../";
	private static final String JAVA_FILES_EXXTENSION = ".java";
	private static final String JSP_FILES_EXXTENSION = ".jsp";
	private static final String HTML_FILES_EXXTENSION = ".html";
	private static final String XML_FILES_EXXTENSION = ".xml";
	private static final int MAX_DEPTH_OF_PACKAGES = Integer.MAX_VALUE;

	public static void main(String[] args) {
		long javaSourceFiles = calculateJavaFilesInProject(JAVA_FILES_EXXTENSION);
		long jspSourceFiles = calculateJavaFilesInProject(JSP_FILES_EXXTENSION);
		long htmlSourceFiles = calculateJavaFilesInProject(HTML_FILES_EXXTENSION);
		long xmlSourceFiles = calculateJavaFilesInProject(XML_FILES_EXXTENSION);

		
//				+ htmlSourceFiles + xmlSourceFiles));
	}


	private static long calculateJavaFilesInProject(String extension) {
		try {
			return Files.find(Paths.get(SRC_FOLDER_NAME), MAX_DEPTH_OF_PACKAGES,
					(path, attr) -> {
						return path.getFileName().toString()
								.endsWith(extension);
					}).count();
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
