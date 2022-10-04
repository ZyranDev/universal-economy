package net.cosmogrp.economy.message;

public interface Sender {

    void sendMessage(String message);

    String getId();

    String getName();

}
