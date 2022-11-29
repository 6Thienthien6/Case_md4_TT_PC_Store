package com.cg.model.dto;

import com.cg.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ProductAvatarDTO  {
    private Long id;
 @NotEmpty(message="Title must be not empty !")
    private String name;

    @NotBlank(message = "Amount must be not empty !")
    @Pattern(regexp = "^[0-9]+$", message = "Quantity must be a positive integer!")
    @Min(value = 1, message = "Small quantity minimum is 1 product")
    @Max(value = 1000, message= "Maximum quantity of 1,000 products")
    private int amount;
    @NotBlank(message = "Please enter price!")
    @Pattern(regexp = "^\\d*(\\.\\d+)?$", message = "Price must be a positive integer!")
    @Min(value = 50000, message="Min Price is 50.000 đ")
    @Max(value = 1000000000, message="Maximum Price là 1.000.000.000 đ")
    private BigDecimal price;
    @NotBlank(message = "Product information cannot be blank!")
    private String description;

    @NotBlank(message = "Photos cannot be blank!")
    MultipartFile file;

    public Product toProduct(){
        return new Product()
                .setId(id)
                .setName(name)
                .setAmount(amount)
                .setPrice(price)
                .setDescription(description);
    }

}
