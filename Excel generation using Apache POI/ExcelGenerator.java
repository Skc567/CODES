import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelGenerator {

    public static void generateExcel(String[] headers, List<List<Object>> data, String outputPath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        createHeaderRow(sheet, headers);
        populateDataRows(sheet, data);

        autoSizeColumns(sheet, headers.length);

        try (FileOutputStream fileOut = new FileOutputStream(outputPath)) {
            workbook.write(fileOut);
        }
    }

    private static void createHeaderRow(Sheet sheet, String[] headers) {
        Row headerRow = sheet.createRow(0);
        CellStyle headerStyle = createHeaderStyle(sheet.getWorkbook());

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }
    }

    private static void populateDataRows(Sheet sheet, List<List<Object>> data) {
        for (int rowIndex = 0; rowIndex < data.size(); rowIndex++) {
            List<Object> rowData = data.get(rowIndex);
            Row dataRow = sheet.createRow(rowIndex + 1);

            for (int columnIndex = 0; columnIndex < rowData.size(); columnIndex++) {
                Cell cell = dataRow.createCell(columnIndex);
                Object cellValue = rowData.get(columnIndex);

                if (cellValue instanceof String) {
                    cell.setCellValue((String) cellValue);
                } else if (cellValue instanceof Double) {
                    cell.setCellValue((Double) cellValue);
                } else if (cellValue instanceof Integer) {
                    cell.setCellValue((Integer) cellValue);
                }
                // Add more data type handling if needed
            }
        }
    }

    private static void autoSizeColumns(Sheet sheet, int numColumns) {
        for (int i = 0; i < numColumns; i++) {
            sheet.autoSizeColumn(i);
        }
    } 

    private static CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        return style;
    }

    public static void main(String[] args) {
        String[] headers = {"Product ID", "Name", "Price", "Tag", "Discount", "TRN", "SO Number"};
        List<List<Object>> data = // Your data, where each inner list represents a row

        String outputPath = "report.xlsx";
        try {
            generateExcel(headers, data, outputPath);
            System.out.println("Excel file generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}