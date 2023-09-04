package com.talentwunder.financetracker;

import com.talentwunder.financetracker.enumeration.Role;
import com.talentwunder.financetracker.enumeration.TransactionType;
import com.talentwunder.financetracker.model.Transaction;
import com.talentwunder.financetracker.model.User;
import com.talentwunder.financetracker.repository.TransactionRepository;
import com.talentwunder.financetracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RequiredArgsConstructor
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TransactionTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testGetAllTransactionsOfUser() {

        Transaction transaction1 = new Transaction();

        transaction1.setId(1L);
        transaction1.setDescription("Some new description");
        transaction1.setAmount(250.5);
        transaction1.setDeleted(false);
        transaction1.setTransactionType(TransactionType.EXPENSE);

        User user1 = new User();
        user1.setAddress("Novi Sad");
        user1.setId(1L);
        user1.setEmail("hello@gmail.com");
        user1.setMobileNumber("0639561297");
        user1.setFirstname("John");
        user1.setLastname("Doe");
        user1.setDeleted(false);
        user1.setImageUrl("None for now");
        user1.setDateOfBirth(LocalDate.now());
        user1.setPassword("password");
        user1.setRole(Role.ADMIN);

        userRepository.save(user1);

        transaction1.setUser(user1);
        transactionRepository.save(transaction1);

        Transaction transaction2 = new Transaction();

        transaction2.setId(2L);
        transaction2.setDescription("Some new description");
        transaction2.setAmount(550.5);
        transaction2.setDeleted(false);
        transaction2.setTransactionType(TransactionType.INCOME);

        Transaction transaction3 = new Transaction();

        transaction3.setId(3L);
        transaction3.setDescription("Some new description");
        transaction3.setAmount(550.5);
        transaction3.setDeleted(false);
        transaction3.setTransactionType(TransactionType.INCOME);

        User user2 = new User();
        user2.setAddress("Novi Sad");
        user2.setId(2L);
        user2.setEmail("hello2@gmail.com");
        user2.setMobileNumber("0619561297");
        user2.setFirstname("John");
        user2.setLastname("Doe");
        user2.setDeleted(false);
        user2.setImageUrl("None for now");
        user2.setDateOfBirth(LocalDate.now());
        user2.setPassword("password");
        user2.setRole(Role.USER);
        transaction2.setUser(user1);

        userRepository.save(user2);
        transaction3.setUser(user2);
        transactionRepository.save(transaction2);
        transactionRepository.save(transaction3);


        entityManager.merge(transaction1);
        entityManager.merge(transaction2);
        entityManager.merge(transaction3);
        entityManager.flush();

        List<Transaction> result = transactionRepository.findByUserId(1L);
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    public void testGetAllTransactions() {
        Transaction transaction1 = new Transaction();

        transaction1.setId(1L);
        transaction1.setDescription("Some new description");
        transaction1.setAmount(250.5);
        transaction1.setDeleted(false);
        transaction1.setTransactionType(TransactionType.EXPENSE);

        User user1 = new User();
        user1.setAddress("Novi Sad");
        user1.setId(1L);
        user1.setEmail("hello@gmail.com");
        user1.setMobileNumber("0639561297");
        user1.setFirstname("John");
        user1.setLastname("Doe");
        user1.setDeleted(false);
        user1.setImageUrl("None for now");
        user1.setDateOfBirth(LocalDate.now());
        user1.setPassword("password");
        user1.setRole(Role.ADMIN);

        userRepository.save(user1);

        transaction1.setUser(user1);
        transactionRepository.save(transaction1);

        Transaction transaction2 = new Transaction();

        transaction2.setId(2L);
        transaction2.setDescription("Some new description");
        transaction2.setAmount(550.5);
        transaction2.setDeleted(false);
        transaction2.setTransactionType(TransactionType.INCOME);

        Transaction transaction3 = new Transaction();

        transaction3.setId(3L);
        transaction3.setDescription("Some new description");
        transaction3.setAmount(550.5);
        transaction3.setDeleted(false);
        transaction3.setTransactionType(TransactionType.INCOME);

        User user2 = new User();
        user2.setAddress("Novi Sad");
        user2.setId(2L);
        user2.setEmail("hello2@gmail.com");
        user2.setMobileNumber("0619561297");
        user2.setFirstname("John");
        user2.setLastname("Doe");
        user2.setDeleted(false);
        user2.setImageUrl("None for now");
        user2.setDateOfBirth(LocalDate.now());
        user2.setPassword("password");
        user2.setRole(Role.USER);
        transaction2.setUser(user1);

        userRepository.save(user2);
        transaction3.setUser(user2);
        transactionRepository.save(transaction2);
        transactionRepository.save(transaction3);


        entityManager.merge(transaction1);
        entityManager.merge(transaction2);
        entityManager.merge(transaction3);
        entityManager.flush();

        List<Transaction> result = transactionRepository.findAll();
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    public void testDeleteTransaction() {
        Transaction transaction1 = new Transaction();

        transaction1.setId(1L);
        transaction1.setDescription("Some new description");
        transaction1.setAmount(250.5);
        transaction1.setDeleted(false);
        transaction1.setTransactionType(TransactionType.EXPENSE);

        User user1 = new User();
        user1.setAddress("Novi Sad");
        user1.setId(1L);
        user1.setEmail("hello@gmail.com");
        user1.setMobileNumber("0639561297");
        user1.setFirstname("John");
        user1.setLastname("Doe");
        user1.setDeleted(false);
        user1.setImageUrl("None for now");
        user1.setDateOfBirth(LocalDate.now());
        user1.setPassword("password");
        user1.setRole(Role.ADMIN);

        userRepository.save(user1);

        transaction1.setUser(user1);
        transactionRepository.save(transaction1);

        transactionRepository.delete(transaction1);

        boolean transactionExists = entityManager.find(Transaction.class, 1L) != null;
        assertThat(transactionExists).isFalse();
    }

    @Test
    public void testSaveTransaction() {
        Transaction transaction1 = new Transaction();

        transaction1.setId(1L);
        transaction1.setDescription("Some new description");
        transaction1.setAmount(250.5);
        transaction1.setDeleted(false);
        transaction1.setTransactionType(TransactionType.EXPENSE);

        User user1 = new User();
        user1.setAddress("Novi Sad");
        user1.setId(1L);
        user1.setEmail("hello@gmail.com");
        user1.setMobileNumber("0639561297");
        user1.setFirstname("John");
        user1.setLastname("Doe");
        user1.setDeleted(false);
        user1.setImageUrl("None for now");
        user1.setDateOfBirth(LocalDate.now());
        user1.setPassword("password");
        user1.setRole(Role.ADMIN);

        userRepository.save(user1);

        transaction1.setUser(user1);
        transactionRepository.save(transaction1);

        boolean transactionExists = entityManager.find(Transaction.class, 1L) != null;
        assertThat(transactionExists).isTrue();
    }

}
