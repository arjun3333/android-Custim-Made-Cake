package com.example.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.dashboard.adapter.Utility;
import com.example.dashboard.models.CustomizedModel;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Create extends FragmentActivity  {
    public UploadTask uploadTask;
    private DatabaseReference mDatabase,mDatabase2;
    private static final int PICK_IMAGE_REQUEST = 22;
    ImageButton btn, btn1, btn2, btn3, btn4, btn5, btn6;
    private Button btn7, btn9;
    private Uri downloadUri=null;
    StorageReference storageReference;
    // Uri indicates, where the image will be picked from
    private Uri filePath=null;
    FirebaseStorage storage;
    private ProgressDialog progressDialog;
    private int val=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        storage = FirebaseStorage.getInstance();
        progressDialog = new ProgressDialog(this);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Images");
        mDatabase2= FirebaseDatabase.getInstance().getReference().child("Customized");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        btn9 = findViewById(R.id.button9);
        btn7 = findViewById(R.id.createhomebtn);
        btn = findViewById(R.id.button);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (val){

                    case 1:
                        replaceFragment(new Type());
                        break;
                    case 2:
                        replaceFragment(new Shape());
                        break;
                    case 3:
                        replaceFragment(new Size());
                        break;
                    case 4:
                        replaceFragment(new Tiers());
                        break;
                    case 5:
                        replaceFragment(new Flavour());
                        break;
                    case 6:
                        replaceFragment(new Filling());
                        break;
                    case 7:
                        replaceFragment(new Info());
                        break;


                }
                val++;
                if(val>7){
                    val=1;
                }
            }
        });


        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Create.this,MainActivity.class);
                startActivity(intent);

            }
        });



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new Type());
                val=1;
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new Shape());
                val=2;
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new Size());
                val=3;
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new Tiers());
                val=4;
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new Flavour());
                val=5;
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new Filling());
                val=6;
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new Info());
                val=7;
            }

        });



    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame1,fragment);
        fragmentTransaction.commit();
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FragmentManager fm = getSupportFragmentManager();
        int index = requestCode >> 16;
        if (index != 0) {
            index--;
            if (fm.getFragments() == null || index < 0
                    || index >= fm.getFragments().size()) {
                Log.w("TAG", "Activity result fragment index out of range: 0x"
                        + Integer.toHexString(requestCode));
                Toast.makeText(getApplicationContext(), data.getData()+"", Toast.LENGTH_SHORT).show();
                Uri selectedImage = data.getData();
                progressDialog.setTitle("Uploading");
                progressDialog.setMessage("Please wait while Adding Car Detail... ");
                progressDialog.setCancelable(false);
                progressDialog.setProgress(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();

                uploadFile(getUriImage(this, data.getData()));
                return;
            }
            Fragment frag = fm.getFragments().get(index);
            if (frag == null) {
                Log.w("TAG", "Activity result no fragment exists for index: 0x"
                        + Integer.toHexString(requestCode));
            } else {
                handleResult(frag, requestCode, resultCode, data);
            }
            return;
        }

    }
    public static String getFileName(Context context, Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
    public static File getCacheDir(Context context) {
        File cache;
        cache = context.getCacheDir();
        if (!cache.exists())
            cache.mkdirs();
        return cache;
    }
    public String getUriImage(Context context, final Uri uri) {
        String img = "";
        String filePath = uri.getPath();

        String name = getFileName(context, uri);
        File file = new File(getCacheDir(context), name);

        int maxBufferSize = 1 * 1024 * 1024;

        try {
            InputStream inputStream =  getContentResolver().openInputStream(uri);
            Log.e("InputStream Size", "Size " + inputStream);
            int bytesAvailable = inputStream.available();
//                    int bufferSize = 1024;
            int bufferSize = Math.min(bytesAvailable, maxBufferSize);
            final byte[] buffers = new byte[bufferSize];

            FileOutputStream outputStream = new FileOutputStream(file);
            int read = 0;
            while ((read = inputStream.read(buffers)) != -1) {
                outputStream.write(buffers, 0, read);
            }
            Log.e("File Size", "Size " + file.length());
            inputStream.close();
            outputStream.close();

            img = file.getPath();
            Log.e("File Path", "Path " + file.getPath());
            file.length();
            Log.e("File Size", "Size " + file.length());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;

    }
    private void handleResult(Fragment frag, int requestCode, int resultCode,
                              Intent data) {
        frag.onActivityResult(requestCode & 0xffff, resultCode, data);
        List<Fragment> frags = frag.getChildFragmentManager().getFragments();
        if (frags != null) {
            for (Fragment f : frags) {
                if (f != null)
                    handleResult(f, requestCode, resultCode, data);
            }
        }
    }
    public void uploadFile(String uriImage) {
        // ((BaseActivity) getActivity()).showProgressDialog();
        Uri file = Uri.fromFile(new File(uriImage));
        final StorageReference riversRef = storageReference.child("Images/" + file.getLastPathSegment());
        uploadTask = riversRef.putFile(file);

// Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Uri file = Uri.fromFile(new File(uriImage));
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressDialog.dismiss();
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...

                // ((BaseActivity) getActivity()).dimissdialog().dismiss();
            }
        });
        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return riversRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    downloadUri = task.getResult();
                    Toast.makeText(Create.this, downloadUri+"", Toast.LENGTH_SHORT).show();
                    // ((BaseActivity) getActivity()).dimissdialog().dismiss();
                    //senMessageToDB(downloadUri.toString());
                    add_car(Utility.filling,
                            Utility.type,
                            Utility.tire,
                            Utility.notes,
                            Utility.flavour);
                } else {
                    // Handle failures
                    // ...
                }
            }
        });
    }


    private void add_car(String name, String model,
                         String price, String condition, String seats) {

        String key1;
        String key2;
        // if(car==null) {
        // key1 = mDatabase.push().getKey();
        key2 = mDatabase2.push().getKey();
        //}else{
        //  key1 = car.getKey();
        // key2 = car.pickup_loc;
        // }
        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = current_user.getUid();

        Map userMap = new HashMap();
        userMap.put("filling", name);
        userMap.put("type", model);
        userMap.put("tire", price);
        userMap.put("notes", condition);
        userMap.put("flavour", seats);
        userMap.put("size", Utility.size);
        userMap.put("shape", Utility.shape);


        userMap.put("image", downloadUri.toString());


        mDatabase2.child(uid).child(key2).setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task1) {
                if (task1.isSuccessful()) {

                    progressDialog.dismiss();
                    Toast.makeText(Create.this, "Your Customized Cake Will Be Deleivered" +
                            "to Your Home Address", Toast.LENGTH_SHORT).show();

                } else {

                    //Toast.makeText(getActivity(), "YOUR NAME IS NOT REGISTERED... MAKE NEW ACCOUNT-- ", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}
