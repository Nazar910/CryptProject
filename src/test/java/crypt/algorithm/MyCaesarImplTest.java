package crypt.algorithm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by nazar on 16/02/17.
 */
public class MyCaesarImplTest {
    private MyCaesarImpl caesar;
    private String englishAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private String englishWithoutLowerCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String ukrainianAlphabet = "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯабвгґдеєжзиіїйклмнопрстуфхцчшщьюя";
    private String russianAlphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";


    @Before
    public void setUp() {
        int key = 1;
        caesar = new MyCaesarImpl(englishAlphabet, key);
    }

    @Test
    public void encryptWhenSendHelloReturnIfmmp() {
        caesar.setAlphabet(englishAlphabet);
        String encrypted = caesar.encrypt("Hello");
        assertEquals("Ifmmp", encrypted);
    }

    @Test
    public void encryptWhenSendWithKey2() {
        caesar.setAlphabet(englishAlphabet);
        String encrypted = caesar.encrypt("Hello", 2);
        assertEquals("Jgnnq", encrypted);
    }

    @Test
    public void encryptWhenSendWithKey27() {
        caesar.setAlphabet(englishAlphabet);
        String encrypted = caesar.encrypt("Hello", 27);
        assertEquals("iFMMP", encrypted);
    }

    @Test
    public void encryptWithoutLowerCaseWhenSendWithKey27() {
        caesar.setAlphabet(englishWithoutLowerCase);
        String encrypted = caesar.encrypt("HELLO", 27);
        assertEquals("IFMMP", encrypted);
    }

    @Test
    public void encryptUkrainianWhenSendHelloReturn() {
        caesar.setAlphabet(ukrainianAlphabet);
        String encrypted = caesar.encrypt("Привіт");
        assertEquals("Рсігїу", encrypted);
    }

    @Test
    public void encryptUkrainianWhenSendWithKey2() {
        caesar.setAlphabet(ukrainianAlphabet);
        String encrypted = caesar.encrypt("Привіт", 2);
        assertEquals("Стїґйф", encrypted);
    }

    @Test
    public void encryptUkrainianWhenSendWithKey27() {
        caesar.setAlphabet(ukrainianAlphabet);
        String encrypted = caesar.encrypt("Привіт", 34);
        assertEquals("рСІГЇУ", encrypted);
    }

    @Test
    public void encryptRussianWhenSendHelloReturn() {
        caesar.setAlphabet(russianAlphabet);
        String encrypted = caesar.encrypt("Привет");
        assertEquals("Рсйгёу", encrypted);
    }

    @Test
    public void encryptRussianWhenSendWithKey2() {
        caesar.setAlphabet(russianAlphabet);
        String encrypted = caesar.encrypt("Привет", 2);
        assertEquals("Сткджф", encrypted);
    }

    @Test
    public void encryptRussianWhenSendWithKey27() {
        caesar.setAlphabet(russianAlphabet);
        String encrypted = caesar.encrypt("Привет", 34);
        assertEquals("рСЙГЁУ", encrypted);
    }
}
