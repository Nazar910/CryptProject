package nazar.pyvovar.service;

import nazar.pyvovar.crypt.algorithm.NoSuchLetterException;
import nazar.pyvovar.crypt.algorithm.caesar.CryptAlgorithm;
import nazar.pyvovar.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by pyvov on 19.02.2017.
 */
@Service
public class CaesarServiceImpl implements CryptService {

    private CryptAlgorithm cryptAlgorithm;

    @Autowired
    public CaesarServiceImpl(CryptAlgorithm cryptAlgorithm) {
        this.cryptAlgorithm = cryptAlgorithm;
    }

    @Override
    public ResponseEntity<String> encrypt(MessageDTO messageDTO) {
        ResponseEntity<String> result;
        try {
            if (!messageDTO.message.equals("") && messageDTO.key != 0 && !messageDTO.alphabet.equals("")) {
                result = ResponseEntity.ok()
                        .body(cryptAlgorithm.encrypt(messageDTO.message, messageDTO.alphabet, messageDTO.key));

            } else if (!messageDTO.message.equals("") && messageDTO.key != 0) {
                result = ResponseEntity.ok()
                        .body(cryptAlgorithm.encrypt(messageDTO.message, messageDTO.key));

            } else if (!messageDTO.message.equals("")) {
                result = ResponseEntity.ok()
                        .body(cryptAlgorithm.encrypt(messageDTO.message));

            } else result = ResponseEntity.noContent().build();
        } catch (NoSuchLetterException e) {
            result = ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
        return result;
    }

    @Override
    public ResponseEntity<?> decrypt(MessageDTO messageDTO) {
        ResponseEntity<?> result;
        try {
            if (!messageDTO.message.equals("") && messageDTO.key != 0 && !messageDTO.alphabet.equals("")) {
                result = ResponseEntity.ok()
                        .body(cryptAlgorithm.decrypt(messageDTO.message, messageDTO.alphabet, messageDTO.key));

            } else if (!messageDTO.message.equals("") && messageDTO.key != 0) {
                result = ResponseEntity.ok()
                        .body(cryptAlgorithm.decrypt(messageDTO.message, messageDTO.key));

            } else if (!messageDTO.message.equals("")) {
                result = ResponseEntity.ok()
                        .body(cryptAlgorithm.bruteForce(messageDTO.message));
            } else {
                result = ResponseEntity.noContent().build();
            }
        } catch (NoSuchLetterException e) {
            result = ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
        return result;
    }
}

