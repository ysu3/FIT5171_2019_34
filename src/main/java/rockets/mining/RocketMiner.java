package rockets.mining;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rockets.dataaccess.DAO;
import rockets.model.Launch;
import rockets.model.LaunchServiceProvider;
import rockets.model.Rocket;

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
    public List<Map.Entry<String, Integer>> mostLaunchedRockets(int k) {
       logger.info("find most active"+ k +"rockets");
       Collection<Rocket> rocketList = dao.loadAll(Rocket.class);
        Map<String, Integer> mapnumber = new HashMap<>();
        Iterator<Rocket> rocket = rocketList.iterator();
        int number = 0;
        while (!rocketList.isEmpty()&& rocketList.iterator().hasNext()) {
            Rocket newrocket = rocket.next();
            if (newrocket.getLaunchOutcome().equals("SUCCESSFUL") && newrocket.getFamily().contains(newrocket.getFamily())) {
                mapnumber.put(newrocket.getFamily(), number + 1);//add the rocket into different group. grouping by the family.
            } else {
                mapnumber.put(newrocket.getFamily(), number);
            }
        }
        Set<Map.Entry<String, Integer>> maplist = mapnumber.entrySet();
       Comparator<Map.Entry<String, Integer>> integerComparator = (a, b)-> -a.getValue().compareTo(b.getValue());
       return maplist.stream().sorted(integerComparator).limit(k).collect(Collectors.toList());
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
        Collection<LaunchServiceProvider> providersList = dao.loadAll(LaunchServiceProvider.class);
        Iterator iteratorTop = mostExpensiveLaunches(k).iterator(); // traversal the top active collection
        ArrayList<Rocket>  rockets = null; // create a new Set for to story the rocket which was launched successful.
        while (!providersList.isEmpty() && providersList.iterator().hasNext()){ // if the provider List is not empty
             LaunchServiceProvider launchServiceProvider = providersList.iterator().next();  // get the provide object.
             int numberRocket = launchServiceProvider.getRocketmap().size(); // the total numbers of rocket for each provide.
             while (launchServiceProvider.getRockets().iterator().hasNext()) {
                 Rocket rocket = launchServiceProvider.getRockets().iterator().next();// traversal the rocket collection for each provide.
                 if(rocket.getLaunchOutcome().equals("SUCCESSFUL")) // select every rocket which was launched successful.
                     rockets.add(rocket);  // add the launched successful rocket in the new collection.
                     int number = rockets.size();  // get the new collection size / the number of the rocket.
                     double ratio = number/numberRocket;
                     launchServiceProvider.setRatio(ratio);//add the the ratio for each provide.
             }
        }
        return providersList.stream().sorted(Comparator.comparingDouble(LaunchServiceProvider::getRatio)).limit(k).collect(Collectors.toList());
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
