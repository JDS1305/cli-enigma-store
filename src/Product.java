import java.util.Date;

public class Product {
    private static int nextId = 1;
    private int id;
    private String name;
    private String brand;
    private double price;
    private Date createdAt;

    public Product (String name, String brand, double price) {
        this.id = nextId++;
        this.name = name;
        this. brand = brand;
        this.price = price;
        this.createdAt = new Date();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreatedAt() {
        return createdAt;
    }}
