package Utils;

import java.util.Random;

public class RandomUserGenerator {
    private static final String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int MIN_USERNAME_LENGTH = 4;
    private static final int MAX_USERNAME_LENGTH = 15;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static Random random;
    public static String generateRandomUsername() {
        random = new Random();
        int length = random.nextInt(MAX_USERNAME_LENGTH - MIN_USERNAME_LENGTH + 1) + MIN_USERNAME_LENGTH;
        StringBuilder username = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALLOWED_CHARACTERS.length());
            username.append(ALLOWED_CHARACTERS.charAt(index));
        }

        return username.toString();
    }

    public static String generateRandomPassword() {
        random = new Random();
        int length = random.nextInt(MAX_USERNAME_LENGTH - MIN_USERNAME_LENGTH + 1) + MIN_PASSWORD_LENGTH;
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALLOWED_CHARACTERS.length());
            password.append(ALLOWED_CHARACTERS.charAt(index));
        }

        return password.toString();
    }
}