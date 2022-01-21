package org.springframework.samples.petclinic.care;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class CareFormatter implements Formatter<Care>{

    private final CareService careService;

    @Autowired
    public CareFormatter(CareService careService) {
        this.careService = careService;
    }

    @Override
    public String print(Care object, Locale locale) {
        // TODO Auto-generated method stub
        return object.name;
    }

    @Override
    public Care parse(String text, Locale locale) throws ParseException {
        // TODO Auto-generated method stub
        Care care = careService.getCare(text);
        if(care != null) return care;
        else throw new ParseException("Care not found " + text, 0);
    }
    
}
