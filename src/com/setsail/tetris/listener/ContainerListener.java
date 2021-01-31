package com.setsail.tetris.listener;

public interface ContainerListener {
    void containerIsFull();
    void removeFullLine(int score);
}
