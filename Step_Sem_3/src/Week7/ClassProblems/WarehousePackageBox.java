interface WarehousePrintable {
    String printLabel();
}

class WarehousePackageBox implements WarehousePrintable {
    private String trackingId;

    public WarehousePackageBox(String trackingId) {
        this.trackingId = trackingId;
    }

    @Override
    public String printLabel() {
        return "Package label: " + trackingId;
    }
}

class WarehouseInvoice implements WarehousePrintable {
    private String invoiceNumber;

    public WarehouseInvoice(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @Override
    public String printLabel() {
        return "Invoice label: " + invoiceNumber;
    }
}

public class WarehouseLabelPrinter {

    static void printAll(WarehousePrintable[] items) {
        for (WarehousePrintable item : items)
            System.out.println(item.printLabel());
    }
}