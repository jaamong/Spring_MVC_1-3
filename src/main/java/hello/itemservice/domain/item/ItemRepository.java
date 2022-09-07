package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); //static, 실제로는 HashMap 사용 X
    private static long sequence = 0L; //static

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    /**
     * store.values() 그대로 반환해도 되지만 (Collection)
     * ArrayList<>로 한번 감싸서 반환하면 실제 store에는 변화가 없다. (안전)
     */
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    /**
     * 실제로는 updateParam 값 3개를 별도로 해당 타입의 객체를 만드는 게 맞다.
     * 이 메서드 안에서 id가 사용이 안되기 때문.
     * 이 경우에는 ItemParamDto를 만들어서 ItemName, price, quantity를 넣어놓는다.
     */
    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    /**
     * 테스트용
     */
    public void clearStore() {
        store.clear();
    }
}
