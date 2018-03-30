import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

public class TableRow {
    private Date dateWork = new Date();
    private Vector<Integer> in = new Vector<>();
    private Vector<Integer> out = new Vector<>();
    private Vector<Integer> timeWork = new Vector<>();
    private String roomIn = "";
    private String roomOut = "";

    public boolean setDateWork(String date) {
        DateFormat format = new SimpleDateFormat("dd.mm.yyyy");
        try {
            this.dateWork = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Date makeDate(String date) {
        DateFormat format = new SimpleDateFormat("dd.mm.yyyy");
        Date newDate = new Date();
        try {
            newDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return newDate;
    }

    public void setIn(String in) {
        try {
            String[] twoPartIn = splitTime(in);
            this.in.add(Integer.parseInt(twoPartIn[0]));
            this.in.add(Integer.parseInt(twoPartIn[1]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setOut(String out) {
        try {
            String[] twoPartOut = splitTime(out);
            this.out.add(Integer.parseInt(twoPartOut[0]));
            this.out.add(Integer.parseInt(twoPartOut[1]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] splitTime(String time) {
        String[] twoPart = time.split(" ");
        if (twoPart.length > 1)
            twoPart = twoPart[1].split(":");
        else
            twoPart = time.split(":");
        return twoPart;
    }

    public void setTimeWork(String timeWork) {
        try {
            String[] twoPartTimeWork = splitTime(timeWork);
            this.timeWork.add(Integer.parseInt(twoPartTimeWork[0]));
            this.timeWork.add(Integer.parseInt(twoPartTimeWork[1]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setRoomIn(String roomIn) {
        this.roomIn = roomIn;
    }

    public void setRoomOut(String roomOut) {
        this.roomOut = roomOut;
    }

    public String getRoomIn() {
        return roomIn;
    }

    public String getRoomOut() {
        return roomOut;
    }

    public String getTimeWork() {
        String str = String.valueOf(timeWork.get(0));
        str += ":";
        str += String.valueOf(timeWork.get(1));
        return str;
    }

    public Date getDateWork() {
        return dateWork;
    }

    public String getIn() {
        String str = String.valueOf(in.get(0));
        str += ":";
        str += String.valueOf(in.get(1));
        return str;
    }

    public String getOut() {
        String str = String.valueOf(out.get(0));
        str += ":";
        str += String.valueOf(out.get(1));
        return str;
    }
}
