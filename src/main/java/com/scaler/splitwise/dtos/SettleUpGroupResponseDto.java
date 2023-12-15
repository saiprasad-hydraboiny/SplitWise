package com.scaler.splitwise.dtos;

import com.scaler.splitwise.Services.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class SettleUpGroupResponseDto {
    private List<Transaction>transactions;
}
