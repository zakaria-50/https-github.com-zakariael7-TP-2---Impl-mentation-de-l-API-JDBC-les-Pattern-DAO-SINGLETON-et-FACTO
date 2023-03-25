package présentation;
import lombok.* ;
import métier.IMetierResultat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
@Data   @AllArgsConstructor @NoArgsConstructor
public class ResultatControleur implements IResultatControleur{

    @Autowired
    @Qualifier("metier")
    IMetierResultat resultatMetier;
    @Override
    public void afficher_Moyenne(Long idResultat)  throws Exception    {
        var resultatAvecMoyenne = resultatMetier.calculer_Moyenne(idResultat) ;
        System.out.println(resultatAvecMoyenne);
    }

}
