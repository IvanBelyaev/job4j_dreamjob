package ru.job4j.dream.servlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.MemStore;
import ru.job4j.dream.store.PsqlStore;
import ru.job4j.dream.store.Store;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PsqlStore.class)
public class PostServletTest {
    /**
     * Test for doPost method.
     * @throws ServletException possible exception.
     * @throws IOException possible exception.
     */
    @Test
    public void whenAddPostThenStoreIt() throws ServletException, IOException {
        Store store = MemStore.instOf();
        PowerMockito.mockStatic(PsqlStore.class);
        when(PsqlStore.instOf()).thenReturn(store);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("1");
        when(req.getParameter("name")).thenReturn("John");
        when(req.getParameter("desc")).thenReturn("new user");
        new PostServlet().doPost(req, resp);
        assertThat(store.findPostById(1).getName(), is("John"));
    }

    /**
     * Test for doGet method.
     * @throws ServletException possible exception.
     * @throws IOException possible exception.
     */
    @Test
    public void whenDoGetThenPostsAttributeMustBeSet() throws ServletException, IOException {
        Store store = MemStore.instOf();
        PowerMockito.mockStatic(PsqlStore.class);
        when(PsqlStore.instOf()).thenReturn(store);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher reqDispatcher = mock(RequestDispatcher.class);
        when(req.getRequestDispatcher(anyString())).thenReturn(reqDispatcher);

        // Collection to store attributes keys/values
        final Map<String, Object> attributes = new ConcurrentHashMap<String, Object>();
        // Mock setAttribute
        Mockito.doAnswer(invocation -> {
                String key = invocation.getArgument(0);
                Object value = invocation.getArgument(1);
                attributes.put(key, value);
                return null;
        }).when(req).setAttribute(anyString(), Mockito.any());
        new PostServlet().doGet(req, resp);
        Collection<Post> posts = (Collection<Post>) attributes.get("posts");
        Iterator<Post> iter = posts.iterator();
        assertThat(attributes.size(), is(1));
        assertThat(posts.size(), is(3));
        assertThat(iter.next().getName(), is("Junior Java Job"));
        assertThat(iter.next().getName(), is("Middle Java Job"));
        assertThat(iter.next().getName(), is("Senior Java Job"));
    }
}
