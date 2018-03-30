import java.io.File;
import java.util.Vector;

public class LoadAllFiles {
    public Vector<Employee> processFilesFromFolder(File folder, Vector<Employee> employees) {
        if (employees == null)
            employees = new Vector<>();
        ParseExcel parseExcel = new ParseExcel();
        File[] folderEntries = folder.listFiles();
        for (File entry : folderEntries) {
            if (entry.isDirectory()) {
                processFilesFromFolder(entry, employees);
                continue;
            } else {
                if (getFileExtension(entry).equals("xlsx")) {
                    Employee employee = parseExcel.ReadExcel(entry.toString(), employees);
                    if (!checkEmp(employee, employees))
                        employees.add(employee);
                }
            }
        }
        return employees;
    }

    public static boolean checkEmp(Employee employee, Vector<Employee> allEmployee) {
        for (Employee oneEmployee : allEmployee) {
            if (employee.getName().equals(oneEmployee.getName()) && employee.getNum() == oneEmployee.getNum()) {
                return true;
            }
        }
        return false;
    }

    //метод определения расширения файла
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        // если в имени файла есть точка и она не является первым символом в названии файла
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            // то вырезаем все знаки после последней точки в названии файла
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }
}
