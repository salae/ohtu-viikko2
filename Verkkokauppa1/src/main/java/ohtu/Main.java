package ohtu;

import ohtu.verkkokauppa.Kauppa;
import ohtu.verkkokauppa.Kauppavarasto;
import ohtu.verkkokauppa.Kirjanpito;
import ohtu.verkkokauppa.KirjanpitoInterface;
import ohtu.verkkokauppa.Pankki;
import ohtu.verkkokauppa.PankkiInterface;
import ohtu.verkkokauppa.Varasto;
import ohtu.verkkokauppa.Viitegeneraattori;
import ohtu.verkkokauppa.ViitegeneraattoriInterface;

public class Main {

    public static void main(String[] args) {
        KirjanpitoInterface kirjanpito = new Kirjanpito();
        Varasto varasto = new Kauppavarasto(kirjanpito);
        PankkiInterface pankki = new Pankki(kirjanpito);
        ViitegeneraattoriInterface viitegen = new Viitegeneraattori();
        Kauppa kauppa = new Kauppa(varasto,pankki, viitegen );

        // kauppa hoitaa yhden asiakkaan kerrallaan seuraavaan tapaan:
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(3);
        kauppa.lisaaKoriin(3);
        kauppa.poistaKorista(1);
        kauppa.tilimaksu("Pekka Mikkola", "1234-12345");

        // seuraava asiakas
        kauppa.aloitaAsiointi();
        for (int i = 0; i < 24; i++) {
            kauppa.lisaaKoriin(5);
        }

        kauppa.tilimaksu("Arto Vihavainen", "3425-1652");

        // kirjanpito
        for (String tapahtuma :  kirjanpito.getTapahtumat()) {
            System.out.println(tapahtuma);
        }
    }
}
