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
public class CitiesErrorHandlerTest {

    @Autowired
    CitiesErrorHandler errorHandler;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void handleError() {
        HttpServletResponse response = mock(HttpServletResponse.class);
        assertEquals("no", errorHandler.handleError(response));
    }

    @Test
    public void getErrorPath() {

        assertEquals("/error", errorHandler.getErrorPath());
    }
}