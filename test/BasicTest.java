import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

    @Test
    public void aVeryImportantThingToTest() {
        assertEquals(2, 1 + 1);
    }

    }

    public void pruebaUsuario(){
        assertNotNull(Usuario.ByEmail("juan@hotmail.com"));
        assertNotNull(Usuario.ByEmailAndPassword("juan"));

    }