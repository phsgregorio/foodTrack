package com.foodtrack.dao;

import java.util.Collection;

public interface GenericDao <T> {

	Collection<T> getAll();

	T getById(int id);

	void removeById(int id);

	void update(T typeObject);

	void insert(T typeObject);
}