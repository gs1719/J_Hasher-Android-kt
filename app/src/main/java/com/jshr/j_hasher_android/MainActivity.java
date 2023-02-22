/*
     MainActivity.java is part of J Hasher (Android).

    J Hasher (Android) is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

    J Hasher (Android) is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along with Foobar. If not, see <https://www.gnu.org/licenses/>.
*/

package com.jshr.j_hasher_android;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity{

    private static final int TIME_INTERVAL = 2000;
    private long backPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void OnAbtC(View view){

    }

    public void OnHtC(View view){

        Intent myIntent = new Intent(MainActivity.this, String_Hasher.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void OnHfC(View view){

        Intent myIntent = new Intent(MainActivity.this, File_Hasher.class);
        MainActivity.this.startActivity(myIntent);
    }
    public void OnChC(View view){

    }

    public void GoToDev(View view){
//        Uri uri = Uri.parse("https://github.com/iharshpathak");
//        startActivity(new Intent(Intent.ACTION_VIEW));
        String url = "https://github.com/iharshpathak";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    public void onBackPressed(){
        if (backPressed + TIME_INTERVAL > System.currentTimeMillis()){
            super.onBackPressed();
            return;
        } else{
            Toast.makeText(getBaseContext(),"Press Back Again To Exit", Toast.LENGTH_SHORT).show();
        }
        backPressed = System.currentTimeMillis();
    }
}