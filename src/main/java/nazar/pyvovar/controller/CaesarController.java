package nazar.pyvovar.controller;

import nazar.pyvovar.dto.MessageDTO;
import nazar.pyvovar.service.CaesarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pyvov on 19.02.2017.
 */
@RestController
@RequestMapping("/api/crypt/caesar")
public class CaesarController {

    private CaesarServiceImpl cryptService;

    @Autowired
    public CaesarController(CaesarServiceImpl cryptService) {
        this.cryptService = cryptService;
    }

    @RequestMapping(path = "/encrypt", method = RequestMethod.POST)
    public ResponseEntity<byte[]> getEncryptedMessage(@RequestBody MessageDTO messageDTO) {
        ResponseEntity<byte[]> encrypt = cryptService.encrypt(messageDTO);
        return encrypt;
    }

    @RequestMapping(path = "/decrypt", method = RequestMethod.POST)
    public ResponseEntity<?> getDecryptedMessage(@RequestBody MessageDTO messageDTO) {
        return cryptService.decrypt(messageDTO);
    }

}
