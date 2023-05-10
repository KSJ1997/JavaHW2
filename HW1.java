import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class HW1 {
    private static final Logger LOGGER = Logger.getLogger(HW1.class.getName());

    public static void main(String[] args) {
        String jsonString = "[{ \"фамилия\":\"Иванов\",\"оценка\":\"5\",\"предмет\":\"Математика\"},{\"фамилия\":\"Петрова\",\"оценка\":\"4\",\"предмет\":\"Информатика\"},{\"фамилия\":\"Краснов\",\"оценка\":\"5\",\"предмет\":\"Физика\"}]";
        String[] students = jsonString.replace("[{", "").replace("}]", "").split("\\},\\{");

        StringBuilder sb = new StringBuilder();
        for (String student : students) {
            String[] values = student.split(",");
            String surname = values[0].substring(values[0].indexOf(":") + 2, values[0].length() - 1);
            String grade = values[1].substring(values[1].indexOf(":") + 2);
            String subject = values[2].substring(values[2].indexOf(":") + 2, values[2].length() - 1);
            sb.append("Студент ").append(surname).append(" получил ").append(grade).append(" по предмету ")
                    .append(subject).append(".\n");
        }
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream("output.txt"), StandardCharsets.UTF_8))) {
            writer.write(sb.toString());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Ошибка записи в файл", e);
            try {
                FileHandler fileHandler = new FileHandler("log.txt");
                LOGGER.addHandler(fileHandler);
                SimpleFormatter formatter = new SimpleFormatter();
                fileHandler.setFormatter(formatter);
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, "Ошибка записи в лог-файл", ex);
            }
        }
    }
}