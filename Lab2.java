import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Варіант 7 
// String
// Відсортувати слова заданого тексту, що починаються з голосних літер, за другою літерою. 

public class Lab2 {

    public static void main(String[] args) {
        try {
            String text = "In my restless dreams, I see that town. Silent Hill. You promised you'd take me there again someday. But you never did. Well, I'm alone there now…";

            List<String> sortedWords = sortVowelWordsBySecondLetter(text);

            System.out.println("Sorted words starting with vowels: " + sortedWords);
        } catch (Exception e) {
            System.err.println("An error occurred while processing the text: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // сортування за другою літерою
    private static List<String> sortVowelWordsBySecondLetter(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input text cannot be null or empty");
        }

        String[] words = input.split("\\s+");

        List<String> vowelWords = new ArrayList<>();

        for (String word : words) {
            if (!word.isEmpty() && startsWithVowel(word)) {
                vowelWords.add(word);
            }
        }

        Collections.sort(vowelWords, new Comparator<String>() {
            @Override
            public int compare(String w1, String w2) {
                char c1 = (w1.length() > 1) ? w1.charAt(1) : '\0';
                char c2 = (w2.length() > 1) ? w2.charAt(1) : '\0';
                return Character.compare(c1, c2);
            }
        });

        return vowelWords;
    }

    // пошук слів, що починаються на голосну
    private static boolean startsWithVowel(String word) {
        char firstChar = Character.toLowerCase(word.charAt(0));
        return firstChar == 'a' || firstChar == 'e' || firstChar == 'i' || firstChar == 'o' || firstChar == 'u';
    }
}
