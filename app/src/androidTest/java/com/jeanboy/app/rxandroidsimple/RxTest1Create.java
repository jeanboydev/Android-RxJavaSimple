package com.jeanboy.app.rxandroidsimple;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by jeanboy on 2017/6/22.
 */
@RunWith(AndroidJUnit4.class)
public class RxTest1Create {

    @Test
    public void TestCreate() {
        Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<String> e) throws Exception {
                e.onNext("hello RxJava 2");
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER);

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("onSubscribe");
                s.request(Long.MAX_VALUE);//请求资源才会调用onNext(),onComplete()
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };

        flowable.subscribe(subscriber);
    }

    @Test
    public void TestCreateSimple() {
        Flowable<String> flowable = Flowable.just("hello RxJava 2");

        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                System.out.println(s);
            }
        };

        flowable.subscribe(consumer);
    }

    @Test
    public void TestCreateFinal() {
        Flowable.just("hello RxJava 2").subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                System.out.println(s);
            }
        });
    }
}
