package new_volta;


import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.EditText;

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

    CustomDatePicker(EditText editText){
        this.editText = editText;
    }


    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        int mYear = year;
        int mMonth = monthOfYear;
        int mDay = dayOfMonth;
        editText.setText(new StringBuilder()
                // Month is 0 based so add 1
                .append(mDay).append("-").append(mMonth + 1).append("-")
                .append(mYear).append(" "));
    }
}