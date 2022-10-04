package net.cosmogrp.economy.message;

public interface Messenger {

    void sendMessage(
            Sender source,
            String path,
            Object... replacements
    );

}
