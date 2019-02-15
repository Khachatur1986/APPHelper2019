package com.example.apphelper2019.ex.observer_observable_ex;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Model extends Observable {
    private List<Integer> list;

    public Model() {
        list = new ArrayList<>(3);
        list.add(2);
        list.add(4);
        list.add(10);
    }


    public void setValueAtIndex(final int index) {
        list.set(index, getValueAtIndex(index) + 1);
        setChanged();
        notifyObservers();
    }

    public int getValueAtIndex(final int index) {
        return list.get(index);
    }

    public void setInitialData() {
        setChanged();
        notifyObservers();
    }
}
