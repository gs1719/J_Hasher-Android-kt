
/*
     File_Hasher.java is part of J Hasher (Android).

    J Hasher (Android) is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

    J Hasher (Android) is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along with Foobar. If not, see <https://www.gnu.org/licenses/>.
*/

package com.jshr.j_hasher_android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.FileUtils;
import android.os.Vibrator;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class File_Hasher extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    private String AlgoSelected="";
    Spinner AlFspinner;
    TextView AlgT, HeadTxt;
    EditText htxt;
    String filePath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_hasher);
        AlFspinner = findViewById(R.id.FSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Algos,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        AlFspinner.setAdapter(adapter);
        AlFspinner.setOnItemSelectedListener(this);
    }

    public void GetFilePath(View view) {
                showFileChooser();
    }


    private static final int FILE_SELECT_CODE = 0;

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("*/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(intent , 1);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK){
            Uri uri = data.getData();
            Context context = getApplicationContext();
            this.filePath = RealPathUtil.getRealPath(context, uri);
            Toast.makeText(this,"Uri = "+uri.toString() + "\n \nPath = "+filePath,Toast.LENGTH_LONG).show();
        }
    }

    public void GetBacked(View view) {
        Intent myIntent = new Intent(File_Hasher.this, MainActivity.class);
        File_Hasher.this.startActivity(myIntent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        AlgoSelected = adapterView.getItemAtPosition(i).toString();
        AlgT = findViewById(R.id.AlTxt);
        if(AlgoSelected.equals("")){AlgT.setText("Select Algorithm"); }
        else{
            AlgT.setText(AlgoSelected+" Algorithm Selected");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void GenFHash(View view) throws IOException, NoSuchAlgorithmException {
        Button button = findViewById(R.id.FhshBtn);
        htxt = findViewById(R.id.FileHTxt);
        if(AlgoSelected.equals("") && filePath.equals("")){
            AlgT.setText("Select Algorithm");
            Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vib.vibrate(500);
            Toast.makeText(this, "Please Select An Algorithm \n And Select Any File", Toast.LENGTH_SHORT).show();
        }
        else if(AlgoSelected.equals("")){
            AlgT.setText("Select Algorithm");
            Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vib.vibrate(500);
            Toast.makeText(this, "Please Select Any Algorithm", Toast.LENGTH_SHORT).show();
        }
        else if(filePath.equals("")){
            Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vib.vibrate(500);
            Toast.makeText(this, "Please Select Any File", Toast.LENGTH_SHORT).show();
        }
        else{
            Hasher hasher = new Hasher();
            HeadTxt = findViewById(R.id.HdTxt);
            HeadTxt.setText(AlgoSelected+" \n H a s h  G e n e r a t e d");
            htxt.setText(hasher.calculateMD5ofFile(filePath));
           // Toast.makeText(this, hasher.FileHasher(filePath,AlgoSelected), Toast.LENGTH_SHORT).show();
            button.setText("Generated !");

        }
    }

}