package hello.itemservice.domain.item;

import lombok.Data;

//핵심 모델 도메인에서 @Data를 쓰는 건 위험함, Dto에서 쓰는 건 괜춘(but, 확인 필수). 여기서는 쓸거임
@Data
//@Getter @Setter
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
