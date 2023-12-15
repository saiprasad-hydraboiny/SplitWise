package com.scaler.splitwise;

import com.scaler.splitwise.commands.CommandExecutor;
import com.scaler.splitwise.commands.SettleUpCommand;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Scanner;


@EnableJpaAuditing
@SpringBootApplication
public class SplitWiseApplication implements CommandLineRunner {

    private CommandExecutor commandExecutor;
    private Scanner scanner=new Scanner(System.in);

    public static void main(String[] args) {

        SpringApplication.run(SplitWiseApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        commandExecutor.addCommand(new SettleUpCommand());

        while(true){
            String input=scanner.next();
            commandExecutor.execute(input);
        }

    }
}
