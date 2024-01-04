package com.aggregation.rest.api.utils.search.filters;

import com.aggregation.rest.api.mappers.base.BaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;

import java.util.ArrayList;
import java.util.List;

public class MongoOperationUtils<T> {
    private static final Logger logger = LoggerFactory.getLogger(MongoOperationUtils.class);
    public MongoOperations mongoOperations;
    public Class<T> classOfT;

    public List<AggregationOperation> list = new ArrayList<AggregationOperation>();

    public MongoOperationUtils() {
    }

    public MongoOperationUtils(MongoOperations mongoOperations, Class<T> classOfT) {
        this.mongoOperations = mongoOperations;
        this.classOfT = classOfT;
    }

    public void addFilter(AggregationOperation aggregationOperation) {
        list.add(aggregationOperation);
    }

    public List<T> fetchMappedResult() {
        TypedAggregation<T> agg = Aggregation.newAggregation(classOfT, list);
        return mongoOperations.aggregate(agg, classOfT, classOfT).getMappedResults();
    }

    public <DTO, Mapper extends BaseMapper<DTO, T>>
    List<DTO> fetchMappedResult(Mapper mapperInstance) {
        TypedAggregation<T> agg = Aggregation.newAggregation(classOfT, list);
        List<T> l = mongoOperations.aggregate(agg, classOfT, classOfT).getMappedResults();
        return mapperInstance.toDtoList(l);
    }

}
