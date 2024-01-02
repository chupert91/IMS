package hupert.inventorymanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * PRODUCT CLASS
 */
public class Product {

    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    private static ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * Product class using all fields as parameters
     * @param id Product ID
     * @param name Product Name
     * @param price Product Price
     * @param stock Product Inventory Level
     * @param min Product Min Inventory
     * @param max Product Max Inventory
     * @param associatedParts Product list of Associated Parts
     */
    public Product(int id, String name, double price, int stock, int min, int max, ObservableList associatedParts) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
        this.associatedParts = associatedParts;
    }

    /**
     * Product ID getter method
     * @return ID
     */
    public int getId() {return id;}

    /**
     * Product Name getter method
     * @return Name
     */
    public String getName() {return name;}

    /**
     * Product Price getter method
     * @return Price
     */
    public double getPrice() {return price;}

    /**
     * Product Stock Level getter method
     * @return Stock
     */
    public int getStock() {return stock;}

    /**
     * Product Min Inventory getter method
     * @return Min
     */
    public int getMin() {return min;}

    /**
     * Product Max Inventory getter function
     * @return Max
     */
    public int getMax() {return max;}

    /**
     * Product Associated Parts getter method
     * @return Associated Parts List
     */
    public ObservableList<Part> getAssociatedParts() {return associatedParts;}

    /**
     * Product Id setter method
     * @param id
     */
    public void setId(int id){this.id = id;}

    /**
     * Product Name setter method
     * @param name
     */
    public void setName(String name){this.name = name;}

    /**
     * Product Price setter method
     * @param price
     */
    public void setPrice(double price){this.price = price;}

    /**
     * Product Stock Level setter method
     * @param stock
     */
    public void setStock(int stock){this.stock = stock;}

    /**
     * Product Max setter method
     * @param max
     */
    public void setMax(int max){this.max = max;}

    /**
     * Product Min setter method
     * @param min
     */
    public void setMin(int min){this.min = min;}

    /**
     * Product Associated Parts setter method
     * @param associatedParts
     */
    public void setAssociatedParts(ObservableList<Part> associatedParts){this.associatedParts = associatedParts;}

    /**
     * Product add Associated Part method
     * @param part
     */
    public void addAssociatedPart(Part part){associatedParts.add(part);}

    /**
     * Product delete Associated Part method
     * @param part
     * @return boolean
     */
    public boolean deleteAssociatedPart(Part part){
        associatedParts.remove(part);
        return true;
    }

    /**
     * Product Associated Parts List getter method
     * @return Associated Parts List
     */
    public static ObservableList<Part> getAllAssociatedParts(){return associatedParts;}
}
