package com.ujwal.billsoft.commons;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class VpsImageUpload {

	public static final String COMPANY_FOLDER = "E:\\java\\";
	

	private static String curTimeStamp = null;

	public void saveUploadedFiles(List<MultipartFile> files, int imageType, String imageName) throws IOException {

		for (MultipartFile file : files) {

			if (file.isEmpty()) {

				continue;

			}

			Path path = Paths.get(COMPANY_FOLDER + imageName);

			byte[] bytes = file.getBytes();

			if (imageType == 1) {
				System.out.println("Inside Image Type =1");

				path = Paths.get(COMPANY_FOLDER + imageName);

				System.out.println("Path= " + path.toString());

			}

			Files.write(path, bytes);

		}

	}

}
