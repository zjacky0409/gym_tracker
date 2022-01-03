package com.example.gym_tracker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import jp.wasabeef.richeditor.RichEditor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddTipsActivity extends AppCompatActivity {


    private RichEditor mEditor; // a rich text editor from wasabeef
    private TextView mPreview;

    // ref: https://github.com/wasabeef/richeditor-android/blob/master/sample/src/main/java/jp/wasabeef/sample/MainActivity.java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tips);


        mEditor = findViewById(R.id.editor);

        // Please ref to the official document by wasabeef
        mEditor.setEditorHeight(200);
        mEditor.setEditorFontSize(22);
        mEditor.setEditorFontColor(Color.BLACK);
        //mEditor.setEditorBackgroundColor(Color.BLUE);
        //mEditor.setBackgroundColor(Color.BLUE);
        //mEditor.setBackgroundResource(R.drawable.bg);
        mEditor.setPadding(10, 10, 10, 10);
        mEditor.setPlaceholder("Type hints here!");
        //mEditor.setInputEnabled(false);
        Button save_btn = findViewById(R.id.hints_save_btn);
        TextView yt_link = findViewById(R.id.yt_link);
        TextView name = findViewById(R.id.tips_name);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            yt_link.setText(bundle.getString("yt_link"));
            name.setText(bundle.getString("name"));
            mEditor.setHtml(bundle.getString("details"));
        }

        // undo button
        findViewById(R.id.action_undo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.undo();
            }
        });


        // Saving the tip
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().isEmpty()) {
                    addTipsToBackEnd(name.getText().toString(), yt_link.getText().toString(), mPreview.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Please fill in name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.action_bold).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setBold();
            }
        });

        findViewById(R.id.action_italic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setItalic();
            }
        });
        findViewById(R.id.action_underline).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setUnderline();
            }
        });
        findViewById(R.id.action_txt_color).setOnClickListener(new View.OnClickListener() {
            private boolean isChanged;

            @Override
            public void onClick(View v) {
                mEditor.setTextColor(isChanged ? Color.BLACK : Color.RED);
                isChanged = !isChanged;
            }
        });

        findViewById(R.id.action_insert_checkbox).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.insertTodo();
            }
        });

        mPreview = findViewById(R.id.preview);
        mEditor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override
            public void onTextChange(String text) {
                mPreview.setText(text);
            }
        });


    }

    // Save the tip to the server
    private void addTipsToBackEnd(String name, String yt_link, String details) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.179:3000/tips/addTips/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // below line is to create an instance for our retrofit api class.
        MyAPIService retrofitAPI = retrofit.create(MyAPIService.class);

        // passing data from our text fields to our modal class.
        Tips modal = new Tips(name, yt_link, details);

        // calling a method to create a post and passing our modal class.
        Call<CheckSuccess> call = retrofitAPI.createTips(modal);

        // on below line we are executing our method.
        call.enqueue(new Callback<CheckSuccess>() {
            @Override
            public void onResponse(Call<CheckSuccess> call, Response<CheckSuccess> response) {

                if (response.body().getSuccess() == true) {
                    Intent replyIntent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("Finish", "true");
                    replyIntent.putExtras(bundle);

                    setResult(RESULT_OK, replyIntent);
                    finish();
                }

            }

            @Override
            public void onFailure(Call<CheckSuccess> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Fail to save tip to backend", Toast.LENGTH_SHORT).show();
            }
        });
    }
}