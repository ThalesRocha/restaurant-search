package com.thalesrocha.restaurantsearch.specification;

import java.util.List;

/**
 * Created by ThalesRocha
 */
public class AndSpecification<T> extends AbstractSpecification<T> {

    private final List<AbstractSpecification<T>> leafComponents;

    @SafeVarargs
    AndSpecification(AbstractSpecification<T>... selectors) {
        this.leafComponents = List.of(selectors);
    }

    @Override
    public boolean test(T t) {
        return leafComponents.stream().allMatch(comp -> (comp.test(t)));
    }
}
