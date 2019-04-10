package rockets.mining;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rockets.dataaccess.DAO;
import rockets.model.Launch;
import rockets.model.LaunchServiceProvider;
import rockets.model.Rocket;

import javax.swing.text.html.HTMLDocument;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Spliterators.iterator;

public class RocketMiner {
    private static Logger logger = LoggerFactory.getLogger(RocketMiner.class);

    private DAO dao;

    private Set<Rocket> listRockert;

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
       logger.info("find most active"+ k +"rockets");
       Collection<Rocket> rocketList = dao.loadAll(Rocket.class);
       Comparator<Rocket> rocketComparator =(a,b)-> -a.getLaunchOutcome().compareTo(Launch.LaunchOutcome.SUCCESSFUL);
       return rocketList.stream().sorted(rocketComparator).limit(k).collect(Collectors.toList());
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
        logger.info("find most reliable launch service "+ k +"");
        Collection<Rocket> rocketList = dao.loadAll(Rocket.class);
        Collection<Rocket> topRockets = mostLaunchedRockets(k);
        listRockert = new HashSet();
        Iterator iterator = rocketList.iterator();
        Iterator iteratorR = topRockets.iterator();
        while (iterator.hasNext())
        {
               Rocket newRocket = (Rocket) iterator.next();
               while (iteratorR.hasNext()){
                   Rocket topRocket= (Rocket) iterator.next();
                   if(newRocket.getManufacturer().equals(topRocket.getManufacturer()) && newRocket.equals(newRocket)){
                       listRockert.add(newRocket);
                   }
               }
        }
        for (Rocket rockets :listRockert)
        {
            int numberRocket = Collections.frequency(listRockert,rockets);
            int numberk = Collections.frequency(topRockets,rockets);
            float persent = (float)(numberk*100/numberRocket);
        }
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
    public String dominantCountry(String orbit) { return null;}

    /**
     * TODO: to be implemented & tested!
     * <p>
     * Returns the top-k most expensive launches.
     *
     * @param k the number of launches to be returned.
     * @return the list of k most expensive launches.
     */
    public List<Launch> mostExpensiveLaunches(int k) {
        return null;
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
        return null;
    }
   
     
     

}
