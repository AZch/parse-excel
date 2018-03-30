import java.io.File;
import java.util.Vector;

public class Main {
    public static void main(String[] argv) {
        ParseExcel Pares = new ParseExcel();
        LoadAllFiles loadAllFiles = new LoadAllFiles();
        Vector<Employee> allEmployee = loadAllFiles.processFilesFromFolder(new File("C:\\Users\\anton\\Desktop\\summer"), null);
        int i = 6;
    }
}
