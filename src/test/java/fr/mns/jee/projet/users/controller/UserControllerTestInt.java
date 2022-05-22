package fr.mns.jee.projet.users.controller;

import fr.mns.jee.projet.dto.EditRequestDTO;
import fr.mns.jee.projet.model.Gender;
import fr.mns.jee.projet.model.User;
import fr.mns.jee.projet.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.constraints.AssertTrue;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTestInt {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @Test
    public void testGet() throws Exception {
        //given
        User user = new User();
        user.setFirstName("Prenom-test");
        user.setLastName("Nom-test");
        user.setGender(Gender.MALE);
        user.setBirthDate(LocalDate.MIN);
        user.setPhoneNumber("0504030201");
        userService.save(user);

        //when
        ResponseEntity<User> response = restTemplate
                .getForEntity(new URL("http://localhost:" + port + "/api/users/"+user.getId()).toString(), User.class);

        //then
        assertNotNull(response.getBody());
        assertEquals(user.getId(), response.getBody().getId());
        assertEquals("Prenom-test", response.getBody().getFirstName());
    }

    @Test
    public void getUserShouldNotFound() throws Exception {
        //when
        ResponseEntity<User> response = restTemplate
                .getForEntity(new URL("http://localhost:" + port +
                        "/api/users/10000").toString(), User.class);

        //then
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    public void testCreateUser() throws Exception {
        //given
        EditRequestDTO user = new EditRequestDTO();
        user.setFirstName("Prenom-test");
        user.setLastName("Nom-test-createuser");
        user.setUsername("testuser");
        user.setGender(Gender.MALE);
        user.setBirthDate(LocalDate.MIN);
        user.setPhoneNumber("0504030201");
        user.setEmail("mns@gmail.fr");
        user.setPassword("azeet");

        //when
        ResponseEntity<?> response = restTemplate
                .postForEntity(new URL("http://localhost:" + port + "/api/users").toString(), user, String.class);

        //then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        User user2 = userService.findByUsername("Nomtest-createPerson");
        assertNotNull(user);
        assertNotNull(response);
    }

    @Test
    public void testUpdate() throws Exception {
        // given
        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setFirstName("Prenom-test");
        savedUser.setLastName("Nom-test");
        savedUser.setUsername("testuserupdate");
        savedUser.setGender(Gender.MALE);
        savedUser.setBirthDate(LocalDate.MIN);
        savedUser.setPhoneNumber("0504030201");
        savedUser.setEmail("test@gmail.fr");
        savedUser.setPassword("azeet");
        userService.save(savedUser);

        EditRequestDTO updatedUser = new EditRequestDTO();
        updatedUser.setFirstName("Prenom-updated");
        updatedUser.setLastName("Nom-updated");
        updatedUser.setUsername("testuserupdate");
        updatedUser.setGender(Gender.MALE);
        updatedUser.setBirthDate(LocalDate.MIN);
        updatedUser.setPhoneNumber("0504030201");
        updatedUser.setEmail("test@gmail.fr");
        updatedUser.setPassword("azeet");

        // when
        restTemplate.put("http://localhost:" + port + "/api/users/" + savedUser.getId(), updatedUser);

        ResponseEntity<User> response = restTemplate
                .getForEntity(new URL("http://localhost:" + port + "/api/users/" + savedUser.getId()).toString(), User.class);

        // then
        assertNotNull(response.getBody());
        assertEquals("Prenom-updated", response.getBody().getFirstName());
    }

    @Test
    public void testDelete() throws Exception {
        // given
        User user = new User();
        user.setId(10L);
        user.setFirstName("Prenom-test");
        user.setLastName("Nom-test");
        user.setUsername("testuserdelete");
        user.setGender(Gender.MALE);
        user.setBirthDate(LocalDate.MIN);
        user.setPhoneNumber("0504030201");
        user.setEmail("test2@gmail.fr");
        user.setPassword("azeet");
        userService.save(user);

        //when
        restTemplate.delete("http://localhost:" + port + "/api/users/" + user.getId());
        ResponseEntity<User> response = restTemplate
                .getForEntity(new URL("http://localhost:" + port + "/api/users/" + user.getId()).toString(), User.class);

        //then
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);

    }

}
