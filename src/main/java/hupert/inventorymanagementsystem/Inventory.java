package hupert.inventorymanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * INVENTORY CLASS
 */
public class Inventory {
    // Create observable list using Part objects
    private static ObservableList<Part> thePartInventory = FXCollections.observableArrayList();

    /**
     * Add Part to Inventory method
     * @param part
     */
    public static void addPart(Part part){
        if (part == null){
            return;
        }
        thePartInventory.add(part);
    }

    /**
     * Part lookup method using ID
     * @param partId
     * @return Part
     */
    public Part lookupPart(int partId){
        ObservableList<Part> allParts = getAllParts();
        ObservableList<Part> partIds = FXCollections.observableArrayList();

        for (Part p : allParts){
            if (p.getId() == partId){
                return p;
            }
        }
        return null;
    }

    /**
     * Part lookup method using name
     * @param name
     * @return
     */
    public Part lookupPart(String name){
        ObservableList<Part> allParts = getAllParts();

        for (Part p : allParts){
            if (p.getName().contains(name)){
                return p;
            }
        }
        return null;
    }

    /**
     * Part delete function
     * @param selectedPart
     */
    public void deletePart(Part selectedPart){
        ObservableList<Part> allParts = getAllParts();
        if (allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
        }
    }

    /**
     * Part update method
     * @param idx
     * @param selectedPart
     */
    public void updatePart(int idx, Part selectedPart){
        ObservableList<Part> allParts = getAllParts();
        allParts.set(idx, selectedPart);
    }

    /**
     * Part Inventory getter method
     * @return
     */
    public static ObservableList<Part> getAllParts(){
        return thePartInventory;
    }

    private static ObservableList<Product> theProdInventory = FXCollections.observableArrayList();

    /**
     * Add Product to Inventory method
     * @param product
     */
    public static void addProduct(Product product){
        if (product == null){
            return;
        }
        theProdInventory.add(product);
    }

    /**
     * Product lookup method using name
     * @param name
     * @return Product
     */
    public Product lookupProduct(String name){
        ObservableList<Product> allProducts = getAllProducts();

        for (Product p : allProducts){
            if (p.getName().contains(name)){
                return p;
            }
        }
        return null;
    }

    /**
     * Product lookup method using ID
     * @param productId
     * @return Product
     */
    public Product lookupProduct(int productId){
        ObservableList<Product> allProducts = getAllProducts();

        for (Product p : allProducts){
            if (p.getId() == productId){
                return p;
            }
        }
        return null;
    }

    /**
     * Product update method
     * @param idx
     * @param selectedProduct
     */
    public void updateProduct(int idx, Product selectedProduct){
        ObservableList<Product> allProducts = getAllProducts();
        allProducts.set(idx, selectedProduct);
    }

    /**
     * Product delete method
     * @param selectedProduct
     */
    public void deleteProduct(Product selectedProduct){
        ObservableList<Product> allProducts = getAllProducts();
        if (allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
        }
    }

    /**
     * Product Inventory getter method
     * @return
     */
    public static ObservableList<Product> getAllProducts(){return theProdInventory;}

    // Part and Product ID lists to compare for ID generation
    static List partIds = new ArrayList<>();
    static List prodIds = new ArrayList<>();



}
