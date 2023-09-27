package com.example.apibdestoque.controller;

import com.example.apibdestoque.model.Produto;
import com.example.apibdestoque.repository.ProdutoRepository;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/selecionar")
    public List<Produto> listarProdutos(){

        return produtoRepository.findAll() ;
    }

    @GetMapping("/selecionar/{id}")
    public ResponseEntity<Produto> getProdutoPorId(@PathVariable Long id){
        Produto produto =   produtoRepository.findById(id).orElse(null);
        if (produto != null){
            return ResponseEntity.ok(produto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/inserir")
    public ResponseEntity<String> inserirProduto(@RequestBody Produto produto){
        produtoRepository.save(produto);
        return ResponseEntity.ok("Produto inserido com sucesso");
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id){
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()){
            produtoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto produtoAtualizado){
        Optional<Produto> produtoExistente = produtoRepository.findById(id);
        if (produtoExistente.isPresent()){
            Produto produto = produtoExistente.get();
            produto.setNome(produtoAtualizado.getNome());
            produto.setDescricao(produtoAtualizado.getDescricao());
            produto.setPreco(produtoAtualizado.getPreco());
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque());
            produtoRepository.save(produto);
            return ResponseEntity.ok(produto);
        }else {
            return  ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/inserirForm")
    public ResponseEntity<String> inserirProduto(@RequestParam long Id,
                                                 @RequestParam String nome,
                                                 @RequestParam String descricao,
                                                 @RequestParam double preco,
                                                 @RequestParam int quantidadeestoque
                                                 ){
        Produto novoProduto = new Produto(Id, nome, descricao, preco, quantidadeestoque);
        try {
            produtoRepository.save(novoProduto);
            return ResponseEntity.ok("Produto inserido com sucesso");
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao inserir o produto");
        }
    }
}
