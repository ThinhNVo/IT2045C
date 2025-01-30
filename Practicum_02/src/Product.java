import java.util.Objects;

public class Product {
    private String ID = "";
    private String Name = "";
    private String Description = "";
    private double Cost = 0;

    public Product(String ID, String name, String description, double cost) {
        this.ID = ID;
        Name = name;
        Description = description;
        Cost = cost;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public double getCost() {
        return Cost;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setCost(double cost) {
        Cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(Cost, product.Cost) == 0 && Objects.equals(ID, product.ID) && Objects.equals(Name, product.Name) && Objects.equals(Description, product.Description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, Name, Description, Cost);
    }

    @Override
    public String toString() {
        return "Product{" +
                "ID='" + ID + '\'' +
                ", Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                ", Cost=" + Cost +
                '}';
    }


    public String toCSV() {
        return ID + ", " + Name + ", " + Description + ", " + Cost;
    }

    public String toJSON() {
        return "{\"ID\":\"" + ID + "\"" +
                ", \"Name\":\"" + Name + "\"" +
                ", \"Description\":\"" + Description + "\"" +
                ", \"Cost\":" + Cost +
                "}";
    }

    public String toXML() {
        return "<Product>" +
                "<ID>" + ID + "</ID>" +
                "<Name>" + Name + "</Name>" +
                "<Description>" + Description + "</Description>" +
                "<Cost>" + Cost + "</Cost>" +
                "</Product>";
    }
}
