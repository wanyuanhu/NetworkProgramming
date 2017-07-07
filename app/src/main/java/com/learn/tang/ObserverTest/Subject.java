package com.learn.tang.ObserverTest;

/**
 * Created by Tang on 2017/7/7.
 */

public interface Subject {

    void add(Observer observer);

    void del(Observer observer);

    void notifyObservers();

    void operation();
}
