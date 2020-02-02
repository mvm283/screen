package utiles;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class urlChecker {
    public static boolean IsMatch(String s, String pattern) {
        try {
            Pattern patt = Pattern.compile(pattern);
            Matcher matcher = patt.matcher(s);
            return matcher.matches();
        } catch (RuntimeException e) {
            return false;
        }
    }
}
