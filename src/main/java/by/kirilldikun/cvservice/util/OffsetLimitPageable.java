package by.kirilldikun.cvservice.util;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

public class OffsetLimitPageable implements Pageable, Serializable {

    private final long offset;

    private final int limit;

    private final Sort sort;

    protected OffsetLimitPageable(long offset, int limit, Sort sort) {
        if (offset < 0) {
            throw new IllegalArgumentException("Offset index must not be less than zero!");
        }

        if (limit < 1) {
            throw new IllegalArgumentException("Limit must not be less than one!");
        }
        this.limit = limit;
        this.offset = offset;
        this.sort = sort;
    }

    protected OffsetLimitPageable(long offset, int limit, Sort.Direction direction, String... properties) {
        this(offset, limit, Sort.by(direction, properties));
    }

    public static OffsetLimitPageable of(long offset, int limit) {
        return of(offset, limit, Sort.unsorted());
    }

    public static OffsetLimitPageable of(long offset, int limit, Sort sort) {
        return new OffsetLimitPageable(offset, limit, sort);
    }

    public static OffsetLimitPageable of(long offset, int limit, Sort.Direction direction, String... properties) {
        return of(offset, limit, Sort.by(direction, properties));
    }

    @Override
    public int getPageNumber() {
        return (int) offset / limit;
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new OffsetLimitPageable(getOffset() + getPageSize(), getPageSize(), getSort());
    }

    public OffsetLimitPageable previous() {
        return hasPrevious() ? new OffsetLimitPageable(getOffset() - getPageSize(), getPageSize(), getSort()) : this;
    }

    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    @Override
    public Pageable first() {
        return new OffsetLimitPageable(0, getPageSize(), getSort());
    }

    @Override
    public Pageable withPage(int pageNumber) {
        final int pageSize = getPageSize();
        return new OffsetLimitPageable((long) pageNumber * pageSize, pageSize, getSort());
    }

    @Override
    public boolean hasPrevious() {
        return offset >= limit;
    }
}