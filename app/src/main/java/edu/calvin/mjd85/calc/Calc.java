package edu.calvin.mjd85.calc;

import java.lang.Integer;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;
import android.view.View.OnClickListener;
import java.util.ArrayList;
import android.widget.AdapterView.OnItemSelectedListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Calc extends Activity {

    int value1;
    int value2;
    int position;
    final EditText value1Text = (EditText) findViewById(R.id.value1);
    Integer val1 = Integer.parseInt(value1Text.getText().toString());
    private SharedPreferences savedValues;

    final EditText value2Text = (EditText) findViewById(R.id.value2);
    Integer val2 = Integer.parseInt(value2Text.getText().toString());
    //Integer val1 = Integer.parseInt(findViewById(R.id.text1).toString());
    //Integer val2 = Integer.parseInt(findViewById(R.id.value2).toString());
    EditText message = (EditText) findViewById(R.id.answer);

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Creating adapter for spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(//creates the array adapter for this spinner
                this, R.array.spinner, android.R.layout.simple_spinner_item);

        // Drop down layout style - list view with radio button
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {//listener for click

            public void onClick(View v) {
                Integer selection = spinner.getSelectedItemPosition();

                if (selection == 1) {
                    Integer answer = val1 + val2;
                    message.setText(answer);
                } else if (selection == 2) {
                    Integer answer = val1 - val2;
                    message.setText(answer);
                } else if (selection == 3) {
                    Integer answer = val1 * val2;
                    message.setText(answer);
                } else if (selection == 4) {
                    Integer answer = val1 / val2;
                    message.setText(answer);
                }
            }
        });
    }
    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putInt("value one", val1);
        editor.putInt("value two", val2);
        editor.putInt("operator value", position);
        editor.commit();
    }
    //override for onresume. pulls stored values and restores them when the app is active again after being paused.
    @Override
    public void onResume() {
        super.onResume();
        value1 = savedValues.getInt("value one", val1);
        value2 = savedValues.getInt("value two", val2);
        position = savedValues.getInt("operator value", position);
    }
}

