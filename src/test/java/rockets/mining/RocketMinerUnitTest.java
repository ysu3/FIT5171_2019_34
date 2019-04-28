package rockets.mining;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.neo4j.codegen.bytecode.While;
import org.neo4j.unsafe.impl.batchimport.stats.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rockets.dataaccess.DAO;
import rockets.dataaccess.neo4j.Neo4jDAO;
import rockets.model.Launch;
import rockets.model.LaunchServiceProvider;
import rockets.model.Rocket;

import javax.security.auth.callback.LanguageCallback;
import javax.swing.plaf.nimbus.State;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static rockets.model.Launch.LaunchOutcome.SUCCESSFUL;
import static rockets.model.Launch.LaunchOutcome.FAILED;


public class RocketMinerUnitTest {
    Logger logger = LoggerFactory.getLogger(RocketMinerUnitTest.class);

    private DAO dao;
    private RocketMiner miner;
    private List<Rocket> rockets;
    private List<LaunchServiceProvider> lsps;
    private List<Launch> launches;

    @BeforeEach
    public void setUp() {
        dao = mock(Neo4jDAO.class);
        miner = new RocketMiner(dao);
        rockets = Lists.newArrayList();

        lsps = Arrays.asList(
                new LaunchServiceProvider("ULA", 1990, "USA"),
                new LaunchServiceProvider("SpaceX", 2002, "USA"),
                new LaunchServiceProvider("ESA", 1975, "Europe ")
        );


        // index of lsp of each rocket
        int[] lspIndex = new int[]{0, 2, 0, 1, 1, 0, 1, 2, 1, 1};

        int[] rocketname = new int[]{0,1,2,3,1,1,2,1,2,3};
        // 10 rockets
        for (int i = 0; i <rocketname.length; i++) {
            rockets.add(new Rocket("rocket_" + rocketname[i], "USA", lsps.get(lspIndex[i])));
        }

        // month of each launch
        int[] months = new int[]{1, 6, 4, 3, 4, 11, 6, 5, 12, 5};

        // index of rocket of each launch
        int[] rocketIndex = new int[]{0, 0, 0, 0, 1, 1, 1, 2, 2, 3};

        // 10 launches
        launches = IntStream.range(0, 10).mapToObj(i -> {
            logger.info("create " + i + " launch in month: " + months[i]);
            Launch l = new Launch();
            l.setLaunchDate(LocalDate.of(2017, months[i], 1));
            l.setLaunchVehicle(rockets.get(rocketIndex[i]));
            l.setLaunchSite("VAFB");
            if(i==0 || i == 1 || i == 3 || i == 5 || i == 7 || i == 8) {
                l.setLaunchOutcome(SUCCESSFUL);
            }else {
                l.setLaunchOutcome(FAILED);
            }
            l.setOrbit("LEO");
            spy(l);
            return l;
        }).collect(Collectors.toList());

        //
        Iterator<Rocket> rocketIterator = rockets.iterator();
        Iterator<Launch> launchIterator = launches.iterator();
            while(launchIterator.hasNext()&& rocketIterator.hasNext()) {
                Launch launch = launchIterator.next();
                Rocket rocket = rocketIterator.next();
                rocket.setLaunch(launch);
            }

         for(LaunchServiceProvider lsp : lsps) {
             int position = lsps.indexOf(lsp);
             Set<Rocket> rocketList = Sets.newLinkedHashSet();
             for (Rocket rocket : rockets) {
                 if (rocket.getManufacturer().getName() == (lsp.getName()))
                     rocketList.add(rocket);
             }
             lsp.setRockets(rocketList);
             lsps.set(position,lsp);
         }

    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    public void shouldReturnTopMostRecentLaunches(int k) {
        when(dao.loadAll(Launch.class)).thenReturn(launches);
        List<Launch> sortedLaunches = new ArrayList<>(launches);
        sortedLaunches.sort((a, b) -> -a.getLaunchDate().compareTo(b.getLaunchDate()));
        List<Launch> loadedLaunches = miner.mostRecentLaunches(k);
        assertEquals(k, loadedLaunches.size());
        assertEquals(sortedLaunches.subList(0, k), loadedLaunches);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    public void shouldReturnTopMostActiveRockets(int k) {
        when(dao.loadAll(Rocket.class)).thenReturn(rockets);
        List<Rocket> rocketList = new ArrayList<>(rockets);
        Map<String, Integer> rocketMap = new HashMap<String, Integer>();
        Iterator<Rocket> rocketIterator = rocketList.iterator();
        List<Rocket> list = new ArrayList<>();
        while (rocketIterator.hasNext()) {
            Rocket rocket = rocketIterator.next();
            if (rocket.getLaunch().getLaunchOutcome() != null && rocketMap.containsKey(rocket.getName())) {
                rocketMap.put(rocket.getName(), rocketMap.get(rocket.getName()) + 1);
            } else {
                rocketMap.put(rocket.getName(), 0);
            }
        }
        List<Map.Entry<String, Integer>> listmap = new ArrayList<>();
        listmap.addAll(rocketMap.entrySet());
        Collections.sort(listmap, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        List<Map.Entry<String, Integer>> list1 = listmap.subList(0, k);
        List<Rocket> rocketList1 = new ArrayList<>();
        Iterator<Rocket> iterator = rocketList.listIterator();
        for (int i = 0; i < k; i++) {
            Map.Entry<String, Integer> entry = list1.listIterator().next();
            while (true) {
                if (!rocketIterator.hasNext()) break;
                Rocket rocket = rocketIterator.next();
                if (rocket.getName().contains(entry.getKey()) && list.size() < k) {
                    boolean add = list.add(rocket);
                    List<Rocket> loadedRockets = miner.mostLaunchedRockets(k);
                    assertEquals(k, loadedRockets.size());
                    assertEquals(rocketList1, loadedRockets);
                }
            }
        }
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    public void shouldReturnMostReliableLaunchServiceProviders(int k){
        when(dao.loadAll(LaunchServiceProvider.class)).thenReturn(lsps);
        List<LaunchServiceProvider> sortedlsp = new ArrayList<>(lsps);
        Collections.sort(sortedlsp,new Comparator<LaunchServiceProvider>(){
            @Override
            public int compare(LaunchServiceProvider o1, LaunchServiceProvider o2) {
                return new Double(o2.getRatio()).compareTo(new Double(o1.getRatio()));
            }});
        List<LaunchServiceProvider> loadedLaunchServiceProvider = miner.mostReliableLaunchServiceProviders(k);
        assertEquals(k, loadedLaunchServiceProvider.size());
        //assertEquals(sortedlsp.subList(0, k), loadedLaunchServiceProvider);
    }

}