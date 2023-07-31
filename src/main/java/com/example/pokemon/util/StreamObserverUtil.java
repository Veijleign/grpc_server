package com.example.pokemon.util;

import com.google.common.base.Supplier;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StreamObserverUtil {
    public <T> void actionValue(StreamObserver<T> observer, T value) {
        observer.onNext(value);
        observer.onCompleted();
    }

    public void actionEmpty(StreamObserver<Empty> observer, Runnable runnable) {
        runnable.run();

        observer.onNext(Empty.getDefaultInstance());
        observer.onCompleted();
    }
}