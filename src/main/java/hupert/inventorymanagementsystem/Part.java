package hupert.inventorymanagementsystem;

/**
 * ABSTRACT CLASS FOR PARTS
 */
public abstract class Part {
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /**
     * Part constructor with all fields as parameters
     * @param id PART ID
     * @param name PART NAME
     * @param price PART PRICE
     * @param stock PART INVENTORY LEVEL
     * @param min MINIMUM INVENTORY LEVEL
     * @param max MAXIMUM INVENTORY LEVEL
     */

    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Part ID getter method
     * @return Part ID
     */
    public int getId() {return id;}

    /**
     * Part Name getter method
     * @return Part Name
     */
    public String getName() {return name;}

    /**
     * Part Price getter method
     * @return Part Price
     */
    public double getPrice() {return price;}
    /**
     * Part Stock getter method
     * @return Part Inventory Level
     */
    public int getStock() {return stock;}
    /**
     * Part Max getter method
     * @return Part Max Inventory
     */
    public int getMin() {return min;}
    /**
     * Part Min getter method
     * @return Part Min Inventory
     */
    public int getMax() {return max;}

    /**
     * Part Name setter method
     * @param name
     */
    public void setName(String name){this.name = name;}

    /**
     * Part Price setter method
     * @param price
     */
    public void setPrice(double price){this.price = price;}

    /**
     * Pert Stock setter method
     * @param stock
     */
    public void setStock(int stock){this.stock = stock;}

    /**
     * Part Min setter method
     * @param min
     */
    public void setMin(int min){this.min = min;}

    /**
     * Part Max setter method
     * @param max
     */
    public void setMax(int max){this.max = max;}

}
