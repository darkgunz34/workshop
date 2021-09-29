package fr.workshop.tools;

import fr.workshop.exception.ObjetVidException;

import java.time.LocalDate;

public final class ControleObjet {

    public static final String VALEUR_IMPOSSIBLE = "Valeur impossible";

    private ControleObjet() {
    }

    public static void champNonVideString(final String chaine) throws ObjetVidException {
        if (chaine == null || chaine.trim().isEmpty()) {
            throw new ObjetVidException(VALEUR_IMPOSSIBLE);
        }
    }

    public static void champNonVideDoubleSuperieurAZero(final double nombre) throws ObjetVidException {
        if (nombre <= 0) {
            throw new ObjetVidException(VALEUR_IMPOSSIBLE);
        }
    }

    public static void champNonVideDate(final LocalDate date) throws ObjetVidException {
        if (date == null || date.isEqual(LocalDate.of(0, 1, 1))) {
            throw new ObjetVidException(VALEUR_IMPOSSIBLE);
        }
    }

    public static void champIdentiqueString(final String s1, final String s2 ) throws ObjetVidException{
        if(s1 == null || ! s1.equals(s2)){
            throw new ObjetVidException("Les chaines ne sont pas identiques");
        }
    }
}
