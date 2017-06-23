package com.jeanboy.app.rxandroidsimple;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jeanboy on 2017/6/22.
 */
@RunWith(AndroidJUnit4.class)
public class RxTest2Map {

    @Test
    public void TestBase() {
        Flowable.just("map").map(new Function<String, String>() {
            @Override
            public String apply(@NonNull String s) throws Exception {
                return s + "-转换后";
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                System.out.println(s);
            }
        });
    }

    @Test
    public void TestSimple() {
        Flowable.just("mapSimple").map(new Function<String, Integer>() {
            @Override
            public Integer apply(@NonNull String s) throws Exception {
                return s.hashCode();
            }
        }).map(new Function<Integer, String>() {

            @Override
            public String apply(@NonNull Integer integer) throws Exception {
                return integer.toString();
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                System.out.println(s);
            }
        });
    }

    @Test
    public void TestFinal() {
        Flowable.just("1", "2", "3", "4").subscribeOn(Schedulers.io())//
                .observeOn(AndroidSchedulers.mainThread())//
                .flatMap(new Function<String, Publisher<Integer>>() {

                    @Override
                    public Publisher<Integer> apply(@NonNull String s) throws Exception {
                        return Flowable.just(Integer.parseInt(s));
                    }
                }).subscribe(new Consumer<Integer>() {

            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                System.out.println(integer);
            }
        });

    }
}
