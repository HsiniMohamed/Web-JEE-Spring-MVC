package org.sid.dao;

import org.sid.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProduitRespository extends JpaRepository<Produit, Long>{

}
