package utils.regData;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RegistrationDataGenerator {
    public static String createUser() {

        return new SimpleDateFormat("SSS").format(new Date()) + "Student";
    };

    public static String createEmail() {

        return new SimpleDateFormat("hhmmssSSS").format(new Date()) + "@abv.bg";
    };

    public static String createDate(){
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

                Date startDate = formatter.parse("01/01/1920");
                Date endDate = new Date();

                long randomMillis = ThreadLocalRandom.current().nextLong(startDate.getTime(), endDate.getTime());

                Date randomDate = new Date(randomMillis);

                return formatter.format(randomDate);

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
    }

    public static String createInvalidEmail() {
        return new SimpleDateFormat("hhmmssSSS").format(new Date()) + "!";
    };

    public static String createPassword(int length) {
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String allCharacters = upperCaseLetters + lowerCaseLetters + digits;

        Random random = new Random();
        StringBuilder pass = new StringBuilder();

        pass.append(upperCaseLetters.charAt(random.nextInt(upperCaseLetters.length())));
        pass.append(lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length())));
        pass.append(digits.charAt(random.nextInt(digits.length())));

        for (int i = 3; i < length; i++) {
            pass.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }

        return pass.toString();
    };

    public static String generateRandomPublicInfo(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+-=[]{}|;':\",.<>?/`~ \n";
        StringBuilder randomPublicInfo = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            randomPublicInfo.append(characters.charAt(random.nextInt(characters.length())));
        }

        return randomPublicInfo.toString();
    }
    public static Map<String, String> fillRegistrationForm(String invalidField, String invalidValue) {
        Faker faker = new Faker();

        String username = faker.name().username();
        String email = faker.internet().emailAddress();
        String date = "01/22/2025";
        String password = faker.internet().password(6, 20, true, true, true);
        String confirmPassword = password;
        String publicInfo = faker.lorem().sentence();

        switch (invalidField) {
            case "username":
                username = invalidValue;
                break;
            case "email":
                email = invalidValue;
                break;
            case "date":
                date = invalidValue;
                break;
            case "password":
                password = invalidValue;
                confirmPassword = invalidValue;
                break;
            case "confirmPassword":
                confirmPassword = invalidValue;
                break;
            case "publicInfo":
                publicInfo = invalidValue;
                break;
        }

        Map<String, String> data = new HashMap<>();
        data.put("username", username);
        data.put("email", email);
        data.put("date", date);
        data.put("password", password);
        data.put("confirmPassword", confirmPassword);
        data.put("publicInfo", publicInfo);

        return data;
    }
}

