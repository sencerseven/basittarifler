package com.sencerseven.basittarifler.command;

import java.io.Serializable;

public class TestCommand implements Serializable{

    private static final long serialVersionUID = 1L;

    private String text;

    public TestCommand() {
    }

    public TestCommand(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
