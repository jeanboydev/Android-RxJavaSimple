package com.jeanboy.app.rxandroidsimple;

import android.os.SystemClock;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jeanboy on 2017/6/22.
 */
@RunWith(AndroidJUnit4.class)
public class RxTest3Error {

    @Test
    public void TestBase() {
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<String> e) throws Exception {
                e.onNext("exception:" + (1 / 0));
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER).subscribe(new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(1);
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("on complete");
            }
        });
    }

    @Test
    public void TestSimple() {
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<String> e) throws Exception {
                e.onNext("将会在3秒后显示");
                SystemClock.sleep(3000);
                e.onNext("hahahahhh");
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER).subscribeOn(Schedulers.io())//指定subscribe执行的线程
                .observeOn(AndroidSchedulers.mainThread())//指定订阅者执行线程
                .subscribe(new Consumer<String>() {//订阅者

                    @Override
                    public void accept(@NonNull String s) throws Exception {
//                        Toast.makeText(RxJava2Activity.this, s, Toast.LENGTH_SHORT).show();
                        System.out.println("on complete");
                    }
                });

    }

    @Test
    public void TestFinal() {

    }
}
