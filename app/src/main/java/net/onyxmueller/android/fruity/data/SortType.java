package net.onyxmueller.android.fruity.data;

import static net.onyxmueller.android.fruity.data.FruitContract.FruitEntry.COLUMN_COMMON_NAME;
import static net.onyxmueller.android.fruity.data.FruitContract.FruitEntry.COLUMN_SCIENTIFIC_NAME;

public enum SortType {
    NONE,
    COMMON_NAME(COLUMN_COMMON_NAME, OrderBy.ASCENDING),
    SCIENTIFIC_NAME(COLUMN_SCIENTIFIC_NAME, OrderBy.ASCENDING);


    private String columnName;
    private OrderBy orderBy;

    SortType() {}

    SortType(String columnName, OrderBy orderBy) {
        this.columnName = columnName;
        this.orderBy = orderBy;
    }

    public String getColumnName() {
        return columnName;
    }

    public OrderBy getOrderBy() {
        return orderBy;
    }
}
