package com.innowise.arraytask.event;

public record ArrayChangeEvent(int index, Number oldValue, Number newValue) {
}