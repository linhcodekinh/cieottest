package com.lima.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hpsf.ClassIDPredefined;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ChildAnchor;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.ObjectData;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Shape;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFObjectData;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.lima.dto.PartDetailDTO;

@Component
public class ExcelUtil {

	@Autowired
	private SaveFileUtil saveFileUtil;

	public List<PartDetailDTO> getListPartDetailFromFileExcel(Path excelNamePath, Integer idPart, String namePart) {
		try {
			List<PartDetailDTO> partDetailDTOList = new ArrayList<>();
			System.out.println("excelNamePath.toString(): " + excelNamePath.toString());
			Workbook workbook = new XSSFWorkbook(new FileInputStream(excelNamePath.toString()));
			// XSSFWorkbook workbook = new XSSFWorkbook(excel.getInputStream());
			// XSSFSheet sheet = workbook.getSheetAt(0);

			Sheet dataTypeSheet = workbook.getSheetAt(0);
			DataFormatter fmt = new DataFormatter();

			Iterator<Row> iterator = dataTypeSheet.iterator();
			Row firstRow = iterator.next();
			Cell firstCell = firstRow.getCell(0);

			XSSFDrawing dp = (XSSFDrawing) dataTypeSheet.createDrawingPatriarch();
			List<XSSFShape> pics = dp.getShapes();

			while (iterator.hasNext()) {
				Row currentRow = iterator.next();
				PartDetailDTO partDetailDTO = new PartDetailDTO();
				Integer questionNo = Integer.parseInt(fmt.formatCellValue(currentRow.getCell(0)));
				partDetailDTO.setQuestionNo(questionNo);
				partDetailDTO.setPassage(fmt.formatCellValue(currentRow.getCell(1)));
				partDetailDTO.setQuestion(fmt.formatCellValue(currentRow.getCell(2)));
				partDetailDTO.setAnswer1(fmt.formatCellValue(currentRow.getCell(3)));
				partDetailDTO.setAnswer2(fmt.formatCellValue(currentRow.getCell(4)));
				partDetailDTO.setAnswer3(fmt.formatCellValue(currentRow.getCell(5)));
				partDetailDTO.setAnswer4(fmt.formatCellValue(currentRow.getCell(6)));
				partDetailDTO.setCorrectAnswer(fmt.formatCellValue(currentRow.getCell(7)));
				partDetailDTO.setDemonstrate(fmt.formatCellValue(currentRow.getCell(8)));

				for (Iterator<? extends Shape> it = pics.iterator(); it.hasNext();) {
					Shape sh = it.next();

					if (sh instanceof XSSFPicture) {
						// image
						XSSFPicture inpPic = (XSSFPicture) sh;
						XSSFClientAnchor clientAnchor = (XSSFClientAnchor) inpPic.getClientAnchor();

						System.out.println("vao XSSFPicture: " + currentRow.getRowNum() + " col1 "
								+ clientAnchor.getCol1() + " col2 " + clientAnchor.getCol2());

						PictureData pict = inpPic.getPictureData();
						byte[] data = pict.getData();
						if (clientAnchor.getCol1() == 9 && clientAnchor.getRow1() == currentRow.getRowNum()) {
							byte[] pictureData = data;
							saveFileUtil.saveFileForFartDetail(idPart, namePart, questionNo, pictureData, 0);
							partDetailDTO.setPhotoLink(questionNo + ".png");
						}
					} else if (sh instanceof XSSFObjectData) {
						XSSFObjectData inpObj = (XSSFObjectData) sh;
						XSSFClientAnchor clientAnchor = (XSSFClientAnchor) inpObj.getAnchor();
						System.out.println("vao obj data: currentRow.getRowNum(): " + currentRow.getRowNum()
								+ " clientAnchor.getRow1(): " + clientAnchor.getRow1() + " col "
								+ clientAnchor.getCol1() + " col2 " + clientAnchor.getCol2());

						byte[] data = inpObj.getObjectData();
						if (clientAnchor.getCol1() == 10 && clientAnchor.getRow1() == currentRow.getRowNum()) {
							byte[] audioData = data;
							saveFileUtil.saveFileForFartDetail(idPart, namePart, questionNo, audioData, 1);
							System.out.println("data audio hhohoho: ");
							partDetailDTO.setAudioLink(questionNo + ".mp3");
						}
					}

				}
				partDetailDTO.setScript(fmt.formatCellValue(currentRow.getCell(11)));
				partDetailDTOList.add(partDetailDTO);
			}
			workbook.close();
			return partDetailDTOList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
