package ohtu;

import ohtu.verkkokauppa.Kauppa;
import ohtu.verkkokauppa.Kirjanpito;
import ohtu.verkkokauppa.KirjanpitoInterface;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {
    //korjattu mielestäni vähän älyttömäksi 3 viikon tehtävän 1 takia
    public static void main(String[] args) {      
        ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring-context.xml");
        Kauppa kauppa = (Kauppa)ctx.getBean("kauppa");        
        kokeileKauppaa(kauppa);

        kauppa.tilimaksu("Arto Vihavainen", "3425-1652");
        kokeileKirjanpitoa(ctx);
    }
    
    private static void kokeileKauppaa(Kauppa kauppa){
        // kauppa hoitaa yhden asiakkaan kerrallaan seuraavaan tapaan:
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(3);
        kauppa.lisaaKoriin(3);
        kauppa.poistaKorista(1);
        kauppa.tilimaksu("Pekka Mikkola", "1234-12345");
    }
    
    private static void kokeileKirjanpitoa(ApplicationContext ctx){
        // kirjanpito
        KirjanpitoInterface kirjanpito = ctx.getBean(Kirjanpito.class);
        
        for (String tapahtuma :  kirjanpito.getTapahtumat()) {
            System.out.println(tapahtuma);
        } 
    }
}
