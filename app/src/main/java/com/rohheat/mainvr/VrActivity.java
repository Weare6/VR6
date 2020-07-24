package com.rohheat.mainvr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class VrActivity extends AppCompatActivity {

    private ImageView download;
    private TextView appstatus;

    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private StorageReference ref;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vr);

        download = findViewById(R.id.skateboard);
        appstatus = findViewById(R.id.appstatus);

        PackageManager pm = getApplicationContext().getPackageManager();
        boolean isInstalled = isPackageInstalled("com.vr6.sample", pm);

        if(isInstalled){
            appstatus.setText("Click to open activity");
        }else{
            appstatus.setText("Click to download download");
        }


        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PackageManager pm = getApplicationContext().getPackageManager();
                boolean isInstalled = isPackageInstalled("com.vr6.sample", pm);

                if(isInstalled){
                    Intent open = getPackageManager().getLaunchIntentForPackage("com.vr6.sample");
                    Toast.makeText(VrActivity.this,"Be ready with VR BOX",Toast.LENGTH_LONG).show();
                    startActivity(open);
                }else {
                    downloadApk();
                    Toast.makeText(VrActivity.this,"Downloading...",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean isPackageInstalled(String packageName, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public void downloadApk(){

        storageReference = firebaseStorage.getInstance().getReference();
        ref = storageReference.child("vrapks").child("sampleforapp.apk");

        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                String url = uri.toString();
                downloadFile(getApplicationContext(),"sampleforapp",".apk",DIRECTORY_DOWNLOADS,url);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"something went wrong",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void downloadFile(Context context,String fileName , String fileExtension , String destinationDirectory, String url){

        DownloadManager downloadManager = (DownloadManager) context
                .getSystemService(context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context,destinationDirectory,fileName + fileExtension);

        downloadManager.enqueue(request);
    }

}