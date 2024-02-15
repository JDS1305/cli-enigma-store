import java.util.List;

public interface ProductService {
    void addProduct(Product product);

    void updateProduct(int id, Product updatedProduct);

    void deleteProduct(int id);

    List<Product> getAllProducts();

    List<Product> searchProducts(String keyword);
}
