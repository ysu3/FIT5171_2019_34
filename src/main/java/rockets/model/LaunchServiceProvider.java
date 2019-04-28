package rockets.model;

import com.google.common.collect.Sets;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

public class LaunchServiceProvider extends Entity {
    private String name;

    private int yearFounded;

    private String country;

    private String headquarters;

    private Set<Rocket> rockets;

    private Map<String, Rocket> rocketmap;

    private String revenue;

    private double ratio;

    public LaunchServiceProvider(String name, int yearFounded, String country) {
        this.name = name;
        this.yearFounded = yearFounded;
        this.country = country;

        rockets = Sets.newLinkedHashSet();
        rocketmap = new HashMap<String, Rocket>();
    }

    public Map<String, Rocket> getRocketGroup(){ return rocketmap;}

    public String getName() {
        return name;
    }

    public int getYearFounded() {
        return yearFounded;
    }

    public String getCountry() {
        return country;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public Set<Rocket> getRockets() {
        return rockets;
    }

    public String getRevenue() {
        return revenue;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }


    public void setRevenue(String revenue) {
        notNull(revenue, "Revenue cannot be null or empty");
        this.revenue = revenue;
    }


    public void setHeadquarters(String headquarters) {
        notBlank(headquarters, "headquarters cannot be null or empty");
        this.headquarters = headquarters;
    }

    public void setRockets(Set<Rocket> rockets) {
        notNull(rockets, "Rockets cannot be null or empty");
        this.rockets = rockets;
    }

    public void addRocketToGroup(Rocket rocket){
        rocketmap.put(rocket.getFamily(), rocket);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LaunchServiceProvider that = (LaunchServiceProvider) o;
        return yearFounded == that.yearFounded &&
                Objects.equals(name, that.name) &&
                Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, yearFounded, country);
    }
}
