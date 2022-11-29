package com.cg.service.product;

import com.cg.model.Product;
import com.cg.model.dto.ProductDTO;
import com.cg.model.dto.UserDTO;
import com.cg.service.IGeneralService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductService extends IGeneralService<Product> {
    void softDelete(Long productId);

//    List<ProductDTO> getAllProduct();
    Product saveWithAvatar (Product product, MultipartFile avatarFile);
    List<ProductDTO> getAllProductWhereDeletedIsFalse();
}
