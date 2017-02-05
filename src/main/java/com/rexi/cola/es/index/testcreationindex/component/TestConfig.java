package com.rexi.cola.es.index.testcreationindex.component;

import com.rexi.cola.es.index.model.IndexConfig;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by moi on 05/02/2017.
 */
@Component
public class TestConfig extends IndexConfig{

	public TestConfig() {
		super("myfirstindex", 1, 0);
	}

	@Override
	public Set<String> getIndexTypes() {
		Set<String> types = new HashSet<String>();
		types.add("mytypes");
		return types;
	}

	@Override
	public Map<String, Object> getIndexMapping(String type) {
		return new HashMap<String, Object>();
	}
}
