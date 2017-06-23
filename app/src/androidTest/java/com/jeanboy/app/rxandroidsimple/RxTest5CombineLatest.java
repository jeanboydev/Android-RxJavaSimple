package com.jeanboy.app.rxandroidsimple;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

/**
 * Created by jeanboy on 2017/6/22.
 */
@RunWith(AndroidJUnit4.class)
public class RxTest5CombineLatest {

    //combineLatest操作符把两个Observable产生的结果进行合并，合并的结果组成一个新的Observable。

    @Test
    public void TestBase() {
        //产生0,5,10,15,20数列
        Observable<Long> observable1 = Observable.timer(1000, TimeUnit.MILLISECONDS).map(new Function<Long, Long>() {
            @Override
            public Long apply(@NonNull Long aLong) throws Exception {
                return aLong * 5;
            }
        }).take(5);
        //产生0,10,20,30,40数列
        Observable<Long> observable2 = Observable.timer(1000, TimeUnit.MILLISECONDS).map(new Function<Long, Long>() {
            @Override
            public Long apply(@NonNull Long aLong) throws Exception {
                return aLong * 10;
            }
        });

        Observable.combineLatest(observable1, observable2, new BiFunction<Long, Long, Object>() {
            @Override
            public Object apply(@NonNull Long aLong, @NonNull Long aLong2) throws Exception {
                Log.e("combine --- >", "aLong = " + aLong + " | aLong2 = " + aLong2);
                return aLong + aLong2;
            }
        }).subscribe(new Observer<Object>() {
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

    }

    @Test
    public void TestFinal() {
    }
}
