package com.chinalwb.wanandroid_base.base;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadTask {

    private DownloadListener downloadListener;

    public interface DownloadListener {
        void onSuccess();

        void onFailure();
    }

    private WeakReference<Context> contextWeakReference;

    public DownloadTask(Context context, DownloadListener downloadListener) {
        this.contextWeakReference = new WeakReference<Context>(context);
        this.downloadListener = downloadListener;
    }

    public void execute(String... downloadArgs) {
        if (downloadArgs == null || downloadArgs.length != 2) {
            Log.e("xx", "Invalid download args.");
            return;
        }

        final String url = downloadArgs[0];
        final String fileName = downloadArgs[1];
        OkHttpClient client = new OkHttpClient.Builder().build();

        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);

        Log.e("xx", "Enqueue thread == " + Thread.currentThread());
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.e("xx", "error " + e.getMessage());
                if (downloadListener != null) {
                    downloadListener.onFailure();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.e("xx", "Response download thread == " + Thread.currentThread());
                Context context = contextWeakReference.get();
                if (context == null) {
                    if (downloadListener != null) {
                        downloadListener.onFailure();
                    }
                    return;
                }

                String filePath = context.getCacheDir() + File.separator + fileName;
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
                if (downloadListener != null) {
                    downloadListener.onSuccess();
                }
            }
        });
    }
}
