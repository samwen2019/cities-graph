package sam_wen.cities_graph;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void httpRequest1() throws Exception {
        assertThat(restTemplate.
                getForObject("http://localhost:" + port + "/",
                        String.class)).contains("no");
    }

    @Test
    public void httpRequest2() throws Exception {
        assertThat(restTemplate.
                getForObject("http://localhost:" + port + "/connected",
                String.class)).contains("no");
    }

    @Test
    public void httpRequest3() throws Exception {
        assertThat(restTemplate.
                getForObject("http://localhost:" + port + "/connected?origin=Boston&destination=Newark",
                        String.class)).contains("yes");
    }

    @Test
    public void httpRequest4() throws Exception {
        assertThat(restTemplate.
                getForObject("http://localhost:" + port + "/connected?origin=Boston&destination=Philadelphia",
                        String.class)).contains("yes");
    }

    @Test
    public void httpRequest5() throws Exception {
        assertThat(restTemplate.
                getForObject("http://localhost:" + port + "/connected?origin=Philadelphia&destination=Albany",
                        String.class)).contains("no");
    }

    @Test
    public void httpRequest6() throws Exception {
        assertThat(restTemplate.
                getForObject("http://localhost:" + port + "/connected?origin=Philadelphia",
                        String.class)).contains("no");
    }

    @Test
    public void httpRequest7() throws Exception {
        assertThat(restTemplate.
                getForObject("http://localhost:" + port + "/connected?destination=Philadelphia",
                        String.class)).contains("no");
    }

    @Test
    public void httpRequest8() throws Exception {
        assertThat(restTemplate.
                getForObject("http://localhost:" + port + "/connected?origin=Philadelphia&destination=",
                        String.class)).contains("no");
    }

    @Test
    public void httpRequest9() throws Exception {
        assertThat(restTemplate.
                getForObject("http://localhost:" + port + "/connected?destination=Philadelphia&origin=",
                        String.class)).contains("no");
    }
}
