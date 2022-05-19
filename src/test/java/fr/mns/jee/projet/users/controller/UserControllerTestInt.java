package fr.mns.jee.projet.users.controller;

import fr.mns.jee.projet.model.Gender;
import fr.mns.jee.projet.model.User;
import fr.mns.jee.projet.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URL;
import java.time.LocalDate;

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

}
