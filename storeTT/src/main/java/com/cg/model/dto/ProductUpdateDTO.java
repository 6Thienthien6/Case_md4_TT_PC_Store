package com.cg.model.dto;

import com.cg.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ProductUpdateDTO {
    private Long id;

    private String name;

    @NotBlank(message = "Amount not null!")
    @Pattern(regexp = "^[0-9]+$", message = "\n" +
            "Quantity must be a positive integer!")
    private int amount;
    @NotBlank(message = "Price is not null!")
    @Pattern(regexp = "^\\d*(\\.\\d+)?$", message = "Price must be a number not less than 0")
    private BigDecimal price;
    @NotBlank(message = "\n" +
            "Product information cannot be blank!")
    private String description;

    @NotBlank(message = "Photos cannot be left blank!")

    public Product toProduct(){
        return new Product()
                .setId(id)
                .setName(name)
                .setAmount(amount)
                .setPrice(price)
                .setDescription(description);
    }
}