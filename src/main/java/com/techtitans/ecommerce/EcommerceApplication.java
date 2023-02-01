package com.techtitans.ecommerce;

import com.techtitans.ecommerce.enums.PaymentType;
import com.techtitans.ecommerce.enums.ProductType;
import com.techtitans.ecommerce.models.*;
import com.techtitans.ecommerce.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}
	@Autowired
	PasswordEncoder passwordEncoder;


	@Bean
	public CommandLineRunner initData(CustomerRepository customerRepository,
									  WalletRepository walletRepository,
									  ProductRepository productRepository,
									  ShoppingCartRepository shoppingCartRepository,
									  CartProductRepository cartProductRepository){
		return(args) ->{

			Customer melba = new Customer("Melba", "Morel", "melba@mindhub.com", passwordEncoder.encode("asd005"), LocalDateTime.now(), "calle 166", LocalDate.now().minusYears(5), "+5493764705569");
			customerRepository.save(melba);
			Customer manu = new Customer("Manu", "Morel", "manu@mindhub.com", passwordEncoder.encode("asd004"), LocalDateTime.now(), "calle 100", LocalDate.now().minusYears(9), "+5493764788954");
			customerRepository.save(manu);
			Customer jose = new Customer("Jose", "Morel", "jose@mindhub.com", passwordEncoder.encode("asd003"), LocalDateTime.now(), "calle 1624", LocalDate.now().minusYears(15), "+5493764209567");
			customerRepository.save(jose);

			Wallet VIN001 = new Wallet("VIN-001", 1899D);
			walletRepository.save(VIN001);
			melba.addWallet(VIN001);
			Wallet VIN002 = new Wallet("VIN-002", 1899789D);
			walletRepository.save(VIN002);
			manu.addWallet(VIN002);
			Wallet VIN003 = new Wallet("VIN-003", 18991458.99D);
			walletRepository.save(VIN003);
			jose.addWallet(VIN003);

			Set<String> categories1 = new HashSet<>();
			categories1.add("Monitor");
			categories1.add("LED");
			categories1.add("HD");

			Set<String> categories2= new HashSet<>();
			categories2.add("Mouse");
			categories2.add("RGB");
			categories2.add("HyperX");

			Set<String> categories3= new HashSet<>();
			categories3.add("Mouse Pad");
			categories3.add("RGB");
			categories3.add("HyperX");

			List<String> image = new ArrayList<>();
			image.add("prueba");
			image.add("pruebas");
			image.add("pruebitas");

			Product monitor = new Product("Monitor", 200D, "MN-589", "Es un lindo monitor", 25, "AMD", categories1, ProductType.MONITOR, image);
			productRepository.save(monitor);
			Product mouse = new Product("Mouse", 20D, "MS-59", "Es un lindo mouse", 100, "HyperX", categories2, ProductType.MOUSE, image);
			productRepository.save(mouse);
			Product mousepad = new Product("Mouse Pad", 200D, "MP-8759", "Es un lindo mousepad", 100, "HyperX", categories3, ProductType.MOUSE_PAD, image);
			productRepository.save(mousepad);


			ShoppingCart cartMelba = new ShoppingCart("CODE-58917", LocalDateTime.now(), PaymentType.CBU);
			shoppingCartRepository.save(cartMelba);
			VIN001.addShoppingCart(cartMelba);

			CartProduct product1 = new CartProduct(5);
			cartProductRepository.save(product1);
			monitor.addCartProduct(product1);
			monitor.setStock(monitor.getStock()-product1.getQuantity());
			cartMelba.addCartProduct(product1);

			CartProduct product2 = new CartProduct(2);
			cartProductRepository.save(product2);
			mouse.addCartProduct(product2);
			mouse.setStock(mouse.getStock() - product2.getQuantity());
			cartMelba.addCartProduct(product2);

			Double total1 = cartMelba.getCartProducts().stream().mapToDouble(cartProduct -> cartProduct.getProduct().getPrice() * cartProduct.getQuantity()).sum();
			cartMelba.setTotal(total1);
			cartMelba.getWallet().setBalance(cartMelba.getWallet().getBalance() - total1);

			//saves

			customerRepository.save(melba);
			customerRepository.save(manu);
			customerRepository.save(jose);
			walletRepository.save(VIN001);
			walletRepository.save(VIN002);
			walletRepository.save(VIN003);
			productRepository.save(monitor);
			productRepository.save(mouse);
			productRepository.save(mousepad);
			shoppingCartRepository.save(cartMelba);
			cartProductRepository.save(product1);
			cartProductRepository.save(product2);
		};
	}
}
