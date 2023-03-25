package modele;
import lombok.* ;


@Data  @AllArgsConstructor  @NoArgsConstructor
public class Resultat {

    private Long id ;
    private Double note1 ;
    private Double note2 ;
    private double coefficient1 ;
    private double coefficient2 ;
    private String nom_Etudiant ;
    private Double moyenne ;




    @Override
    public String toString() {

    var resultatStr = "=====================================================  \n";
        resultatStr += "=>Resultat N°         :" + getId() +"                            =" + "\n";
        resultatStr += "=>Nom de l'etudiant   :" + getNom_Etudiant() +"                        =" + "\n";
        resultatStr += "----------------------------------------------------=   \n";
        resultatStr += "=>La note N° 1        :" + getNote1() + "\n";
        resultatStr += "=>La note N° 2        :" + getNote2() + "\n";
        resultatStr += "=>Coefficient 1       :" + getCoefficient1() + "%"+"\n";
        resultatStr += "=>Coefficient 2       :" + getCoefficient1() + "%"+"\n";
        resultatStr += "----------------------------------------------------=   \n";
        resultatStr += "=>Moyenne             :"
                + (getMoyenne() == 0.0 ? "NON-CALCULE" : getMoyenne() + "\n");
        resultatStr += "=====================================================  \n";

        return resultatStr;

    }

}
