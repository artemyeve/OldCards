package by.epam.xml.entity;

public enum OldCardEnum {
    OLD_CARDS("old_cards"),
    CARD("card"),
    THEMA("thema"),
    COUNTRY("country"),
    YEAR("year"),
    VALUABLE("valuable"),
    TYPE("type"),
    AUTHOR("author"),
    SEND("send");

    private String value;

    private OldCardEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
