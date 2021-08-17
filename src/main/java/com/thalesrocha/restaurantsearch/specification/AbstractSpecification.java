package com.thalesrocha.restaurantsearch.specification;

import java.util.function.Predicate;

/**
 * Created by ThalesRocha
 */
public abstract class AbstractSpecification<T> implements Predicate<T> {

    public AbstractSpecification<T> and(AbstractSpecification<T> other) {
        return new AndSpecification<>(this, other);
    }

    public AbstractSpecification<T> or(AbstractSpecification<T> other) {
        return new OrSpecification<>(this, other);
    }

    public AbstractSpecification<T> not() {
        return new NotSpecification<>(this);
    }

}