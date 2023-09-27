package com.example.apibdestoque.repository;

import com.example.apibdestoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
