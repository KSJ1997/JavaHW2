import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class HW1 {
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

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            writer.write(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
            // Запись ошибки в лог-файл
            try (BufferedWriter logWriter = new BufferedWriter(new FileWriter("log.txt"))) {
                logWriter.write(e.getMessage());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
