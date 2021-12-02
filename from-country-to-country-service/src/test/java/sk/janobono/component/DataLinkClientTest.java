package sk.janobono.component;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
@SpringBootTest
class DataLinkClientTest {

    @Autowired
    public DataLinkClient dataLinkClient;

    @Test
    public void getDataTest() throws Exception {
        byte[] data = dataLinkClient.getData();
        assertThat(data).isNotNull();
    }
}
