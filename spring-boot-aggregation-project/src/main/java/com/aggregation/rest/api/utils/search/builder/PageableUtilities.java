package com.aggregation.rest.api.utils.search.builder;


import com.aggregation.rest.api.mappers.base.BaseMapper;
import com.aggregation.rest.api.utils.search.pageable.PageableClass;
import org.springframework.data.domain.Page;

public class PageableUtilities {

    public static <T, PageDTO extends PageableClass<T>>
    PageDTO transferToPageDTO(
            Page<T> entityPage,
            PageDTO pageDTO,
            String verifiedSortColumn,
            String verifiedSortOrder,
            String pluralResourceName) {

        pageDTO.setHasContent(entityPage.hasContent());
        pageDTO.setHasNext(entityPage.hasNext());
        pageDTO.setHasPrevious(entityPage.hasPrevious());
        pageDTO.setFirst(entityPage.isFirst());
        pageDTO.setLast(entityPage.isLast());

        pageDTO.setTotalElements(entityPage.getTotalElements());

        pageDTO.setTotalPages(entityPage.getTotalPages());

        pageDTO.setData(entityPage.getContent());

        pageDTO.setPerPage(entityPage.getSize());
        pageDTO.setPageNumber(entityPage.getNumber());
        pageDTO.setSize(entityPage.getSize());

        pageDTO.setPluralResourceName(pluralResourceName);

        if (verifiedSortOrder != null && !verifiedSortOrder.equals("")
                && verifiedSortColumn != null && !verifiedSortColumn.equals("")) {
            pageDTO.setSortOrder(verifiedSortOrder);
            pageDTO.setSortColumn(verifiedSortColumn);
            pageDTO.setSorted(true);
        }

        return pageDTO;
    }

    public static <Entity, DTO, PageDTO extends PageableClass<DTO>, Mapper extends BaseMapper<DTO, Entity>>
    PageDTO transferToPageDTO(
            Page<Entity> entityPage,
            PageDTO pageDTO,
            Mapper mapperInstance,
            String verifiedSortColumn,
            String verifiedSortOrder,
            String pluralResourceName) {

        pageDTO.setHasContent(entityPage.hasContent());
        pageDTO.setHasNext(entityPage.hasNext());
        pageDTO.setHasPrevious(entityPage.hasPrevious());
        pageDTO.setFirst(entityPage.isFirst());
        pageDTO.setLast(entityPage.isLast());

        pageDTO.setTotalElements(entityPage.getTotalElements());

        pageDTO.setTotalPages(entityPage.getTotalPages());

        pageDTO.setData(
                mapperInstance.toDtoList(entityPage.toList())
        );

        pageDTO.setPerPage(entityPage.getSize());
        pageDTO.setPageNumber(entityPage.getNumber());
        pageDTO.setSize(entityPage.getSize());

        pageDTO.setPluralResourceName(pluralResourceName);

        if (verifiedSortOrder != null && !verifiedSortOrder.equals("")
                && verifiedSortColumn != null && !verifiedSortColumn.equals("")) {
            pageDTO.setSortOrder(verifiedSortOrder);
            pageDTO.setSortColumn(verifiedSortColumn);
            pageDTO.setSorted(true);
        }

        return pageDTO;
    }


}
