package com.techtitans.ecommerce.models;

import com.techtitans.ecommerce.enums.PaymentType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String operationCode;

    private LocalDateTime creationDate;

    private PaymentType paymentMethod;

    private Boolean aprobated;

    private Double total;

    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @OneToMany(mappedBy = "shoppingCart")
    private Set<CartProduct> cartProducts;

    @OneToOne(mappedBy = "shoppingCart", fetch = EAGER)
    @JoinColumn(name = "order_id")
    private Order order;

    public ShoppingCart() {
    }

    public ShoppingCart(String operationCode,
                        LocalDateTime creationDate,
                        PaymentType paymentMethod,
                        Double total) {
        this.operationCode = operationCode;
        this.creationDate = creationDate;
        this.paymentMethod = paymentMethod;
        this.total = total;
    }

    public void addOrder(Order oerder){
        order.setShoppingCart(this);
        this.order = order;
    }

    public void addCartProduct(CartProduct cartProduct){
        cartProduct.setShoppingCart(this);
        cartProducts.add(cartProduct);
    }

    public String getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public PaymentType getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentType paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Boolean getAprobated() {
        return aprobated;
    }

    public void setAprobated(Boolean aprobated) {
        this.aprobated = aprobated;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}
