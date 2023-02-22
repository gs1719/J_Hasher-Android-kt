/*
     Hasher.java is part of J Hasher (Android).

    J Hasher (Android) is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

    J Hasher (Android) is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

    You should have received a copy of the GNU General Public License along with Foobar. If not, see <https://www.gnu.org/licenses/>.
*/



package com.jshr.j_hasher_android;

import android.os.Build;
import androidx.annotation.RequiresApi;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {
    private byte[] input;
    private String Algorithm;
    public String HashVal;
    public String FHashVal;

    public void setStringHash(String inp, String Al){
        this.input = inp.getBytes();
        this.Algorithm =Al;
        this.HashVal = HasherCore();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setFileHash(String fPath, String Al){
//        String RfPath="";
//        try {
//            for(int i=0;i<fPath.length();i++){
//                if(fPath.charAt(i)=='/'){
//                    RfPath=RfPath+"//";
//                }
//                else{
//                    RfPath=RfPath+fPath.charAt(i);
//                }
//            }
//            File file = new File(RfPath);
//            this.input= Files.readAllBytes(file.toPath());
//            this.Algorithm =Al;
//            this.FHashVal = HasherCore();
//        }catch (Exception e){};
    }

    private String HasherCore(){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(Algorithm);
            messageDigest.update(input);
            byte[] digestedBytes = messageDigest.digest();

            StringBuilder hexString = new StringBuilder();

            for (byte aMsgDigest : digestedBytes){
                String h = Integer.toHexString(0xFF & aMsgDigest);
                while (h.length()<2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        }catch (Exception e){e.printStackTrace();};
        return "";
    }

    public String getHashVal(){
        return HashVal;
    }
    public String getFHashVal(){
        return FHashVal;
    }

    public String calculateMD5ofFile(String location) throws IOException, NoSuchAlgorithmException {
        String RfPath="";
        for(int i=0;i<location.length();i++){
                if(location.charAt(i)=='/'){
                    RfPath=RfPath+"//";
                }
                else{
                    RfPath=RfPath+location.charAt(i);
                }
            }
        FileInputStream fs= new FileInputStream(RfPath);
        MessageDigest md = MessageDigest.getInstance("MD5");
        int bufferSize= 8192;
        byte[] buffer=new byte[bufferSize];
        int bytes=0;
        do{
            bytes=fs.read(buffer,0,bufferSize);
            if(bytes>0)
                md.update(buffer,0,bytes);

        }while(bytes>0);
        byte[] Md5Sum = md.digest();
        return ByteArraytoHexString(Md5Sum);
    }
    public static String ByteArraytoHexString(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
