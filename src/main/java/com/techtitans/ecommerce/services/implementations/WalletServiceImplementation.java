package com.techtitans.ecommerce.services.implementations;

import com.techtitans.ecommerce.models.Wallet;
import com.techtitans.ecommerce.repositories.WalletRepository;
import com.techtitans.ecommerce.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletServiceImplementation implements WalletService {

    @Autowired
    WalletRepository walletRepository;

    @Override
    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }

    @Override
    public Wallet getWalletById(Long id) {
        return walletRepository.findById(id).orElse(null);
    }

    @Override
    public Wallet getWalletByNumber(String number) {
        return walletRepository.findByNumber(number);
    }

    @Override
    public void saveWallet(Wallet wallet) {
        walletRepository.save(wallet);
    }
}
