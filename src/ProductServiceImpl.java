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
    public boolean deleteProduct(int id) {
        if (productList.isEmpty()) {
            System.out.println("No products available to delete.");
            return false;
        }

        for (Product product : productList) {
            if (product.getId() == id) {
                productList.remove(product);
                return true;
            }
        }
        System.out.println("Product with ID " + id + " not found!");
        return false;
    }

    @Override
    public List<Product> getAllProducts() {
        return productList;
    }

    public Product getProductById(int id) {
        for (Product product : productList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        List<Product> result = new ArrayList<>();
        for (Product product : productList) {
            if (product.getName().toLowerCase().contains(keyword.toLowerCase()) || product.getBrand().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }
}
