package hupert.inventorymanagementsystem;

/**
 * IN-HOUSE CLASS
 */
public class InHouse extends Part {

    private int machineID;

    /**
     * In-House constructor using all fields as parameters
     * @param id Part ID
     * @param name Part Name
     * @param price Part Price
     * @param stock Part Inventory Level
     * @param min Part Min Inventory Level
     * @param max Part Max Inventory Level
     * @param mID Part Machine ID
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int mID) {
        super(id, name, price, stock, min, max);
        this.machineID = mID;
    }

    /**
     * Part Machine ID getter method
     * @return Machine ID
     */
    public int getMachineID() {return machineID;}

    /**
     * Part Machine ID setter method
     * @param machineID
     */
    public void setMachineID(int machineID){this.machineID = machineID;}

}
