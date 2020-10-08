package practica1;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

@Test
public class RegistrAppTest extends ReadFileTest{

    public void correctInput() throws Exception {
        generic(0);
    }

    @Test(expectedExceptions = SemesterFormatException.class)
    public void invalidSemester() throws Exception {
        generic(1);
    }

    private void generic(int i) throws Exception {
        Logger logger = Logger.getLogger(Cliente.class.getName());
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();

        List<String> input = readInput(i);
        List<String> output = readOutput(i);
        Cliente ce2a = new Cliente("CE2A");
        Cliente dga = new Cliente("DGA");
        Cliente counterDocentes = new Cliente("Counter Docentes");
        Cliente counterAlumnos = new Cliente("Counter Alumnos");
        RegistrApp session = new RegistrApp(input.get(0));
        session.addClient(ce2a);
        session.addClient(dga);
        session.addClient(counterDocentes);
        session.addClient(counterAlumnos);
        session.registerClass(input.get(1), input.get(2));

        List<ILoggingEvent> logsList = listAppender.list;

        Assert.assertEquals(output.get(0), logsList.get(0).getMessage());
        Assert.assertEquals(output.get(1), logsList.get(1).getMessage());
        Assert.assertEquals(output.get(2), logsList.get(2).getMessage());
        Assert.assertEquals(output.get(3), logsList.get(3).getMessage());
    }
}
