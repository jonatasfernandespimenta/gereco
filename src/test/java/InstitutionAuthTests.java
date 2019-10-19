import helpers.InstitutionAuth;
import models.Institution;
import org.junit.Test;
import org.mockito.Mockito;
import services.InstitutionService;

import static helpers.InstitutionAuth.encryptPassword;
import static junit.framework.TestCase.*;

public class InstitutionAuthTests {
    private static final String PASSWORD = "1234";
    private static final String ENCRYPTED_PASSWORD = "28970aab014cf552b44cf480a4e9a9db47cfc1d6ecd29d8a23a8b4a0aba7395f33ca1695fde9f9" +
            "dd36aa3f4eb4404c02a29454fb47632d68f16e26ffeb7bed42";

    private InstitutionService institutionService = Mockito.mock(InstitutionService.class);

    private InstitutionAuth institutionAuth = new InstitutionAuth(institutionService);

    @Test
    public void encyptPassword(){
        assertEquals(ENCRYPTED_PASSWORD, encryptPassword(PASSWORD));
    }

    @Test
    public void successLogin(){
        String institutionEmail = "etec@etec.com";
        Institution institution = new Institution("institution name", institutionEmail, ENCRYPTED_PASSWORD);
        Mockito.when(institutionService.findByEmail(institutionEmail)).thenReturn(institution);

        assertTrue(institutionAuth.login(institutionEmail, PASSWORD));
    }

    @Test
    public void failureLogin(){
        String institutionEmail = "etec@etec.com";
        String incorrectInstitutionPassword = "12345";
        Institution institution = new Institution("institution name", institutionEmail, ENCRYPTED_PASSWORD);
        Mockito.when(institutionService.findByEmail(institutionEmail)).thenReturn(institution);

        assertFalse(institutionAuth.login(institutionEmail, incorrectInstitutionPassword));
    }

    @Test
    public void validatePasswordAndReturnMessage_whenPasswordIsEmpty() {
        String password = "";
        String confirmPassword = "1234";

        assertEquals("Fill in the fields!"
                ,InstitutionAuth.validatePasswordAndReturnMessage(password, confirmPassword));
    }

    @Test
    public void validatePasswordAndReturnMessage_whenConfirmPasswordIsEmpty() {
        String password = "123";
        String confirmPassword = "";

        assertEquals("Fill in the fields!"
                ,InstitutionAuth.validatePasswordAndReturnMessage(password, confirmPassword));
    }

    @Test
    public void validatePasswordAndReturnMessage_whenPasswordIsLessThanSix() {
        String password = "123";
        String confirmPassword = "123";

        assertEquals("Password must contain at least 6 characters!"
                ,InstitutionAuth.validatePasswordAndReturnMessage(password, confirmPassword));
    }

    @Test
    public void validatePasswordAndReturnMessage_whenPasswordDoesNotContainSpecialChar() {
        String password = "123567";
        String confirmPassword = "123567";

        assertEquals("Password must contain a special character!"
                ,InstitutionAuth.validatePasswordAndReturnMessage(password, confirmPassword));
    }

    @Test
    public void validatePasswordAndReturnMessage_whenPasswordDoesNotContainUpperCase() {
        String password = "123567!";
        String confirmPassword = "123567!";

        assertEquals("Password must contain a uppercase letter!"
                ,InstitutionAuth.validatePasswordAndReturnMessage(password, confirmPassword));
    }

    @Test
    public void validatePasswordAndReturnMessage_whenPasswordDoesNotContainNumber() {
        String password = "ThisIsATestPassword!";
        String confirmPassword = "ThisIsATestPassword!";

        assertEquals("Password must contain a number!"
                ,InstitutionAuth.validatePasswordAndReturnMessage(password, confirmPassword));
    }

    @Test
    public void validatePasswordAndReturnMessage_whenPasswordsDoNotMatch() {
        String password = "ThisIsATestPassword1!";
        String confirmPassword = "ThisIsATestPassword2";

        assertEquals("The passwords are different!"
                ,InstitutionAuth.validatePasswordAndReturnMessage(password, confirmPassword));
    }

    @Test
    public void validatePasswordAndReturnMessage_whenPasswordMeetsReq() {
        String password = "HulkAngry123!";
        String confirmPassword = "HulkAngry123!";

        assertNull("Warning should be null."
                , InstitutionAuth.validatePasswordAndReturnMessage(password, confirmPassword));
    }
}
