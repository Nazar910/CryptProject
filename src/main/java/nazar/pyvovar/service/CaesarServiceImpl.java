package nazar.pyvovar.service;

import nazar.pyvovar.crypt.algorithm.NoSuchLetterException;
import nazar.pyvovar.crypt.algorithm.caesar.CryptAlgorithm;
import nazar.pyvovar.dto.MessageDTO;
import nazar.pyvovar.tools.RequestsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public ResponseEntity<byte[]> encrypt(MessageDTO messageDTO) {
        ResponseEntity<byte[]> result;
        try {
            if (!messageDTO.message.equals("") && messageDTO.key != 0 && !messageDTO.alphabet.equals("")) {
                result = ResponseEntity.ok()
                        .body(cryptAlgorithm.encrypt(messageDTO.message, messageDTO.alphabet, messageDTO.key).getBytes());

            } else if (!messageDTO.message.equals("") && messageDTO.key != 0) {
                result = ResponseEntity.ok()
                        .body(cryptAlgorithm.encrypt(messageDTO.message, messageDTO.key).getBytes());

            } else if (!messageDTO.message.equals("")) {
                result = ResponseEntity.ok()
                        .body(cryptAlgorithm.encrypt(messageDTO.message).getBytes());

            } else {
                result = ResponseEntity.noContent().build();
            }
        } catch (NoSuchLetterException e) {
            result = ResponseEntity.badRequest()
                    .body(e.getMessage().getBytes());
        }
        return result;
    }

    @Override
    public ResponseEntity<?> decrypt(MessageDTO messageDTO) {
        ResponseEntity<?> result;
        try {
            if (!messageDTO.message.equals("")) {
                if (messageDTO.key != 0) {
                    if (!messageDTO.alphabet.equals("")) {
                        result = ResponseEntity.ok()
                                .body(cryptAlgorithm.decrypt(messageDTO.message, messageDTO.alphabet, messageDTO.key).getBytes());

                    } else  {
                        result = ResponseEntity.ok()
                                .body(cryptAlgorithm.decrypt(messageDTO.message, messageDTO.key).getBytes());

                    }
                } else {
                    LinkedList<String> list = new LinkedList<>();
                    if (!messageDTO.alphabet.equals("")) {
                        list.addAll(cryptAlgorithm.bruteForce(messageDTO.message, messageDTO.alphabet));
                    } else {
                        list.addAll(cryptAlgorithm.bruteForce(messageDTO.message));
                    }
                    if (messageDTO.isIntelliSearch) {
                            outer:
                            if (cryptAlgorithm.isEnglish()) {
                                for (String s : list) {
                                    if (!sender.sendGet(
                                            "https://owlbot.info/api/v1/dictionary/" +
                                                    s.split(" ")[0].toLowerCase() + "?format=json").equals("[]")) {
                                        result = ResponseEntity.ok().body(new ArrayList<String>(Collections.singletonList(s)));
                                        break outer;
                                    }
                                }
                                result = ResponseEntity.ok().body(list);
                            } else result = ResponseEntity.ok().body(list);
                    } else {
                        result = ResponseEntity.ok().body(list);
                    }
                }
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

