package nazar.pyvovar.service;

import nazar.pyvovar.crypt.algorithm.NoSuchLetterException;
import nazar.pyvovar.crypt.algorithm.caesar.CryptAlgorithm;
import nazar.pyvovar.dto.MessageDTO;
import nazar.pyvovar.tools.RequestsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by pyvov on 19.02.2017.
 */
@Service
public class CaesarServiceImpl implements CryptService {

    private CryptAlgorithm cryptAlgorithm;
    private RequestsSender sender;

    @Autowired
    public CaesarServiceImpl(CryptAlgorithm cryptAlgorithm, RequestsSender sender) {
        this.cryptAlgorithm = cryptAlgorithm;
        this.sender = sender;
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

            } else {
                result = ResponseEntity.noContent().build();
            }
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
                LinkedList<String> list = new LinkedList<>(cryptAlgorithm.bruteForce(messageDTO.message));
                outer:
                if (cryptAlgorithm.isEnglish()) {
                    for (String s : list) {
                        if (!sender.sendGet(
                                "https://owlbot.info/api/v1/dictionary/" +
                                        s.split(" ")[0].toLowerCase() + "?format=json").equals("[]")) {
                            result = ResponseEntity.ok()
                                    .body(s);
                            break outer;
                        }
                    }
                    result = ResponseEntity.ok().body(list);
                } else result = ResponseEntity.ok().body(list);

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

