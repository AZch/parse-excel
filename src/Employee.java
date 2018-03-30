import java.util.Vector;

public class Employee {
    private Vector<TableRow> allRow = new Vector<>(); // записи таблицы
    private Vector<Integer> allTimeWork = new Vector<>();
    private String name; // имя сотрудника
    private long num; // табельный номер

    public long getNum() {
        return num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public void addRow(TableRow tableRow) {
        if (!zeroStr(tableRow))
            allRow.add(tableRow);
    }

    public void addTimeWork(String timeWork) {
        String[] partTime = timeWork.split(":");
        if (partTime.length > 2) {
            if (allTimeWork.size() > 2) {
                if (allTimeWork.get(2) + Integer.parseInt(partTime[2]) >= 60) {
                    allTimeWork.set(2, allTimeWork.get(2) + Integer.parseInt(partTime[2]) - 60);
                    if (allTimeWork.get(1) + Integer.parseInt(partTime[1]) + 1 >= 24) {
                        allTimeWork.set(1, allTimeWork.get(1) + Integer.parseInt(partTime[1]) - 24);
                        allTimeWork.set(0, allTimeWork.get(0) + Integer.parseInt(partTime[0]) + 1);
                    } else {
                        allTimeWork.set(1, allTimeWork.get(1) + Integer.parseInt(partTime[1]) + 1);
                        allTimeWork.set(0, allTimeWork.get(0) + Integer.parseInt(partTime[0]));
                    }
                } else {
                    allTimeWork.set(0, allTimeWork.get(0) + Integer.parseInt(partTime[0]));
                    allTimeWork.set(1, allTimeWork.get(1) + Integer.parseInt(partTime[1]));
                    allTimeWork.set(2, allTimeWork.get(2) + Integer.parseInt(partTime[2]));
                }
            } else {
                allTimeWork.add(Integer.parseInt(partTime[0]));
                allTimeWork.add(Integer.parseInt(partTime[1]));
                allTimeWork.add(Integer.parseInt(partTime[2]));
            }
        }
    }

    // проверка на пустуб строку
    private boolean zeroStr(TableRow tableRow) {
        // параметры пустой строки
        if (tableRow.makeDate("01.01.2000").toString().equals(tableRow.toString()) &&
                tableRow.getIn().equals("0:0") && tableRow.getOut().equals("0:0") &&
                tableRow.getDateWork().equals("0:0") && tableRow.getRoomIn().equals("") &&
                tableRow.getRoomOut().equals(""))
            return true;
        return false;
    }
}
