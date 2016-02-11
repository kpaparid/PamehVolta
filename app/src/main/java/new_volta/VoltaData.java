package new_volta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by alkou on 9/2/2016.
 */
public class VoltaData implements Serializable {

    String title,date,time,place,comment;

    //the ids or the names of the invited friends
    ArrayList<String> ids;

    public VoltaData(String title, String date, String time, String place, String comment) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.place = place;
        this.comment = comment;
    }
    public VoltaData(String title, String date, String time, String place, ArrayList<String>ids, String comment) {

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
