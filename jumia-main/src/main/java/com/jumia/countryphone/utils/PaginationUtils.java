package com.jumia.countryphone.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class PaginationUtils {

    /**
     * Builds the list with pagination based on items and current pagination state.
     * @param items
     * @param pageable
     * @param <T>
     * @return current pagination state
     */
    public static <T> Page<T> buildListWithPagination(List<T> items, Pageable pageable) {

        List<T> list = Collections.emptyList();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        if (items.size() > startItem) {
            int toIndex = Math.min(startItem + pageSize, items.size());
            list = items.subList(startItem, toIndex);
        }
        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), items.size());
    }

    /**
     * Builds page numbers based on list.
     *
     * @param listPage
     * @param <T>
     * @return list items
     */
    public static <T> List<Integer> buildPageNumbers(Page<T> listPage) {
        int totalPages = listPage.getTotalPages();
        if (totalPages > 0) {
            return IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
        }
        return null;
    }
}
