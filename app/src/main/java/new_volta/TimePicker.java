package new_volta;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by sAnideos on 4/12/2015.
 */
public class TimePicker extends DialogFragment{


    private EditText txtTime;

    public void setText(EditText txtTime)
    {
        this.txtTime = txtTime;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState){

        TimeSettings settings = new TimeSettings(getActivity());
        settings.setEditText(txtTime);
        TimePickerDialog dialog;
        Calendar c = Calendar.getInstance();
        dialog = new TimePickerDialog(getActivity(), settings, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), false);

        return dialog;

    }


}
