package sam_wen.cities_graph;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CitiesControllerTest {

    @Autowired
    CitiesController controller;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void isConnected1() {
        HttpServletResponse response = mock(HttpServletResponse.class);
        String result = controller.isConnected(response, "a", "b");
        assertEquals("no", result);
    }

    @Test
    public void isConnected2() {
        HttpServletResponse response = mock(HttpServletResponse.class);
        String result = controller.isConnected(response, "", "b");
        assertEquals("no", result);
    }

    @Test
    public void isConnected3() {
        HttpServletResponse response = mock(HttpServletResponse.class);
        String result = controller.isConnected(response, "", "");
        assertEquals("no", result);
    }

    @Test
    public void isConnected4() {
        HttpServletResponse response = mock(HttpServletResponse.class);
        String result = controller.isConnected(response, null, null);
        assertEquals("no", result);
    }

    @Test
    public void isConnected5() {
        HttpServletResponse response = mock(HttpServletResponse.class);
        String result = controller.isConnected(response, "Boston", "Newark");
        assertEquals("yes", result);
    }

    @Test
    public void isConnected6() {
        HttpServletResponse response = mock(HttpServletResponse.class);
        String result = controller.isConnected(response, "Boston", "Philadelphia");
        assertEquals("yes", result);
    }

    @Test
    public void isConnected7() {
        HttpServletResponse response = mock(HttpServletResponse.class);
        String result = controller.isConnected(response, "Philadelphia", "Albany");
        assertEquals("no", result);
    }
}