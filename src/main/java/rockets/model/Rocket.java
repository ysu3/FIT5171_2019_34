package rockets.model;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

public class Rocket extends Entity {
    private String name;

    private String country;

    private LaunchServiceProvider manufacturer;

    private String massToLEO;

    private String massToGTO;

    private String massToOther;

    private Launch LaunchOutcome;

    private String family;

    private String series;

    private String rocketCode;


    /**
     * All parameters shouldn't be null.
     *
     * @param name
     * @param country
     * @param manufacturer
     */
    public Rocket(String name, String country, LaunchServiceProvider manufacturer) {
        notNull(name);
        notEmpty(name,"name can not be empty");
        notNull(country);
        notEmpty(country,"Country cannot be empty.");
        notNull(manufacturer);
        //notNull(launchOutcome);
        this.name = name;
        this.country = country;
        this.manufacturer = manufacturer;
        //this.LaunchOutcome = launchOutcome;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public LaunchServiceProvider getManufacturer() {
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
        this.massToLEO = massToLEO;
    }

    public void setMassToGTO(String massToGTO) {
        this.massToGTO = massToGTO;
    }

    public void setMassToOther(String massToOther) {
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

    public Launch getLaunchOutcome() {
        return LaunchOutcome;
    }

    public void setLaunchOutcome(Launch launchOutcome) {
        LaunchOutcome = launchOutcome;
    }

    public String getRocketCode() {
        return rocketCode;
    }

    public void setRocketCode(String rocketCode) {
        this.rocketCode = rocketCode;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) { this.family = family;}

}
