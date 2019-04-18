package rockets.model;

import com.google.common.collect.Sets;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.*;


public class LaunchServiceProvider extends Entity {
    private String name;

    private int yearFounded;

    private String country;

    private String headquarters;

    private Set<Rocket> rockets;

    private Map<String, Rocket> rocketmap;

    //private Collection<Rocket> rocketmap;

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

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    public void setRockets(Set<Rocket> rockets) {
        this.rockets = rockets;
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

    public void addRocketToGroup(Rocket rocket){
        rocketmap.put(rocket.getFamily(), rocket);
    }
    //public Collection<Rocket> getRocketmap() {return rocketmap;}

   // public void setRocketmap(Collection<Rocket> rocketmap) {this.rocketmap = rocketmap;}

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public double getRatio() {
        return ratio;
    }
}
