package com.techtitans.ecommerce.models;

import com.techtitans.ecommerce.enums.PaymentType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity
public class Wallet {

    @Id
    @GeneratedValue(strategy = AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String walletNumber;

    private Double balance;

    @OneToOne(mappedBy = "wallet", fetch = EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "wallet", fetch = EAGER)
    private Set<ShoppingCart> shoppingCarts = new HashSet<>();

    public Wallet() {
    }

    public Wallet(String walletNumber,
                  PaymentType depositMethod,
                  Double balance) {
        this.walletNumber = walletNumber;
        this.balance = balance;
    }

    public void addWallet(ShoppingCart shoppingCart){
        shoppingCart.setWallet(this);
        shoppingCarts.add(shoppingCart);
    }

    public Long getId() {
        return id;
    }

    public String getWalletNumber() {
        return walletNumber;
    }

    public void setWalletNumber(String walletNumber) {
        this.walletNumber = walletNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(Set<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }
}
