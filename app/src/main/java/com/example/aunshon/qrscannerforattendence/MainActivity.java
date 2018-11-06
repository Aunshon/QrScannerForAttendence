package com.example.aunshon.qrscannerforattendence;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button scanBtn;
    private int CAMERA_PERMISSION_CODE=1;
     public  static TextView resultview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanBtn=findViewById(R.id.scanBtn);
        resultview=findViewById(R.id.resultview);

    }

    public void scanBtnClicked(View view) {
        if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED){

            //Toast.makeText(this, "Permisssion already granted", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(MainActivity.this,ScannerActivity.class);
            startActivity(intent);
        }
        else {
            RequestCameraPermission();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==CAMERA_PERMISSION_CODE && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            //Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(MainActivity.this,ScannerActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }

    private void RequestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)){
            new AlertDialog.Builder(this)
                    .setTitle("Camera Permission Needed")
                    .setMessage("Camera permission is denied.To Scan via Camera , permission is needed.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},CAMERA_PERMISSION_CODE);
                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();
        }else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},CAMERA_PERMISSION_CODE);
        }
    }
}
