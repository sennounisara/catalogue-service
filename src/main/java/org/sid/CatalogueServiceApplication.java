package org.sid;

import org.sid.DAO.CategoryRepository;
import org.sid.DAO.ProduitRepository;
import org.sid.Entity.Category;
import org.sid.Entity.Produit;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;
import java.util.*;
@SpringBootApplication
public class CatalogueServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogueServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CategoryRepository categoryRepository, ProduitRepository produitRepository){
        return args -> {
            categoryRepository.deleteAll();
            Stream.of("C1 Ordinateur","C2 Imprimante").forEach(c -> {
                categoryRepository.save(new Category(c.split(" ")[0],c.split(" ")[1],new ArrayList<>()));
            });
            categoryRepository.findAll().forEach(System.out::println);

            produitRepository.deleteAll();
            Category cat1 = categoryRepository.findById("C1").get();
            Stream.of("P1 HP_05","P2 Dell").forEach(c -> {
                Produit P = produitRepository.save(new Produit(c.split(" ")[0],c.split(" ")[1],Math.random()*1000,cat1));
                cat1.getListProduit().add(P);
                categoryRepository.save(cat1);
            });
            Category cat2 = categoryRepository.findById("C2").get();
            Stream.of("P3 HP_05","P4 Dell").forEach(p -> {
                Produit P1 = produitRepository.save(new Produit(p.split(" ")[0],p.split(" ")[1],Math.random()*1000,cat2));
                cat2.getListProduit().add(P1);
                categoryRepository.save(cat2);
            });
            produitRepository.findAll().forEach(System.out::println);
        };
    }
}
