import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Cart {
    private Map<Long, Integer> cart;

    public Cart(){
        cart = new HashMap<>();
    }

    public void showItems(Set<Product> productSet){

        System.out.println("-------------------- 장바구니 목록 --------------------");
        for(Product product : productSet){
            if(cart.get(product.getKey()) != null)
                System.out.printf("상품 이름 : %-10.10s , 상품 가격 : %d원 \n", product.getName() , product.getPrice());
        }
    }

    public String addProduct(Long id , int cnt){

        if(cart.get(id) != null){
            cart.put(id, cart.get(id) + cnt);
        }else{
            cart.put(id, cnt);
        }

        return "주문하신 상품이 정상적으로 장바구니에 추가되었습니다";
    }

    public String removeProduct(Long id, int cnt){

        if(cart.get(id) == null){
            return "해당 상품은 장바구니에 존재하지 않습니다";
        }else{
            if(cart.get(id) - cnt <= 0){
                cart.remove(id);
            }else{
                cart.put(id, cart.get(id) - cnt);
            }
        }

        return "상품이 정상적으로 차감되었습니다";
    }
}
