package nazar.pyvovar.dto;

/**
 * Created by pyvov on 19.02.2017.
 */
public class MessageDTO {
    public final String message;
    public final int key;
    public final String alphabet;
    public boolean isIntelliSearch;

    public MessageDTO(String message, int key, String alphabet, boolean isIntelliSearch) {
        this.message = message;
        this.key = key;
        this.alphabet = alphabet;
        this.isIntelliSearch = isIntelliSearch;
    }

    public MessageDTO(String message, int key) {
        this.message = message;
        this.key = key;
        this.alphabet = "";
        this.isIntelliSearch = false;
    }

    public MessageDTO() {
        this.alphabet = "";
        this.message = "";
        this.key = 0;
        this.isIntelliSearch = false;
    }
}
