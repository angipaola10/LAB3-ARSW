/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import org.springframework.boot.test.context.SpringBootTest;
import edu.eci.arsw.springdemo.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Angi
 */
@SpringBootTest
public class SpringdemoTest {
    
    
    @Autowired
    GrammarChecker gc;
   
    ApplicationContext ac;
    
    @Before
    public void setup(){
        ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        gc = ac.getBean(GrammarChecker.class);
    }

    // Test with english checker
    
    @Test
    public void shouldCheckWithEnglishChecker(){
        assertEquals(gc.check("la la la "), "Spell checking output:Checked with english checker:la la la Plagiarism checking output: Not available yet");
    }
    
    @Test
    public void shouldNotCheckWithSpanishChecker(){
        assertNotEquals(gc.check("la la la "),"Spell checking output:revisando (la la la ) con el verificador de sintaxis del espanolPlagiarism checking output: Not available yet");
    }
    
    /*
    // Test with spanish checker

    @Test
    public void shouldNotCheckWithEnglishChecker(){
        assertNotEquals(gc.check("la la la "), "Spell checking output:Checked with english checker:la la la Plagiarism checking output: Not available yet");
    }
    
    @Test
    public void shouldCheckWithSpanishChecker(){
        assertEquals(gc.check("la la la "),"Spell checking output:revisando (la la la ) con el verificador de sintaxis del espanolPlagiarism checking output: Not available yet");
    }
    */
    
}
