package dao;

import modele.Etudiant;

public abstract class Factory {

    public static final int MySQL_DAO_FACTORY = 0 ;

    public static final int File_DAO_FACTORY = 1 ;

    public abstract IDao<Etudiant, Long> getEtudiantDAO() ;

}
