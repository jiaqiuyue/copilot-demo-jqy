package edu.wisc.my.messages.data;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import org.junit.Test;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class MessagesFromTextFileTest {

  /**
   * Test that an exception encountered in preparing to read the text file results in a throw from
   * the reader.
   */
  @Test(expected = RuntimeException.class)
  public void exceptionPropagates() {
    MessagesFromTextFile messageReader = new MessagesFromTextFile();

    Environment env = mock(Environment.class);
    when(env.getProperty("message.source")).thenThrow(new RuntimeException());
    messageReader.setEnv(env);

    ResourceLoader loader = mock(ResourceLoader.class);
    messageReader.setResourceLoader(loader);

    messageReader.allMessages();
  }

  /**
   * Test that an IOException encountered in reading the text file results in a throw from the
   * reader.
   */
  @Test(expected = RuntimeException.class)
  public void ioExceptionPropagates() throws IOException {
    MessagesFromTextFile messageReader = new MessagesFromTextFile();

    Environment env = mock(Environment.class);
    when(env.getProperty("message.source")).thenReturn("some-particular-message-file.json");
    messageReader.setEnv(env);

    Resource resource = mock(Resource.class);
    when(resource.getInputStream()).thenThrow(IOException.class);

    ResourceLoader loader = mock(ResourceLoader.class);
    when(loader.getResource("some-particular-message-file.json")).thenReturn(resource);
    messageReader.setResourceLoader(loader);

    messageReader.allMessages();
  }

  /**
   * 测试正常解析 demoMessages.json 文件，确保所有字段都能被正确解析。
   */
  @Test
  public void parsesDemoMessagesJsonSuccessfully() throws IOException {
    MessagesFromTextFile messageReader = new MessagesFromTextFile();

    Environment env = mock(Environment.class);
    when(env.getProperty("message.source")).thenReturn("classpath:demoMessages.json");
    messageReader.setEnv(env);

    Resource resource = mock(Resource.class);
    InputStream is = getClass().getClassLoader().getResourceAsStream("demoMessages.json");
    when(resource.getInputStream()).thenReturn(is);

    ResourceLoader loader = mock(ResourceLoader.class);
    when(loader.getResource("classpath:demoMessages.json")).thenReturn(resource);
    messageReader.setResourceLoader(loader);

    // 解析消息
    java.util.List<edu.wisc.my.messages.model.Message> messages = messageReader.allMessages();

    // 校验解析结果
    org.junit.Assert.assertNotNull(messages);
    org.junit.Assert.assertEquals(7, messages.size());

    // 校验第一个消息的字段
    edu.wisc.my.messages.model.Message msg0 = messages.get(0);
    org.junit.Assert.assertEquals("demo-low-priority-no-group-no-date", msg0.getId());
    org.junit.Assert.assertEquals("No group. No date. Low priority.", msg0.getTitle());
    org.junit.Assert.assertEquals("No group. No date. Low priority.", msg0.getTitleShort());
    org.junit.Assert.assertEquals("notification", msg0.getMessageType());
    org.junit.Assert.assertNull(msg0.getPriority());
    org.junit.Assert.assertNotNull(msg0.getFilter());
    org.junit.Assert.assertNull(msg0.getFilter().getGoLiveDate());
    org.junit.Assert.assertNull(msg0.getFilter().getExpireDate());
    org.junit.Assert.assertTrue(msg0.getFilter().getGroups().isEmpty());
    org.junit.Assert.assertNotNull(msg0.getActionButton());
    org.junit.Assert.assertEquals("Go", msg0.getActionButton().getLabel());
    org.junit.Assert.assertEquals("http://www.google.com", msg0.getActionButton().getUrl());

    // 校验第二个消息没有 filter 字段
    edu.wisc.my.messages.model.Message msg1 = messages.get(1);
    org.junit.Assert.assertEquals("demo-no-filter-at-all", msg1.getId());
    org.junit.Assert.assertNull(msg1.getFilter());

    // 校验过期消息
    edu.wisc.my.messages.model.Message expiredMsg = messages.get(2);
    org.junit.Assert.assertEquals("expired-the-day-Elvis-died", expiredMsg.getId());
    org.junit.Assert.assertEquals("1977-08-16", expiredMsg.getFilter().getExpireDate());

    // 校验未来 goLiveDate
    edu.wisc.my.messages.model.Message futureMsg = messages.get(3);
    org.junit.Assert.assertEquals("will-not-go-live-until-Americas-tricentennial", futureMsg.getId());
    org.junit.Assert.assertEquals("2076-07-04", futureMsg.getFilter().getGoLiveDate());

    // 校验 group 校验
    edu.wisc.my.messages.model.Message groupMsg = messages.get(4);
    org.junit.Assert.assertEquals("valid-date-invalid-group", groupMsg.getId());
    org.junit.Assert.assertEquals("2016-07-04", groupMsg.getFilter().getGoLiveDate());
    org.junit.Assert.assertEquals("2080-07-04", groupMsg.getFilter().getExpireDate());
    org.junit.Assert.assertEquals(2, groupMsg.getFilter().getGroups().size());

    // 校验 everyone group
    edu.wisc.my.messages.model.Message everyoneMsg = messages.get(5);
    org.junit.Assert.assertEquals("valid-date-everyone-group", everyoneMsg.getId());
    org.junit.Assert.assertTrue(everyoneMsg.getFilter().getGroups().contains("Everyone"));

    // 校验最后一个消息
    edu.wisc.my.messages.model.Message lastMsg = messages.get(6);
    org.junit.Assert.assertEquals("valid-date-everyone-group", lastMsg.getId());
    org.junit.Assert.assertEquals("Valid date. Everyone group.", lastMsg.getTitle());
  }

}
