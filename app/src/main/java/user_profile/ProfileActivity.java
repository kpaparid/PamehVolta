package user_profile;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import com.csd.pahmehvolta.R;

public class ProfileActivity extends AppCompatActivity {


    public void init(){
        ImageButton imageView1;
        RoundImage roundedImage;
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.unknown);
        roundedImage = new RoundImage(bm);

        imageView1 = (ImageButton) findViewById(R.id.button_topleft);
        imageView1.setImageDrawable(roundedImage);
        imageView1 = (ImageButton) findViewById(R.id.buttonp1);
        imageView1.setImageDrawable(roundedImage);
        imageView1 = (ImageButton) findViewById(R.id.buttonp2);
        imageView1.setImageDrawable(roundedImage);
        imageView1 = (ImageButton) findViewById(R.id.buttonp3);
        imageView1.setImageDrawable(roundedImage);
        imageView1 = (ImageButton) findViewById(R.id.buttonp4);
        imageView1.setImageDrawable(roundedImage);
        imageView1 = (ImageButton) findViewById(R.id.buttonp5);
        imageView1.setImageDrawable(roundedImage);
        imageView1 = (ImageButton) findViewById(R.id.buttont1);
        imageView1.setImageDrawable(roundedImage);
        imageView1 = (ImageButton) findViewById(R.id.buttont2);
        imageView1.setImageDrawable(roundedImage);
        imageView1 = (ImageButton) findViewById(R.id.buttont3);
        imageView1.setImageDrawable(roundedImage);
        imageView1 = (ImageButton) findViewById(R.id.buttont4);
        imageView1.setImageDrawable(roundedImage);
        imageView1 = (ImageButton) findViewById(R.id.buttont5);
        imageView1.setImageDrawable(roundedImage);
        getprofile();
        getTopPlaces();
        getTopFriends();
        getLastVolta();
        TextView tv=(TextView)findViewById(R.id.textView2);

    }

    private void getTopPlaces() {

    }
    private void getTopFriends() {
    }
    private void getLastVolta() {
        String lastvolta="29/11/2015";
        TextView tv=(TextView)findViewById(R.id.textView14);
        tv.setText(lastvolta);
    }
    private void getprofile() {
        String age="15";
        String friends="10";
        String groups="15";
        String voltes="5";
        String name="John Smith";
        TextView tv=(TextView)findViewById(R.id.textView6);
        tv.setText(age);
        tv=(TextView)findViewById(R.id.textView7);
        tv.setText(friends);
        tv=(TextView)findViewById(R.id.textView8);
        tv.setText(groups);
        tv=(TextView)findViewById(R.id.textView9);
        tv.setText(voltes);
        tv=(TextView)findViewById(R.id.textView10);
        tv.setText(name);
    }
    private static final int SELECT_PICTURE = 1;

    private String selectedImagePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
        TextView tv=(TextView)findViewById(R.id.textView2);
        //Typeface face=Typeface.createFromAsset(getAssets(),"Exo2-ThinCondensed.otf");
       // tv.setTypeface(face);
//        ImageButton ib = (ImageButton) findViewById(R.id.button_topleft);
//        ib.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Change Photo!",
//                        Toast.LENGTH_LONG).show();
//            }
//        });

        findViewById(R.id.button_topleft)
                .setOnClickListener(new View.OnClickListener() {

                    public void onClick(View arg0) {

                        // in onCreate or any event where your want the user to
                        // select a file
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent,
                                "Select Picture"), SELECT_PICTURE);
                    }
                });


    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                selectedImagePath = getPath(selectedImageUri);
            }
        }
    }
    public String getPath(Uri uri) {
        // just some safety built in
        if( uri == null ) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        // this is our fallback here
        return uri.getPath();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;

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
