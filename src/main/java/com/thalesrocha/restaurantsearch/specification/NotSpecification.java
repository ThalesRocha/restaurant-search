package com.thalesrocha.restaurantsearch.specification;

/**
 * Created by ThalesRocha
 */
public class NotSpecification<T> extends AbstractSpecification<T> {

    private final AbstractSpecification<T> component;

    NotSpecification(AbstractSpecification<T> selector) {
        this.component = selector;
    }

    @Override
    public boolean test(T t) {
        return !(component.test(t));
    }
}