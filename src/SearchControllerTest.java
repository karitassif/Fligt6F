import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by atlim on 31.3.2017.
 */
@RunWith(Arquillian.class)
public class SearchControllerTest {

    @Test
    public void searchDiscountFlights() throws Exception {
        sc.searchDiscountFlights(12);
        List<Flight> found = sc.getFlights();
        assertEquals(4, found.size());
    }

    @Test
    public void showPrice() throws Exception {
        sc.searchDiscountFlights(10);
        List<Flight> found = sc.getFlights();
        assertEquals(26, sc.showPrice(found.get(0), 2, 2));

    }

    @Test
    public void filterFlights() throws Exception {
        sc.searchDiscountFlights(20);
        sc.filterFlights(12, true);
        List<Flight> found = sc.getFilteredFlights();
        assertEquals(4,found.size());
    }

    @Test
    public void sortFlights() throws Exception {
        sc.searchDiscountFlights(20);
        sc.sortFlights(true);
        List<Flight> found = sc.getFlights();
        assertEquals(10, found.get(0).getPrice());
        assertEquals(12, found.get(4).getPrice());
        assertEquals(14, found.get(found.size()-1).getPrice());
    }

    SearchController sc;
    @Before
    public void setUp() throws Exception {
        sc = new SearchController();
    }

    @After
    public void tearDown() throws Exception {
        sc = null;
    }

    @Test
    public void testSearchFlightsAirportCode() throws Exception {
        sc.searchFlights("KEF", "CPH", 20, 2,
                new GregorianCalendar(2017,5,24));
        List<Flight> found = sc.getFlights();

        assertEquals("KEF", found.get(0).getDeparture().getAirportCode());
        assertEquals("CPH", found.get(0).getDestination().getAirportCode());
        assertEquals(1, found.get(0).getFlightNumber());
    }
    @Test
    public void testSearchFlightAvailableSeats() throws Exception {
        sc.searchFlights("KEF", "ARN", 20, 4,
                new GregorianCalendar(2017,6,24));
        List<Flight> found = sc.getFlights();
        assertEquals(2, found.size());
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(SearchController.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
