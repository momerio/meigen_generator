package com.kis.meigen_generator.api;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DataMeigen {

    private Apikey apikey = new Apikey();

    private String urlHead = "https://momdev.club/meigen-generator-api/index.php";
    private String urlId   = "testhoge";
    private String urlKey  = apikey.getApikey();
    private String urlGenerateNum = "3";
    private String url = urlHead + "?id=" + urlId + "&key=" + urlKey + "&generate_num=" + urlGenerateNum;


    // ファイル読み込みでAPIkeyを取得
    private String getApiKey() {

        String apiKey = "";
        try {
            File file = new File("apikey.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            apiKey = br.readLine();

            br.close();
        } catch(FileNotFoundException e) {
            System.out.println(e);
        } catch(IOException e) {
            System.out.println(e);
        }

        return apiKey;

    }

    public void getDataMeigen(TextView meigenView) {

        //httpリクエスト
        try {
            httpRequest(url, meigenView);
        } catch (Exception e) {
            Log.e("Hoge", e.getMessage());
        }

    }

    private void httpRequest(String url, TextView meigenView) throws IOException {

        //OkHttpClinet生成
        OkHttpClient client = new OkHttpClient();

        //request生成
        Request request = new Request.Builder()
                .url(url)
                .build();

        //非同期リクエスト
        client.newCall(request)
                .enqueue(new Callback() {

                    //エラーのとき
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Log.e("Hoge",e.getMessage());
                    }

                    //正常のとき
                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                        //response取り出し
                        final String jsonStr = response.body().string();
                        Log.d("Hoge","jsonStr=" + jsonStr);

                        //JSON処理
                        try{
                            //jsonパース
                            JSONObject json      = new JSONObject(jsonStr);
                            JSONArray  jsonArray = json.getJSONArray("data");

                            final String[] data  = new String[Integer.parseInt(urlGenerateNum)];
                            for (int i = 0; i < Integer.parseInt(urlGenerateNum); i++) {
                                data[i] = jsonArray.get(i).toString();
                            }

                            //親スレッドUI更新
                            Handler mainHandler = new Handler(Looper.getMainLooper());
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    meigenView.setText(data[0]);
                                }
                            });


                        }catch(Exception e){
                            Log.e("Hoge",e.getMessage());
                        }

                    }
                });
    }

}
