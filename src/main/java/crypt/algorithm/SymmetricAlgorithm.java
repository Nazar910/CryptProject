package crypt.algorithm;


/**
 * Created by nazar on 16/02/17.
 */
public abstract class SymmetricAlgorithm {
    protected String alphabet;
    protected int key;

    public SymmetricAlgorithm(String alphabet, int key) {
        this.alphabet = alphabet;
        this.key = key;
    }

    public void setAlphabet(String alphabet) {
        this.alphabet = alphabet;
    }

    public abstract String encrypt(String plainText);
    public abstract String encrypt(String plainText, int newKey);
    public abstract String decrypt(String encryptedText);
    public abstract String decrypt(String encryptedText, int newKey);
}

