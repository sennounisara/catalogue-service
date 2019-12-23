package org.sid.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Category {
    @Id
    private String id;
    private String name;
    // Créer deux collection une de categorie et l'autre de produit
    @DBRef
    private Collection<Produit> listProduit = new ArrayList<>();
}
