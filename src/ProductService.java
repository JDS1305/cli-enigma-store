import java.util.List;

public interface ProductService {
    void addProduct(Product product);

    void updateProduct(int id, Product updatedProduct);

    boolean deleteProduct(int id);

    List<Product> getAllProducts();

    List<Product> searchProducts(String keyword);

    Product getProductById(int id);
}
