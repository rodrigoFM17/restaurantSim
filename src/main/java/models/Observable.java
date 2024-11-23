package models;

import java.util.List;

public interface Observable {

    List<Observer> observers = List.of();

    void notifyObservers();
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
}
