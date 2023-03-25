package dao;

import modele.Resultat;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component("dao")
public class ResultatDao implements IDao<Resultat,Long >{


    public static Set<Resultat> DBResultats() {

        return new HashSet<Resultat>(
                Arrays.asList(
                        new Resultat(1L , 18.00 , 15.00 , 2 , 2 , "issam" , 0.0),
                        new Resultat(2L, 20.00, 9.00, 2,2, "Tarek", 0.0),
                        new Resultat(3L, 17.00, 10.00, 2,1.5 , "sarah", 20.0),
                        new Resultat(4L, 7.00, 11.00, 2.0, 3, "sanae", 0.0)

                ));
    }

    @Override
    public Resultat trouverParId(Long id) {
        System.out.println("[DAO = DS volatile] trouver le Resultat Nr° :"+ id);
        return      DBResultats()
                .stream()
                .filter(resultat -> resultat.getId() == id)
                .findFirst()
                .orElse(null) ;
    }




}
