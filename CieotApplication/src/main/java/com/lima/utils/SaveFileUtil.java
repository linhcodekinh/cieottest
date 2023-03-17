package com.lima.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.lima.common.MyConstants;
import com.lima.payload.request.PartDTOWithFileRequest;

@Component
public class SaveFileUtil {

	public Path staticP = Paths.get("static");

	public Path imageP = Paths.get("image");
	public Path audioP = Paths.get("audio");
	public Path excelP = Paths.get("excel");

	public Path partDetailP = Paths.get("detail");

	public Path imagePath = MyConstants.CURRENT_FOLDER.resolve(staticP).resolve(imageP);
	public Path audioPath = MyConstants.CURRENT_FOLDER.resolve(staticP).resolve(audioP);
	public Path excelPath = MyConstants.CURRENT_FOLDER.resolve(staticP).resolve(excelP);

	public void saveFilesForPart(PartDTOWithFileRequest partDTOWithFileRequest, Integer idPart, String namePart)
			throws IllegalStateException, IOException {
		MultipartFile fileAudio = partDTOWithFileRequest.getAudioFile();
		MultipartFile filePhoto = partDTOWithFileRequest.getPhotoFile();
		MultipartFile fileExcel = partDTOWithFileRequest.getQuestionExcelFile();

		Path partNamePath = Paths.get(idPart + "-" + namePart);

		Path imagePartNamePath = imagePath.resolve(partNamePath);
		Path audioPartNamePath = audioPath.resolve(partNamePath);
		Path excelPartNamePath = excelPath.resolve(partNamePath);

		// create path for part
		if (!Files.exists(imagePartNamePath)) {
			Files.createDirectories(imagePartNamePath);
		}
		if (!Files.exists(audioPartNamePath)) {
			Files.createDirectories(audioPartNamePath);
		}
		if (!Files.exists(excelPartNamePath)) {
			Files.createDirectories(excelPartNamePath);
		}

		Path filePhotoPath = imagePartNamePath.resolve(idPart + ".png");
		Path fileAudioPath = audioPartNamePath.resolve(idPart + ".mp3");
		Path fileExcelPath = excelPartNamePath.resolve(idPart + ".xlsx");

		filePhoto.transferTo(new File(filePhotoPath.toString()));
		fileAudio.transferTo(new File(fileAudioPath.toString()));
		fileExcel.transferTo(new File(fileExcelPath.toString()));

	}

	public void saveFilesForFartDetail(Integer idPart, String namePart, Integer questionNo, byte[] filePhoto)
			throws IOException {
		Path partNamePath = Paths.get(idPart + "-" + namePart);

		Path imagePartNamePath = imagePath.resolve(partNamePath).resolve(partDetailP);

		// create path for part detail
		if (!Files.exists(imagePartNamePath)) {
			Files.createDirectories(imagePartNamePath);
		}

		Path filePhotoPath = imagePartNamePath.resolve(questionNo + ".png");
		Files.write(filePhotoPath, filePhoto);
	}

}
