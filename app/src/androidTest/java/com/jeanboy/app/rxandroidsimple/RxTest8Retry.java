package com.jeanboy.app.rxandroidsimple;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

/**
 * Created by jeanboy on 2017/6/22.
 */
@RunWith(AndroidJUnit4.class)
public class RxTest8Retry {

    @Test
    public void TestBase() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                if (e.isDisposed()) return;
                //循环输出数字
                try {
                    for (int i = 0; i < 10; i++) {
                        if (i == 4) {
                            throw new Exception("this is number 4 error！");
                        }
                        e.onNext(i);
                    }
                    e.onComplete();
                } catch (Throwable t) {
                    e.onError(t);
                }
            }
        });

        //重试2次
        observable.retry(2).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                System.out.println("Next:" + integer);
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

        //根据when重试，when没有异常才重试
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                System.out.println("subscribing");
                e.onError(new RuntimeException("always fails"));
            }
        });

        observable.retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(@NonNull Observable<Throwable> throwableObservable) throws Exception {
                return throwableObservable.zipWith(Observable.range(1, 3), new BiFunction<Throwable, Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Throwable throwable, @NonNull Integer integer) throws Exception {
                        return integer;
                    }
                }).flatMap(new Function<Integer, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(@NonNull Integer integer) throws Exception {
                        System.out.println("delay retry by " + integer + " second(s)");
                        //每一秒中执行一次
                        return Observable.timer(integer, TimeUnit.SECONDS);
                    }
                });
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {

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
    public void TestFinal() {

    }
}
