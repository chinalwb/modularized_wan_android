package com.chinalwb.wanandroid.base;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import androidx.annotation.Nullable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadService extends IntentService {

    public static final String EXTRA_URL = "EXTRA_URL";
    public static final String EXTRA_FILENAME = "EXTRA_FILENAME";

    public DownloadService() {
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String url = intent.getStringExtra(EXTRA_URL);
        String fileName = intent.getStringExtra(EXTRA_FILENAME);
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.e("xx", "error " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                String filePath = getCacheDir() + File.separator + fileName;
                Log.e("xx", "file path == " + filePath);
                File file = new File(filePath);
                if (file.exists()) {
                    file.delete();
                }
                boolean newFile = file.createNewFile();
                if (!newFile) {
                    Log.e("xx", "Cannot new file!!");
                    return;
                }
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(result);
                fileWriter.close();
                Log.e("xx", "File saved at == " + filePath + ", url == " + url);
            }
        });
    }
}
