package org.springframework.samples.petclinic.care;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class CareFormatter implements Formatter<Care>{

    @Autowired
    private CareService careService;

    public CareFormatter(CareService careService) {
        this.careService = careService;
    }

    @Override
    public String print(Care object, Locale locale) {
        return object.getName();
    }

    @Override
    public Care parse(String text, Locale locale) throws ParseException {
        List<Care> cares = careService.getAllCares();
        for (Care care : cares) {
            if (care.getName().equals(text)) {
                return care;
            }
        }
        throw new ParseException("Could not parse the text", 0);
    }
    
}
