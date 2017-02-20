package nazar.pyvovar.tools;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.logging.Logger;

import static org.junit.Assert.*;

/**
 * Created by pyvov on 20.02.2017.
 */
public class RequestSenderImplTest {

    private RequestsSender sender;
    private Logger log;

    @Before
    public void setUp() {
        sender = new RequestsSenderImpl();
        log = Logger.getLogger("test");
    }

    @Test
    public void whenSendApiGetOk() throws IOException {
        String s = sender.sendGet("https://owlbot.info/api/v1/dictionary/d?format=json");
        assertEquals("[]", s);
    }

}
