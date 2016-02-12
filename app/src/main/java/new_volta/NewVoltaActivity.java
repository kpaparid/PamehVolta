package new_volta;


import android.app.DatePickerDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import com.csd.pahmehvolta.BaseActivity;
import com.csd.pahmehvolta.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;


public class NewVoltaActivity extends BaseActivity {

    private EditText editDate, editTime;
    private EditText editTitle, editPlace, editComment;

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


        editDate = (EditText) findViewById(R.id.editDate);

        editTime = (EditText)findViewById(R.id.editTime);

        editTitle = (EditText) findViewById(R.id.editTitle);
        editPlace = (EditText) findViewById(R.id.editPlace);
        editComment = (EditText) findViewById(R.id.editComment);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void setDate(View view)
    {

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(this,
                new CustomDatePicker(editDate), mYear, mMonth, mDay);
        dialog.getDatePicker().setMinDate(System.currentTimeMillis());
        dialog.show();

    }

    public void setTime(View view) {
        TimePicker dialog = new TimePicker();
        dialog.setText(editTime);
        dialog.show(getFragmentManager(), "time_picker");
    }



    public void saveVolta(View view)
    {
        View focusTo=null;

        if (editTitle.getText().toString().isEmpty()){
            //if editTitle is empty return and focus to editTitle edit field
            Toast toast = Toast.makeText(getApplicationContext(),"Title is required",Toast.LENGTH_SHORT);
            toast.show();
            focusTo = editTitle;
        }else if(editDate.getText().toString().isEmpty()|| editTime.getText().toString().isEmpty()){
            //case either date or editTitle (or both) is empty
            Toast toast = Toast.makeText(getApplicationContext(),"Date and time are required",Toast.LENGTH_SHORT);
            toast.show();
            if(editTime.getText().toString().isEmpty()){
                focusTo= editTime;
            }
            if(editDate.getText().toString().isEmpty()){
                focusTo = editDate;
            }
        }else if (editPlace.getText().toString().isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(),"Place is required field",Toast.LENGTH_SHORT);
            toast.show();
            focusTo = editPlace;
        }

        //if any required field is empty cahnge focus and halt
        if(focusTo != null){
            focusTo.requestFocus();
            return;
        }

        //transmute the time date from text to Date object
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm");
        String dateInString = editDate.getText().toString()+ " "+ editTime.getText().toString();
        Log.d(TAG,"dateInString : "+dateInString+"\n");
        Date date = null;
        try {
            date = sdf.parse(dateInString);
        } catch (ParseException e) {
            Log.d(TAG, "Exception parsing date!" +"\n");
            e.printStackTrace();
            editDate.requestFocus();
            return;
        }
        Log.d(TAG,"date:"+date+"\n");
        //Toast.makeText(this,"date: "+date,Toast.LENGTH_SHORT).show();

        VoltaData volta= new VoltaData(editTitle.getText().toString(), date, editTime.getText().toString(), editPlace.getText().toString(), editComment.getText().toString());

        if(isOnline()) {
            new SaveNewVolta(volta).execute(((Void) null));
        }else {
            Toast.makeText(this, "No internet connection!", Toast.LENGTH_LONG).show();
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    public class SaveNewVolta extends AsyncTask<Void, Void, Boolean> {

        private String title,place, comment;
        private String dateString;
        private ArrayList<Integer> friends;

        private int result;

        SaveNewVolta(VoltaData volta){
            title = volta.getTitle();
            place = volta.getPlace();
            comment = volta.getComment();

            dateString = volta.getDateString();

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
                postVolta.put("date",this.dateString);//note: it must be in readable format by the database
                postVolta.put("place", this.place);

                // postVolta.put("participants", this.friends.toString());
                postVolta.put("comment", this.comment);

                Log.d(TAG, "starting");
                //Log.d(TAG,"title:"+title);
                // getting product details by making HTTP request

                result = returnCode(performPostCall(SAVE_VOLTA_URL, postVolta));

                Log.d(TAG, "result: " + result);


            } catch (Exception e) {
                e.printStackTrace();
                Log.w(TAG,"exception sto performPostCall");
            }

            return true;
        }

        private int returnCode(String html){
            if(html == "")
                return 0;

            return html.charAt(html.indexOf("result")+8) - 48;
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
                conn.setDoInput(true);
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
                    //TODO: na ftiaksw ti epistrefei to site.Na epistrefei true/false analoga me tin epituxia i oxi tou query
                    //TODO: kai analoga na enimerwnw ton xristi me toast kai isws na ton pigainoume kapou allou
                    Log.d(TAG,"epistrofi apo to site "+ response);

                    Spanned html =  Html.fromHtml(response);

                }
                else {
                    response="";

                }

            } catch (Exception e) {
                Log.d(TAG,"exception sto perform post call: "+ e);
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

            return result.toString();
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            //TODO: Handle returned result
            /*switch (result) {
                case 0: //CONNECTION_ERROR
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
            //if success get outta here
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
