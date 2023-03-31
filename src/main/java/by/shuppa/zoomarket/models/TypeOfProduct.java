package by.shuppa.zoomarket.models;

public enum TypeOfProduct {
    PET("Питомец"),
    FEED("Еда"),
    EQUIPMENT("Оборудование");

    TypeOfProduct(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public final String type;
}

