public enum Bestellstatus {
    PROCESSING("Processing"),
    IN_DELIVERY( "In Delivery"),
    COMPLETED("Completed");

    private final String name;
    private Bestellstatus(String name) {
        this.name = name;
    }
}
