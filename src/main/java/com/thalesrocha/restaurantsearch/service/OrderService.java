package com.thalesrocha.restaurantsearch.service;

import java.util.List;

/**
 * Created by ThalesRocha
 */
public interface OrderService<T> {

    List<T> order(List<T> list);

}
