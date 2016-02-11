package new_volta;


        import android.app.FragmentTransaction;
        import android.content.Context;
        import android.os.AsyncTask;
        import android.support.v7.app.ActionBar;
        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.text.Html;
        import android.text.Spanned;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.*;
        import android.graphics.Typeface;

        import com.csd.pahmehvolta.BaseActivity;
        import com.csd.pahmehvolta.R;

        import java.io.BufferedReader;
        import java.io.BufferedWriter;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.OutputStream;
        import java.io.OutputStreamWriter;
        import java.io.UnsupportedEncodingException;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.net.URLEncoder;
        import java.text.DateFormat;
        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.Calendar;
        import java.util.Date;
        import java.util.GregorianCalendar;
        import java.util.HashMap;
        import java.util.Locale;
        import java.util.Map;

        import javax.net.ssl.HttpsURLConnection;

        import login.HttpHandler;


public class NewVoltaActivity extends BaseActivity {

    private EditText txtDate,txtTime;
    private EditText title,place,comment;

    private static final String SAVE_VOLTA_URL = "http://koufalex.webpages.auth.gr/volta/newVolta.php" ;

    private final String TAG="newVolta";

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

        title  = (EditText) findViewById(R.id.editTitle);
        place  = (EditText) findViewById(R.id.editPlace);
        comment  = (EditText) findViewById(R.id.editComment);

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

    public void saveVolta(View view)
    {
        Log.d(TAG,"sto save Volta");

        /*
        title.getText()
        txtDate.getText()
        txtTime.getText()
        place.getText()

        comment.getText()
         */

        /*Toast toast = Toast.makeText(getApplicationContext(),(txtDate.getText()+" "+txtTime.getText()+""+title.getText()+" "+place.getText()
        + " "+comment.getText() ) ,Toast.LENGTH_SHORT);
        toast.show();*/
        //public VoltaData(String title, String date, String time, String place, String comment) {
        VoltaData volta= new VoltaData(title.getText().toString(), txtDate.getText().toString(),txtTime.getText().toString(),place.getText().toString(),comment.getText().toString());


        new SaveNewVolta(volta.getTitle(),volta.getDate(),
                volta.getTime(),volta.getPlace(),null,
                volta.getComment()).execute(((Void) null));



    }

    public class SaveNewVolta extends AsyncTask<Void, Void, Boolean> {

        private final String title,place, comment;
        private Date date=null;
        private ArrayList<Integer> friends;

        private int result;

        SaveNewVolta(String title,String date, String time, String place, ArrayList<Integer> friends,String comment) {

            this.title = title;
            this.place = place;
            this.comment = comment;


/*
            try {

                date+=" "+time;
                Log.d(TAG, date);
                Calendar mydate = new GregorianCalendar();
//check if the format is correct
                this.date = new SimpleDateFormat("d-M-YYYY HH:mm", Locale.ENGLISH).parse(date);
                Log.d(TAG, this.date.toString());
                mydate.setTime(this.date);
                //breakdown
               /* System.out.println("mydate -> "+mydate);
                System.out.println("year   -> "+mydate.get(Calendar.YEAR));
                System.out.println("month  -> "+mydate.get(Calendar.MONTH));
                System.out.println("dom    -> "+mydate.get(Calendar.DAY_OF_MONTH));
                System.out.println("dow    -> "+mydate.get(Calendar.DAY_OF_WEEK));
                System.out.println("hour   -> "+mydate.get(Calendar.HOUR));
                System.out.println("minute -> "+mydate.get(Calendar.MINUTE));
                System.out.println("second -> "+mydate.get(Calendar.SECOND));
                System.out.println("milli  -> "+mydate.get(Calendar.MILLISECOND));
                System.out.println("ampm   -> "+mydate.get(Calendar.AM_PM));
                System.out.println("hod    -> "+mydate.get(Calendar.HOUR_OF_DAY));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }*/
            if (friends != null){
                this.friends =new ArrayList<>();
                this.friends.addAll(friends);
            }
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                // Building Parameters
                HashMap<String, String> postVolta = new HashMap<>();
                postVolta.put("title", this.title);
                postVolta.put("date",this.date.toString());
                postVolta.put("place", this.place);
                postVolta.put("participants", this.friends.toString());
                postVolta.put("comment",this.comment);

                Log.d(TAG, "starting");
                // getting product details by making HTTP request
                Log.d(TAG,"perform post call:"+performPostCall(SAVE_VOLTA_URL, postVolta));

                Log.d(TAG, "result: " + result);


            } catch (Exception e) {
                e.printStackTrace();
                Log.w(TAG,"exception sto performPostCall");
            }

            return true;
        }

        private String  performPostCall(String requestURL, HashMap<String, String> postDataParams) {

            URL url;
            String response = "";


            try {
                url = new URL(requestURL);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
//                conn.setDoInput(true);
                conn.setDoOutput(true);


                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();
                int responseCode=conn.getResponseCode();

                Log.d(TAG,"responce code ="+ responseCode);

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line=br.readLine()) != null) {
                        response+=line;
                    }
                    Log.d(TAG,"epistrofi apo to site "+ response);

                    Spanned html =  Html.fromHtml(response);

                }
                else {
                    response="";

                }


/*
                HttpResponse httpResponse = httpClient.execute(httpPost);

                HttpEntity entity = httpResponse.getEntity();

                inputStream = entity.getContent();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);

                StringBuilder sb = new StringBuilder();

                String line;

                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }

                jsonString = sb.toString();

                jArray = new JSONArray(jsonString);

                result = jsonString;*/


            } catch (Exception e) {
                Log.d(TAG,"exception sto perform post call");
                e.printStackTrace();
            }

            return response;
        }

        private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
            StringBuilder result = new StringBuilder();
            boolean first = true;
            for(Map.Entry<String, String> entry : params.entrySet()){
                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            }

            Log.d(TAG,"WeirdStrat"+result.toString());
            return result.toString();
        }

        @Override
        protected void onPostExecute(final Boolean success) {

            //showProgress(false);

           /* switch (result) {
                case HttpHandler.CONNECTION_ERROR:
                    Log.d("Login Failure!", "Could not connect to database");
                    showAlertDialog("Connection Error", "Could not connect to the database, check your internet connection and try again.");
                    break;
                case HttpHandler.LOGIN_USERNAME_ERROR:
                    Log.d("Login Failure!", "Username does not exist");
                    mEmailView.setError("Email does not exist.");
                    mEmailView.requestFocus();
                    break;
                case HttpHandler.LOGIN_PASSWORD_ERROR:
                    Log.d("Login Failure!", "Wrong password");
                    mPasswordView.setError("Wrong Password");
                    mPasswordView.requestFocus();
                    break;
                case HttpHandler.LOGIN_SUCCESS:
                    Log.d("Login Failure!", "Success!!!!!!");
                    showAlertDialog("Success!", "Everything OK! Now the app will exit :(");
                    break;
            }*/
    Toast toast = Toast.makeText(getApplicationContext(),"on post execute shit",Toast.LENGTH_SHORT);
            toast.show();

        }

        @Override
        protected void onCancelled() {

        }
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
