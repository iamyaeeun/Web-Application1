package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookForm {

    private Long id; //수정하기 위해 id 필요

    private String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;
}
