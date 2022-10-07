package com.example.pembejeospringboot.Services;

import com.example.pembejeospringboot.DTO.ProductRequestDTO;
import com.example.pembejeospringboot.DTO.ProductResponseDTO;
import com.example.pembejeospringboot.Exceptions.ResourceNotFoundException;
import com.example.pembejeospringboot.Models.Product;
import com.example.pembejeospringboot.Models.Supplier;
import com.example.pembejeospringboot.Repositories.ProductRepository;
import lombok.Data;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Data
@Service
public class ProductServices {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Add  product
    public Product addProduct(ProductRequestDTO productRequestDTO){
        Supplier supplier = new Supplier();
        
        supplier.setSupplierId(productRequestDTO.getSupplierId());

        Product product = modelMapper.map(productRequestDTO, Product.class);

        product.setSupplier(supplier);

        return productRepository.save(product);
        
    }

      //get all products

      public List<ProductResponseDTO> viewProducts(){
        List<ProductResponseDTO> arrayList = new ArrayList<>();
        for(Product product :productRepository.findAll()){

            ProductResponseDTO productResponseDTO = modelMapper.map(product, ProductResponseDTO.class);
            
            productResponseDTO.setProductId(product.getProductId());
            productResponseDTO.setProductDescr(product.getProductDescr());
            productResponseDTO.setProductName(product.getProductName());
            productResponseDTO.setProductPrice(product.getProductPrice());
            productResponseDTO.setCategory(product.getCategory());


            productResponseDTO.setSupplierId(product.getSupplier().getSupplierId());
            productResponseDTO.setSupplierFName(product.getSupplier().getSupplierFName());
            productResponseDTO.setSupplierLName(product.getSupplier().getSupplierLName());
            productResponseDTO.setSupplierAddress(product.getSupplier().getSupplierAddress());
            productResponseDTO.setSupplierPhoneNo(product.getSupplier().getSupplierPhoneNo());

            arrayList.add(productResponseDTO);
        }
      return arrayList;
      } 

    // get product by id
    public Optional<Product> viewProductById(Long id){
        return productRepository.findById(id);
    }

   // delete product
    public ResponseEntity<Map<String,Boolean>> removeProduct(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Invalid Id Selected"));
        productRepository.delete(product);

        Map<String, Boolean> response = new HashMap<>();
        response.put("Product is deleted!",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

   // update product
    public Product editProduct(Long id, ProductRequestDTO productRequestDTO){
        Product product1 = productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Invalid id selected"));
        
        Product product = modelMapper.map(productRequestDTO, Product.class);

        product1.setProductName(product.getProductName());
        product1.setProductDescr(product.getProductDescr());
        product1.setProductPrice(product.getProductPrice());
        product1.setCategory(product.getCategory());

        return productRepository.save(product1);
    }

    public List<ProductResponseDTO> selectProductBySupplierId(Long Id){
        List<ProductResponseDTO> arrayList = new ArrayList<>();
        for(Product product :productRepository.getProductBySupplierId(Id)){

            ProductResponseDTO productResponseDTO = modelMapper.map(product, ProductResponseDTO.class);

            productResponseDTO.setProductId(product.getProductId());
            productResponseDTO.setProductDescr(product.getProductDescr());
            productResponseDTO.setProductName(product.getProductName());
            productResponseDTO.setProductPrice(product.getProductPrice());
            productResponseDTO.setCategory(product.getCategory());


            productResponseDTO.setSupplierId(product.getSupplier().getSupplierId());
            productResponseDTO.setSupplierFName(product.getSupplier().getSupplierFName());
            productResponseDTO.setSupplierLName(product.getSupplier().getSupplierLName());
            productResponseDTO.setSupplierAddress(product.getSupplier().getSupplierAddress());
            productResponseDTO.setSupplierPhoneNo(product.getSupplier().getSupplierPhoneNo());

            arrayList.add(productResponseDTO);
        }
        return arrayList;
    }



    public List<ProductResponseDTO> selectProductByCategory(String category){

        List<ProductResponseDTO> arrayList = new ArrayList<>();
        for(Product product :productRepository.getByCategory(category)){

            ProductResponseDTO productResponseDTO = modelMapper.map(product, ProductResponseDTO.class);

            productResponseDTO.setProductId(product.getProductId());
            productResponseDTO.setProductDescr(product.getProductDescr());
            productResponseDTO.setProductName(product.getProductName());
            productResponseDTO.setProductPrice(product.getProductPrice());
            productResponseDTO.setCategory(product.getCategory());


            productResponseDTO.setSupplierId(product.getSupplier().getSupplierId());
            productResponseDTO.setSupplierFName(product.getSupplier().getSupplierFName());
            productResponseDTO.setSupplierLName(product.getSupplier().getSupplierLName());
            productResponseDTO.setSupplierAddress(product.getSupplier().getSupplierAddress());
            productResponseDTO.setSupplierPhoneNo(product.getSupplier().getSupplierPhoneNo());

            arrayList.add(productResponseDTO);
        }
        return arrayList;
    }

}
