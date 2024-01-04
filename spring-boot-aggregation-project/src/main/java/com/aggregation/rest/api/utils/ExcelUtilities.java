package com.aggregation.rest.api.utils;

import com.google.common.base.Stopwatch;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ExcelUtilities {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtilities.class);

    public ByteArrayInputStream personExcel() throws IOException {

        String[] headers = {"Id", "firstName", "lastName", "Email"};
        String sheetName = "Person_LIST";

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = getConfiguredSheet(sheetName, workbook);

        CellStyle headerStyle = getHeaderStyle(workbook);
        CellStyle evenStyle = getEvenStyle(workbook);
        CellStyle oddStyle = getOddStyle(workbook);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Map<Integer, Integer> lengthMap = new HashMap<>();

        createHeaderRow(headerStyle, evenStyle, oddStyle, headers, workbook, sheet, lengthMap);

        autoSizeForAllColumns(sheet, lengthMap);

        workbook.write(out);
        return new ByteArrayInputStream(out.toByteArray());
    }


    public static XSSFSheet getConfiguredSheet(String sheetName, XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.createSheet(sheetName);
        sheet.addIgnoredErrors(new CellRangeAddress(0, 99999, 0, 25), IgnoredErrorType.NUMBER_STORED_AS_TEXT);
        return sheet;
    }

    private static CellStyle getHeaderStyle(Workbook book) {
        CellStyle style = book.createCellStyle();
        applyBaseStylings(style, true);

        style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());

        Font font = book.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);

        return style;
    }

    private static CellStyle getEvenStyle(Workbook book) {
        CellStyle style = book.createCellStyle();
        applyBaseStylings(style, false);

        style.setFillForegroundColor(IndexedColors.WHITE.getIndex());

        return style;
    }

    private static CellStyle getOddStyle(Workbook book) {
        CellStyle style = book.createCellStyle();
        applyBaseStylings(style, false);

        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());

        return style;
    }

    private static void applyBaseStylings(CellStyle style, boolean isHeader) {
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.BOTTOM);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        if (!isHeader) {
            style.setBorderRight(BorderStyle.MEDIUM);
            style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        }
    }

    public static void createHeaderRow(CellStyle headerStyle, CellStyle evenStyle, CellStyle oddStyle, String[] headers, XSSFWorkbook workbook, XSSFSheet sheet, Map<Integer, Integer> lengthMap) {
        createRowOnSheet(headerStyle, evenStyle, oddStyle,
                workbook, sheet, 0, lengthMap, headers);
        sheet.createFreezePane(0, 1, 0, 1);
    }

    public static Row createRowOnSheet(CellStyle headerStyle, CellStyle evenStyle, CellStyle oddStyle, Workbook book, Sheet sheet, int rowIndex, Map<Integer, Integer> lengthMap, String... values) {
        Row row = sheet.createRow(rowIndex);
        boolean isHeader = rowIndex == 0;
        boolean isEven = ((rowIndex - 1) % 2) == 0;

        for (String value : values) {
            int cellIndex = row.getLastCellNum() == -1 ? 0 : row.getLastCellNum();
            if (value == null) {
                value = "";
            }
            Integer previousLength = lengthMap.getOrDefault(cellIndex, null);
            if (previousLength == null || value.length() > previousLength) {
                lengthMap.put(cellIndex, value.length());
            }

            Cell cell = row.createCell(cellIndex);

            boolean isDouble = value.matches("^(\\d)*(\\.)");
            boolean isInt = value.matches("^[0-9]{1,4}$");
            boolean isCurrency = value.matches("^\\$(\\d{1,3}(\\, \\d{3})*|(\\d+))(\\.\\d{2})?$");


            Double valueAsDouble = Doubles.tryParse(value);
            Integer valueAsInt = Ints.tryParse(value);
            Double valueAsCurrency = Doubles.tryParse(value.replace("$", ""));

            CellStyle style = chooseStyleForRow(headerStyle, evenStyle, oddStyle, book, isHeader, isEven);

            if (isDouble) {
                style.setDataFormat((short) 8);
            }

            if (isInt) {
                style.setDataFormat((short) 0);
            }

            if (isCurrency) {
                style.setDataFormat((short) 7);
            }


            if (isDouble && valueAsDouble != null) {
                cell.setCellValue(valueAsDouble);
            } else if (isInt && valueAsInt != null) {
                cell.setCellValue(valueAsInt);
            } else if (isCurrency && valueAsCurrency != null) {
                cell.setCellValue(valueAsCurrency);
            } else {
                cell.setCellValue(value);
            }

            cell.setCellStyle(style);
        }

        return row;
    }


    public static CellStyle chooseStyleForRow(CellStyle headerStyle, CellStyle evenStyle, CellStyle oddStyle, Workbook book, boolean isHeader, boolean isEven) {
        return isHeader ? headerStyle : isEven ? evenStyle : oddStyle;
    }

    private static void autoSizeForAllColumns(Sheet sheet, Map<Integer, Integer> lengthMap) {
        if (lengthMap == null || lengthMap.isEmpty()) return;

        Stopwatch stopwatch = Stopwatch.createStarted();

        for (Map.Entry<Integer, Integer> pair : lengthMap.entrySet()) {
            // Max width is 65280, ensure it doesn't exceed that otherwise it throws an exception
            sheet.setColumnWidth(pair.getKey(), Math.min(65280, pair.getValue() * 325));
        }

        stopwatch.stop();
        logger.debug("autoSizeForAllColumns took {}ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

    public static ResponseEntity<InputStreamResource> getExcelSheetResponseEntity(String fileName, ByteArrayInputStream excelSheetData) {
        InputStreamResource file = new InputStreamResource(excelSheetData);

        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        headers.add("Content-Disposition", "attachment; filename=" + fileName);

        return ResponseEntity.ok().contentType(mediaType).headers(headers).body(file);
    }

    public static String formatValueForAmount(Double amount) {
        Locale locale = new Locale("en", "US");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(amount);
    }

    public static String moneyFormatter(Double amount) {
        String am = String.format("%.2f", amount);
        Locale locale = new Locale("en", "US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        return fmt.format(Double.parseDouble(am));
    }

    public static String getStringValueFromCell(Row curRow, int index) {
        Cell cell = curRow.getCell(index);

        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return "";
            default:
                return "Unsupported Cell Type";
        }
    }


    private static void addBorderToBottom(XSSFSheet sheet, boolean isEven) {
        XSSFRow row = sheet.getRow(sheet.getLastRowNum());

        Iterator<Cell> cellIterator = row.cellIterator();

        while (cellIterator.hasNext()) {
            Cell currentCell = cellIterator.next();

            CellStyle existingCellCss = sheet.getWorkbook().createCellStyle();

            //adding border to existing css.
            existingCellCss.cloneStyleFrom(currentCell.getCellStyle());
            existingCellCss.setBorderBottom(BorderStyle.MEDIUM);
            existingCellCss.setBottomBorderColor(IndexedColors.BLACK.getIndex());

            currentCell.setCellStyle(existingCellCss);
        }


    }
}
