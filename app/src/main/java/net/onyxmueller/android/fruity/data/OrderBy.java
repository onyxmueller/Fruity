package net.onyxmueller.android.fruity.data;


public enum OrderBy {

    ASCENDING("ASC"),
    DESCENDING("DESC"),
    NONE(null);

    private final String order;

    OrderBy(String order) {
        this.order = order;
    }

    public String getOrder() {
        return order;
    }
}
