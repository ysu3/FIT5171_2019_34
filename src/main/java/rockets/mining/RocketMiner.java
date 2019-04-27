package rockets.mining;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rockets.dataaccess.DAO;
import rockets.model.Launch;
import rockets.model.LaunchServiceProvider;
import rockets.model.Rocket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.*;


public class RocketMiner {
    private static Logger logger = LoggerFactory.getLogger(RocketMiner.class);

    private DAO dao;

    public RocketMiner(DAO dao) {
        this.dao = dao;
    }

    /**
     * TODO: to be implemented & tested!
     * Returns the top-k most active rockets, as measured by number of completed launches.
     *
     * @param k the number of rockets to be returned.
     * @return the list of k most active rockets.
     */
    public List<Rocket> mostLaunchedRockets(int k) {
        return null;
    }

    /**
     * TODO: to be implemented & tested!
     * <p>
     * Returns the top-k most reliable launch service providers as measured
     * by percentage of successful launches.
     *
     * @param k the number of launch service providers to be returned.
     * @return the list of k most reliable ones.
     */
    public List<LaunchServiceProvider> mostReliableLaunchServiceProviders(int k) {
        return null;
    }

    /**
     * <p>
     * Returns the top-k most recent launches.
     *
     * @param k the number of launches to be returned.
     * @return the list of k most recent launches.
     */
    public List<Launch> mostRecentLaunches(int k) {
        logger.info("find most recent " + k + " launches");
        Collection<Launch> launches = dao.loadAll(Launch.class);
        Comparator<Launch> launchDateComparator = (a, b) -> -a.getLaunchDate().compareTo(b.getLaunchDate());
        return launches.stream().sorted(launchDateComparator).limit(k).collect(Collectors.toList());
    }

    /**
     * TODO: to be implemented & tested!
     * <p>
     * Returns the dominant country who has the most launched rockets in an orbit.
     *
     * @param orbit the orbit
     * @return the country who sends the most payload to the orbit
     */
    public String dominantCountry(String orbit) {
        logger.info("find the most launched country in " + orbit + "orbit");
        Collection<Launch> launches = dao.loadAll(Launch.class);
        ArrayList<Rocket> rockets = new ArrayList<>();
        for(Launch launch:launches){
            if(launch.getOrbit().equals(orbit)){
                rockets.add(launch.getLaunchVehicle());
            }
        }
        Map<String, Integer> rocketCountryMap = new HashMap<String, Integer>();
        for(Rocket rocket:rockets){
            if(rocketCountryMap.containsKey(rocket.getCountry())){
                rocketCountryMap.put(rocket.getCountry(), rocketCountryMap.get(rocket.getCountry()) + 1);
            }else{
                rocketCountryMap.put(rocket.getCountry(), 1);
            }
        }
        int maxV = 0;
        String maxK = null;
        String maxK_mayberemove = null;
        Iterator keys = rocketCountryMap.keySet().iterator();
        while (keys.hasNext()) {
            Object key = keys.next();
            maxK = key.toString();
            int value = rocketCountryMap.get(key);
            if (value > maxV) {
                if (null != maxK_mayberemove) {
                    rocketCountryMap.clear();
                }
                maxV = value;
                rocketCountryMap.put(maxK, maxV);
                maxK_mayberemove = maxK;
            } else if (value == maxV) {
                rocketCountryMap.put(maxK, maxV);
            }
        }
        return maxK;
    }

    /**
     * TODO: to be implemented & tested!
     * <p>
     * Returns the top-k most expensive launches.
     *
     * @param k the number of launches to be returned.
     * @return the list of k most expensive launches.
     */
    public List<Launch> mostExpensiveLaunches(int k) {
        logger.info("find the most" + k + "expensive launches");
        Collection<Launch> mels = dao.loadAll(Launch.class);
        Comparator<Launch> melsComparator = (a, b) -> -a.getPrice().compareTo(b.getPrice());
        return mels.stream().sorted(melsComparator).limit(k).collect(Collectors.toList());
    }

    /**
     * TODO: to be implemented & tested!
     * <p>
     * Returns a list of launch service provider that has the top-k highest
     * sales revenue in a year.
     *
     * @param k the number of launch service provider.
     * @param year the year in request
     * @return the list of k launch service providers who has the highest sales revenue.
     */
    public List<LaunchServiceProvider> highestRevenueLaunchServiceProviders(int k, int year) {

        logger.info("find highest revenue " + k + " LaunchServiceProvider in " + year);
        Collection<LaunchServiceProvider> lsps = dao.loadAll(LaunchServiceProvider.class);
        Comparator<LaunchServiceProvider> lspsRevenueComparator = (a, b) -> -a.getRevenue().compareTo(b.getRevenue());
        return lsps.stream().sorted(lspsRevenueComparator).limit(k).collect(Collectors.toList());

    }
}
