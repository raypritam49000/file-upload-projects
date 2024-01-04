package com.aggregation.rest.api.utils.search.builder;

import com.aggregation.rest.api.mappers.base.BaseMapper;
import com.aggregation.rest.api.utils.search.pageable.PageableClass;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchBuilder<T> {

    public MongoTemplate mongoTemplate;
    final List<Criteria> criteria = new ArrayList<>();
    private final int pageNumber;
    private final int pageSize;
    Class<T> classOfT;
    private Pageable pageable;
    private Logger logger;

    public SearchBuilder(int pageNumber, int pageSize, Class<T> classOfT, MongoTemplate mongoTemplate) {
        this.classOfT = classOfT;
        this.mongoTemplate = mongoTemplate;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }


    public void setSortCriterion(String sortOrder, String sort) {
        pageable = PageRequest.of(pageNumber, pageSize, sortOrder.equals("ASC") ? Sort.by(Sort.Direction.ASC) : Sort.by(Sort.Direction.DESC, sort));
    }


    public void addSearchFilter(Criteria criteria1) {
        criteria.add(criteria1);
    }


    public Page<T> get() {
        final Query query = new Query();
        if (!criteria.isEmpty())
            query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])));

        long total = mongoTemplate.count(query, classOfT);

        query.with(pageable);

        List<T> list = mongoTemplate.find(query, classOfT);

        return PageableExecutionUtils.getPage(list, pageable, () -> total);
    }



    public <PageDTO extends PageableClass<T>>
    PageDTO getAsPageDTO(PageDTO pageDTO,
                         String verifiedSortColumn,
                         String verifiedSortOrder,
                         String pluralResourceName) {

        Page<T> entityPage = get();

        return PageableUtilities.transferToPageDTO(
                entityPage,
                pageDTO,
                verifiedSortColumn,
                verifiedSortOrder,
                pluralResourceName);
    }


    public <DTO, PageDTO extends PageableClass<DTO>, Mapper extends BaseMapper<DTO, T>>
    PageDTO getAsPageDTO(PageDTO pageDTO,
                         Mapper mapperInstance,
                         String verifiedSortColumn,
                         String verifiedSortOrder,
                         String pluralResourceName) {
        Page<T> entityPage =get();

        return PageableUtilities.transferToPageDTO(
                entityPage,
                pageDTO,
                mapperInstance,
                verifiedSortColumn,
                verifiedSortOrder,
                pluralResourceName);
    }



}
