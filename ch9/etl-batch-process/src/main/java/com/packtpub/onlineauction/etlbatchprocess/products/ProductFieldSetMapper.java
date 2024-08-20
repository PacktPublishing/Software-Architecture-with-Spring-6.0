package com.packtpub.onlineauction.etlbatchprocess.products;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class ProductFieldSetMapper implements FieldSetMapper<ProductDto> {

    @Override
    public ProductDto mapFieldSet(final FieldSet fieldSet) throws BindException {

//        String photoBase64 = fieldSet.readString("photo");  // Read the photo as a Base64 encoded string
//        byte[] photoBytes = Base64.getDecoder().decode(photoBase64);  // Decode the Base64 string to byte array


        return ProductDto.builder()
                .id(fieldSet.readInt("id"))  // Assuming id is of type Integer
                .name(fieldSet.readString("name"))
                .description(fieldSet.readString("description"))
//                .photo(photoBytes)  // Assuming the photo is a byte array stored as a string
                .userId(fieldSet.readInt("user_id"))  // Assuming userId is of type Integer
                .build();
    }
}
