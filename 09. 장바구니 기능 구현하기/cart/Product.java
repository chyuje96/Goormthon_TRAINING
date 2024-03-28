import java.util.Objects;

public class Product {
    private Long key;
    private String name;
    private int price;

    public Product(Long key, String name, int price){
        this.key = key;
        this.name = name;
        this.price = price;
    }

    public Long getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return Objects.equals(key, product.key);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(key);
    }
}
