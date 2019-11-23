package com.rishabh.bloodbank.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.androidnetworking.interfaces.UploadProgressListener;
import com.bumptech.glide.Glide;
import com.rishabh.bloodbank.R;
import java.io.File;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import org.json.JSONObject;

public class MakeRequestActivity extends AppCompatActivity {

  EditText messageText;
  TextView chooseImageText;
  ImageView postImage;
  Button submit_button;
  Uri imageUri;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_make_request);
    OkHttpClient.Builder client = new Builder();
    client.connectTimeout(30, TimeUnit.SECONDS);
    client.readTimeout(5, TimeUnit.MINUTES);
    client.writeTimeout(5, TimeUnit.MINUTES);
    AndroidNetworking.initialize(getApplicationContext(),client.build());
    messageText = findViewById(R.id.message);
    chooseImageText = findViewById(R.id.choose_text);
    postImage = findViewById(R.id.post_image);
    submit_button = findViewById(R.id.submit_button);
    submit_button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        if(isValid()){
          //code to upload this post.
          uploadRequest(messageText.getText().toString());
        }
      }
    });

    chooseImageText.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        //code to pick image
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 101);
      }
    });

  }


  @SuppressLint("NewApi")
  private String getPath(Uri uri) throws URISyntaxException {
    final boolean needToCheckUri = Build.VERSION.SDK_INT >= 19;
    String selection = null;
    String[] selectionArgs = null;
    // Uri is different in versions after KITKAT (Android 4.4), we need to
    // deal with different Uris.
    if (needToCheckUri && DocumentsContract.isDocumentUri(getApplicationContext(), uri)) {
      if (isExternalStorageDocument(uri)) {
        final String docId = DocumentsContract.getDocumentId(uri);
        final String[] split = docId.split(":");
        return Environment.getExternalStorageDirectory() + "/" + split[1];
      } else if (isDownloadsDocument(uri)) {
        final String id = DocumentsContract.getDocumentId(uri);
        uri = ContentUris.withAppendedId(
            Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
      } else if (isMediaDocument(uri)) {
        final String docId = DocumentsContract.getDocumentId(uri);
        final String[] split = docId.split(":");
        final String type = split[0];
        if ("image".equals(type)) {
          uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        } else if ("video".equals(type)) {
          uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        } else if ("audio".equals(type)) {
          uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        }
        selection = "_id=?";
        selectionArgs = new String[]{
            split[1]
        };
      }
    }
    if ("content".equalsIgnoreCase(uri.getScheme())) {
      String[] projection = {
          MediaStore.Images.Media.DATA
      };
      Cursor cursor = null;
      try {
        cursor = getContentResolver()
            .query(uri, projection, selection, selectionArgs, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        if (cursor.moveToFirst()) {
          return cursor.getString(column_index);
        }
      } catch (Exception e) {
      }
    } else if ("file".equalsIgnoreCase(uri.getScheme())) {
      return uri.getPath();
    }
    return null;
  }


  public static boolean isExternalStorageDocument(Uri uri) {
    return "com.android.externalstorage.documents".equals(uri.getAuthority());
  }


  public static boolean isDownloadsDocument(Uri uri) {
    return "com.android.providers.downloads.documents".equals(uri.getAuthority());
  }


  public static boolean isMediaDocument(Uri uri) {
    return "com.android.providers.media.documents".equals(uri.getAuthority());
  }



  private void uploadRequest(String message){
    //code to upload the message
  }


  private void uploadImage(){
    //code to upload image
    String path = "";
    try {
      path = getPath(imageUri);
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    AndroidNetworking.upload("http://busy-programmer.000webhostapp.com/test_upload.php")
        .addMultipartFile("file",new File(path))
        .setPriority(Priority.HIGH)
        .addQueryParameter("key", "value")
        .build()
        .setUploadProgressListener(new UploadProgressListener() {
          @Override
          public void onProgress(long bytesUploaded, long totalBytes) {
            // do anything with progress
            Log.d("PROGRESS", "progress "+(bytesUploaded/totalBytes)*100);
            chooseImageText.setText(String.format("%d %%", (bytesUploaded / totalBytes) * 100));
          }
        })
        .getAsString(new StringRequestListener() {
          @Override
          public void onResponse(String response) {
            Toast.makeText(MakeRequestActivity.this, ""+response, Toast.LENGTH_SHORT).show();
          }

          @Override
          public void onError(ANError anError) {
            Toast.makeText(MakeRequestActivity.this, anError.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            Log.d("ERROR", anError.getErrorDetail()+anError.getErrorBody()+anError.getMessage());
          }
        });
  }


  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if(requestCode == 101 && resultCode == RESULT_OK){
      if(data!=null){
        imageUri = data.getData();
        Glide.with(getApplicationContext()).load(imageUri).into(postImage);
        uploadImage();
      }
    }
  }

  private boolean isValid(){
    if(messageText.getText().toString().isEmpty()){
      showMessage("Message shouldn't be empty");
      return false;
    }
    return true;
  }


  private void showMessage(String msg){
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
  }


}
