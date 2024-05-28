import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите строку, содержащую параметры -> <Фамилия>,<Имя>,<Отчество>,<датарождения>,<номертелефона>,<пол>. \n" +
                "Заданные параметры должны вводиться через пробел.\n" +
                "Строка дата должна быть задана в формате dd.mm.yyyy, номер телефона должен содержать 11 цифр, а пол должен быть задан буквой f или m!");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String[] fields = input.split(" ");
        if(fields.length != 6){
            System.err.println("Неверное кол-во полей, либо отсутствовали пробелы в строке!");
        }
        String surname = fields[0];
        String firstName = fields[1];
        String lastName = fields[2];

        SimpleDateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
        Date date;
        try{
            date = formater.parse(fields[3]);
        } catch (ParseException e) {
            System.err.println("Неверный формат даты!");
            return;
        }

        if(fields[4].length() != 11){
            System.err.println("Цифр не 11!");
        }
        Long phoneNumber;
        try {
            phoneNumber = Long.parseLong(fields[4]);

        } catch (NumberFormatException e) {
            System.err.println("Неверный формат номера");
            return;
        }

        String sex = fields[5];
        if(!Objects.equals(sex, "f") && !Objects.equals(sex, "m")) {
            System.err.println("Неверный ввод пола! Поле <пол> может содержать только f и m!");
        }
        String pathFile = surname + ".txt";

        try (FileWriter writer = new FileWriter(pathFile, true)) {
            writer.write(surname + " " + firstName + " " + lastName + " " + formater.format(date) + " " + phoneNumber + " " + sex);
            writer.write('\n');
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}