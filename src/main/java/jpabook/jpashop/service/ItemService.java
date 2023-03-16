package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId,String name,int price,int stockQuantity){
        Item findItem=itemRepository.findOne(itemId); //id를 기반으로 실제 DB에 있는 영속 상태 엔티티를 찾아옴
        //findItem은 영속 상태이므로 준영속과 달리 itemRepository.save, em.persist, merge 호출할 필요 없음
        //Transaction이 커밋되면 플러쉬를 날려 영속성 컨텍스트에 있는 엔티티(findItem) 변경 감지 후 DB에 업데이트 쿼리 날려 업데이트 함
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
    }

    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }
}
