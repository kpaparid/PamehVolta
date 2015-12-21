package new_volta;


        import android.app.DatePickerDialog;
        import android.app.Dialog;
        import android.app.DialogFragment;
        import android.os.Bundle;
        import android.widget.CalendarView;
        import android.widget.EditText;
        import android.widget.TextView;

        import java.util.Calendar;

/**
 * Created by sAnideos on 4/12/2015.
 */
public class DatePicker extends DialogFragment {

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

        dialog.getDatePicker().setCalendarViewShown(false);
        dialog.getDatePicker().setSpinnersShown(true);
        //dialog.getDatePicker().setCalendarViewShown(true);

        return dialog;

    }





}
