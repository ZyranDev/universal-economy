package net.cosmogrp.economy.context;

import net.cosmogrp.economy.account.EconomyAccount;
import net.cosmogrp.economy.message.Sender;
import net.cosmogrp.economy.transaction.TransactionType;

public interface TransactionContextBuilder {

    TransactionContextBuilder setSource(Sender source);

    TransactionContextBuilder setTarget(Sender targetPlayer);

    TransactionContextBuilder setAmount(double amount);

    TransactionContextBuilder setType(TransactionType type);

    TransactionContextBuilder setSourceAccount(EconomyAccount sourceAccount);

    TransactionContextBuilder setTargetAccount(EconomyAccount targetAccount);

    TransactionContextBuilder setParentPath(String path);

    TransactionContext build();

}
