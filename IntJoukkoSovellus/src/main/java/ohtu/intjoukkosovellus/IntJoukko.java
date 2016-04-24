
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int OLETUSKAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] alkiot;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        alkiot = new int[OLETUSKAPASITEETTI];
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        this(); //oletus: pielessä olevat parametrit tuottavat oletusarvot
        if (kapasiteetti > 0) {
           alkiot = new int[kapasiteetti]; 
        }
        if (kasvatuskoko > 0) {
            this.kasvatuskoko = kasvatuskoko;
        }       
    }

    public boolean lisaa(int luku) {
        if (kuuluu(luku)) return false;
        if (!kuuluu(luku) && this.mahtavuus() == alkiot.length) laajennaTaulukkoa();
        alkiot[this.mahtavuus()] = luku;
        alkioidenLkm++;
        return true; 
    }
    
    private void laajennaTaulukkoa(){
        int[] vanha = alkiot;
        alkiot = new int[this.mahtavuus() + this.kasvatuskoko];
        this.kopioiTaulukko(vanha, alkiot);
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == alkiot[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        int kohta = this.etsiLuku(luku);
        if(kohta != -1){
            this.poistaLukuTaulukosta(kohta);
            return true;
        }
        return false;
    }
    
    private void poistaLukuTaulukosta(int indeksi){
        for(int i = indeksi; i < this.mahtavuus()-1; i++){
            alkiot[i] = alkiot[i+1];
        }
        this.alkioidenLkm--;
    }
    
    private int etsiLuku(int luku) { 
        for (int i = 0; i < this.mahtavuus(); i++) 
            if (alkiot[i] == luku) return i;  
        return -1; 
    } 
    
    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        String tuotos = "{";
        for(int alkio : this.toIntArray()){
            tuotos += alkio;
            if(this.etsiLuku(alkio) != this.mahtavuus()-1) tuotos += ", " ; 
        }
        return tuotos + "}";
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = alkiot[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko();
        for (int alkio : a.toIntArray()) yhdiste.lisaa(alkio);
        for (int alkio : b.toIntArray()) yhdiste.lisaa(alkio);
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();
        for (int alkio : a.toIntArray()) {
            if(b.kuuluu(alkio)) leikkaus.lisaa(alkio);
        }
        return leikkaus;
    }
    
    public static IntJoukko erotus (IntJoukko a, IntJoukko b) {
        IntJoukko erotus = a;
        int[] poistettavat = b.toIntArray();
        for (int alkio : poistettavat) {
            a.poista(alkio);
        } 
        return erotus;
    }
        
}