package be.vdab.mail.controllers;

import be.vdab.mail.dto.NieuwLid;
import be.vdab.mail.services.LidService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LidController {
    private final LidService lidService;

    public LidController(LidService lidService) {
        this.lidService = lidService;
    }
    @PostMapping("leden")
    void registreer(@RequestBody @Valid NieuwLid nieuwLid) throws MessagingException {
        lidService.registreer(nieuwLid);
    }
}
