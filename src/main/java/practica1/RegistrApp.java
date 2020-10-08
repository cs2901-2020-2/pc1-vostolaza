package practica1;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class RegistrApp {
    List<Cliente> clients = new ArrayList<>();
    String professor;
    public RegistrApp(String _professor){professor = _professor;}

    public void addClient(Cliente client){
        clients.add(client);
    }

    public void registerClass(String title, String link) throws Exception {
        validateSemester(title.split(",")[0].split(" ")[0]);
        validateCourse(title.split(",")[0].split(" ")[1]);
        validateLanguage(title.split(",")[0].split(" ")[2]);
//        String section = title.split(",")[1];
        validateWeek(title.split(",")[2]);
//        String prof = title.split(",")[3];
        String date = title.split(",")[4].split(" ")[0];
        String time = title.split(",")[4].split(" ")[1];
        String type = title.split(",")[4].split(" ")[2];


        for (Cliente client : clients){
            client.notify(title, link);
        }
    }

    void validateWeek(String week) throws Exception {
        if (week.length() != 8) {throw new WeekFormatException("Invalid week lenght");}
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(week.charAt(i));
        }
        if (!sb.toString().equals("Semana")) {throw new WeekFormatException("Invalid week format");}
    }

    void validateLanguage(String lang) throws Exception{
        if (!lang.equals("EN") || !lang.equals("ES")) {throw new InvalidLanguageException("Invalid language tag");}
    }

    void validateCourse(String course) throws Exception{
        if (course.length() != 6) {throw new CourseLengthException("Invalid course code length");}
    }

    void validateSemester(String semester) throws Exception{
        if (semester.length() != 7) {throw new SemesterFormatException("Invalid semester length");}
        if (semester.charAt(4) != '-') {throw new SemesterFormatException("Invalid semester separator");}
        if (semester.charAt(5) != 'I' || semester.charAt(6) != 'I') {throw new SemesterFormatException("Invalid semester");}
    }

}