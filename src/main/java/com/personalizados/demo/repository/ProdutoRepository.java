package com.personalizados.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personalizados.demo.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
