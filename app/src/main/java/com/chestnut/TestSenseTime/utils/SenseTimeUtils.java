package com.chestnut.TestSenseTime.utils;

import com.chestnut.Common.utils.LogUtils;
import com.chestnut.TestSenseTime.bean.AddFaceBean;
import com.chestnut.TestSenseTime.bean.CreateFaceSetsBean;
import com.chestnut.TestSenseTime.bean.CreateGroupBean;
import com.chestnut.TestSenseTime.bean.CreatePersonBean;
import com.chestnut.TestSenseTime.bean.DetectBean;
import com.chestnut.TestSenseTime.bean.SearchFromFaceSetBean;
import com.chestnut.TestSenseTime.bean.SearchGroupBean;
import com.google.gson.Gson;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpParams;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;

/**
 * <pre>
 *     author: Chestnut
 *     blog  : http://www.jianshu.com/u/a0206b5f4526
 *     time  : 2017/6/29 16:05
 *     desc  :
 *     thanks To:
 *     dependent on:
 *     update log:
 * </pre>
 */
public class SenseTimeUtils {

    /*常量*/
    public static final String API_ID = "e06e1d8b64924d2098a6fe2783989ef7";
    public static final String API_SECRET = "a0aecab0ca40456390fce15c5135c056";
    public static final String TAG = "SenseTimeUtils";
    public static final boolean OpenLog = true;
    public static final String API_GET_INFO = "https://v1-api.visioncloudapi.com/info/api";
    public static final String API_POST_DETECTION = "https://v1-api.visioncloudapi.com/face/detection";
    public static final String API_POST_CREATE_FACE_SETS = "https://v1-api.visioncloudapi.com/faceset/create";
    public static final String API_POST_ADD_FACE = "https://v1-api.visioncloudapi.com/faceset/add_face";
    public static final String API_POST_TRAINING = "https://v1-api.visioncloudapi.com/face/training";
    public static final String API_POST_SEARCH = "https://v1-api.visioncloudapi.com/face/search";
    public static final String API_POST_CREATE_GROUP = "https://v1-api.visioncloudapi.com/group/create";
    public static final String API_POST_SEARCH_FROM_GROUP = "https://v1-api.visioncloudapi.com/face/identification";
    public static final String API_POST_CREATE_PERSON = "https://v1-api.visioncloudapi.com/person/create";
    public static final String API_POST_ADD_PERSON_2_GROUP = "https://v1-api.visioncloudapi.com/group/add_person";
    public static final String API_POST_ADD_FACE_2_PERSON = "https://v1-api.visioncloudapi.com/person/add_face";

    /*变量*/
    private static long time1 = 0;
    private static long time2 = 0;

    /*方法*/
    public static Observable<DetectBean> detect(String img_local_path) {
        return Observable.create(subscriber -> {
            OkHttpClient okHttpClient = new OkHttpClient();
            MultipartBody.Builder body = new MultipartBody.Builder().setType(MultipartBody.FORM);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),getBytesFromFile(new File(img_local_path)));
            body.addFormDataPart("api_id",API_ID);
            body.addFormDataPart("api_secret",API_SECRET);
            body.addFormDataPart("file","fileName"+".mp3",requestBody);
            final Request request = new Request.Builder()
                    .url(API_POST_DETECTION)
                    .post(body.build())
                    .build();
            time1 = System.currentTimeMillis();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    subscriber.onError(e);
                    subscriber.onCompleted();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    time2 = System.currentTimeMillis();
                    if (response!=null) {
                        try {
                            String s = response.body().string();
                            LogUtils.i(OpenLog,TAG,"detect:"+s);
                            subscriber.onNext(new Gson().fromJson(s,DetectBean.class));
                        } catch (Exception e) {
                            subscriber.onNext(null);
                            e.printStackTrace();
                        }
                    }
                    else
                        subscriber.onNext(null);
                    subscriber.onCompleted();
                }
            });
        });
    }

    public static Observable<CreateFaceSetsBean> createFaceSets(String name) {
        HttpParams params = new HttpParams();
        params.put("api_id",API_ID);
        params.put("api_secret",API_SECRET);
        params.put("name",name);
        time1 = System.currentTimeMillis();
        return new RxVolley.Builder()
                .url(API_POST_CREATE_FACE_SETS)
                .httpMethod(RxVolley.Method.POST)
                .params(params)
                .getResult().map(result -> {
                    time2 = System.currentTimeMillis();
                    if (result==null)
                        return null;
                    else {
                        if (result.isSuccess()) {
                            try {
                                String s = new String(result.data);
                                LogUtils.i(OpenLog,TAG,"createFaceSets:"+s);
                                return new Gson().fromJson(s,CreateFaceSetsBean.class);
                            } catch (Exception e) {
                                e.printStackTrace();
                                return null;
                            }
                        }
                        else {
                            return null;
                        }
                    }
                });
    }

    public static Observable<AddFaceBean> addFace(String faceSetsId, String faceId) {
        HttpParams params = new HttpParams();
        params.put("api_id",API_ID);
        params.put("api_secret",API_SECRET);
        params.put("faceset_id",faceSetsId);
        params.put("face_id",faceId);
        time1 = System.currentTimeMillis();
        return new RxVolley.Builder()
                .url(API_POST_ADD_FACE)
                .httpMethod(RxVolley.Method.POST)
                .params(params)
                .getResult().map(result -> {
                    time2 = System.currentTimeMillis();
                    if (result==null)
                        return null;
                    else {
                        if (result.isSuccess()) {
                            try {
                                String s = new String(result.data);
                                LogUtils.i(OpenLog,TAG,"addFace:"+s);
                                return new Gson().fromJson(s,AddFaceBean.class);
                            } catch (Exception e) {
                                e.printStackTrace();
                                return null;
                            }
                        }
                        else {
                            return null;
                        }
                    }
                });
    }

    public static Observable<Boolean> trainingFaceSet(String faceSetsId) {
        HttpParams params = new HttpParams();
        params.put("api_id",API_ID);
        params.put("api_secret",API_SECRET);
        params.put("faceset_id",faceSetsId);
        time1 = System.currentTimeMillis();
        return new RxVolley.Builder()
                .url(API_POST_TRAINING)
                .httpMethod(RxVolley.Method.POST)
                .params(params)
                .getResult().map(result -> {
                    time2 = System.currentTimeMillis();
                    if (result==null)
                        return false;
                    else {
                        if (result.isSuccess()) {
                            String s = new String(result.data);
                            LogUtils.i(OpenLog,TAG,"training:"+s);
                            return s.contains("ok") || s.contains("OK");
                        }
                        else {
                            return false;
                        }
                    }
                });
    }

    public static Observable<SearchFromFaceSetBean> searchFromFaceSets(String faceId, String faceSetsId) {
        HttpParams params = new HttpParams();
        params.put("api_id",API_ID);
        params.put("api_secret",API_SECRET);
        params.put("face_id",faceId);
        params.put("faceset_id",faceSetsId);
        params.put("top",1);
        time1 = System.currentTimeMillis();
        return new RxVolley.Builder()
                .url(API_POST_SEARCH)
                .httpMethod(RxVolley.Method.POST)
                .params(params)
                .getResult().map(result -> {
                    time2 = System.currentTimeMillis();
                    if (result==null)
                        return null;
                    else {
                        if (result.isSuccess()) {
                            try {
                                String s = new String(result.data);
                                LogUtils.i(OpenLog,TAG,"search:"+s);
                                return new Gson().fromJson(s,SearchFromFaceSetBean.class);
                            } catch (Exception e) {
                                e.printStackTrace();
                                return null;
                            }
                        }
                        else {
                            return null;
                        }
                    }
                });
    }

    /*Group*/
    public static Observable<CreateGroupBean> createGroup(String name) {
        HttpParams params = new HttpParams();
        params.put("api_id",API_ID);
        params.put("api_secret",API_SECRET);
        params.put("name",name);
        time1 = System.currentTimeMillis();
        return new RxVolley.Builder()
                .url(API_POST_CREATE_GROUP)
                .httpMethod(RxVolley.Method.POST)
                .params(params)
                .getResult().map(result -> {
                    time2 = System.currentTimeMillis();
                    if (result==null)
                        return null;
                    else {
                        if (result.isSuccess()) {
                            return JsonUtils.getInstance().getBean(new String(result.data), CreateGroupBean.class);
                        }
                        else {
                            return null;
                        }
                    }
                });
    }

    public static Observable<SearchGroupBean> searchGroup(String faceId, String groupId) {
        HttpParams params = new HttpParams();
        params.put("api_id",API_ID);
        params.put("api_secret",API_SECRET);
        params.put("face_id",faceId);
        params.put("group_id",groupId);
        params.put("top",1);
        time1 = System.currentTimeMillis();
        return new RxVolley.Builder()
                .url(API_POST_SEARCH_FROM_GROUP)
                .httpMethod(RxVolley.Method.POST)
                .params(params)
                .getResult().map(result -> {
                    time2 = System.currentTimeMillis();
                    if (result==null)
                        return null;
                    else {
                        if (result.isSuccess()) {
                            String s = new String(result.data);
                            LogUtils.i(OpenLog,TAG,"searchGroup:"+s);
                            return JsonUtils.getInstance().getBean(s, SearchGroupBean.class);
                        }
                        else {
                            return null;
                        }
                    }
                });
    }

    public static Observable<CreatePersonBean> createPerson(String name) {
        HttpParams params = new HttpParams();
        params.put("api_id",API_ID);
        params.put("api_secret",API_SECRET);
        params.put("name",name);
        time1 = System.currentTimeMillis();
        return new RxVolley.Builder()
                .url(API_POST_CREATE_PERSON)
                .httpMethod(RxVolley.Method.POST)
                .params(params)
                .getResult().map(result -> {
                    time2 = System.currentTimeMillis();
                    if (result==null)
                        return null;
                    else {
                        if (result.isSuccess()) {
                            String s = new String(result.data);
                            LogUtils.i(OpenLog,TAG,"createPerson:"+s);
                            return JsonUtils.getInstance().getBean(s,CreatePersonBean.class);
                        }
                        else {
                            return null;
                        }
                    }
                });
    }

    public static Observable<Boolean> addPerson2Group(String groupId, String personId) {
        HttpParams params = new HttpParams();
        params.put("api_id",API_ID);
        params.put("api_secret",API_SECRET);
        params.put("group_id",groupId);
        params.put("person_id",personId);
        time1 = System.currentTimeMillis();
        return new RxVolley.Builder()
                .url(API_POST_ADD_PERSON_2_GROUP)
                .httpMethod(RxVolley.Method.POST)
                .params(params)
                .getResult().map(result -> {
                    time2 = System.currentTimeMillis();
                    if (result==null)
                        return false;
                    else {
                        if (result.isSuccess()) {
                            String s = new String(result.data);
                            LogUtils.i(OpenLog,TAG,"addPerson2Group:"+s);
                            return s.contains("OK") || s.contains("ok");
                        }
                        else {
                            return false;
                        }
                    }
                });
    }

    public static Observable<Boolean> addFace2Person(String faceId, String personId) {
        HttpParams params = new HttpParams();
        params.put("api_id",API_ID);
        params.put("api_secret",API_SECRET);
        params.put("face_id",faceId);
        params.put("person_id",personId);
        time1 = System.currentTimeMillis();
        return new RxVolley.Builder()
                .url(API_POST_ADD_FACE_2_PERSON)
                .httpMethod(RxVolley.Method.POST)
                .params(params)
                .getResult().map(result -> {
                    time2 = System.currentTimeMillis();
                    if (result==null)
                        return false;
                    else {
                        if (result.isSuccess()) {
                            String s = new String(result.data);
                            LogUtils.i(OpenLog,TAG,"addFace2Person:"+s);
                            return s.contains("OK") || s.contains("ok");
                        }
                        else {
                            return false;
                        }
                    }
                });
    }

    private static byte[] getBytesFromFile(File f) {
        if (f == null) {
            return null;
        }
        try {
            FileInputStream stream = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = stream.read(b)) != -1)
                out.write(b, 0, n);
            stream.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
        }
        return null;
    }

    public static long getUseTime() {
        long time = time2 - time1;
        time2 = 0;
        time1 = 0;
        return time;
    }
}
