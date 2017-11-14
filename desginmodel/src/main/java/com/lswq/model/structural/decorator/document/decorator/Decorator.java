package com.lswq.model.structural.decorator.document.decorator;

import com.lswq.model.structural.decorator.document.component.Document;

public class Decorator implements Document {
    public Document document;

    public Decorator(Document document) {
        this.document = document;
    }

    @Override
    public void display() {
        this.document.display();
    }
}
