package com.jcy.jcyycback.common.service;


import lombok.Data;

import java.util.List;

@Data
public class PageHelper<T> {
    String startDate;

    String endDate;

    Integer pageSize;

    Integer current;

    long total;

    T object;

    List<T> objects;

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PageHelper))
            return false;
        PageHelper <?> other = (PageHelper)o;
        if (!other.canEqual(this))
            return false;
        Object this$startDate = getStartDate(), other$startDate = other.getStartDate();
        if ((this$startDate == null) ? (other$startDate != null) : !this$startDate.equals(other$startDate))
            return false;
        Object this$endDate = getEndDate(), other$endDate = other.getEndDate();
        if ((this$endDate == null) ? (other$endDate != null) : !this$endDate.equals(other$endDate))
            return false;
        Object this$pageSize = getPageSize(), other$pageSize = other.getPageSize();
        if ((this$pageSize == null) ? (other$pageSize != null) : !this$pageSize.equals(other$pageSize))
            return false;
        Object this$current = getCurrent(), other$current = other.getCurrent();
        if ((this$current == null) ? (other$current != null) : !this$current.equals(other$current))
            return false;
        if (getTotal() != other.getTotal())
            return false;
        Object this$object = getObject(), other$object = other.getObject();
        if ((this$object == null) ? (other$object != null) : !this$object.equals(other$object))
            return false;
        Object this$objects = (Object)getObjects();
        Object other$objects = (Object)other.getObjects();
        return !((this$objects == null) ? (other$objects != null) : !this$objects.equals(other$objects));
    }

    protected boolean canEqual(Object other) {
        return other instanceof PageHelper;
    }

    @Override
    public String toString() {
        return "PageHelper(startDate=" + getStartDate() + ", endDate=" + getEndDate() + ", pageSize=" + getPageSize() + ", current=" + getCurrent() + ", total=" + getTotal() + ", object=" + getObject() + ", objects=" + getObjects() + ")";
    }

    public Integer getPageSize() {
        return Integer.valueOf((this.pageSize.intValue() == 0) ? 20 : this.pageSize.intValue());
    }

    public Integer getCurrent() {
        return Integer.valueOf((this.current.intValue() == 0) ? 1 : this.current.intValue());
    }

    public Integer getLimitMin() {
        return Integer.valueOf((getCurrent().intValue() - 1) * getPageSize().intValue());
    }

    public long getLimitMax() {
        Integer limitMin = getLimitMin();
        Integer pageSize = getPageSize();
        return ((limitMin.intValue() + pageSize.intValue()) <= this.total) ? (limitMin.intValue() + pageSize.intValue()) : this.total;
    }
}
