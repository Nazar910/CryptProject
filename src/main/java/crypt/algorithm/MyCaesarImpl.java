package crypt.algorithm;


/**
 * Created by nazar on 16/02/17.
 */
public class MyCaesarImpl extends SymmetricAlgorithm{
    public MyCaesarImpl(String alphabet, int key) {
        super(alphabet, key);
    }

    public String encrypt(String plainText) {
        return encrypt(plainText, this.key);
    }

    public String encrypt(String plainText, int newKey) {
        if (!checkText(plainText)) throw new RuntimeException("There is now such letter in alphabet");
        StringBuilder encoded = new StringBuilder();
        for (char i : plainText.toCharArray()) {
            if (Character.isLetter(i)) {
                encoded.append(alphabet.charAt((indexInAlphabet(i) + newKey) % alphabet.length() ));
            } else {
                encoded.append(i);
            }
        }
        return encoded.toString();
    }

    public String decrypt(String encryptedText) {
        return null;
    }

    public String decrypt(String encryptedText, int newKey) {
        return null;
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
