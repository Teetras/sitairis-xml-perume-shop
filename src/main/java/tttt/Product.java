package tttt;
public class Product {
    private String name;
    private String description;
    private double price;
    private String group;

    public Product(String name, String description, double price, String group) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.group = group;
    }

    // Геттеры и сеттеры для всех полей

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
