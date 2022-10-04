package net.cosmogrp.economy;

import net.cosmogrp.economy.account.EconomyAccount;
import net.cosmogrp.economy.context.TransactionContext;
import net.cosmogrp.economy.message.Messenger;
import net.cosmogrp.economy.message.Sender;
import net.cosmogrp.economy.transaction.TransactionType;
import net.cosmogrp.economy.transaction.executor.TransactionExecutor;

public abstract class DefaultEconomyHandler
        extends AbstractEconomyHandler<TransactionContext> {

    public DefaultEconomyHandler(
            TransactionExecutor<TransactionContext> transactionExecutor,
            Messenger messenger,
            String identifier
    ) {
        super(transactionExecutor, messenger, identifier);
    }

    @Override
    protected TransactionContext createContext(
            Sender source, Sender target,
            double amount, TransactionType type,
            EconomyAccount sourceAccount,
            EconomyAccount targetAccount
    ) {
        return TransactionContext.builder()
                .setAmount(amount)
                .setType(type)
                .setParentPath(identifier)
                .setSource(source)
                .setTarget(target)
                .setSourceAccount(sourceAccount)
                .setTargetAccount(targetAccount)
                .build();
    }
}
