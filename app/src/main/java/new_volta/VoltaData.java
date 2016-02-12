package new_volta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by alkou on 9/2/2016.
 */
public class VoltaData implements Serializable {

    String title,time,place,comment;
    Date date;

    //the ids or the names of the invited friends
    ArrayList<String> ids;

    public VoltaData(String title, Date date, String time, String place, String comment) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.place = place;
        this.comment = comment;
    }
    public VoltaData(String title, Date date, String time, String place, ArrayList<String>ids, String comment) {

        this.ids= new ArrayList<>();
        this.ids.addAll(ids);

        this.title = title;
        this.date = date;
        this.time = time;
        this.place = place;
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }
    public String getDateString(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        StringBuilder sb =new StringBuilder();
        sb.append(cal.get(Calendar.YEAR));
        sb.append("-").append(cal.get(Calendar.MONTH));
        sb.append("-").append(cal.get(Calendar.DAY_OF_MONTH));
        sb.append(" ").append(cal.get(Calendar.HOUR_OF_DAY));
        sb.append(":").append(cal.get(Calendar.MINUTE));
        sb.append(":00");
        return sb.toString();
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ArrayList<String> getIds() {
        return ids;
    }

    public void setIds(ArrayList<String> ids) {
        this.ids = ids;
    }
}
