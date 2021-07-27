package com.myCompany.repository;

import com.myCompany.entity.Cards;
import com.myCompany.entity.ClientAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Cards, Integer> {

    @Query(value = "SELECT * FROM account_numbers e WHERE e.first_name = :first_name", nativeQuery = true)
    List<ClientAccount> retrieveByName(@Param("first_name") String first_name);

    @Query(value = "SELECT id_card FROM cards e WHERE e.id_card = :id_card AND e.pin =:pin", nativeQuery = true)
    Integer retrieveByCardNumber(@Param("id_card") int id_card, @Param("pin") int pin);

    @Query(value = "SELECT money FROM balance e WHERE e.id_card = :id_card", nativeQuery = true)
    Integer checkBalance(@Param("id_card") int id_card);

    @Query(value = "SELECT email FROM account_numbers e INNER JOIN cards c ON e.id_account = c.id_account WHERE id_card = :id_card", nativeQuery = true)
    String getEmail(@Param("id_card") int id_card);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE balance SET money = money + :addMoney WHERE id_card = :id_cards", nativeQuery = true)
    void depositMoney(@Param("id_cards") int id_cards, @Param("addMoney") int addMoney);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE balance SET money = money - :removeMoney WHERE id_card = :id_cards", nativeQuery = true)
    void withdrawMoney(@Param("id_cards") int id_cards, @Param("removeMoney") int removeMoney);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT INTO logging (id_card, money, date, operation, balance_before_operation, balance_after_operation) VALUES(:id_cards, :money, :date, :operationType, :balanceBeforeOperation, :balanceAfterOperation)", nativeQuery = true)
    void logging(@Param("id_cards") int id_cards, @Param("operationType") String operationType, @Param("money") int money, @Param("date") Date date, @Param("balanceBeforeOperation") int balanceBeforeOperation, @Param("balanceAfterOperation") int balanceAfterOperation);


}
