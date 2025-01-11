package utils.regData;


import java.text.SimpleDateFormat;
import java.util.Date;
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

    public static String createInvalidUsername() {

        return new SimpleDateFormat("SSS").format(new Date());
    };

    public static String createInvalidEmail() {
        return new SimpleDateFormat("hhmmssSSS").format(new Date()) + "!";
    };

    public static String createInvalidDate() {
        String emptyMail = "   ";
        return emptyMail;
    };

}

