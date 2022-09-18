/*
     String_Hasher.java is part of J Hasher (Android).

    J Hasher (Android) is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

    J Hasher (Android) is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along with Foobar. If not, see <https://www.gnu.org/licenses/>.
*/


package com.jshr.j_hasher_android;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.w3c.dom.Text;

public class String_Hasher extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner Alspinner;
   private String AlgoSelected="";
    TextView Alg;
    EditText inp;
     String hashVal;
     TextView Ltxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string_hasher);
        Alspinner = findViewById(R.id.ASpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Algos,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Alspinner.setAdapter(adapter);
        Alspinner.setOnItemSelectedListener(this);
    }

    public void GoBack(View view){
        Intent myIntent = new Intent(String_Hasher.this, MainActivity.class);
        String_Hasher.this.startActivity(myIntent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        AlgoSelected = adapterView.getItemAtPosition(i).toString();
        Alg = findViewById(R.id.AlgSelTxt);
        if(AlgoSelected.equals("")){Alg.setText("Select Algorithm"); }
        else{
            Alg.setText(AlgoSelected+" Algorithm Selected");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void GenHash(View view){
        Button button = findViewById(R.id.GenSHBtn);
        inp = findViewById(R.id.inpTxt);
        if(AlgoSelected.equals("") && inp.getText().toString().equals("")){
            Alg.setText("Select Algorithm");
            Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vib.vibrate(500);
            Toast.makeText(this, "Please Select An Algorithm \n And Enter Any Text", Toast.LENGTH_SHORT).show();
        }
        else if(AlgoSelected.equals("")){
            Alg.setText("Select Algorithm");
            Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vib.vibrate(500);
            Toast.makeText(this, "Please Select Any Algorithm", Toast.LENGTH_SHORT).show();
        }
        else if(inp.getText().toString().equals("")){
            Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vib.vibrate(500);
            Toast.makeText(this, "Please Enter Some Text", Toast.LENGTH_SHORT).show();
        }
        else{
            Hasher hasher = new Hasher();
            hasher.setStringHash(inp.getText().toString(),AlgoSelected);
             this.hashVal = hasher.getHashVal();
             Ltxt = findViewById(R.id.HsTxt);
             Ltxt.setText(AlgoSelected+" \n H a s h  G e n e r a t e d");
             inp.setText(hashVal);
             button.setText("Generated !");

        }

    }
}