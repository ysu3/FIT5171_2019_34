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

import static rockets.model.Launch.LaunchOutcome.SUCCESSFUL;


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
        logger.info("find most active  " + k + " rockets");
        Collection<Rocket> rocketList = dao.loadAll(Rocket.class); //get the Collection for all the rockets;
        Map<String,Integer> rocketHashMap = new HashMap<>(); //create a Map for the rocket and grouping by the family
        List<Rocket> list = new ArrayList<>();
        Iterator<Rocket> rockets = rocketList.iterator();
        while (!rocketList.isEmpty() && rockets.hasNext()) {
            Rocket rocket = rockets.next();
            if (rocket.getLaunch().getLaunchOutcome()==SUCCESSFUL && rocketHashMap.containsKey(rocket.getName())){
                rocketHashMap.put(rocket.getName(),rocketHashMap.get(rocket.getName())+1);
            } else {
                rocketHashMap.put(rocket.getName(),0);
            }
        }
        List<Map.Entry<String, Integer>> RMLList = new ArrayList<>();
        RMLList.addAll(rocketHashMap.entrySet());
        Collections.sort(RMLList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                return entry2.getValue().compareTo(entry1.getValue());
            }
        });
        Iterator<Rocket> rocketIterator = rocketList.iterator();
        for (int i =0;i<k ;i++)
        {
            Map.Entry<String, Integer> entry = RMLList.listIterator().next();
            while (true)
            {
                if (!rocketIterator.hasNext()) break;
                Rocket rocket = rocketIterator.next();
                if(rocket.getName().contains(entry.getKey()) && list.size()< k){
                    boolean add = list.add(rocket);
                }
            }
        }
        return list;
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
        logger.info("find most reliable launch service " + k + "");
        Collection<LaunchServiceProvider> providersList = dao.loadAll(LaunchServiceProvider.class);
        List<LaunchServiceProvider> launchServiceProviderList = new ArrayList<>();
        int number = 0;
        int total = 0;
        double ratio = 0.0;
        Iterator<LaunchServiceProvider> launchServiceProvider = providersList.iterator();
        while (!providersList.isEmpty() && launchServiceProvider.hasNext()) {
            LaunchServiceProvider lsp = launchServiceProvider.next();
            total = lsp.getRockets().size();
            if (lsp.getRockets().iterator().next().getLaunch().getLaunchOutcome()==SUCCESSFUL)
                number = number +1;
            ratio = number/total;
            lsp.setRatio(ratio);
            launchServiceProviderList.add(lsp);
        }
        Collections.sort(launchServiceProviderList,new Comparator<LaunchServiceProvider>(){
            @Override
            public int compare(LaunchServiceProvider o1, LaunchServiceProvider o2) {
                return new Double(o2.getRatio()).compareTo(new Double(o1.getRatio()));
            }});
        return launchServiceProviderList.stream().limit(k).collect(Collectors.toList());
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
        logger.info("find the most launched country in " + orbit + " orbit");
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
        ArrayList<LaunchServiceProvider> lspsArrayList = new ArrayList<LaunchServiceProvider>();
        for(LaunchServiceProvider lsp:lsps){
            if(lsp.getYearFounded() == year){
                lspsArrayList.add(lsp);
            }
        }
        LaunchServiceProvider[] lspsArray = lspsArrayList.toArray(new LaunchServiceProvider[lspsArrayList.size()]);


        Comparator<LaunchServiceProvider> lspsRevenueComparator = (a,b) -> -a.getRevenue().compareTo(b.getRevenue());
        return lsps.stream().sorted(lspsRevenueComparator).limit(k).collect(Collectors.toList());
    }
}

