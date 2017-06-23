package com.jeanboy.app.rxandroidsimple;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Created by jeanboy on 2017/6/22.
 */
@RunWith(AndroidJUnit4.class)
public class RxTest4List {

    @Test
    public void TestBase() {
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(1);
        list.add(5);

        Flowable.just(list).subscribe(new Consumer<List<Integer>>() {
            @Override
            public void accept(@NonNull List<Integer> integers) throws Exception {
                for (Integer integer : integers)
                    System.out.println(integer);
            }
        });

        // TODO: 2017/6/22
        Flowable.fromIterable(list).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                System.out.println(integer);
            }
        });
    }

    @Test
    public void TestSimple() {
        //Flowable.flatMap 可以把一个 Flowable 转换成另一个 Flowable

        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(1);
        list.add(5);

        Flowable.just(list).flatMap(new Function<List<Integer>, Publisher<Integer>>() {
            @Override
            public Publisher<Integer> apply(@NonNull List<Integer> integers) throws Exception {
                return Flowable.fromIterable(integers);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                System.out.println(integer);
            }
        });

    }

    @Test
    public void TestFinal() {
        Flowable.fromArray(1, 20, 5, 0, -1, 8).filter(new Predicate<Integer>() {
            @Override
            public boolean test(@NonNull Integer integer) throws Exception {
                return integer.intValue() > 5;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                System.out.println(integer);
            }
        });

        Flowable.fromArray(1, 20, 5, 0, -1, 8).take(2)//取其中2个
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        System.out.println(integer);
                    }
                });

        Flowable.just(1,2,3).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                System.out.println("保存:" + integer);
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                System.out.println(integer);
            }
        });
    }
}
