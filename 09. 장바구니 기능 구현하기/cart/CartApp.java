import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CartApp {
    public static void main(String[] args) throws Exception {
        String path = "src/cart/product.csv";
        String line = "";
        String[] input;

        BufferedReader br = new BufferedReader(new FileReader(path));
        Set<Product> productSet = new HashSet<>(); // 제품 목록

        // TODO : 클래스를 생성하여 상품 목록에 넣는다 ( CSV 파일을 활용하여 )
        while((line = br.readLine()) != null){

            input = line.split(",");
            Long id = Long.parseLong(input[0]);
            String name = input[1];
            int price = Integer.parseInt(input[2]);

            productSet.add(new Product(id, name, price));
        }

        // 상품 목록 확인
        System.out.println("-------------------- 상품 목록 --------------------");
        for(Product product : productSet){
            System.out.printf("상품 이름 : %-10.10s , 상품 가격 : %d원 \n", product.getName() , product.getPrice());
        }

        Cart myCart = new Cart();

        // TODO :  상품을 장바구니에 추가
        System.out.println(myCart.addProduct(1L, 2));
        System.out.println(myCart.addProduct(1L, 3));
        System.out.println(myCart.addProduct(3L, 7));
        System.out.println(myCart.addProduct(2L, 5));
        System.out.println(myCart.addProduct(5L, 1));

        // TODO : 상품을 장바구니에서 제거
        System.out.println(myCart.removeProduct(1L ,4));
        System.out.println(myCart.removeProduct(5L ,2));

        // TODO :  장바구니에 현재 담긴 상품들을 출력 ( 상품이름, 각 상품의 개수 );
        myCart.showItems(productSet);



    }
}
