package net.chrisrichardson.eventstore.examples.management.restaurant;

import net.chrisrichardson.eventstore.examples.management.restaurant.integrationtests.AbstractRestaurantManagementIntegrationTest;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {EndToEndTestConfiguration.class})
@WebAppConfiguration
public class EndToEndTest extends AbstractRestaurantManagementIntegrationTest {

    @Value("#{systemEnvironment['SERVICE_HOST']  ?: 'localhost'}")
    private String hostName;

    private String makeBaseUrl(int port, String path) {
        return "http://" + hostName + ":" + port + "/" + path;
    }

    @Autowired
    RestTemplate restTemplate;


    @Override
    protected String getHost() {
        return hostName;
    }

    @Override
    protected int getCommandSidePort() {
        return 8081;
    }

    @Override
    protected int getQuerySidePort() {
        return 8082;
    }
}
