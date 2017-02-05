package com.rexi.cola.es.index.model;

/**
 * Created by moi on 05/02/2017.
 */
public class Index {

	private final String indexAliasName;

	public Index(String indexName) {
		this.indexAliasName = indexName;
	}

	public String getIndexAliasName() {
		return this.indexAliasName;
	}
}
