package com.drew_benham.listapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.drew_benham.listapp.R;
import com.drew_benham.listapp.models.Media;
import com.drew_benham.listapp.models.MusicMedia;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddEditActivity extends AppCompatActivity {
    public static final String RESULT_MEDIA = "resultMedia";

    private AddEditViewHolder viewholder;
    private MusicMedia media;
    private boolean isMusic;
    private boolean isEdit;

    public static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_layout);
        if (getIntent().getBooleanExtra(TabActivity.ADD, false)) {
            media = new MusicMedia();
            isEdit = false;
        } else {
            media = (MusicMedia) getIntent().getSerializableExtra(DetailedMedia.MEDIA_DETAIL);
            isEdit = true;
        }

        //TODO: change this when you add types.
        isMusic = true;
        setupLayout();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveItemMenuBtn:
                saveItem();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveItem() {
        media.setTitle(viewholder.titleEdit.getText().toString());
        media.setSubTitle(viewholder.subTitleEdit.getText().toString());

        Intent resultIntent = new Intent();
        resultIntent.putExtra(RESULT_MEDIA, media);
        setResult(RESULT_OK, resultIntent);
        finish();
    }



    private void setupLayout() {
        viewholder = new AddEditViewHolder();

        if (isMusic) {
            viewholder.titleText.setText(R.string.artist_title);
            viewholder.subTitleText.setText(R.string.album_sub);
        }
        else {
            viewholder.titleText.setText(R.string.movie_title);
            viewholder.subTitleText.setText(R.string.movie_description);
        }
        if (media.getImageSrc() != 0) {
            viewholder.thumbnailPreview.setImageResource(media.getImageSrc());
        } else {
            viewholder.thumbnailPreview.setImageResource(R.drawable.ic_launcher_background);
        }

        viewholder.pickImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }

    private void selectImage() {
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

        startActivityForResult(chooserIntent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                return;
            }

            try {
                InputStream picDataStream = this.getContentResolver().openInputStream(data.getData());

                int targetW = viewholder.thumbnailPreview.getWidth();
                int targetH = viewholder.thumbnailPreview.getHeight();

                BitmapFactory.Options bitmapFactory = new BitmapFactory.Options();
                bitmapFactory.inJustDecodeBounds = true;

                int photoW = bitmapFactory.outWidth;
                int photoH = bitmapFactory.outHeight;

                int scale = Math.min(photoW / targetW, photoH / targetH);
                bitmapFactory.inJustDecodeBounds = false;
                bitmapFactory.inSampleSize = scale;

                Bitmap imageScaled = BitmapFactory.decodeStream(picDataStream, null, bitmapFactory);
                viewholder.thumbnailPreview.setImageBitmap(imageScaled);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private class AddEditViewHolder {
        private TextView titleText;
        private TextView titleEdit;
        private TextView subTitleText;
        private TextView subTitleEdit;
        private ImageView thumbnailPreview;
        private ImageButton pickImageBtn;

        AddEditViewHolder() {
            titleText = findViewById(R.id.titleTextLbl);
            titleEdit = findViewById(R.id.titleEditText);
            subTitleText = findViewById(R.id.subTitleTextLbl);
            subTitleEdit = findViewById(R.id.subTitleEditText);
            thumbnailPreview = findViewById(R.id.thumbnailPreview);
            pickImageBtn = findViewById(R.id.picImageBtn);
        }
    }
}
