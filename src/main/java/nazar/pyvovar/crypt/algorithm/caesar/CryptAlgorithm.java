package nazar.pyvovar.crypt.algorithm.caesar;

import nazar.pyvovar.crypt.algorithm.NoSuchLetterException;

import java.util.Collection;

/**
 * Created by pyvov on 19.02.2017.
 */
public interface CryptAlgorithm {
    String encrypt(String plainText) throws NoSuchLetterException;
    String encrypt(String plainText, String alphabet) throws NoSuchLetterException;
    String encrypt(String plainText, int newKey) throws NoSuchLetterException;
    String encrypt(String plainText, String alphabet, int newKey) throws NoSuchLetterException;
    String decrypt(String encryptedText, int newKey) throws NoSuchLetterException;
    String decrypt(String encryptedText, String alphabet, int newKey) throws NoSuchLetterException;
    Collection<String> bruteForce(String encryptedText) throws NoSuchLetterException;
    Collection<String> bruteForce(String encryptedText, String alphabet) throws NoSuchLetterException;
    boolean isEnglish();
}
