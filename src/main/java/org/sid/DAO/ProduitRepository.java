package org.sid.DAO;

import org.sid.Entity.Produit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProduitRepository extends MongoRepository<Produit, String> {
}
