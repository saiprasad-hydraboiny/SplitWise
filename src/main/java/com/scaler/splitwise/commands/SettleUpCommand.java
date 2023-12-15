package com.scaler.splitwise.commands;

import com.scaler.splitwise.controllers.SettleUpController;
import com.scaler.splitwise.dtos.SettleUpGroupRequestDto;
import com.scaler.splitwise.dtos.SettleUpUserRequestDto;
import com.scaler.splitwise.dtos.SettleUpUserResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;



@Component
public class SettleUpCommand implements Command{

    private SettleUpController settleUpController;
    @Override
    public void execute(String input) {
        List<String>words=List.of(input.split(" "));
        Long userId=Long.valueOf(words.get(0));

        SettleUpUserRequestDto request=new SettleUpUserRequestDto();
        request.setUserId(userId);
        SettleUpUserResponseDto response=settleUpController.settleUpUser(request);

    }

    @Override
    public boolean matches(String input) {

        List<String>words=List.of(input.split(" "));

        return words.size()==2 && words.get(1).equalsIgnoreCase(CommandKeywords.settleUp);


    }
}
