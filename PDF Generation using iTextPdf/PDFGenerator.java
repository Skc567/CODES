import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PdfGenerator {

    public static void generatePdf(String[] headers, List<List<Object>> data, String outputPath) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(outputPath));
            document.open();

            PdfPTable table = createTable(headers, data);
            document.add(table);

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    private static PdfPTable createTable(String[] headers, List<List<Object>> data) {
        PdfPTable table = new PdfPTable(headers.length);
        table.setWidthPercentage(100);

        // Set header cell background color to yellow
        PdfPCell headerCell = new PdfPCell();
        headerCell.setBackgroundColor(BaseColor.YELLOW);
        headerCell.setPadding(5);

        for (String header : headers) {
            headerCell.setPhrase(new Phrase(header));
            table.addCell(headerCell);
        }

        // Alternating row colors: green and white
        boolean isGreen = false;
        for (List<Object> rowData : data) {
            PdfPCell dataCell = new PdfPCell();
            dataCell.setPadding(5);
            for (Object cellValue : rowData) {
                if (isGreen) {
                    dataCell.setBackgroundColor(BaseColor.GREEN);
                } else {
                    dataCell.setBackgroundColor(BaseColor.WHITE);
                }
                dataCell.setPhrase(new Phrase(cellValue.toString()));
                table.addCell(dataCell);
                isGreen = !isGreen;
            }
        }

        return table;
    }

    public static void main(String[] args) {
        String[] headers = {"Product ID", "Name", "Price", "Tag", "Discount", "TRN", "SO Number"};

        List<List<Object>> data = // Your data

        String outputPath = "product_report.pdf";
        generatePdf(headers, data, outputPath);
        System.out.println("PDF report generated successfully.");
    }
}
