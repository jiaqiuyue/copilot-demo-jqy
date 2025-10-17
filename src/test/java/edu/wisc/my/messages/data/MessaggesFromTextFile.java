import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.wisc.my.messages.model.Message;
import edu.wisc.my.messages.model.MessageArray;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class MessagesFromTextFileTest {
  private MessagesFromTextFile messageReader;
  private Environment env;
  private ResourceLoader loader;

  @Before
  public void setUp() {
    messageReader = new MessagesFromTextFile();
    env = mock(Environment.class);
    loader = mock(ResourceLoader.class);
    messageReader.setEnv(env);
    messageReader.setResourceLoader(loader);
  }

  /**
   * Test correct parsing of a valid messages.json structure.
   */
  @Test
  public void parsesValidMessagesJson() throws Exception {
    String json = "{ \"messages\": [ " +
      "{ \"id\": \"test1\", \"title\": \"Title1\", \"description\": \"Desc1\" }, " +
      "{ \"id\": \"test2\", \"title\": \"Title2\", \"description\": \"Desc2\" } " +
      "] }";
    ByteArrayInputStream is = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));

    Resource resource = mock(Resource.class);
    when(env.getProperty("message.source")).thenReturn("classpath:messages.json");
    when(loader.getResource("classpath:messages.json")).thenReturn(resource);
    when(resource.getInputStream()).thenReturn(is);

    List<Message> messages = messageReader.allMessages();
    assertNotNull(messages);
    assertEquals(2, messages.size());
    assertEquals("test1", messages.get(0).getId());
    assertEquals("Title1", messages.get(0).getTitle());
    assertEquals("Desc1", messages.get(0).getDescription());
    assertEquals("test2", messages.get(1).getId());
    assertEquals("Title2", messages.get(1).getTitle());
    assertEquals("Desc2", messages.get(1).getDescription());
  }

  /**
   * Test parsing when messages array is empty.
   */
  @Test
  public void parsesEmptyMessagesArray() throws Exception {
    String json = "{ \"messages\": [] }";
    ByteArrayInputStream is = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));

    Resource resource = mock(Resource.class);
    when(env.getProperty("message.source")).thenReturn("classpath:messages.json");
    when(loader.getResource("classpath:messages.json")).thenReturn(resource);
    when(resource.getInputStream()).thenReturn(is);

    List<Message> messages = messageReader.allMessages();
    assertNotNull(messages);
    assertEquals(0, messages.size());
  }

  /**
   * Test parsing when messages field is missing.
   */
  @Test(expected = RuntimeException.class)
  public void throwsWhenMessagesFieldMissing() throws Exception {
    String json = "{ \"notMessages\": [] }";
    ByteArrayInputStream is = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));

    Resource resource = mock(Resource.class);
    when(env.getProperty("message.source")).thenReturn("classpath:messages.json");
    when(loader.getResource("classpath:messages.json")).thenReturn(resource);
    when(resource.getInputStream()).thenReturn(is);

    messageReader.allMessages();
  }
}