package nazar.pyvovar.crypt.algorithm.caesar;

import nazar.pyvovar.crypt.algorithm.IndexCalculator;
import nazar.pyvovar.crypt.algorithm.NoSuchLetterException;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;

/**
 * Created by pyvov on 19.02.2017.
 */
public class CaesarImpl implements CryptAlgorithm {
    private final String alphabet;
    private final int key;

    public CaesarImpl(String alphabet, int key) {
        this.alphabet = alphabet;
        this.key = key;
    }

    @Override
    public String encrypt(String plainText) throws NoSuchLetterException {
        return encrypt(plainText, this.alphabet, this.key);
    }

    @Override
    public String encrypt(String plainText, String alphabet) throws NoSuchLetterException {
        return encrypt(plainText, alphabet, this.key);
    }

    @Override
    public String encrypt(String plainText, int newKey) throws NoSuchLetterException {
        return encrypt(plainText, this.alphabet, newKey);
    }

    @Override
    public String encrypt(String plainText, String alphabet, int newKey) throws NoSuchLetterException {
        return crypt(plainText, alphabet, newKey,
                (c, key) -> (indexInAlphabet(c, alphabet) + key) % alphabet.length());
    }

    @Override
    public String decrypt(String encryptedText, int newKey) throws NoSuchLetterException {
        return decrypt(encryptedText, this.alphabet, newKey);
    }

    @Override
    public String decrypt(String encryptedText, String alphabet, int newKey) throws NoSuchLetterException {
        return crypt(encryptedText, alphabet, newKey,
                (c, key) -> (indexInAlphabet(c, alphabet) - key + alphabet.length()) % alphabet.length());
    }

    @Override
    public Collection<String> bruteForce(String encryptedText) throws NoSuchLetterException {
        return bruteForce(encryptedText, this.alphabet);
    }

    @Override
    public Collection<String> bruteForce(String encryptedText, String alphabet) throws NoSuchLetterException {
        String[] result = new String[alphabet.length() - 1];
        for (int key = 1; key < alphabet.length(); key++) {
            result[key - 1] = decrypt(encryptedText, alphabet, key);
        }
        return Arrays.asList(result);
    }

    public boolean isEnglish() {
        return Pattern.compile("[A-Za-z]*").matcher(alphabet).matches();
    }

    private String crypt(String text, String alphabet, int newKey, IndexCalculator indexCalculator) throws NoSuchLetterException {
        if (!checkText(text, alphabet)) throw new NoSuchLetterException("There is no such letter in alphabet");
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                int index = indexCalculator.calculate(c, newKey);
                result.append(alphabet.charAt(index));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    private int indexInAlphabet(char c, String alphabet) {
        for (int i = 0; i < alphabet.length(); i++) {
            if (alphabet.charAt(i) == c) {
                return i;
            }
        }
        return -1;
    }

    private boolean checkText(String plaintText, String  alphabet) {
        for (char c : plaintText.toCharArray()) {
            if (Character.isLetter(c) && alphabet.indexOf(c) == -1) return false;
        }
        return true;
    }


}
