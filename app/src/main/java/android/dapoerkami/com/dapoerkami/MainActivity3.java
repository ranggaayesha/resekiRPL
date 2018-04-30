package android.dapoerkami.com.dapoerkami;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.share.widget.ShareDialog;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MainActivity3 extends AppCompatActivity {

    private static final int REQUEST_VIDEO_CODE = 1000;
    Button btnShareLink, btnSharePhoto, btnShareVideo;
    CallbackManager callbackManager;
    ShareDialog shareDialog;

    Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            SharePhoto sharePhoto = new SharePhoto.Builder()
                    .setBitmap(bitmap)
                    .build();

            if (ShareDialog.canShow(SharePhotoContent.class)) {
                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(sharePhoto)
                        .build();
                shareDialog.show(content);
            }
        }

        @Override
        public void onBitmapFailed(Exception e, Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_main3);

        // Init View

        // btnShareLink = (Button) findViewById(R.id.btnShareLink);
        btnSharePhoto = (Button) findViewById(R.id.btnSharePhoto);
        //btnShareVideo = (Button) findViewById(R.id.btnShareVideo);


        // Init FB
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        /*

        btnShareLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {
                        Toast.makeText(MainActivity3.this, "Share successful!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(MainActivity3.this, "Share Cancel", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(MainActivity3.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        .setQuote("This is useful link")
                        .setContentUrl(Uri.parse("https://www.youtube.com"))
                        .build();

                if(ShareDialog.canShow(ShareLinkContent.class)) {
                    shareDialog.show(linkContent);
                }
            }
        });

        */

        btnSharePhoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Create callback
                shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {
                        Toast.makeText(MainActivity3.this, "Share successful!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(MainActivity3.this, "Share Cancel", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(MainActivity3.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                // We will fetch photo from link and convert to bitmap
                File f = new File("");

                Picasso.get()
                        .load("https://scontent-sit4-1.xx.fbcdn.net/v/t1.0-9/31518506_1801158069941947_2140912494066008064_n.jpg?_nc_cat=0&oh=3d47d8402caf72c9274da691c65bf294&oe=5B9B8B83")
                        .into(target);
            }
        });

        /*

        btnShareVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Choose Video dialog
                Intent intent = new Intent();
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select video"), REQUEST_VIDEO_CODE);
            }
        });

        */

        printKeyHash();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RESULT_OK) {
            if (requestCode == REQUEST_VIDEO_CODE) {
                Uri selectedVideo = data.getData();

                ShareVideo video = new ShareVideo.Builder()
                        .setLocalUrl(selectedVideo)
                        .build();

                ShareVideoContent videoContent = new ShareVideoContent.Builder()
                        .setContentTitle("This is useful video")
                        .setContentDescription("Funny video from EDMT Dev download from Youtube")
                        .setVideo(video)
                        .build();

                if (shareDialog.canShow(ShareVideoContent.class))
                    shareDialog.show(videoContent);
            }
        }
    }

    public void printKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("android.dapoerkami.com.dapoerkami",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(),Base64.DEFAULT));
            }


        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


}
