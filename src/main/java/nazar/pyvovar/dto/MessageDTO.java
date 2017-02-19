package nazar.pyvovar.dto;

/**
 * Created by pyvov on 19.02.2017.
 */
public class MessageDTO {
    public final String message;
    public final int key;
    public final String alphabet;

    public MessageDTO(String message, int key, String alphabet) {
        this.message = message;
        this.key = key;
        this.alphabet = alphabet;
    }

    public MessageDTO() {
        this.alphabet = "";
        this.message = "";
        this.key = 0;
    }
}
