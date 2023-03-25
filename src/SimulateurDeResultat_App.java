import dao.IDao;
import dao.ResultatDao ;
import métier.IMetierResultat;
import métier.ResultatMetier ;
import modele.Resultat;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import présentation.IResultatControleur;
import présentation.ResultatControleur ;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.Scanner ;

public class SimulateurDeResultat_App {

    static IResultatControleur resultatControleur ;

    static Scanner clavier = new Scanner(System.in) ;

    private static boolean estUnNombre(String input){
        try {
            Integer.parseInt(input) ;   return true ;
        }
        catch (Exception e){
            return false ;
        }
    }

    public static void test1(){

        var dao         =   new ResultatDao() ;
        var metier      =   new ResultatMetier() ;
        var controleur  =   new ResultatControleur() ;

        metier      .setResultatDao(dao);
        controleur  .setResultatMetier(metier);

        String rep = "" ;
        do {
            System.out.println("==> [Test 1] calcule de Moyenne de Resultat <==\n");
            try {
                String input = "" ;
                while (true){
                    System.out.println("Entrer l'id du resultat : ");
                    input = clavier.nextLine() ;
                    if (estUnNombre(input)) break;
                    System.err.println("Entrer non valide !!!");
                }
                Long id = Long.parseLong(input) ;
                controleur.afficher_Moyenne(id);
            }catch (Exception e){
                System.err.println(e.getMessage());
            }

            System.out.println("voulez vous quittez (oui/non) ? "); rep = clavier.nextLine() ;

        }while (!rep.equalsIgnoreCase("oui")) ;
        System.out.println("Au revoir o_o");
    }


    public static void test2()  throws Exception{

        String daoClass ;
        String serviceClass ;
        String controllerClass ;

        Properties properties = new Properties() ;


        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream("config.properties") ;


        if (propertiesFile == null) throw new Exception("fichier config introuvable !!!") ;
        else {
            try {
                properties.load(propertiesFile);
                daoClass            =properties.getProperty("DAO") ;
                serviceClass        =properties.getProperty("SERVICE") ;
                controllerClass     =properties.getProperty("CONTROLLER") ;
                propertiesFile.close();
            }
            catch (IOException e)   {
                throw new Exception("Probléme de chargement des propriétés du fichier config ");
            }
            finally {
                properties.clear();
            }
        }
        try {
            Class cDao                          = Class.forName(daoClass) ;
            Class cMetier                       = Class.forName(serviceClass) ;
            Class cControleur                   = Class.forName(controllerClass) ;


            var dao                 = (IDao<Resultat, Long>)cDao.getDeclaredConstructor().newInstance();
            var metier              = (IMetierResultat)     cMetier.getDeclaredConstructor().newInstance();
            resultatControleur      = (IResultatControleur) cControleur.getDeclaredConstructor().newInstance() ;

            Method  setDao          = cMetier.getMethod("setResultatDao" , IDao.class) ;
            setDao.invoke(metier,dao) ;


            Method  setMetier       = cControleur.getMethod("setResultatMetier",IMetierResultat.class) ;
            setMetier.invoke(resultatControleur, metier) ;


            resultatControleur.afficher_Moyenne(1L);
        }
        catch (Exception e){e.printStackTrace();}
    }

    public static void test3()  throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-ioc.xml") ;
        resultatControleur = (IResultatControleur) context.getBean("controleur") ;
        resultatControleur.afficher_Moyenne(1L);
    }

    public static void test4()  throws Exception    {
        ApplicationContext context = new AnnotationConfigApplicationContext("dao", "métier", "présentation") ;

        resultatControleur = (IResultatControleur) context.getBean(IResultatControleur.class) ;
        resultatControleur.afficher_Moyenne(1L);
        resultatControleur.afficher_Moyenne(2L);
        resultatControleur.afficher_Moyenne(3L);
        resultatControleur.afficher_Moyenne(4L);
    }


    public static void main (String[] args) throws Exception{

      
        test4();}

}
