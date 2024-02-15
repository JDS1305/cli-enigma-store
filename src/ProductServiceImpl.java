import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private List<Product> productList;

    public ProductServiceImpl() {
        this.productList = new ArrayList<>();
    }

    @Override
    public void addProduct(Product product) {
        productList.add(product);
    }

    @Override
    public void updateProduct(int id, Product updatedProduct) {
        for (Product product : productList) {
            if (product.getId() == id) {
                product.setName(updatedProduct.getName());
                product.setBrand(updatedProduct.getBrand());
                product.setPrice(updatedProduct.getPrice());
                break;
            }
        }
    }

    @Override
    public void deleteProduct(int id) {
        for (Product product : productList) {
            if (product.getId() == id) {
                productList.remove(product);
                break;
            }
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productList;
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        List<Product> result = new ArrayList<>();
        for (Product product : productList) {
            if (product.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                    product.getBrand().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }
}
