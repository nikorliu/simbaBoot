package com.simba.mongo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class MongoDB {

	@Autowired
	private MongoTemplate mongoTemplate;

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	/**
	 * 构造分页查询的对象
	 * 
	 * @param query
	 * @param start
	 * @param limit
	 * @return
	 */
	public Query page(Query query, int start, int limit) {
		return query.skip(start).limit(limit);
	}

	/**
	 * 构造查询对象
	 * 
	 * @param params
	 * @return
	 */
	public Query buildQuery(Map<String, Object> params) {
		Criteria c = new Criteria();
		for (Map.Entry<String, Object> entry : params.entrySet()) {
			c.and(entry.getKey()).is(entry.getValue());
		}
		return new Query(c);
	}

}
