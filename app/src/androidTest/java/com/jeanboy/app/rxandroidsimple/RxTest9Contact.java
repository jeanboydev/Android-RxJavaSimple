package com.jeanboy.app.rxandroidsimple;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jeanboy on 2017/6/22.
 */
@RunWith(AndroidJUnit4.class)
public class RxTest9Contact {


    @Test
    public void TestBase() {
        Observable<Object> api1 = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> e) throws Exception {
                // TODO: 2017/6/23 请求数据
                if (true) {
                    e.onNext("");
                }
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());

        Observable<Object> api2 = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> e) throws Exception {
                // TODO: 2017/6/23 请求数据
                if (true) {
                    e.onNext("");
                }
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());

        Observable<Object> api3 = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> e) throws Exception {
                // TODO: 2017/6/23 请求数据
                if (true) {
                    e.onNext("");
                }
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());

        //访问3个接口，多个数据源
        Observable.concat(api1, api2, api3).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Test
    public void TestSimple() {
        //获取缓存逻辑
        Observable<String> memorySource = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                // TODO: 2017/6/23 请求数据
                if (true) {
                    e.onNext("");
                }
                e.onComplete();
            }
        });
        Observable<String> diskSource  = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                // TODO: 2017/6/23 请求数据
                if (true) {
                    e.onNext("");
                }
                e.onComplete();
            }
        });
        Observable<String> networkSource  = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                // TODO: 2017/6/23 请求数据
                if (true) {
                    e.onNext("");
                }
                e.onComplete();
            }
        });


        Observable.concat(memorySource,diskSource,networkSource).first("")
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {

            }
        });
    }

    @Test
    public void TestFinal() {

    }
}
