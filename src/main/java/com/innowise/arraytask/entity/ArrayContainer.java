package com.innowise.arraytask.entity;

public record ArrayContainer(int id, String name, AbstractArray array) {

    @Override
    public String toString() {
        return String.format("ArrayContainer{id=%d, name='%s', array=%s}", id, name, array);
    }
}