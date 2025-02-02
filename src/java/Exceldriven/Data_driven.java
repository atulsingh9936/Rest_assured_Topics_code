package src.java.Exceldriven;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Data_driven {

    public ArrayList<String> getdata(String testcaseName,String sheetname) throws IOException {
        ArrayList <String> a = new ArrayList<String>();
        FileInputStream fis = new FileInputStream("C:\\Users\\Atul\\Downloads\\Demodata.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        int sheets = workbook.getNumberOfSheets();
        for(int i=0;i<sheets;i++) {
            if(workbook.getSheetName(i).equalsIgnoreCase(sheetname)) {

                XSSFSheet sheet = workbook.getSheetAt(i);

                Iterator <Row> rows = sheet.iterator();
                Row firstrow = rows.next();
                Iterator <Cell>  ce = firstrow.cellIterator();
                int k=0;
                int column=0;
                while (ce.hasNext()){
                    Cell value=ce.next();
                    if(value.getStringCellValue().equalsIgnoreCase("TestCases")){
                        column=k;

                    }
                    k++;

                }
                System.out.println(column);
                while(rows.hasNext()){
                    Row r=  rows.next();
                    if( r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName)){
                        Iterator<Cell>  cv=  r.cellIterator();
                        while(cv.hasNext()){

                            Cell c = cv.next();
                            if(c.getCellType()== CellType.STRING) {

                                a.add(c.getStringCellValue());
                            }
                            else{
                               a.add( NumberToTextConverter.toText(c.getNumericCellValue()));

                            }
                        }
                    }

                }


            }
        }

        return a;
    }


}
