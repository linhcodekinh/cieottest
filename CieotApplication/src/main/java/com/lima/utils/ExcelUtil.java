package com.lima.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
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
			System.out.println(firstCell.getStringCellValue());

			XSSFDrawing dp = (XSSFDrawing) dataTypeSheet.createDrawingPatriarch();
			List<XSSFShape> pics = dp.getShapes();

			while (iterator.hasNext()) {
				Row currentRow = iterator.next();
				PartDetailDTO partDetailDTO = new PartDetailDTO();
				partDetailDTO.setQuestionNo(Integer.parseInt(fmt.formatCellValue(currentRow.getCell(0))));
				Integer questionNo = Integer.parseInt(fmt.formatCellValue(currentRow.getCell(0)));
				partDetailDTO.setPassage(Integer.parseInt(fmt.formatCellValue(currentRow.getCell(1))));
				partDetailDTO.setQuestion(fmt.formatCellValue(currentRow.getCell(2)));
				partDetailDTO.setAnswer1(fmt.formatCellValue(currentRow.getCell(3)));
				partDetailDTO.setAnswer2(fmt.formatCellValue(currentRow.getCell(4)));
				partDetailDTO.setAnswer3(fmt.formatCellValue(currentRow.getCell(5)));
				partDetailDTO.setAnswer4(fmt.formatCellValue(currentRow.getCell(6)));
				partDetailDTO.setCorrectAnswer(fmt.formatCellValue(currentRow.getCell(7)));
				partDetailDTO.setDemonstrate(fmt.formatCellValue(currentRow.getCell(8)));

				// image
				for (Iterator<? extends XSSFShape> it = pics.iterator(); it.hasNext();) {
					XSSFPicture inpPic = (XSSFPicture) it.next();
					XSSFClientAnchor clientAnchor = (XSSFClientAnchor) inpPic.getClientAnchor();

					PictureData pict = inpPic.getPictureData();
					byte[] data = pict.getData();
					if (clientAnchor.getCol1() == 9 && clientAnchor.getRow1() == currentRow.getRowNum()) {
						byte[] pictureData = data;
						saveFileUtil.saveFilesForFartDetail(idPart, namePart, questionNo, pictureData);
						partDetailDTO.setPhotoLink(questionNo + ".png");
					}

				}
				partDetailDTO.setScript(fmt.formatCellValue(currentRow.getCell(10)));
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
