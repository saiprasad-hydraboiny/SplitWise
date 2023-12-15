package com.scaler.splitwise.dtos;

import com.scaler.splitwise.Services.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
public class SettleUpUserResponseDto {

    private List<Transaction>transactions;
}
