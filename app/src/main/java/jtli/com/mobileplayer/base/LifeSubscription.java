package jtli.com.mobileplayer.base;

import rx.Subscription;

public interface LifeSubscription {
    void bindSubscription(Subscription subscription);
}

