package net.cosmogrp.economy.context;

import net.cosmogrp.economy.account.EconomyAccount;
import net.cosmogrp.economy.message.Sender;
import net.cosmogrp.economy.transaction.TransactionType;

public abstract class LayoutContextBuilder<T extends TransactionContextBuilder>
        implements TransactionContextBuilder {

    protected Sender source;
    protected Sender target;
    protected double amount;
    protected TransactionType type;
    protected EconomyAccount sourceAccount;
    protected EconomyAccount targetAccount;
    protected String parentPath;

    protected LayoutContextBuilder() {
    }

    @Override
    public T setSource(Sender sourcePlayer) {
        this.source = sourcePlayer;
        return back();
    }

    @Override
    public T setTarget(Sender targetPlayer) {
        this.target = targetPlayer;
        return back();
    }

    @Override
    public T setAmount(double amount) {
        this.amount = amount;
        return back();
    }

    @Override
    public T setType(TransactionType type) {
        this.type = type;
        return back();
    }

    @Override
    public T setSourceAccount(EconomyAccount sourceAccount) {
        this.sourceAccount = sourceAccount;
        return back();
    }

    @Override
    public T setTargetAccount(EconomyAccount targetAccount) {
        this.targetAccount = targetAccount;
        return back();
    }

    @Override
    public T setParentPath(String parentPath) {
        this.parentPath = parentPath;
        return back();
    }

    protected abstract T back();

}
