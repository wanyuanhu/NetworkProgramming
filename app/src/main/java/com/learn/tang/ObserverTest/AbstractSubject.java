package com.learn.tang.ObserverTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tang on 2017/7/7.
 */

public abstract class AbstractSubject implements Subject {
    private List<Observer> list = new ArrayList<>();

    @Override
    public void add(Observer observer) {
        list.add(observer);
    }

    @Override
    public void del(Observer observer) {
        list.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : list) {
            observer.update();
        }
    }
}
