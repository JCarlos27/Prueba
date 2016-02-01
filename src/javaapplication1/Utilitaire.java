/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author Irene
 */
public class Utilitaire {
    
    final static private double VALEUR_DE_BASE_FONCIERE = 733.77;
    final private double TAXE_SCOLAIRE = 0.012;
    final private double TAXE_MUNICIPALE = 0.025;
    final static private double MULTIPLICATEUR_DROIT_PASSAGE_RESIDENTIEL_TYPE1 = 0.10;
    final static private double MULTIPLICATEUR_DROIT_PASSAGE_RESIDENTIEL_TYPE0 = 0.05;
    final static private double MULTIPLICATEUR_DROIT_PASSAGE_RESIDENTIEL_TYPE2 = 0.15;
    final static private double MONTANT_BASE_DROIT_PASSAGE = 500.00;
    
    final static private int SERVICE_BASE = 2; 
    final static private int MIN_SUPERFICIE_FRAIS_SERVICE_RES = 500;
    final static private int MAX_SUPERFICIE_FRAIS_SERVICE_RES = 10000;
    final static private double MIN_FRAIS_SERVICE_RES = 0;
    final static private double MOY_FRAIS_SERVICE_RES = 500.00;
    final static private double MAX_FRAIS_SERVICE_RES = 1000.00;
    
    final static private int MIN_SUPERFICIE_FRAIS_SERVICE_COM = 500;
    final static private double MIN_FRAIS_SERVICE_COM = 500.00;
    final static private double MOY_FRAIS_SERVICE_COM = 1500.00;
    final static private double MAX_FRAIS_SERVICE_COM = 5000.00;
    
    
    
    public static void trouverValeurLot(Terrain terrainRecu){
        double multiplicateur; 
        
             switch (terrainRecu.getType()) {
                case 0:
                    multiplicateur = terrainRecu.getPrix_min();
                    
                    break;
                case 1:
                     multiplicateur = ((terrainRecu.getPrix_min() + terrainRecu.getPrix_max()) / 2 );
                    break;
                case 2:
                     multiplicateur = terrainRecu.getPrix_max();
                    break;
                default:
                    multiplicateur = 0;
                    break;
            }
            for (Lotissement lotissementObtenu : terrainRecu.lot) {
                 lotissementObtenu.setValeur_par_lot(multiplicateur * lotissementObtenu.getSuperficie()); 
            }
    }
    
    public static void calculerDroitPassage (Terrain terrainRecu){
        
        switch (terrainRecu.getType()){
            case 0:
                formuleDroitPassage(terrainRecu,MULTIPLICATEUR_DROIT_PASSAGE_RESIDENTIEL_TYPE0);
                break;
            case 1:
                formuleDroitPassage(terrainRecu,MULTIPLICATEUR_DROIT_PASSAGE_RESIDENTIEL_TYPE1);
                break;
            case 2:
                formuleDroitPassage(terrainRecu,MULTIPLICATEUR_DROIT_PASSAGE_RESIDENTIEL_TYPE2);
                break;
            default:
                break; 
        }
    }
    
    
    public static void calculerFraisServices(Terrain terrainRecu){
        
        double valeurOriginaleLot;
        double valeurFraisServiceCalcule = 0;
        double valeurNombreService;
        
        switch (terrainRecu.getType()){
            case 0:
                break;
            case 1:
                for (Lotissement lotissementObtenu : terrainRecu.lot) {
                        valeurOriginaleLot = lotissementObtenu.getValeur_par_lot();
                        valeurNombreService = SERVICE_BASE + lotissementObtenu.getNombre_service();
                        double valeurSuperficie = lotissementObtenu.getSuperficie();
                        if( valeurSuperficie <= MIN_SUPERFICIE_FRAIS_SERVICE_RES ) {
                           valeurFraisServiceCalcule += ( MIN_FRAIS_SERVICE_RES * valeurNombreService );
                        } else if ( valeurSuperficie > MIN_SUPERFICIE_FRAIS_SERVICE_RES &&  valeurSuperficie <= MAX_SUPERFICIE_FRAIS_SERVICE_RES) {
                           valeurFraisServiceCalcule += ( MOY_FRAIS_SERVICE_RES * valeurNombreService );    
                        } else if ( valeurSuperficie > MAX_SUPERFICIE_FRAIS_SERVICE_RES ) {
                           valeurFraisServiceCalcule += ( MAX_FRAIS_SERVICE_RES * valeurNombreService );    
                        } else {
                           valeurFraisServiceCalcule += 0;
                        } 
                        valeurOriginaleLot += valeurFraisServiceCalcule;
                        lotissementObtenu.setValeur_par_lot(valeurOriginaleLot);
                    }
                
                
                break;
            case 2:
                 for (Lotissement lotissementObtenu : terrainRecu.lot) {
                        valeurOriginaleLot = lotissementObtenu.getValeur_par_lot();
                        valeurNombreService = SERVICE_BASE + lotissementObtenu.getNombre_service();
                        double valeurSuperficie = lotissementObtenu.getSuperficie();
                        if( valeurSuperficie <= MIN_SUPERFICIE_FRAIS_SERVICE_COM ) {
                            if(MIN_FRAIS_SERVICE_COM * valeurNombreService>MAX_FRAIS_SERVICE_COM){
                                valeurFraisServiceCalcule += MAX_FRAIS_SERVICE_COM; 
                            }else{
                                valeurFraisServiceCalcule += (MIN_FRAIS_SERVICE_COM * valeurNombreService);
                            }
                        } else if ( valeurSuperficie > MIN_SUPERFICIE_FRAIS_SERVICE_RES ){
                            if(MOY_FRAIS_SERVICE_COM * valeurNombreService>MAX_FRAIS_SERVICE_COM){
                                valeurFraisServiceCalcule += MAX_FRAIS_SERVICE_COM; 
                            }else{
                                valeurFraisServiceCalcule += (MOY_FRAIS_SERVICE_COM * valeurNombreService);
                            }
          
                        } 
                        valeurOriginaleLot += valeurFraisServiceCalcule;
                        lotissementObtenu.setValeur_par_lot(valeurOriginaleLot);
                
                 }
                
                break;
            
            
        }
        
    }
    //hola
    private static void formuleDroitPassage(Terrain terrainRecu, Double frais){
        double valeurParLot;
        double valeurLotDroit;
        for (Lotissement lotissementObtenu : terrainRecu.lot) {
            valeurParLot = lotissementObtenu.getValeur_par_lot();
            valeurLotDroit = (frais * valeurParLot);
            valeurLotDroit = (MONTANT_BASE_DROIT_PASSAGE -(lotissementObtenu.nombre_droits_passage * valeurLotDroit));
            lotissementObtenu.setValeur_par_lot(lotissementObtenu.getValeur_par_lot() + valeurLotDroit);
            
        }
        
        
    }

    public static void calculeValeurFonciere(Terrain terrainRecu) {
    double valeurFonciereTotale = 0;
    for (Lotissement lotissementObtenu : terrainRecu.lot) {
            valeurFonciereTotale += lotissementObtenu.getValeur_par_lot();
    }
    valeurFonciereTotale += VALEUR_DE_BASE_FONCIERE;
    terrainRecu.setValeur_fonciere_total(valeurFonciereTotale);
    terrainRecu.setValeur_fonciere_total(Math.round(terrainRecu.getValeur_fonciere_total() * 100.0) / 100.0);
    }
    
}
