package be.vdab.mail.services;

import be.vdab.mail.domain.Lid;
import be.vdab.mail.dto.NieuwLid;
import be.vdab.mail.mailing.LidMailing;
import be.vdab.mail.repositories.LidRepository;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class LidService {
    private final LidRepository lidRepository;
    private final LidMailing lidMailing;

    public LidService(LidRepository lidRepository, LidMailing lidMailing) {
        this.lidRepository = lidRepository;
        this.lidMailing = lidMailing;
    }
    @Transactional
    public void registreer(NieuwLid nieuwLid) throws MessagingException {
        var lid = new Lid(nieuwLid.voornaam(), nieuwLid.familienaam(),
                nieuwLid.emailAdres());
        lidRepository.save(lid);
        lidMailing.stuurMailNaRegistratie(lid);
    }
}
