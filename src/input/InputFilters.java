package input;

public final class InputFilters {
    private InputSort sort;
    private InputContains contains;

    public InputFilters() {
    }

    public InputSort getSort() {
        return sort;
    }

    public void setSort(final InputSort sort) {
        this.sort = sort;
    }

    public InputContains getContains() {
        return contains;
    }

    public void setContains(final InputContains contains) {
        this.contains = contains;
    }
}
