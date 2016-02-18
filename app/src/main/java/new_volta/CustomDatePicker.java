package new_volta;


import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by sAnideos on 4/12/2015.
 *//*
public class DatePicker2 extends DialogFragment {

    private EditText txtDate;

    public void setText(EditText txtDate)
    {
        this.txtDate = txtDate;
    }


    public Dialog onCreateDialog(Bundle savedInstanceState){

        DateSettings settings = new DateSettings(getActivity());
        settings.setEditText(txtDate);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog;

        dialog = new DatePickerDialog(getActivity(), settings, year, month, day);

        //min date allowed is the current
        dialog.getDatePicker().setMinDate(System.currentTimeMillis());

        dialog.getDatePicker().setCalendarViewShown(false);
        dialog.getDatePicker().setSpinnersShown(true);
        //dialog.getDatePicker().setCalendarViewShown(true);

        return dialog;

    }
}*/

class CustomDatePicker implements DatePickerDialog.OnDateSetListener {

    EditText editText;
    Context context;
    String TAG = "newVolta";

    CustomDatePicker(EditText editText,Context cntx){
        this.editText = editText;
        context = cntx;
    }


    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        int mYear = year;
        int mMonth = monthOfYear;
        int mDay = dayOfMonth;

        Calendar minDate = Calendar.getInstance();
        minDate.set(Calendar.DAY_OF_MONTH, minDate.get(Calendar.DAY_OF_MONTH)-1);

        Calendar cal= Calendar.getInstance();
        cal.clear();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, monthOfYear);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        if(minDate.compareTo(cal) >=0){
            Log.d(TAG,"cal:"+cal.get(Calendar.YEAR)+"-"+ cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.DAY_OF_MONTH));
            Log.d(TAG, "The date given by the user is before current date!\n");
            Toast.makeText(context,"The selected date cannot be before current",Toast.LENGTH_SHORT).show();
        }else{
            editText.setText(new StringBuilder()
                    // Month is 0 based so add 1
                    .append(mDay).append("-").append(mMonth + 1).append("-")
                    .append(mYear).append(" "));
        }

    }
}