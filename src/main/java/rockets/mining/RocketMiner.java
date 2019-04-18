package rockets.mining;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rockets.dataaccess.DAO;
import rockets.model.Launch;
import rockets.model.LaunchServiceProvider;
import rockets.model.Rocket;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;
import static java.util.Spliterators.iterator;

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
    /**  //The top-K most active rockets grouping by family.
    public List<Map.Entry<String, Integer>> mostLaunchedRockets(int k) {
     public List<Rocket> mostLaunchedRockets(int k) {
        logger.info("find most active"+ k +"rockets");
        Collection<Rocket> rocketList = dao.loadAll(Rocket.class); //get the Collection for all the rockets;
        Map<String, Integer> mapnumber = new HashMap<>(); //create a Map for the rocket and grouping by the family
        Iterator<Rocket> rockets = rocketList.iterator();
        int number = 0;
        while (!rocketList.isEmpty()&& rocketList.iterator().hasNext()) {
            Rocket rocket = rockets.next();
            if (rocket.getLaunch().getLaunchOutcome().equals("SUCCESSFUL") && rocket.getFamily().contains(rocket.getFamily())) { // get the rocket which was launched and successful
                mapnumber.put(rocket.getFamily(), number + 1);//add the rocket into different group. grouping by the family.
            } else {
                mapnumber.put(rocket.getFamily(), number); // if the rocket not in the Map the number is 0
            }
        }
        Comparator<Map.Entry<String,Integer>> comparator =Comparator.comparing(Map.Entry<String,Integer>::getValue);
                //(a,b)->a.getValue().compareTo(b.getValue());
        return mapnumber.entrySet().stream().sorted(comparator).limit(k).collect(Collectors.toList());

        //return mapnumber.entrySet().stream().sorted(comparator).limit(k).map(entry -> entry.getKey()).collect(Collectors.toList());
    }
    */
    //The top-K most active rockets without grouping by family.
    public List<Rocket> mostLaunchedRockets(int k) {
        logger.info("find most active" + k + "rockets");
        Collection<Rocket> rocketList = dao.loadAll(Rocket.class); //get the Collection for all the rockets;
        Map<Rocket,Integer> rocketHashMap = new HashMap<>(); //create a Map for the rocket and grouping by the family
        Iterator<Rocket> rockets = rocketList.iterator();
        int number = 0;
        while (!rocketList.isEmpty() && rocketList.iterator().hasNext()) {
            Rocket rocket = rockets.next();
            if (rocket.getLaunch().getLaunchOutcome().equals("SUCCESSFUL") && rocket.getFamily().contains(rocket.getFamily())) { // get the rocket which was launched and successful
                rocketHashMap.put(rocket,+1);
            } else {
                rocketHashMap.put(rocket,number);
            }
        }
        Comparator<Map.Entry<Rocket,Integer>> comparator = Comparator.comparing(Map.Entry<Rocket,Integer>::getValue);
        return rocketHashMap.entrySet().stream().sorted(comparator).limit(k).map(entry -> entry.getKey()).collect(Collectors.toList());

                //filter(entry->entry.getValue()>= k).map(entry->entry.getKey()).collect(Collectors.toList());
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
        List<Rocket> topRocket = mostLaunchedRockets(k); //get the top K Rocket which is launched successful.
        Map<LaunchServiceProvider, Double> percentageTopLSP = new HashMap<>(); // Create a Map to store  the launchServiceProvider(LSP)
        int number = 0;
        double ratio =0.0;
        while (!providersList.isEmpty() && providersList.iterator().hasNext()){ // if the provider List is not empty and has next.
            LaunchServiceProvider launchServiceProvider = providersList.iterator().next();  // traverse the LSP collection and get the provide object.
            int numberRocket = launchServiceProvider.getRockets().size(); // the total numbers of rocket for each provide.
             while (launchServiceProvider.getRockets().iterator().hasNext()) {
                 Rocket rocket = launchServiceProvider.getRockets().iterator().next();// traversal the rocket collection for each provide.
                 if(topRocket.contains(rocket)) // select every rocket which was launched successful.
                     //rockets.add(rocket);  // add the launched successful rocket in the new collection.
                     number += 1;   // get the new collection size / the number of the rocket.
                     //launchServiceProvider.setRatio(ratio);//add the the ratio for each provide.
             }
            ratio = number/numberRocket;
            percentageTopLSP.put(launchServiceProvider,ratio);
        }
        //Comparator<Map.Entry<LaunchServiceProvider,Double>> comparator = Comparator.comparing(Map.Entry<LaunchServiceProvider,Double>::getValue);
        return percentageTopLSP.entrySet().stream().sorted(Comparator.comparing(Map.Entry<LaunchServiceProvider,Double>::getValue)).limit(k)
                .map(entry->entry.getKey())
                .collect(Collectors.toList());
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
