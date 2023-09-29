package bg.softuni.springdataintro;


import bg.softuni.springdataintro.models.Account;
import bg.softuni.springdataintro.models.User;
import bg.softuni.springdataintro.services.AccountService;
import bg.softuni.springdataintro.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final UserService userService;
    private final AccountService accountService;

    @Autowired
    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
//        this.userService.register("first", 20);
        User user = this.userService.findByUsername("first");

        this.accountService.depositMoney(
                BigDecimal.TEN,
                user.getAccountIds().get(0));

        this.accountService.withdrawMoney(
                BigDecimal.ONE,
                user.getAccountIds().get(0));
    }

}
