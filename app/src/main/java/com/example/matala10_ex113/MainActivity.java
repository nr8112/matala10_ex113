package com.example.matala10_ex113;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author Noa Rahamim
 * @version 1.0
 * @since 2/1/2021
 *  description:Internal Files:
 *  The application includes-
 * An input field in which he will write text
 * A text display field in which text is displayed
 * Two buttons:
 * Each click on the Save button will save and add the typed text to previously typed text
 * Each click on the Reset button will delete the history of all text typing
 * Each click on the Exit button will respond like the Save button and also finish the program!
 * Each time the program is run it will read all the text saved in the last port and will be displayed in the appropriate field
 */
public class MainActivity extends AppCompatActivity {


    String klick,all;       /** keeps the user input. */
    EditText ed;
    TextView tv;


    /**
     * onCreate...
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed = findViewById(R.id.et);
        tv = findViewById(R.id.tv);

        try {
            FileInputStream fis= openFileInput("text.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer sb = new StringBuffer();
            klick = br.readLine();
            while (klick != null) {
                sb.append(klick+'\n');
                klick = br.readLine();
            }
            all=sb.toString();
            isr.close();
            tv.setText(all);
        } catch (IOException e) {
            e.printStackTrace();
        }
        }





//------------------------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * Writing to text file text.txt from the text the user typed
     *
     * @param view the view
     */
    public void save(View view) {
        String strwr=ed.getText().toString();
        try {
            FileOutputStream fos = openFileOutput("text.txt",MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(strwr);
            bw.close();
            tv.setText(""+all+" "+strwr);
            all=strwr+all;
            ed.setText("");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * reset the file .
     *
     * @param view the view
     */
    public void reset(View view) {
        try {
            FileOutputStream ofo = openFileOutput("text.txt",MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(ofo);
            BufferedWriter bw = new BufferedWriter(osw);
            all="";
            bw.write(all);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tv.setText("");
        ed.setText("");
    }

    /**
     * Exits the program & saves information.
     *
     * @param view the view
     */
    public void exit(View view) {
        String strwr=tv.getText().toString();
        try {
            FileOutputStream fos = openFileOutput("text.txt",MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            all=strwr+all;
            bw.write(all);
            bw.close();
            finish();
        } catch (IOException e) {
            e.printStackTrace();
            finish();
        }
    }


    //-------------------------------------------------------------------------------------------------------------------------

    /**
     * General menu
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    /**
     * gos to cred activity
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String st = item.getTitle().toString();
        if (st.endsWith("Credits")) {
            Intent si = new Intent(this, cred.class);
            startActivity(si);
        }
        return true;
    }
}

