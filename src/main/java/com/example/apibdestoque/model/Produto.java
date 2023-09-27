package com.example.apibdestoque.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String nome;
    private String descricao;
    private double preco;
    private int quantidadeestoque;

    public Produto(long Id,String nome, String descricao, double preco, int quantidadeestoque) {
        this.Id = Id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidadeestoque = quantidadeestoque  ;
    }

    public Produto() {
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeestoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeestoque = quantidadeEstoque;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "Id=" + Id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", preco=" + preco +
                ", quantidadeEstoque=" + quantidadeestoque +
                '}';
    }
}
