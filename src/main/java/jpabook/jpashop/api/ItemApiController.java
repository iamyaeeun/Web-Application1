package jpabook.jpashop.api;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ItemApiController {

    private final ItemService itemService;

    @GetMapping("/api/items")
    public ItemResult itemList(){
        List<Item> items = itemService.findItems();

        List<ItemDto> itemCollect=items.stream()
                .map(item -> new ItemDto(item.getName(), item.getPrice(), item.getStockQuantity()))
                .collect(Collectors.toList());

        return new ItemResult(itemCollect);
    }

   @PostMapping("/api/items/register")
   public CreateItemResponse saveItem(@RequestBody @Valid CreateItemRequest request){

        Book book=new Book();
        book.setName(request.getName());
        book.setPrice(request.getPrice());
        book.setStockQuantity(request.getStockQuantity());

        itemService.saveItem(book);
        return new CreateItemResponse(book.getId());
   }

   @Data
   static class CreateItemRequest{
        private String name;
        private int price;
       private int stockQuantity;
   }

   @Data
   static class CreateItemResponse{
        private Long id;

        public CreateItemResponse(Long id){this.id=id;}
   }

    @Data
    @AllArgsConstructor
    static class ItemResult<T>{
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class ItemDto{
        private String name;
        private int price;
        private int stockQuantity;
    }
}
