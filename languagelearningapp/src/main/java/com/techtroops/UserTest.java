import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserTest {
    private User user;
    void setUp() {
        
        user = new User();
        user.setEmail("sdffsdfdsfsd");
    }
    @Test
    void testGetAchievementDates() {

    }

    @Test
    void testGetAchievements() {

    }

    @Test
    void testGetEmail() {
        
            // Set email
            String testEmail = "test@example.com";
            user.setEmail(testEmail);
            
            // Test if getEmail returns the correct email
            assertEquals(testEmail, user.getEmail(), "getEmail should return the correct email address");

    }

    @Test
    void testGetPassword() {

        user = new User();
        String testpas ="dsdsfsdfdsfds";
        user.setPassword("fsdfsdfsd");
        assertEquals("fsdfsdfsd", user.getPassword());

    }

    @Test
    void testGetPreferredLanguage() {

    }

    @Test
    void testGetProgressReport() {

    }

    @Test
    void testGetProgressTrackers() {

    }

    @Test
    void testGetUser() {

    }

    @Test
    void testGetUserID() {

    }

    @Test
    void testGetUsername() {

    }

    @Test
    void testLogin() {

    }

    @Test
    void testRegister() {

    }

    @Test
    void testSetAchievementDates() {

    }

    @Test
    void testSetAchievements() {

    }

    @Test
    void testSetEmail() {

    }

    @Test
    void testSetPassword() {

    }

    @Test
    void testSetPreferredLanguage() {

    }

    @Test
    void testSetProgressTrackers() {

    }

    @Test
    void testSetUserID() {

    }

    @Test
    void testSetUsername() {

    }
}
