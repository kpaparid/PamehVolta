package new_volta;


        import android.app.DatePickerDialog;
        import android.content.Context;
        import android.view.LayoutInflater;
        import android.widget.DatePicker;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

/**
 * Created by sAnideos on 4/12/2015.
 */
public class DateSettings implements DatePickerDialog.OnDateSetListener{

    Context context;
    EditText txtDate;
    public DateSettings(Context context)
    {
        this.context = context;
    }

    public void setEditText(EditText txtDate)
    {
        this.txtDate = txtDate;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        txtDate.setText(monthOfYear + " - " + dayOfMonth + " - " + year);
        Toast toast = Toast.makeText(context, "Date: " + monthOfYear + " - " + dayOfMonth + " - " + year, Toast.LENGTH_LONG);
        toast.show();
    }
}
