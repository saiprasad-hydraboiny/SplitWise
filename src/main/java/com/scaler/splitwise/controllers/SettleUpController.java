package com.scaler.splitwise.controllers;


import com.scaler.splitwise.Services.SettleUpService;
import com.scaler.splitwise.Services.Transaction;
import com.scaler.splitwise.dtos.SettleUpGroupRequestDto;
import com.scaler.splitwise.dtos.SettleUpGroupResponseDto;
import com.scaler.splitwise.dtos.SettleUpUserRequestDto;
import com.scaler.splitwise.dtos.SettleUpUserResponseDto;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class SettleUpController {

    private SettleUpService settleUpService;

    public SettleUpUserResponseDto settleUpUser(SettleUpUserRequestDto request){

        SettleUpUserResponseDto response=new SettleUpUserResponseDto();
        List<Transaction>transactions=settleUpService.settleUpUser(request.getUserId());
        response.setTransactions(transactions);
        return response;
    }

    public SettleUpGroupResponseDto settleUpGroup(SettleUpGroupRequestDto request){
        SettleUpGroupResponseDto response=new SettleUpGroupResponseDto();
        List<Transaction>transactions=settleUpService.settleUpGroup(request.getGroupId());
        response.setTransactions(transactions);
        return response;
    }
}
