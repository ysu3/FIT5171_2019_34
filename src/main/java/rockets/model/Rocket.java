package rockets.model;

import java.util.ArrayList;
import java.util.Objects;

import static org.apache.commons.lang3.Validate.notNull;

public class  Rocket extends Entity {

    private String name;

    private String country;

    private LaunchServiceProvider manufacturer;

    private Launch launch;

    private String family;

    private String series;

    private String rocketCode;

    private String massToLEO;

    private String massToGTO;

    private String massToOther;


    /**
     * All parameters shouldn't be null.
     *
     * @param name
     * @param country
     * @param manufacturer
     */
    public Rocket(String name, String country, LaunchServiceProvider manufacturer) {
        notNull(name);
        notNull(country);
        notNull(manufacturer);

        this.name = name;
        this.country = country;
        this.manufacturer = manufacturer;

    }


    public String getName() { return name; }

    public String getCountry() {
        return country;
    }

    public LaunchServiceProvider getManufacturer() {
        return manufacturer;
    }

    public String getFamily(){ return family;}

    public String getSeries(){ return series; }

    public String getRocketCode(){ return rocketCode;}

    public String getMassToLEO() {
        return massToLEO;
    }

    public String getMassToGTO() {
        return massToGTO;
    }

    public String getMassToOther() {
        return massToOther;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String Country) {
        this.country = Country;
    }
    public void setManufacturer(LaunchServiceProvider Manufacturer) {
        this.manufacturer = Manufacturer;
    }

    public void setFamily(String family){
        notNull(family,"Family cannot be null");
        this.family = family;
    }

    public void setSeries(String series){
        notNull(series,"Series cannot be null");
        this.series = series;
    }

    public void setRocketCode(String rocketCode){
        notNull(rocketCode,"RocketCode cannot be null");
        this.rocketCode = rocketCode;
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


    public Launch getLaunch() {
        return launch;
    }

    public void setLaunch(Launch launch) {
        this.launch = launch;
    }
}
