package com.scaler.splitwise.Services;


import com.scaler.splitwise.models.Expense;
import com.scaler.splitwise.models.ExpenseUser;
import com.scaler.splitwise.models.Group;
import com.scaler.splitwise.models.User;
import com.scaler.splitwise.repositories.ExpenseRepository;
import com.scaler.splitwise.repositories.ExpenseUserRepository;
import com.scaler.splitwise.repositories.GroupRepository;
import com.scaler.splitwise.repositories.UserRepository;
import com.scaler.splitwise.strategies.SettleUpStrategy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SettleUpService {

    private UserRepository userRepository;
    private ExpenseUserRepository expenseUserRepository;
    private SettleUpStrategy settleUpStrategy;
    private GroupRepository groupRepository;
    private ExpenseRepository expenseRepository;

    public List<Transaction> settleUpUser(Long userId){

        //1.Get all the expenses the user is part of
        //2.Iterate through all the expenses and find out all the people involved and how much is owed/owes by each
        //3.Use Min and Max Algo to calculate the List of transactions
        //4.return the list of transactions
        Optional<User>userOptional=userRepository.findById(userId);
        if(userOptional.isEmpty()) throw new RuntimeException();

        User user=userOptional.get();

        List<ExpenseUser>expenseUsers=expenseUserRepository.findAllbyUser(user);
        List<Expense>expenses=new ArrayList<>();
        for(ExpenseUser expenseUser:expenseUsers){
            expenses.add(expenseUser.getExpense());
        }

        List<Transaction>transactions=settleUpStrategy.settle(expenses);

        List<Transaction>filteredTransactions=new ArrayList<>();
        for(Transaction transaction: transactions){
            if(transaction.getFrom().equals(user) || transaction.getTo().equals(user)){
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;

    }

    public List<Transaction> settleUpGroup(Long groupId){

        Optional<Group>groupOptional=groupRepository.findById(groupId);
        if(groupOptional.isEmpty())throw new RuntimeException();

        Group group= groupOptional.get();
        List<Expense>expenses=expenseRepository.findAllByGroup(group);

        List<Transaction>transactions=settleUpStrategy.settle(expenses);

        return transactions;

    }
}
