package net.cosmogrp.economy;

import net.cosmogrp.economy.account.EconomyAccount;
import net.cosmogrp.economy.message.Sender;
import net.cosmogrp.economy.transaction.amount.TransactionAmount;
import org.jetbrains.annotations.Nullable;

public interface EconomyHandler {

    @Nullable EconomyAccount getAccount(Sender target);

    @Nullable EconomyAccount getTargetAccount(
            Sender sender,
            Sender target
    );

    boolean deposit(
            @Nullable Sender source, Sender target,
            double amount
    );

    default boolean deposit(Sender target, double amount) {
        return deposit(null, target, amount);
    }

    boolean withdraw(
            @Nullable Sender source, Sender target,
            double amount
    );

    default boolean withdraw(Sender target, double amount) {
        return withdraw(null, target, amount);
    }

    boolean transfer(
            Sender source, Sender target,
            TransactionAmount amount
    );

    boolean hasEnough(Sender source, double amount);

    double getBalance(Sender source);

    void sendBalance(Sender source);

}
