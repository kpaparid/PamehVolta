package new_volta;

import android.app.*;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;

/**
 * Created by sAnideos on 4/12/2015.
 */
public class TimeSettings implements TimePickerDialog.OnTimeSetListener{

    Context context;
    EditText txtTime;

    public void setEditText(EditText txtDate)
    {
        this.txtTime = txtDate;
    }
    public TimeSettings(Context context)
    {
        this.context = context;
    }


    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String tempH = hourOfDay+"";
        String tempM = minute+"";
        if(hourOfDay < 10)
        {
            tempH = "0"+tempH;
        }
        if(minute < 10)
        {
            tempM = "0"+tempM;
        }
        txtTime.setText(tempH + ":" + tempM);
        Toast toast = Toast.makeText(context,  tempH + ":" + tempM, Toast.LENGTH_LONG);
        toast.show();
    }
}
