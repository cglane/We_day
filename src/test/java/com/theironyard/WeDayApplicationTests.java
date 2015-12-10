package com.theironyard;
import com.theironyard.Entities.Photo;
import com.theironyard.Entities.Post;
import com.theironyard.Entities.User;
import com.theironyard.Entities.Wedding;
import com.theironyard.Services.PostRepository;
import com.theironyard.Services.UserRepository;
import com.theironyard.Services.WeddingRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WeDayApplication.class)
@WebAppConfiguration
public class WeDayApplicationTests {

    @Autowired
    WeddingRepository weddings;

    @Autowired
    UserRepository users;

    @Autowired
    PostRepository posts;

    @Autowired
    WebApplicationContext wap;

    MockMvc mockMvc;

    @Before
    public void before() {
        weddings.deleteAll();
        users.deleteAll();
        mockMvc = MockMvcBuilders.webAppContextSetup(wap).build();
    }

    @Test
    public void createWedding() throws Exception {
        Wedding wedding = new Wedding();
        wedding.date = "2014-01-01";
        wedding.location = "Charelston";
        wedding.weddingName = "Our Wedding";

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(wedding);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/create-wedding")
                        .content(json)
                        .contentType("application/json")
        );

        assertTrue(weddings.count() == 1);
    }

    @Test
    public void createAdmin() throws Exception {
        User user = new User();
        user.password = "1234";
        user.username = "Nathan";
        user.address = "123 Fake Street";
        user.phone = "123- 456-789";
        user.email = "user@hotmail.com";
        user.zip = "4321";
        user.isAdmin = true;

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(user);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/create-admin")
                        .content(json)
                        .contentType("application/json")
        );

        assertTrue(users.count() == 1);
    }

    @Test
    public void createPost() throws Exception {
        Post post = new Post();
        post.text = "This is a message";
        post.sender = "Nathan";

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(post);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/create-post")
                        .content(json)
                        .contentType("application/json")
                        .sessionAttr("username", "testUserName")
        );
        assertTrue(posts.count() == 1);
    }
}