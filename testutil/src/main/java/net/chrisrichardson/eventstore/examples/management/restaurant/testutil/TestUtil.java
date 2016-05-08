package net.chrisrichardson.eventstore.examples.management.restaurant.testutil;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import rx.Observable;
import rx.functions.Func1;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class TestUtil {

    public static <T> T awaitPredicateTrue(Func1<Long, T> func, Func1<T, Boolean> predicate) {
        try {
            return Observable.interval(400, TimeUnit.MILLISECONDS)
                    .take(50)
                    .map(func)
                    .filter(predicate)
                    .toBlocking().first();

        } catch (Exception e) {
            // Rx Java throws an exception with a stack trace from a different thread
            //  https://github.com/ReactiveX/RxJava/issues/3558
            throw new RuntimeException(e);
        }
    }

    public static Object awaitSuccessfulRequest(Supplier<ResponseEntity> func) {
        try {
            return Observable.interval(400, TimeUnit.MILLISECONDS)
                    .take(50)
                    .map(x -> func.get())
                    .filter(resp -> resp.getStatusCode() == HttpStatus.OK && resp.getBody() != null)
                    .toBlocking().first().getBody();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Object awaitNotFound(Supplier<ResponseEntity> func) {
        try {
            return Observable.interval(400, TimeUnit.MILLISECONDS)
                    .take(50)
                    .map(x -> func.get())
                    .filter(resp -> resp.getStatusCode() == HttpStatus.NOT_FOUND)
                    .toBlocking().first();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
