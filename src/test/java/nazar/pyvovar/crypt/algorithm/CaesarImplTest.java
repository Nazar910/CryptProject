package nazar.pyvovar.crypt.algorithm;

import nazar.pyvovar.crypt.algorithm.caesar.CaesarImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by pyvov on 19.02.2017.
 */
public class CaesarImplTest {
    private String englishAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private String englishWithoutLowerCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String ukrainianAlphabet = "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯабвгґдеєжзиіїйклмнопрстуфхцчшщьюя";
    private String russianAlphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private CaesarImpl caesar;

    @Before
    public void setUp() {
        int key = 1;
        caesar = new CaesarImpl(englishAlphabet, key);
    }

    @Test
    public void encryptWhenSendHelloReturnIfmmp() throws NoSuchLetterException {
        String encrypted = caesar.encrypt("Hello");
        assertEquals("Ifmmp", encrypted);
    }

    @Test(expected = NoSuchLetterException.class)
    public void encryptAndWaitForNuSuchLetterException() throws NoSuchLetterException {
        String encrypted = caesar.encrypt("Hellы");
    }

    @Test
    public void encryptWhenSendWithKey2() throws NoSuchLetterException {
        String encrypted = caesar.encrypt("Hello", 2);
        assertEquals("Jgnnq", encrypted);
    }

    @Test
    public void encryptWhenSendWithKey27() throws NoSuchLetterException {
        String encrypted = caesar.encrypt("Hello", 27);
        assertEquals("iFMMP", encrypted);
    }

    @Test
    public void decryptWhenSendIfmmpReturnHello() throws NoSuchLetterException {
        String decrypted = caesar.decrypt("Ifmmp", 1);
        assertEquals("Hello", decrypted);
    }

    @Test
    public void decryptWhenSendWithKey2() throws NoSuchLetterException {
        String decrypted = caesar.decrypt("Jgnnq", 2);
        assertEquals("Hello", decrypted);
    }

    @Test
    public void decryptWhenSendWithKey27() throws NoSuchLetterException {
        String decrypted = caesar.decrypt("iFMMP", 27);
        assertEquals("Hello", decrypted);
    }

    @Test
    public void encryptWithoutLowerCaseWhenSendWithKey27() throws NoSuchLetterException {
        String encrypted = caesar.encrypt("HELLO", englishWithoutLowerCase, 27);
        assertEquals("IFMMP", encrypted);
    }

    @Test
    public void encryptUkrainianWhenSendHelloReturn() throws NoSuchLetterException {
        String encrypted = caesar.encrypt("Привіт", ukrainianAlphabet);
        assertEquals("Рсігїу", encrypted);
    }

    @Test
    public void encryptUkrainianWhenSendWithKey2() throws NoSuchLetterException {
        String encrypted = caesar.encrypt("Привіт", ukrainianAlphabet, 2);
        assertEquals("Стїґйф", encrypted);
    }

    @Test
    public void encryptUkrainianWhenSendWithKey27() throws NoSuchLetterException {
        String encrypted = caesar.encrypt("Привіт", ukrainianAlphabet, 34);
        assertEquals("рСІГЇУ", encrypted);
    }

    @Test
    public void encryptRussianWhenSendHelloReturn() throws NoSuchLetterException {
        String encrypted = caesar.encrypt("Привет", russianAlphabet);
        assertEquals("Рсйгёу", encrypted);
    }

    @Test
    public void encryptRussianWhenSendWithKey2() throws NoSuchLetterException {
        String encrypted = caesar.encrypt("Привет", russianAlphabet, 2);
        assertEquals("Сткджф", encrypted);
    }

    @Test
    public void encryptRussianWhenSendWithKey27() throws NoSuchLetterException {
        String encrypted = caesar.encrypt("Привет", russianAlphabet, 34);
        assertEquals("рСЙГЁУ", encrypted);
    }

    @Test
    public void decryptWithBruteForce() throws NoSuchLetterException {
        LinkedList<String> decrypted = new LinkedList<>(caesar.bruteForce("Hello"));
        assertEquals("Gdkkn", decrypted.getFirst());
        assertEquals("Ifmmp", decrypted.getLast());
    }

    @Test
    public void encryptWithSpace() throws NoSuchLetterException {
        String encrypted = caesar.encrypt("Hello! How are you?", 2);
        assertEquals("Jgnnq! Jqy ctg Aqw?", encrypted);
    }

    @Test
    public void IsEnglishTrue() {
        assertTrue(caesar.isEnglish());
    }

    @Test
    public void IsEnglishFalse() {
        CaesarImpl caesar1 = new CaesarImpl(ukrainianAlphabet, 1);
        assertFalse(caesar1.isEnglish());
    }

    @Test
    public void decryptWhenSendWithKey70() throws NoSuchLetterException {
        String encrypted = caesar.decrypt("ZwDDG", 70);
        assertEquals("Hello", encrypted);
    }
}
