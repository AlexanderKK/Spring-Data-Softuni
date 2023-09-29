package bg.softuni.springdataintro.services;

import bg.softuni.springdataintro.models.Account;
import bg.softuni.springdataintro.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal amount, Long id) {
        Optional<Account> account = this.accountRepository.findById(id);
        if(account.isEmpty()) {
            throw new RuntimeException("Account does not exist");
        }

        BigDecimal current = account.get().getBalance();
        if(current.compareTo(amount) < 0) {
            throw new RuntimeException("Cannot withdraw");
        }

        account.get().setBalance(current.subtract(amount));

        this.accountRepository.save(account.get());
    }

    @Override
    public void depositMoney(BigDecimal amount, Long id) {
        Account account = this.accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not present"));

        BigDecimal currentBalance = account.getBalance();
        BigDecimal newBalance = currentBalance.add(amount);
        account.setBalance(newBalance);

        this.accountRepository.save(account);
    }

}
