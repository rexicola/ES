package com.rexi.cola.es.index.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by moi on 05/02/2017.
 */
abstract public class IndexConfig {

	private final int numberOfShards;
	private final int numberOfReplicas;
	private final String indexAlias;

	protected IndexConfig(String indexAlias, int numberOfShards, int numberOfReplicas) {
		this.indexAlias       = indexAlias;
		this.numberOfShards   = numberOfShards;
		this.numberOfReplicas = numberOfReplicas;
	}

	public String getIndexAlias() {
		return this.indexAlias;
	}

	public Map<String, Object> getIndexSettings() {

		Map <String, Object> indexSettings = new HashMap<String, Object>();

		indexSettings.put("number_of_shards", this.numberOfShards);
		indexSettings.put("number_of_replicas", this.numberOfReplicas);

		return indexSettings;
	}

	abstract public Set<String> getIndexTypes();

	abstract public Map <String, Object> getIndexMapping(String type);

}
