package com.example.retrofithttp;



import com.example.retrofithttp.bean.BaseBean;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：junj
 * 时间：2016/8/12
 * 描述：TOTO
 */
public class RxHelper {
    /**
     * 对结果进行预处理
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<BaseBean<T>, T> handleResult() {

        return new ObservableTransformer<BaseBean<T>, T>() {
            @Override
            public Observable<T> apply(@NonNull Observable<BaseBean<T>> upstream) {
                return upstream.flatMap(new Function<BaseBean<T>, Observable<T>>() {
                    @Override
                    public Observable<T> apply(@NonNull BaseBean<T> tBaseBean) throws Exception {
                        if (tBaseBean.success()) {
                            return createData(tBaseBean.data);
                        } else {
                            return Observable.error(new ServerException(tBaseBean.errorCode, tBaseBean.message));
                        }
                    }

                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }

        };

    }

    /**
     * 创建成功的数据
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> e) throws Exception {
                if (data == null) {
                    e.onError(new NullPointerException("data为空"));
                } else {
                    e.onNext(data);
                    e.onComplete();
                }
            }
        });

    }
}
