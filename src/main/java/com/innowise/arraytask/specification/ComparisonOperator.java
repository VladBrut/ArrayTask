package com.innowise.arraytask.specification;

public enum ComparisonOperator {
    GREATER {
        @Override
        public boolean apply(double a, double b) {
            return a > b;
        }
    },
    LESS {
        @Override
        public boolean apply(double a, double b) {
            return a < b;
        }
    },
    EQUAL {
        @Override
        public boolean apply(double a, double b) {
            return Double.compare(a, b) == 0;
        }
    };

    public abstract boolean apply(double a, double b);
}