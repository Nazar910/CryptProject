package nazar.pyvovar.service;

import nazar.pyvovar.dto.MessageDTO;
import org.springframework.http.ResponseEntity;

import java.util.Collection;

/**
 * Created by pyvov on 19.02.2017.
 */
public interface CryptService {
    ResponseEntity<String> encrypt(MessageDTO messageDTO);
    ResponseEntity<?> decrypt(MessageDTO messageDTO);
}
