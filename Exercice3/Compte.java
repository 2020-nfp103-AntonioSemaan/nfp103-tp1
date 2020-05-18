package Exercice3;

public class Compte {
  private int solde = 0;

  public void ajouter(int somme) {
    solde += somme;
    System.out.printf(" ajoute %d ", somme);
  }

  public void retirer(int somme) {
    solde -= somme;
    System.out.printf(" retire %d", somme);
  }

  public void operationNulle(int somme) {
    solde += somme;
    System.out.printf(" ajoute %d ", somme);
    solde -= somme;
    System.out.printf(" retire %d", somme);
  }

  public int getSolde() {
    return solde;
  }
}
    
