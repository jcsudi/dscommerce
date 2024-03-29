package com.jcjrta.dscommerce.services;

import com.jcjrta.dscommerce.dto.CategoryDTO;
import com.jcjrta.dscommerce.dto.ProductDTO;
import com.jcjrta.dscommerce.dto.ProductMinDTO;
import com.jcjrta.dscommerce.entities.Category;
import com.jcjrta.dscommerce.entities.Product;
import com.jcjrta.dscommerce.repositories.ProductRepository;
import com.jcjrta.dscommerce.services.exceptions.DatabaseExceptions;
import com.jcjrta.dscommerce.services.exceptions.ResourceNotFoundExceptions;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO f(Long id){
        Optional<Product> result = repository.findById(id);
        Product p = result.orElseThrow(() -> new ResourceNotFoundExceptions("Recurso não encontrado"));
        ProductDTO productDTO = new ProductDTO(p);
        return productDTO;
    }

    @Transactional(readOnly = true)
    public Page<ProductMinDTO> findAll(String name, Pageable pageable){
        Page<Product> result =  repository.searchByName(name, pageable);
        return result.map(x -> new ProductMinDTO(x));
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto){
        Product entity = new Product();
        /*entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());*/
        copyDtoToEntity(dto, entity);
        Product entitySalvar = repository.save(entity);
        ProductDTO dt = new ProductDTO(entitySalvar);
        return dt;

    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto){
        try {
            Product entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            Product entitySalvar = repository.save(entity);
            ProductDTO dt= new ProductDTO(entitySalvar);
            return dt;
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundExceptions("Produto não encontrado");
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        try{
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundExceptions("Recurso não encontrado");
        }
        catch (DataIntegrityViolationException e){
            throw new DatabaseExceptions("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());
        entity.setDescription(dto.getDescription());
        entity.getCategories().clear();
        for (CategoryDTO catDTO : dto.getCategories()){
            Category cat = new Category();
            cat.setId(catDTO.getId());
            entity.getCategories().add(cat);
        }
    }
}
