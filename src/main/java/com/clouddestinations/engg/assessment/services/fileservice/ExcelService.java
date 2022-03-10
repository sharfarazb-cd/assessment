package com.clouddestinations.engg.assessment.services.fileservice;

import com.clouddestinations.engg.assessment.common.ResourceProvider;
import com.google.gson.Gson;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Iterator;

@Service
public class ExcelService {
    @Autowired
    private ResourceProvider resourceProvider;

    public static boolean checkExtension(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        boolean result = Boolean.FALSE;
        if (extension.equalsIgnoreCase("xlsx") || extension.equalsIgnoreCase("xls")) {
            result = Boolean.TRUE;
        }
        return result;
    }

    private static Workbook getMyWorkbook(MultipartFile file) {
        Workbook workbook = null;
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        try {
            if (ext.equals("xlsx")) {
                workbook = new XSSFWorkbook(file.getInputStream());
            } else
                workbook = new HSSFWorkbook(file.getInputStream());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return workbook;
    }

    public JSONArray convertExcelToJSON(MultipartFile file) {
        Workbook myWorkbook = getMyWorkbook(file);
        Sheet mySheet = myWorkbook.getSheetAt(0);
        Row headerRow = mySheet.getRow(1);
        Iterator<Row> rowIterator = mySheet.iterator();
        rowIterator.next();
        rowIterator.next();
        JSONArray mainJsonArray = new JSONArray();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            JSONObject mainJsonObject = new JSONObject();
            JSONArray rattingJsonArray = new JSONArray();
            Iterator<Cell> cellIterator = row.iterator();
            int headCellIndex = 0;
            while (cellIterator.hasNext()) {
                JSONObject ratingJsonObject = new JSONObject();
                Cell cell = cellIterator.next();
                getCellValue(headerRow, mainJsonObject, headCellIndex, ratingJsonObject, cell);
                headCellIndex++;
                if (!ratingJsonObject.isEmpty()) {
                    rattingJsonArray.put(ratingJsonObject);
                }
            }
            if (!rattingJsonArray.isEmpty()) {
                mainJsonObject.put("ratings", rattingJsonArray);
            }
            mainJsonArray.put(mainJsonObject);
        }
        return mainJsonArray;
    }

    private void getCellValue(Row headerRow, JSONObject mainJsonObject, int headCellIndex,
                              JSONObject ratingJsonObject, Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                if (resourceProvider.getRating().containsKey(headerRow.getCell(headCellIndex).toString().replaceAll(
                        "\\s", ""))) {
                    ratingJsonObject.put(resourceProvider.getRating().get("RatingYearVariableName").toString(),
                            resourceProvider.getRating().get(headerRow.getCell(headCellIndex).toString().replaceAll(
                                    "\\s", "")));
                    ratingJsonObject.put(resourceProvider.getRating().get("RatingVariableName").toString(),
                            cell.getStringCellValue());
                } else {
                    if (resourceProvider.getVariables().containsKey(headerRow.getCell(headCellIndex).toString().replaceAll("\\s", "")))
                        mainJsonObject.put(resourceProvider.getVariables().get(headerRow.getCell(headCellIndex).getStringCellValue().replaceAll("\\s", "")).toString(),
                                cell.getStringCellValue());
                }
                break;
            case NUMERIC:
                if (resourceProvider.getVariables().containsKey(headerRow.getCell(headCellIndex).toString().replaceAll("\\s", ""))) {
                    if (DateUtil.isCellDateFormatted(cell)) {
                        mainJsonObject.put(resourceProvider.getVariables().get(headerRow.getCell(headCellIndex).getStringCellValue().replaceAll("\\s", "")).toString(),
                                cell.getDateCellValue());
                    } else {
                        mainJsonObject.put(resourceProvider.getVariables().get(headerRow.getCell(headCellIndex).getStringCellValue().replaceAll("\\s", "")).toString(),
                                cell.getNumericCellValue());
                    }
                }
                break;
            case BOOLEAN:
                if (resourceProvider.getVariables().containsKey(headerRow.getCell(headCellIndex).toString().replaceAll("\\s", "")))
                    mainJsonObject.put(resourceProvider.getVariables().get(headerRow.getCell(headCellIndex).getStringCellValue().replaceAll("\\s", "")).toString(),
                            cell.getBooleanCellValue());
                break;
            case FORMULA:
                if (resourceProvider.getVariables().containsKey(headerRow.getCell(headCellIndex).toString().replaceAll("\\s", "")))
                    mainJsonObject.put(resourceProvider.getVariables().get(headerRow.getCell(headCellIndex).getStringCellValue().replaceAll("\\s", "")).toString(),
                            cell.getCellFormula());
                break;
            case BLANK:
                if (resourceProvider.getVariables().containsKey(headerRow.getCell(headCellIndex).toString().replaceAll("\\s", "")))
                    mainJsonObject.put(resourceProvider.getVariables().get(headerRow.getCell(headCellIndex).getStringCellValue().replaceAll("\\s", "")).toString(), "");
                break;
            default:
        }
    }

    public <T> T convertJSONToList(JSONArray jsonArray, Class<T> classType) {
        return new Gson().fromJson(jsonArray.toString(), classType);
    }
}
