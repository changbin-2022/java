import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Варіант 7 
// String
// Відсортувати слова заданого тексту, що починаються з голосних літер, за другою літерою. 

public class Lab4 {

    public static void main(String[] args) {
        try {
            String inputText = "In my restless dreams, I see that town. Silent Hill. You promised you'd take me there again someday. But you never did. Well, I'm alone there now…";

            // створюємо об’єкт тексту
            Text text = new Text(inputText);

            // знаходимо всі слова, що починаються на голосну
            List<Word> vowelWords = text.getWordsStartingWithVowel();

            // Сортуємо їх за другою літерою
            Collections.sort(vowelWords, new Comparator<Word>() {
                @Override
                public int compare(Word w1, Word w2) {
                    char c1 = w1.getSecondLetter();
                    char c2 = w2.getSecondLetter();
                    return Character.compare(c1, c2);
                }
            });

            // вивід результату
            System.out.println("Sorted words starting with vowels:");
            for (Word word : vowelWords) {
                System.out.println(word);
            }

        } catch (Exception e) {
            System.err.println("Помилка при обробці тексту: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

// клас Letter - представляє одну літеру
class Letter {
    private char value; // символ літери

    public Letter(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

// клас Punctuation - представляє розділовий знак
class Punctuation {
    private char symbol;

    public Punctuation(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }
}

// клас Word - представляє слово, що складається з масиву літер

class Word {
    private List<Letter> letters;

    public Word(String word) {
        letters = new ArrayList<>();
        for (char ch : word.toCharArray()) {
            if (Character.isLetter(ch)) {
                letters.add(new Letter(ch));
            }
        }
    }

    // повертає слово у вигляді рядка
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Letter l : letters) {
            sb.append(l.getValue());
        }
        return sb.toString();
    }

    // перевіряє, чи слово починається на голосну
    public boolean startsWithVowel() {
        if (letters.isEmpty()) return false;
        char firstChar = Character.toLowerCase(letters.get(0).getValue());
        return "aeiou".indexOf(firstChar) >= 0;
    }

    // повертає другу літеру або символ 0, якщо слово коротше за 2 символи
    public char getSecondLetter() {
        if (letters.size() < 2) return '\0';
        return Character.toLowerCase(letters.get(1).getValue());
    }
}

// клас Sentence — представляє речення, що складається зі слів та розділових знаків
class Sentence {
    private List<Object> parts; // може містити Word або Punctuation

    public Sentence(String sentence) {
        parts = new ArrayList<>();
        String[] tokens = sentence.split(" ");
        for (String token : tokens) {
            if (token.isEmpty()) continue;
            char lastChar = token.charAt(token.length() - 1);
            if (isPunctuation(lastChar)) {
                String wordPart = token.substring(0, token.length() - 1);
                if (!wordPart.isEmpty()) {
                    parts.add(new Word(wordPart));
                }
                parts.add(new Punctuation(lastChar));
            } else {
                parts.add(new Word(token));
            }
        }
    }

    // перевіряє, чи символ є розділовим знаком
    private boolean isPunctuation(char c) {
        return ",.!?;:…".indexOf(c) >= 0;
    }

    // повертає всі слова з речення
    public List<Word> getWords() {
        List<Word> words = new ArrayList<>();
        for (Object part : parts) {
            if (part instanceof Word) {
                words.add((Word) part);
            }
        }
        return words;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object part : parts) {
            sb.append(part.toString()).append(" ");
        }
        return sb.toString().trim();
    }
}

// клас Text — представляє весь текст, що складається з масиву речень
class Text {
    private List<Sentence> sentences;

    public Text(String rawText) {
        // очищаємо текст від зайвих пробілів і табуляцій
        String cleanedText = rawText.replaceAll("[\\t ]+", " ");
        String[] sentenceArray = cleanedText.split("(?<=[.!?])\\s*");
        sentences = new ArrayList<>();
        for (String s : sentenceArray) {
            if (!s.trim().isEmpty()) {
                sentences.add(new Sentence(s.trim()));
            }
        }
    }

    // повертає всі слова, що починаються з голосної літери
    public List<Word> getWordsStartingWithVowel() {
        List<Word> result = new ArrayList<>();
        for (Sentence sentence : sentences) {
            for (Word word : sentence.getWords()) {
                if (word.startsWithVowel()) {
                    result.add(word);
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Sentence s : sentences) {
            sb.append(s.toString()).append(" ");
        }
        return sb.toString().trim();
    }
}
