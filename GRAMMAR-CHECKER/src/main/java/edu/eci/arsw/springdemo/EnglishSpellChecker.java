package edu.eci.arsw.springdemo;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;

@Service("englishSpellChecker")
public class EnglishSpellChecker implements SpellChecker {

	@Override
	public String checkSpell(String text) {		
		return "Checked with english checker:"+text;
	}

        
}
