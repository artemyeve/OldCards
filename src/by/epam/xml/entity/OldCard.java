package by.epam.xml.entity;


public class OldCard {

    private String thema;
    private String type;
    private boolean send;
    private String country;
    private int year;
    private String author;
    private String valuable;

    public OldCard(){}

    public OldCard(String thema, String type, boolean send, String country, int year, String author, String valuable){
        this.thema = thema;
        this.type = type;
        this.send = send;
        this.country = country;
        this.year = year;
        this.author = author;
        this.valuable = valuable;
    }

    public OldCard(String thema, String type, boolean send, String country, int year, String valuable){
        this.thema = thema;
        this.type = type;
        this.send = send;
        this.country = country;
        this.year = year;
        this.author = "unknown";
        this.valuable = valuable;
    }

    public String getThema() {
        return thema;
    }

    public void setThema(String thema) {
        this.thema = thema;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSend() {
        return send;
    }

    public void setSend(boolean send) {
        this.send = send;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getValuable() {
        return valuable;
    }

    public void setValuable(String valuable) {
        this.valuable = valuable;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Thema: ").append(thema).append("\n");
        sb.append("Type: ").append(type).append("\n");
        sb.append("Is sent: ").append(send).append("\n");
        sb.append("Country: ").append(country).append("\n");
        sb.append("Year: ").append(year).append("\n");
        sb.append("Author: ").append(author).append("\n");
        sb.append("Valuable: ").append(valuable).append("\n");
        return sb.toString();
    }

}
