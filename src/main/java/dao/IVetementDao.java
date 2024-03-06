package dao;

import java.util.List;
import metier.entities.Vetement;
public interface IVetementDao {
public Vetement save(Vetement p);
public List<Vetement> VetementsParMC(String mc);
public Vetement getVetement(Long id); public Vetement updateVetement(Vetement p);
public void deleteVetement(Long id);
}