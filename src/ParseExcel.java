import com.microsoft.schemas.office.visio.x2012.main.CellType;
import javafx.scene.control.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

public class ParseExcel {
    public Employee ReadExcel(String nameFile, Vector<Employee> allEmployee) {
        Employee employee = new Employee();
        try {
            FileInputStream inputStream = new FileInputStream(new File(nameFile));
            String name = getNameFile(nameFile);
            employee.setName(name);

            // книга
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

            // лист
            XSSFSheet sheet = workbook.getSheetAt(0);
            // итератор по листам
            Iterator<Sheet> sheetIterator = workbook.iterator();
            while (sheetIterator.hasNext()) {
                // итератор по строкам
                Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = sheetIterator.next().iterator();
                boolean startRead = false;
                boolean startReadTableRow = false;
                boolean skipRow = false;
                while (rowIterator.hasNext()) {
                    org.apache.poi.ss.usermodel.Row row = rowIterator.next();
                    if (skipRow)
                        continue;

                    // итератор по столбцам
                    Iterator<org.apache.poi.ss.usermodel.Cell> cellIterator = row.cellIterator();
                    int countCell = 0;
                    boolean stop = false;
                    TableRow tableRow = new TableRow();
                    while (cellIterator.hasNext()) {

                        if (stop)
                            break;
                        if (skipRow)
                            break;

                        org.apache.poi.ss.usermodel.Cell cell = cellIterator.next();

                        org.apache.poi.ss.usermodel.CellType cellType = cell.getCellTypeEnum();

                        if (!startRead && cell.getStringCellValue().equals(name))
                            startRead = true;

                        switch (cellType) {
                            case BLANK:
                                //if (countCell < 6 && startReadTableRow && startRead)
                                //    skipRow = true;
                                /*if (countRow > 3) {
                                    switch (countCell) {
                                        case 0:
                                            if (!tableRow.setDateWork("01.01.2000"))
                                                return employee;
                                            break;
                                        case 1:
                                            tableRow.setIn("00:00");
                                            break;
                                        case 2:
                                            tableRow.setOut("00:00");
                                            break;
                                        case 3:
                                            tableRow.setRoomIn("");
                                            break;
                                        case 4:
                                            tableRow.setRoomOut("");
                                            break;
                                        case 5:
                                            tableRow.setTimeWork("00:00");
                                            employee.addRow(tableRow);
                                            stop = true;
                                            break;
                                    }
                                    countCell++;
                                }
                                System.out.print("\t");*/
                                break;
                            case STRING:
                                if (startReadTableRow && name.equals(cell.getStringCellValue()))
                                    startRead = false;
                                if (!startRead && startReadTableRow && countCell == 2) {
                                    //if (isStrTime(cell.getStringCellValue())) {
                                        employee.addTimeWork(cell.getStringCellValue());
                                        //employee.addRow(tableRow);
                                        return employee;
                                    //} else
                                        //return employee;
                                }
                                if (startRead && !startReadTableRow && isDate(cell.getStringCellValue())) // если ещё не читаем записи и дошли до даты
                                    startReadTableRow = true;
                                else if (startRead && !startReadTableRow && countCell == 2) { // если ещё не читаем записи и дошли до табельного номера
                                    employee.setNum(Long.parseLong(cell.getStringCellValue()));
                                    employee = checkNameNum(employee, allEmployee);
                                }
                                if (startRead && startReadTableRow) // читаем записи
                                    switch (countCell) {
                                        case 0:
                                            tableRow.setDateWork(cell.getStringCellValue());
                                            break;
                                        case 1:
                                            //if (isStrTime(cell.getStringCellValue()))
                                                tableRow.setIn(cell.getStringCellValue());
                                            //else
                                                //return employee;
                                            break;
                                        case 2:
                                            //if (isStrTime(cell.getStringCellValue()))
                                                tableRow.setOut(cell.getStringCellValue());
                                            //else
                                               // return employee;
                                            break;
                                        case 3:
                                            tableRow.setRoomIn(cell.getStringCellValue());
                                            break;
                                        case 4:
                                            tableRow.setRoomOut(cell.getStringCellValue());
                                            break;
                                        case 5:
                                            tableRow.setTimeWork(cell.getStringCellValue());
                                            employee.addRow(tableRow);
                                            //stop = true;
                                            break;
                                    }
                                    countCell++;
                                    break;
                                }
                        }
                    }
                    //System.out.println();
                }
                //System.out.println();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
        return employee;
    }

    public static Employee checkNameNum(Employee employee, Vector<Employee> allEmployee) {
        for (Employee oneEmployee : allEmployee) {
            if (employee.getName().equals(oneEmployee.getName()) && employee.getNum() == oneEmployee.getNum()) {
                //System.out.println(employee.getName() + " в него же");
                return oneEmployee;
            }
        }
        return employee;
    }

    public static boolean isDate(String date) {
        String[] splitDate = date.split("\\.");
        if (splitDate.length > 2)
            return true;
        else
            return false;
    }

    public static String getNameFile(String file) {
        return file.subSequence(file.lastIndexOf('\\') + 1, file.lastIndexOf('.')).toString();
    }

    public static boolean isStrTime(String str) {
        String[] strParse = str.split(":");
        if (strParse.equals(str))
            return false;
        else
            return true;
        /*try {
            Float.parseFloat(str);
        } catch(NumberFormatException nfe) {
            return false;
        }*/
        //return true;
    }
}
