package com.jeanboy.app.rxandroidsimple;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;

import static android.R.attr.value;

/**
 * Created by jeanboy on 2017/6/22.
 */
@RunWith(AndroidJUnit4.class)
public class RxTest7Zip {

    @Test
    public void TestBase() {
        //zip操作符是把两个observable提交的结果，严格按照顺序进行合并
        Observable<Integer> observable1=Observable.just(10,20,30);
        Observable<Integer> observable2=Observable.just(4,8,12,16);
        Observable.zip(observable1, observable2, new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(@NonNull Integer integer, @NonNull Integer integer2) throws Exception {
                return integer+integer2;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println("Next:" + value);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.err.println("Error: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("Sequence complete.");
            }
        });

    }

    @Test
    public void TestSimple() {

    }

    @Test
    public void TestFinal() {

    }
}
