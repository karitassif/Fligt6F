/*import org.jboss.arquillian.container.test.api.Deployment;
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
*/

/**
 * Created by atlim on 31.3.2017.
 */
//@RunWith(Arquillian.class)
public class SearchControllerTest {

    /*
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
    public void searchDiscountFlights() throws Exception {
        sc.searchDiscountFlights(12);
        List<Flight> found = sc.getFlights();
        assertEquals(4, found.size());
    }
    @Test
    public void searchDiscountFlightsEmptyList() throws Exception {
        sc.searchDiscountFlights(-5);
        List<Flight> found = sc.getFlights();
        assertEquals(0, found.size());
    }

    @Test
    public void showPrice() throws Exception {
        sc.searchDiscountFlights(10);
        List<Flight> found = sc.getFlights();
        assertEquals(26, sc.showPrice(found.get(0), 2, 2));

    }

    @Test
    public void filterFlightsNoChildDiscount() throws Exception {
        sc.searchDiscountFlights(20);
        sc.filterFlights(20, false);
        List<Flight> found = sc.getFilteredFlights();
        assertEquals(1,found.size());
    }

    @Test
    public void filterFlights() throws Exception {
        sc.searchDiscountFlights(20);
        sc.filterFlights(12, true);
        List<Flight> found = sc.getFilteredFlights();
        assertEquals(3,found.size());
    }

    @Test
    public void sortFlightsByPrice() throws Exception {
        sc.searchDiscountFlights(20);
        sc.sortFlights(true);
        List<Flight> found = sc.getFlights();
        for (Flight flight : found){
            System.out.println(flight.getPrice());
        }
        assertEquals(10, found.get(0).getPrice());
        assertEquals(13, found.get(4).getPrice());
        assertEquals(14, found.get(found.size()-1).getPrice());
    }
    @Test
    public void sortFlightsByDate() throws Exception {
        sc.searchDiscountFlights(20);
        sc.sortFlights(false);
        List<Flight> found = sc.getFlights();
        for (Flight flight : found){
            System.out.println(flight.getFlightNumber());
        }
        assertEquals(6, found.get(0).getFlightNumber());
        assertEquals(2, found.get(4).getFlightNumber());
        assertEquals(4, found.get(found.size()-1).getFlightNumber());
    }
    @Test
    public void sortFlightsEmptyList() throws Exception {
        sc.searchDiscountFlights(5);
        sc.sortFlights(true);
        List<Flight> found = sc.getFlights();
        for (Flight flight : found){
            System.out.println(flight.getPrice());
        }
        assertEquals(0, found.size());

    }

    @Test
    public void testSearchFlightsAirportCodeEmpty() throws Exception {
        sc.searchFlights("", "CPH", 20, 2,
                new GregorianCalendar(2017,5,24));
        List<Flight> found = sc.getFlights();

        assertEquals(0, found.size());
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
    */

}
