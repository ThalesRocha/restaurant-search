package com.thalesrocha.restaurantsearch.specification;

import java.util.List;

/**
 * Created by ThalesRocha
 */
public class OrSpecification<T> extends AbstractSpecification<T> {

    private final List<AbstractSpecification<T>> leafComponents;

    @SafeVarargs
    OrSpecification(AbstractSpecification<T>... selectors) {
        this.leafComponents = List.of(selectors);
    }

    @Override
    public boolean test(T t) {
        return leafComponents.stream().anyMatch(comp -> comp.test(t));
    }
}