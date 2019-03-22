package rockets.model;

import java.util.ArrayList;
import java.util.Objects;

import static org.apache.commons.lang3.Validate.notNull;

public class Rocket extends Entity {
    private String name;

    private String country;

    private String manufacturer;

    private String massToLEO;

    private String massToGTO;

    private String massToOther;

//    private ArrayList<Rocket> rockets = new ArrayList<Rocket>();

    /**
     * All parameters shouldn't be null.
     *
     * @param name
     * @param country
     * @param manufacturer
     */
    public Rocket(String name, String country, String manufacturer) {
        notNull(name);
        notNull(country);
        notNull(manufacturer);

        this.name = name;
        this.country = country;
        this.manufacturer = manufacturer;

    }



//    Rocket r = new Rocket("1", "2", "3");
//    putAllRocketsTogether(r, rockets);

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getMassToLEO() {
        return massToLEO;
    }

    public String getMassToGTO() {
        return massToGTO;
    }

    public String getMassToOther() {
        return massToOther;
    }

    public void setMassToLEO(String massToLEO) {
        notNull(massToLEO, "Mass cannot be null");
        this.massToLEO = massToLEO;
    }

    public void setMassToGTO(String massToGTO) {
        notNull(massToGTO, "Mass cannot be null");
        this.massToGTO = massToGTO;
    }

    public void setMassToOther(String massToOther) {
        notNull(massToOther, "Mass cannot be null");
        this.massToOther = massToOther;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rocket rocket = (Rocket) o;
        return Objects.equals(name, rocket.name) &&
                Objects.equals(country, rocket.country) &&
                Objects.equals(manufacturer, rocket.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, manufacturer);
    }

    @Override
    public String toString() {
        return "Rocket{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", massToLEO='" + massToLEO + '\'' +
                ", massToGTO='" + massToGTO + '\'' +
                ", massToOther='" + massToOther + '\'' +
                '}';
    }

//    public void putAllRocketsTogether(Rocket o, ArrayList<Rocket> rockets){
//        rockets.add(o);
//    }

}
