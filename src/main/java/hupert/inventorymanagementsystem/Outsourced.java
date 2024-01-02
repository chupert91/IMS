package hupert.inventorymanagementsystem;

/**
 * OUTSOURCED CLASS
 */
public class Outsourced extends Part{

    private String company;

    /**
     * Outsourced (Part) constructor using all fields as parameters
     * @param id Outsourced ID
     * @param name Outsourced Name
     * @param price Outsourced Price
     * @param stock Outsourced Inventory Level
     * @param min Outsourced Minimum Inventory Level
     * @param max Outsourced Maximum Inventory Level
     * @param company Company Name
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String company) {
        super(id, name, price, stock, min, max);
        this.company = company;
    }

    /**
     * Outsourced Company Name getter method
     * @return Company Name
     */
    public String getCompanyName() {return company;}

    /**
     * Outsourced Company Name setter function
     * @param newCompany Company Name
     */
    public void setCompanyName(String newCompany){this.company = newCompany;}
}
