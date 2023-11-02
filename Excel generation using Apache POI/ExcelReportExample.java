import java.util.ArrayList;
import java.util.List;

public class ExcelReportExample {

    public static void main(String[] args) {
        String[] headers = {"Product ID", "Name", "Price", "Tag", "Discount", "TRN", "SO Number"};

        List<List<Object>> data = new ArrayList<>();

        // Sample data for products 
        data.add(List.of(1, "Product A", 29.99, "Tag1", 0.1, "TRN123", "SO001"));
        data.add(List.of(2, "Product B", 19.95, "Tag2", 0.05, "TRN124", "SO002"));
        data.add(List.of(3, "Product C", 39.99, "Tag1", 0.15, "TRN125", "SO003"));
        data.add(List.of(4, "Product D", 15.49, "Tag3", 0.2, "TRN126", "SO004"));

        String outputPath = "product_report.xlsx";
        try {
            ExcelGenerator.generateExcel(headers, data, outputPath);
            System.out.println("Excel file generated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}