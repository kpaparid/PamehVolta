package new_volta;


        import android.content.Context;
        import android.support.v7.app.ActionBar;
        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.*;
        import android.graphics.Typeface;

        import com.csd.pahmehvolta.BaseActivity;
        import com.csd.pahmehvolta.R;


public class NewVoltaActivity extends BaseActivity {

    private EditText txtDate;
    private EditText txtTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_volta);


        //action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflator.inflate(R.layout.action_bar, null);

        actionBar.setCustomView(view);


        txtDate = (EditText) findViewById(R.id.editDate);
        //txtDate.setFocusable(false);
        //txtDate.setKeyListener(null);
        txtTime = (EditText)findViewById(R.id.editTime);
        //txtTime.setKeyListener(null);
        //TypeFaceUtil.overrideFont(getApplicationContext(), "SERIF", "LDFComicSans.ttf");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void setDate(View view)
    {
        DatePicker pickerDialog = new DatePicker();
        pickerDialog.setText(txtDate);
        pickerDialog.show(getFragmentManager(), "picker");

    }

    public void setTime(View view)
    {
        TimePicker dialog = new TimePicker();
        dialog.setText(txtTime);
        dialog.show(getFragmentManager(), "time_picker");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
