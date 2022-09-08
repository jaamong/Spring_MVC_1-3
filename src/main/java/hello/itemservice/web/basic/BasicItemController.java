package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    /**
     * Get으로 오면 addForm 호출
     */
    @GetMapping("/add")
    public String addForm() {
        return "/basic/addForm";
    }

    /**
     * addForm에서 Post로 오면 save 호출
     */
//    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                            @RequestParam Integer price,
                            @RequestParam Integer quantity,
                            Model model) {

        //아래는 4줄은 @ModelAttribute와 동일
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "/basic/item";
    }

    /**
     * @ModelAttribute는 두가지를 같이 처리해준다.
     * 1. 모델 객체 생성
     * 2. 생성한 모델 객체를 뷰에 삽입 : 그때 이름은 @ModelAttribute("이름 속성")에 지정한 것
     * ex. @ModelAttribute("itemA")라고 하면 model.addAttribute("itemA", ... ) 라고 담긴다.
     */
//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item) {

        itemRepository.save(item);
//        model.addAttribute("item", item); //자동 추가, 생략 가능

        return "/basic/item";
    }

    /**
     * @ModelAttribute 이름 속성 생략 가능
     * 생략하면 클래스 명에서 첫글자가 소문자로 치환되어 model.addAttribute에 담긴다
     */
//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {

        itemRepository.save(item);
        return "/basic/item";
    }

    /**
     * @ModelAttribute 생략 가능
     */
//    @PostMapping("/add")
    public String addItemV4(Item item) {

        itemRepository.save(item);
        return "/basic/item";
    }

    /**
     * 상품 등록 후 중복 방지를 위해 redirect (PRG)
     */
//    @PostMapping("/add")
    public String addItemV5(Item item) {

        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId(); //url에 변수 더해서 사용 : url 인코딩이 안되서 위험
    }

    /**
     * RedirectAttribute
     *  - url 인코딩 해결
     *  - pathVariable 바인딩
     *  - 나머지는 쿼리 파라미터로 처리
     */
    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    /**
     * HTML Form 전송은 PUT, PATCH를 지원하지 않는다.
     * GET, POST만 사용할 수 있다.
     */
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }


    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}