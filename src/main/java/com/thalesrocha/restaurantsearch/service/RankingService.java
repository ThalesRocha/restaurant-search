package com.thalesrocha.restaurantsearch.service;

import java.util.List;

/**
 * Created by ThalesRocha
 */
public interface RankingService<T> {

    List<T> rank(int topNth, List<T> list);

}
