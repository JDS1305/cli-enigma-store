public class ProductValidator {
    public static boolean isProductNameValid(String name) {
        return name != null && name.length() >= 3 && name.length() <= 50;
    }

    public static boolean isProductBrandValid(String brand) {
        return brand != null && brand.length() >= 3 && brand.length() <= 30;
    }

    public static boolean isProductPriceValid(String price) {
        try {
            double number = Double.parseDouble(price);
            return number >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
