package métier;
import dao.IDao;
import lombok.* ;
import modele.Resultat ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;


@Service("metier")
@Data   @AllArgsConstructor @NoArgsConstructor
public class ResultatMetier implements IMetierResultat  {

    @Autowired
    @Qualifier("dao")

    IDao<Resultat, Long> resultatDao ;

    @Override

    public Resultat calculer_Moyenne(Long idResultat)   throws Exception {
        var resultat = resultatDao.trouverParId(idResultat);

        if (resultat == null)
            throw new Exception("L' id du Resultat est incorrecte :: [Resultat non trouvé] ");
        else {

            double coeff1             = resultat.getCoefficient1() ;
            double coeff2             = resultat.getCoefficient2() ;

            double note1         = resultat.getNote1() ;
            double note2         = resultat.getNote2() ;

            double moyenne      =((note1 * coeff1)+(note2 * coeff2))/(coeff1+coeff2) ;
            DecimalFormat df = new DecimalFormat("0.00");
            df.format(moyenne) ;

            resultat.setMoyenne(moyenne);
            return resultat ;

        }
    }
}
