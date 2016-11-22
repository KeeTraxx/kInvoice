package ch.compile.kinvoice.controller;

import ch.compile.kinvoice.model.KInvoiceUserSettings;
import ch.compile.kinvoice.repository.KInvoiceUserSettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private KInvoiceUserSettingsRepository kInvoiceUserSettingsRepository;

    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }

    @RequestMapping(value = "/user/settings", method = RequestMethod.GET)
    public KInvoiceUserSettings getSettings(Principal principal) {
        return kInvoiceUserSettingsRepository.findOne(principal.getName());
    }

    @RequestMapping(value = "/user/settings", method = RequestMethod.POST)
    public KInvoiceUserSettings saveSettings(@RequestBody KInvoiceUserSettings settings, Principal principal) {
        settings.setId(principal.getName());
        return kInvoiceUserSettingsRepository.save(settings);
    }

}
