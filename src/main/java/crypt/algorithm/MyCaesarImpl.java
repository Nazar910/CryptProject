package crypt.algorithm;


/**
 * Created by nazar on 16/02/17.
 */
public class MyCaesarImpl extends SymmetricAlgorithm {

    public MyCaesarImpl(String alphabet, int key) {
        super(alphabet, key);
    }

    public String encrypt(String plainText) {
        return encrypt(plainText, this.key);
    }

    public String encrypt(String plainText, int newKey) {
        return crypt(plainText, newKey,
                (c, key) -> (indexInAlphabet(c) + key) % alphabet.length());
    }

    public String decrypt(String encryptedText) {
        //in case we are sure that text encrypted with Caesar Cipher code
        char c = encryptedText.charAt(0);
        return null;
    }

    public String decrypt(String encryptedText, int key) {
        return crypt(encryptedText, key,
                (c, _key) -> (indexInAlphabet(c) - _key) % alphabet.length());
    }

    private String crypt(String text, int newKey, IndexCalculator indexCalculator) {
        if (!checkText(text)) throw new RuntimeException("There is no such letter in alphabet");
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                result.append(alphabet.charAt(indexCalculator.calculate(c, newKey)));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    private int indexInAlphabet(char c) {
        for (int i = 0; i < alphabet.length(); i++) {
            if (alphabet.charAt(i) == c) {
                return i;
            }
        }
        return -1;
    }

    private boolean checkText(String plaintText) {
        for (char c : plaintText.toCharArray()) {
            if (alphabet.indexOf(c) == -1) return false;
        }
        return true;
    }
}


