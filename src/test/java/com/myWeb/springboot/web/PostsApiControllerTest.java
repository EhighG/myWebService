package com.myWeb.springboot.web;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.myWeb.springboot.domain.posts.Posts;
import com.myWeb.springboot.domain.posts.PostsRepository;
import com.myWeb.springboot.web.dto.PostsSaveRequestDto;
import com.myWeb.springboot.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// For mockMvc

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @After
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles="USER")
    public void Posts_????????????() throws Exception {
        //given
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        //when
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    @WithMockUser(roles="USER")
    public void Posts_????????????() throws Exception {
        //given
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        Long updateId = savedPosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        //when
        mvc.perform(put(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        //then
        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }
}

//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.myWeb.springboot.domain.posts.Posts;
//import com.myWeb.springboot.domain.posts.PostsRepository;
//import com.myWeb.springboot.web.dto.PostsSaveRequestDto;
//import com.myWeb.springboot.web.dto.PostsUpdateRequestDto;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MockMvcBuilder;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class PostsApiControllerTest {
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private PostsRepository postsRepository;
//
//    @After
//    public void tearDown() throws Exception {
//        postsRepository.deleteAll();
//    }
//
//    private WebApplicationContext context;
//
//    private MockMvc mvc;
//
//    @Before
//    public void setup() {
//        mvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .apply(springSecurity())
//                .build();
//    }
//
//    @Test
//    @WithMockUser(roles="USER") // MockMvc????????? ????????????.
//    public void Posts??????() throws Exception {
//        // given
//        String title = "title";
//        String content = "content";
//        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
//                .title(title)
//                .content(content)
//                .author("author")
//                .build();
//
//        String url = "http://localhost:" + port + "api/v1/posts";
//
//        // when
//        mvc.perform(post(url)
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(new ObjectMapper().writeValueAsString(requestDto)))
//                .andExpect(status().isOk());
//        // requestDto ???????????? post?????? ?????????(??? ?????? Api??? ?????????) ?????? ??????
//        // post?????? ????????? ????????? ResponseEntity??? ??????(PostApiController.save()??? ?????????(saved Post??? Id + ?????? ?????????)
//
//        // then
//        // save() ???????????? ???????????? ??????
////        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
////        assertThat(responseEntity.getBody()).isGreaterThan(0l);
//
//        // repository??? ????????? ??? ??????
//        List<Posts> all = postsRepository.findAll();
//        assertThat(all.get(0).getTitle()).isEqualTo(title);
//        assertThat(all.get(0).getContent()).isEqualTo(content);
//    }
//
//    @Test
//    @WithMockUser(roles="USER")
//    public void Posts????????????() throws Exception {
//        // given
//        Posts savedPosts = postsRepository.save(Posts.builder()
//                .title("title")
//                .content("content")
//                .author("author")
//                .build());
//
//        Long updateId = savedPosts.getId();
//        String newTitle = "title2";
//        String newContent = "content2";
//
//        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
//                .title(newTitle)
//                .content(newContent)
//                .build();
//
//        String url = "http://localhost:" + port + "api/v1/posts/" + updateId; // PostsApiController.update??? id???
//
//        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto); // http request entity
//
//        // when
////        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);
//        // restTemplate.exchange() : ????????? ?????? http request??? ?????? response??? ??????
//        mvc.perform(put(url)
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(new ObjectMapper().writeValueAsString(requestDto)))
//                .andExpect(status().isOk());
//
//
//        // then
////        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
////        assertThat(responseEntity.getBody()).isGreaterThan(0L);
//
//        List<Posts> all = postsRepository.findAll();
//        assertThat(all.get(0).getTitle()).isEqualTo(newTitle);
//        assertThat(all.get(0).getContent()).isEqualTo(newContent);
//    }
//
//    @Test
//    public void BaseTimeEntity??????() {
//        // given
//        LocalDateTime now = LocalDateTime.of(2022,8,5,0,0,0);
//        postsRepository.save(Posts.builder()
//                .title("title")
//                .content("content")
//                .author("author")
//                .build());
//        // when
//        List<Posts> postsList = postsRepository.findAll();
//
//        // then
//        Posts posts = postsList.get(0);
//
//        System.out.println(">>>>>>>>> createDate="+posts.getCreatedDate() + ", modifiedDate="+posts.getModifiedDate());
//
//        assertThat(posts.getCreatedDate()).isAfter(now);
//        assertThat(posts.getModifiedDate()).isAfter(now);
//    }
//}