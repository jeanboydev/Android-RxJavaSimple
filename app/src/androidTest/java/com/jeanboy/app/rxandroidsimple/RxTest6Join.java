package com.jeanboy.app.rxandroidsimple;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
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
public class RxTest6Join {

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

        observable1.join(observable2, new Function<Long, ObservableSource<Long>>() {
            //observableA产生结果生命周期控制函数
            @Override
            public ObservableSource<Long> apply(@NonNull Long aLong) throws Exception {
                //使Observable延迟600毫秒执行
                return Observable.just(aLong).delay(600, TimeUnit.MILLISECONDS);
            }
        }, new Function<Long, ObservableSource<Long>>() {
            //observableB产生结果生命周期控制函数
            @Override
            public ObservableSource<Long> apply(@NonNull Long aLong) throws Exception {
                //使Observable延迟600毫秒执行
                return Observable.just(aLong).delay(600, TimeUnit.MILLISECONDS);
            }
        }, new BiFunction<Long, Long, Long>() {
            //observableA产生的结果与observableB产生的结果的合并规则
            @Override
            public Long apply(@NonNull Long aLong, @NonNull Long aLong2) throws Exception {
                return aLong + aLong2;
            }
        }).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Long aLong) {
                System.out.println("Next: " + aLong);
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

        observable1.groupJoin(observable2, new Function<Long, ObservableSource<Long>>() {
            //observableA产生结果生命周期控制函数
            @Override
            public ObservableSource<Long> apply(@NonNull Long aLong) throws Exception {
                //使Observable延迟600毫秒执行
                return Observable.just(aLong).delay(600, TimeUnit.MILLISECONDS);
            }
        }, new Function<Long, ObservableSource<Long>>() {
            //observableB产生结果生命周期控制函数
            @Override
            public ObservableSource<Long> apply(@NonNull Long aLong) throws Exception {
                //使Observable延迟600毫秒执行
                return Observable.just(aLong).delay(600, TimeUnit.MILLISECONDS);
            }
        }, new BiFunction<Long, Observable<Long>, Observable<Long>>() {
            @Override
            public Observable<Long> apply(@NonNull Long aLong, @NonNull Observable<Long> longObservable) throws Exception {
                return longObservable.map(new Function<Long, Long>() {
                    @Override
                    public Long apply(@NonNull Long aLong) throws Exception {
                        return null;
                    }
                });
            }
        }).subscribe(new Observer<Observable<Long>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Observable<Long> longObservable) {

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
        }).take(5);

        Observable.merge(observable1, observable2).subscribe(new Observer<Long>() {

            @Override
            public void onError(Throwable e) {
                System.err.println("Error: " + e.getMessage());
            }

            @Override
            public void onComplete() {

                System.out.println("Sequence complete.");
            }

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("Next:" + aLong);
            }
        });

        //StartWith

        Observable.just(10, 20, 30).startWith(Observable.fromArray(2, 3, 4)).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onError(Throwable e) {
                System.err.println("Error: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("Sequence complete.");
            }

            @Override
            public void onNext(Integer value) {
                System.out.println("Next:" + value);
            }
        });
    }
}
