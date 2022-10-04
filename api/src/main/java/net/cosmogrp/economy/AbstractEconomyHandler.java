package net.cosmogrp.economy;

import net.cosmogrp.economy.account.EconomyAccount;
import net.cosmogrp.economy.context.TransactionContext;
import net.cosmogrp.economy.message.Messenger;
import net.cosmogrp.economy.message.Sender;
import net.cosmogrp.economy.transaction.TransactionType;
import net.cosmogrp.economy.transaction.amount.TransactionAmount;
import net.cosmogrp.economy.transaction.executor.TransactionExecutor;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractEconomyHandler<T extends TransactionContext>
        implements EconomyHandler {

    private final TransactionExecutor<T> transactionExecutor;
    protected final Messenger messenger;

    protected final String identifier;

    public AbstractEconomyHandler(
            TransactionExecutor<T> transactionExecutor,
            Messenger messenger,
            String identifier
    ) {
        this.transactionExecutor = transactionExecutor;
        this.messenger = messenger;
        this.identifier = identifier;
    }

    @Override
    public boolean deposit(
            @Nullable Sender source,
            Sender target,
            double amount
    ) {
        return execute(
                source, target,
                TransactionType.DEPOSIT, amount
        );
    }

    @Override
    public boolean withdraw(
            @Nullable Sender source,
            Sender target,
            double amount
    ) {
        return execute(
                source, target,
                TransactionType.WITHDRAW, amount
        );
    }

    @Override
    public boolean transfer(
            Sender source, Sender target,
            TransactionAmount amount
    ) {
        return executeTransfer(
                source, target, amount,
                getAccount(source),
                getTargetAccount(source, target)
        );
    }

    @Override
    public void sendBalance(Sender source) {
        EconomyAccount account = getAccount(source);

        if (account == null) {
            return;
        }

        messenger.sendMessage(
                source, "balance",
                "%balance%", account.getBalance()
        );
    }

    @Override
    public boolean hasEnough(Sender source, double amount) {
        EconomyAccount account = getAccount(source);

        if (account == null) {
            return false;
        }

        return account.getBalance() >= amount;
    }

    @Override
    public double getBalance(Sender source) {
        EconomyAccount account = getAccount(source);

        if (account == null) {
            return -1;
        }

        return account.getBalance();
    }

    protected boolean executeTransfer(
            Sender source,
            @Nullable Sender target,
            TransactionAmount amount,
            EconomyAccount sourceAccount,
            EconomyAccount targetAccount
    ) {
        if (sourceAccount == null || targetAccount == null) {
            return false;
        }

        double finalAmount = amount.parse(sourceAccount);

        if (finalAmount == -1) {
            return false;
        }

        return transactionExecutor.execute(createContext(
                source, target, finalAmount,
                TransactionType.TRANSFER,
                sourceAccount, targetAccount
        ));
    }

    private boolean execute(
            Sender source, Sender target,
            TransactionType type, double amount
    ) {
        if (source == null) {
            source = defaultSource();
        }

        return transactionExecutor.execute(createContext(
                source, target, amount,
                type,
                null, getTargetAccount(source, target)
        ));
    }

    protected abstract Sender defaultSource();

    protected abstract T createContext(
            Sender source,
            Sender target,
            double amount,
            TransactionType type,
            EconomyAccount sourceAccount,
            EconomyAccount targetAccount
    );

}
