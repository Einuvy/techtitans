package com.techtitans.ecommerce.controllers;

import com.techtitans.ecommerce.dto.CustomerDTO;
import com.techtitans.ecommerce.dto.CustomerRegisterDTO;
import com.techtitans.ecommerce.models.Customer;
import com.techtitans.ecommerce.models.Wallet;
import com.techtitans.ecommerce.services.implementations.CustomerServiceImplementation;
import com.techtitans.ecommerce.services.implementations.WalletServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.techtitans.ecommerce.utils.RandomNumberUtils.getRandomNumber5;
import static com.techtitans.ecommerce.utils.VerificationUtils.isMissing;

@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    CustomerServiceImplementation customerService;
    @Autowired
    WalletServiceImplementation walletService;

    @GetMapping("/customers")
    public List<CustomerDTO> getCustomers(){
        return customerService.getAllCustomers().stream().map(customer -> new CustomerDTO(customer)).collect(Collectors.toList());
    }

    @GetMapping("/customer/{id}")
    public CustomerDTO getCustomer(@PathVariable Long id){
        return new CustomerDTO(customerService.findCustomerById(id));
    }

    @PostMapping("/customers")
    public ResponseEntity<Object> createCustomer(@RequestBody CustomerRegisterDTO customerRegister){

        if(isMissing(customerRegister.getFirstName())){
            return new ResponseEntity<>("Missing First Name", HttpStatus.FORBIDDEN);
        }
        if (isMissing(customerRegister.getLastName())){
            return new ResponseEntity<>("Missing Last Name", HttpStatus.FORBIDDEN);
        }
        if (isMissing(customerRegister.getEmail())){
            return new ResponseEntity<>("Missing Email", HttpStatus.FORBIDDEN);
        }
        if (isMissing(customerRegister.getPassword())){
            return new ResponseEntity<>("Missing Password", HttpStatus.FORBIDDEN);
        }
        if (isMissing(customerRegister.getAddress())){
            return new ResponseEntity<>("Missing Address", HttpStatus.FORBIDDEN);
        }
        if (isMissing(customerRegister.getPhoneNumber())){
            return new ResponseEntity<>("Missing Phone number", HttpStatus.FORBIDDEN);
        }
        if (customerRegister.getBirthDate().isAfter(LocalDate.now())){
            return new ResponseEntity<>("Wrong birth date", HttpStatus.FORBIDDEN);
        }
        if(customerRegister.getBirthDate().isBefore(LocalDate.now().minusYears(13))){
            return new ResponseEntity<>("Pal lobi", HttpStatus.FORBIDDEN);
        }
        if (isMissing(customerRegister.getBirthDate().toString())){
            return new ResponseEntity<>("Missing birth date", HttpStatus.FORBIDDEN);
        }

        Customer customer = new Customer(customerRegister.getFirstName(),
                                         customerRegister.getLastName(),
                                         customerRegister.getEmail(),
                                         customerRegister.getPassword(),
                                         LocalDateTime.now(),
                                         customerRegister.getAddress(),
                                         customerRegister.getBirthDate(),
                                         customerRegister.getPhoneNumber());
        customerService.saveCustomer(customer);

        String walletNumber;
        do {
            Integer number = getRandomNumber5();
            walletNumber = "WALL-" + number.toString();
        }while (walletService.getWalletByNumber(walletNumber) != null);

        Wallet wallet = new Wallet(walletNumber, 0D);
        customer.addWallet(wallet);
        walletService.saveWallet(wallet);
        customerService.saveCustomer(customer);

        return new ResponseEntity<>("Account Created",HttpStatus.CREATED);
    }
}
